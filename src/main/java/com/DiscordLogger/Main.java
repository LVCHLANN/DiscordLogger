package com.DiscordLogger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public String discordWebhookUrl;
    private Logger logger;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        discordWebhookUrl = getConfig().getString("discord-webhook-url");

        if (discordWebhookUrl == null || discordWebhookUrl.equals("INSERT-WEBHOOK-URL")) {
            getLogger().warning("Discord webhook URL is not set in the config file! Please update config.yml.");
        }

        logger = getLogger();
        Bukkit.getPluginManager().registerEvents(new EventListener(this, logger), this);

        if (getCommand("discordlogger") != null) {
            getCommand("discordlogger").setExecutor(new ReloadCommand(this));
        } else {
            getLogger().severe("Command 'discordlogger' is missing from plugin.yml!");
        }

        getLogger().info("DiscordLogger plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("DiscordLogger plugin disabled.");
    }

    // Changed from private to public
    public void setDiscordWebhookUrl(String url) {
        this.discordWebhookUrl = url;
    }

    public void logToDiscord(String eventType, String message) {
        if (discordWebhookUrl == null || discordWebhookUrl.isEmpty()) {
            getLogger().warning("Discord webhook URL is not set. Cannot log to Discord.");
            return;
        }

        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(discordWebhookUrl).openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Format the timestamp in Markdown with backticks
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                String formattedTimestamp = "`" + timestamp + "`"; // Wrap timestamp with backticks

                // Format the log message in Markdown
                String jsonMessage = String.format("{\"content\":\"%s **%s** %s\"}", formattedTimestamp, eventType, message);

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
}
