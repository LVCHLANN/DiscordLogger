package com.DiscordLogger;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class EventListener implements Listener {

    private final Main plugin;

    public EventListener(Main plugin) {
        this.plugin = plugin;
    }

    // Log player join event
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("log-player-join", true)) {
            logToDiscord("Player Join", event.getPlayer().getName() + " joined the server.");
        }
    }

    // Log player quit event
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (plugin.getConfig().getBoolean("log-player-quit", true)) {
            logToDiscord("Player Quit", event.getPlayer().getName() + " left the server.");
        }
    }

    // Log player chat event
    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        if (plugin.getConfig().getBoolean("log-player-chat", true)) {
            logToDiscord("Player Chat", event.getPlayer().getName() + ": " + event.getMessage());
        }
    }

    // Log server command event
    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        if (plugin.getConfig().getBoolean("log-server-command", true)) {
            logToDiscord("Server Command", "Command executed: /" + event.getCommand());
        }
    }

    // Log block break event
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (plugin.getConfig().getBoolean("log-block-break", false)) {
            logToDiscord("Block Break", event.getPlayer().getName() + " broke a block at " + event.getBlock().getLocation());
        }
    }

    // Log block place event
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (plugin.getConfig().getBoolean("log-block-place", false)) {
            logToDiscord("Block Place", event.getPlayer().getName() + " placed a block at " + event.getBlock().getLocation());
        }
    }

    // Log player death event
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (plugin.getConfig().getBoolean("log-player-death", false)) {
            logToDiscord("Player Death", event.getDeathMessage());
        }
    }

    // General method to log messages to Discord via webhook (asynchronously)
    private void logToDiscord(String eventType, String message) {
        if (plugin.getDiscordWebhookUrl() == null || plugin.getDiscordWebhookUrl().isEmpty()) {
            plugin.getLogger().warning("Discord webhook URL is not set. Cannot log to Discord.");
            return;
        }

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                JSONObject json = new JSONObject();
                json.put("content", "**" + eventType + "**: " + message);

                HttpURLConnection connection = (HttpURLConnection) new URL(plugin.getDiscordWebhookUrl()).openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    plugin.getLogger().warning("Failed to send message to Discord, response code: " + responseCode);
                }

            } catch (IOException e) {
                plugin.getLogger().severe("Error sending log to Discord: " + e.getMessage());
            }
        });
    }
}