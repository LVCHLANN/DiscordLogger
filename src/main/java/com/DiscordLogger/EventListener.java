package com.DiscordLogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
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
            String logMessage = plugin.getServerName().isEmpty()
                    ? event.getPlayer().getName() + " joined the server."
                    : "[" + plugin.getServerName() + "] " + event.getPlayer().getName() + " joined the server.";

            plugin.logToDiscord("Player Join", logMessage);
            plugin.getLogger().info(logMessage);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (plugin.getConfig().getBoolean("log-player-quit", true)) {
            String logMessage = plugin.getServerName().isEmpty()
                    ? event.getPlayer().getName() + " left the server."
                    : "[" + plugin.getServerName() + "] " + event.getPlayer().getName() + " left the server.";

            plugin.logToDiscord("Player Quit", logMessage);
            plugin.getLogger().info(logMessage);
        }
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        if (plugin.getConfig().getBoolean("log-server-command", true)) {
            String sender = event.getSender().getName();
            String logMessage = plugin.getServerName().isEmpty()
                    ? "Command executed: /" + event.getCommand() + " by " + sender
                    : "[" + plugin.getServerName() + "] Command executed: /" + event.getCommand() + " by " + sender;

            plugin.logToDiscord("Server Command", logMessage);
            plugin.getLogger().info(logMessage);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (plugin.getConfig().getBoolean("log-block-break", false)) {
            String logMessage = plugin.getServerName().isEmpty()
                    ? event.getPlayer().getName() + " broke a block at " + event.getBlock().getLocation()
                    : "[" + plugin.getServerName() + "] " + event.getPlayer().getName() + " broke a block at " + event.getBlock().getLocation();

            plugin.logToDiscord("Block Break", logMessage);
            plugin.getLogger().info(logMessage);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (plugin.getConfig().getBoolean("log-block-place", false)) {
            String logMessage = plugin.getServerName().isEmpty()
                    ? event.getPlayer().getName() + " placed a block at " + event.getBlock().getLocation()
                    : "[" + plugin.getServerName() + "] " + event.getPlayer().getName() + " placed a block at " + event.getBlock().getLocation();

            plugin.logToDiscord("Block Place", logMessage);
            plugin.getLogger().info(logMessage);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (plugin.getConfig().getBoolean("log-player-death", false)) {
            String logMessage = plugin.getServerName().isEmpty()
                    ? event.getDeathMessage()
                    : "[" + plugin.getServerName() + "] " + event.getDeathMessage();

            plugin.logToDiscord("Player Death", logMessage);
            plugin.getLogger().info(logMessage);
        }
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        if (plugin.getConfig().getBoolean("log-player-chat", true)) {
            String serverNamePrefix = "[" + plugin.getServerName() + "] ";
            plugin.logToDiscord("Player Chat", serverNamePrefix + event.getPlayer().getName() + ": " + event.getMessage());
            plugin.getLogger().info(serverNamePrefix + event.getPlayer().getName() + ": " + event.getMessage());
        }
    }

}
