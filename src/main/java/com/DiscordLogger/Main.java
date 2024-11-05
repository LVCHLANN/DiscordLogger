package com.DiscordLogger;

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
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends JavaPlugin implements Listener {

    private String discordWebhookUrl;

    @Override
    public void onEnable() {
        loadConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("DiscordLogger plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("DiscordLogger plugin disabled.");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        logToDiscord("Player Join", event.getPlayer().getName() + " has joined the server.");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        logToDiscord("Player Quit", event.getPlayer().getName() + " has left the server.");
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        logToDiscord("Server Command", "Command executed: /" + event.getCommand());
    }

    private void loadConfig() {
        File discordLoggerFolder = new File(getDataFolder().getParent(), "DiscordLogger");

        if (!discordLoggerFolder.exists()) {
            if (discordLoggerFolder.mkdir()) {
                getLogger().info("DiscordLogger folder created successfully.");
            } else {
                getLogger().severe("Failed to create DiscordLogger folder. Please check permissions.");
            }
        } else {
            getLogger().info("DiscordLogger folder already exists.");
        }

        File configFile = new File(discordLoggerFolder, "config.yml");
        if (!configFile.exists()) {
            try (InputStream in = getResource("config.yml");
                 FileOutputStream out = new FileOutputStream(configFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                getLogger().info("Config file 'config.yml' created successfully.");
            } catch (IOException e) {
                getLogger().severe("Could not create config.yml: " + e.getMessage());
            }
        } else {
            getLogger().info("Config file 'config.yml' already exists.");
        }

        saveDefaultConfig();
        discordWebhookUrl = getConfig().getString("discord-webhook-url");

        if (discordWebhookUrl == null || discordWebhookUrl.equals("INSERT-WEBHOOK-URL")) {
            getLogger().warning("Discord webhook URL is not set in the config file! Please update config.yml.");
        }
    }

    private void logToDiscord(String eventType, String message) {
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                JSONObject json = new JSONObject();
                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                String formattedMessage = String.format("`[%s]` **%s** %s", timestamp, eventType, message);
                json.put("content", formattedMessage);

                HttpURLConnection connection = (HttpURLConnection) new URL(discordWebhookUrl).openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                    getLogger().info("Message sent successfully to Discord.");
                } else {
                    getLogger().warning("Failed to send message to Discord, response code: " + responseCode);
                }

            } catch (IOException e) {
                getLogger().severe("Error sending log to Discord: " + e.getMessage());
            }
        });
    }
}
