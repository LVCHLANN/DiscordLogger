package com.DiscordLogger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Main extends JavaPlugin {

    private String discordWebhookUrl;
    private String serverName;
    private Map<String, String> advancementMap;

    @Override
    public void onEnable() {
        try {
            saveDefaultConfig();
            discordWebhookUrl = getConfig().getString("discord-webhook-url");
            serverName = getConfig().getString("server-name", "");

            if (discordWebhookUrl == null || discordWebhookUrl.equals("INSERT-WEBHOOK-URL")) {
                getLogger().warning("Discord webhook URL is not set in the config file! Please update config.yml.");
            }

            // Load advancements.json
            saveResource("advancements.json", false); // If not already there
            loadAdvancementMap();

            // Register listeners and command
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
                if (responseCode != 204) {
                    getLogger().warning("Failed to send message to Discord, response code: " + responseCode);
                }

            } catch (Exception e) {
                getLogger().severe("Error sending log to Discord: " + e.getMessage());
            }
        });
    }

    private void loadAdvancementMap() {
        try (InputStreamReader reader = new InputStreamReader(getResource("advancements.json"), StandardCharsets.UTF_8)) {
            advancementMap = new Gson().fromJson(reader, new TypeToken<Map<String, String>>() {}.getType());
            getLogger().info("Loaded " + advancementMap.size() + " advancement mappings.");
        } catch (Exception e) {
            getLogger().warning("Failed to load advancements.json: " + e.getMessage());
        }
    }

    public String getLocalizedAdvancement(String key) {
        return advancementMap != null ? advancementMap.getOrDefault(key, key) : key;
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
