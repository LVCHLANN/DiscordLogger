package com.DiscordLogger;  // Package name is com.DiscordLogger

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main extends JavaPlugin implements Listener {  // The class name is DiscordLogger

    private String discordWebhookUrl;

    @Override
    public void onEnable() {
        // Create the "DiscordLogger" folder if it doesn't exist
        File pluginFolder = new File(getDataFolder(), "DiscordLogger");
        if (!pluginFolder.exists()) {
            pluginFolder.mkdir();  // Create the folder
            getLogger().info("Created folder: DiscordLogger");

            // Now create the config.yml file with the placeholder URL
            File configFile = new File(pluginFolder, "config.yml");
            if (!configFile.exists()) {
                try (InputStream in = getResource("config.yml");
                     FileOutputStream out = new FileOutputStream(configFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }
                    getLogger().info("Created config.yml with placeholder webhook URL.");
                } catch (IOException e) {
                    getLogger().severe("Could not create config.yml: " + e.getMessage());
                }
            }
        }

        // Load the configuration
        saveDefaultConfig();  // Ensure the config.yml is created if it wasn't manually done
        discordWebhookUrl = getConfig().getString("discord-webhook-url");

        if (discordWebhookUrl == null || discordWebhookUrl.equals("INSERT-WEBHOOK-URL")) {
            getLogger().warning("Discord webhook URL is not set in the config file! Please update config.yml.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Register events
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("DiscordLogger plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("DiscordLogger plugin disabled.");
    }

    // Log player join event
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        logToDiscord("Player Join", event.getPlayer().getName() + " joined the server.");
    }

    // Log player quit event
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        logToDiscord("Player Quit", event.getPlayer().getName() + " left the server.");
    }

    // Log server commands
    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        logToDiscord("Server Command", "Command executed: /" + event.getCommand());
    }

    // General method to log messages to Discord via webhook (asynchronously)
    private void logToDiscord(String eventType, String message) {
        // Use runAsync to execute this on a separate thread
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

                int responseCode = connection.getResponseCode(); // Optional, you can log this to check if the request was successful
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    getLogger().warning("Failed to send message to Discord, response code: " + responseCode);
                }

            } catch (IOException e) {
                getLogger().severe("Error sending log to Discord: " + e.getMessage());
            }
        });
    }
}
