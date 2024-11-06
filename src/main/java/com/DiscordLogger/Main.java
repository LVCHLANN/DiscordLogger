package com.DiscordLogger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Main extends JavaPlugin implements Listener {

    private String discordWebhookUrl;

    @Override
    public void onEnable() {
        // Create the "DiscordLogger" folder if it doesn't exist
        File pluginFolder = new File(getDataFolder(), "DiscordLogger");
        if (!pluginFolder.exists()) {
            if (pluginFolder.mkdir()) {
                getLogger().info("Created folder: DiscordLogger");
            } else {
                getLogger().warning("Failed to create folder: DiscordLogger. Please check permissions.");
            }
        }

        // Create the config file if it doesn't exist
        File configFile = new File(pluginFolder, "config.yml");
        if (!configFile.exists()) {
            try {
                saveResource("config.yml", false);
                getLogger().info("Created config.yml with placeholder webhook URL.");
            } catch (Exception e) {
                getLogger().warning("Could not create config.yml: " + e.getMessage());
            }
        }

        // Load the configuration
        saveDefaultConfig();
        discordWebhookUrl = getConfig().getString("discord-webhook-url");

        if (discordWebhookUrl == null || discordWebhookUrl.equals("INSERT-WEBHOOK-URL")) {
            getLogger().warning("Discord webhook URL is not set in the config file! Please update config.yml.");
        }

        // Register events
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("DiscordLogger plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("DiscordLogger plugin disabled.");
    }

    // Command to reload the config
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("discordlogger") && args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("discordlogger.reload")) {
                reloadConfig();
                discordWebhookUrl = getConfig().getString("discord-webhook-url");
                sender.sendMessage("[DiscordLogger] Config reloaded successfully!");
                return true;
            } else {
                sender.sendMessage("[DiscordLogger] You do not have permission to reload the config.");
                return false;
            }
        }
        return false;
    }

    // Log player join event
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (getConfig().getBoolean("log-player-join")) {
            logToDiscord("Player Join", event.getPlayer().getName() + " joined the server.");
        }
    }

    // Log player quit event
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (getConfig().getBoolean("log-player-quit")) {
            logToDiscord("Player Quit", event.getPlayer().getName() + " left the server.");
        }
    }

    // Log server commands
    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        if (getConfig().getBoolean("log-server-commands")) {
            logToDiscord("Server Command", "Command executed: /" + event.getCommand());
        }
    }

    // General method to log messages to Discord via webhook (asynchronously)
    private void logToDiscord(String eventType, String message) {
        Bukkit.getScheduler().runTask(this, () -> {
            try {
                JSONObject json = new JSONObject();
                json.put("content", "**" + eventType + "**: " + message);

                // Send the log to Discord webhook
                HttpURLConnection connection = (HttpURLConnection) new URL(discordWebhookUrl).openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Send the JSON payload as the POST body
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);  // Use UTF-8 charset
                    os.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    getLogger().warning("Failed to send message to Discord, response code: " + responseCode);
                }

            } catch (IOException e) {
                getLogger().severe("Error sending log to Discord: " + e.getMessage());
            }
        });
    }
}
