package com.DiscordLogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class EventListener implements Listener {

    private final Main plugin;

    public EventListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("log-player-join", true)) {
            String logMessage = event.getPlayer().getName() + " joined the server.";
            plugin.logToDiscord("Player Join", logMessage);
            plugin.getLogger().info(logMessage);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (plugin.getConfig().getBoolean("log-player-quit", true)) {
            String logMessage = event.getPlayer().getName() + " left the server.";
            plugin.logToDiscord("Player Quit", logMessage);
            plugin.getLogger().info(logMessage);
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (plugin.getConfig().getBoolean("log-player-chat", true)) {
            String logMessage = event.getPlayer().getName() + ": " + event.getMessage();
            plugin.logToDiscord("Chat", logMessage);
            plugin.getLogger().info(logMessage);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (plugin.getConfig().getBoolean("log-player-death", true)) {
            String logMessage = event.getDeathMessage();
            plugin.logToDiscord("Death", logMessage);
            plugin.getLogger().info(logMessage);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (plugin.getConfig().getBoolean("log-block-break", false)) {
            String logMessage = event.getPlayer().getName() + " broke " +
                    event.getBlock().getType() + " at " + event.getBlock().getLocation();
            plugin.logToDiscord("Block Break", logMessage);
            plugin.getLogger().info(logMessage);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (plugin.getConfig().getBoolean("log-block-place", false)) {
            String logMessage = event.getPlayer().getName() + " placed " +
                    event.getBlock().getType() + " at " + event.getBlock().getLocation();
            plugin.logToDiscord("Block Place", logMessage);
            plugin.getLogger().info(logMessage);
        }
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        if (plugin.getConfig().getBoolean("log-server-command", false)) {
            String logMessage = "Console ran command: /" + event.getCommand();
            plugin.logToDiscord("Server Command", logMessage);
            plugin.getLogger().info(logMessage);
        }
    }
}
