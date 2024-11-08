package com.DiscordLogger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private String discordWebhookUrl;

    @Override
    public void onEnable() {
        try {
            // Ensure the config file is created and loaded
            File configFile = new File(getDataFolder(), "config.yml");
            if (!configFile.exists()) {
                saveDefaultConfig();
                getLogger().info("Created config.yml with placeholder webhook URL.");
            } else {
                reloadConfig();
            }

            // Load the Discord Webhook URL from the config
            discordWebhookUrl = getConfig().getString("discord-webhook-url");
            if (discordWebhookUrl == null || discordWebhookUrl.equals("INSERT-WEBHOOK-URL")) {
                getLogger().warning("Discord webhook URL is not set in the config file! Please update config.yml.");
            }

            // Register events
            Bukkit.getPluginManager().registerEvents(new EventListener(this), this);

            // Register reload command
            getCommand("discordlogger").setExecutor(new ReloadCommand(this));

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

    public String getDiscordWebhookUrl() {
        return discordWebhookUrl;
    }
}