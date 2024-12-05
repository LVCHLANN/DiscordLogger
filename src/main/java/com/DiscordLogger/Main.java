package com.DiscordLogger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main extends JavaPlugin {

    private String discordWebhookUrl;
    private String serverName;

    @Override
    public void onEnable() {
        try {
            saveDefaultConfig();
            discordWebhookUrl = getConfig().getString("discord-webhook-url");
            serverName = getConfig().getString("server-name", ""); // Default to empty string if not provided

            if (discordWebhookUrl == null || discordWebhookUrl.equals("INSERT-WEBHOOK-URL")) {
                getLogger().warning("Discord webhook URL is not set in the config file! Please update config.yml.");
            }

            // Register events and command
            Bukkit.getPluginManager().registerEvents(new EventListener(this), this);
            if (getCommand("discordlogger") != null) {
                getCommand("discordlogger").setExecutor(new ReloadCommand(this));
            } else {
                getLogger().severe("Command 'discordlogger' is missing from plugin.yml!");
            }

            getLogger().info("DiscordLogger plugin enabled!");

        } catch (Exception e) {
            getLogger().severe("Error enabling DiscordLogger plugin: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("DiscordLogger plugin disabled.");
    }

    public void logToDiscord(String eventType, String message) {
        if (discordWebhookUrl == null || discordWebhookUrl.isEmpty()) {
            getLogger().warning("Discord webhook URL is not set. Cannot log to Discord.");
            return;
        }

        // Include server name in the log if specified
        String formattedMessage = serverName.isEmpty()
                ? message
                : String.format("[%s] %s", serverName, message);

        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(discordWebhookUrl).openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                String jsonMessage = String.format("{\"content\":\"**%s**: %s\"}", eventType, formattedMessage);
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonMessage.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();
                if (responseCode != 204) { // 204 is OK for Discord webhooks
                    getLogger().warning("Failed to send message to Discord, response code: " + responseCode);
                }

            } catch (Exception e) {
                getLogger().severe("Error sending log to Discord: " + e.getMessage());
            }
        });
    }

    public String getDiscordWebhookUrl() {
        return discordWebhookUrl;
    }

    public void setDiscordWebhookUrl(String discordWebhookUrl) {
        this.discordWebhookUrl = discordWebhookUrl;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
