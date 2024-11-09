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

import java.util.logging.Logger;

public class EventListener implements Listener {

    private final Main plugin;
    private final Logger logger;

    public EventListener(Main plugin, Logger logger) {
        this.plugin = plugin;
        this.logger = logger;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("log-player-join", true)) {
            plugin.logToDiscord("Player Join", event.getPlayer().getName() + " joined the server.");
            logger.info(event.getPlayer().getName() + " joined the server.");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (plugin.getConfig().getBoolean("log-player-quit", true)) {
            plugin.logToDiscord("Player Quit", event.getPlayer().getName() + " left the server.");
            logger.info(event.getPlayer().getName() + " left the server.");
        }
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        if (plugin.getConfig().getBoolean("log-player-chat", true)) {
            plugin.logToDiscord("Player Chat", event.getPlayer().getName() + ": " + event.getMessage());
            logger.info(event.getPlayer().getName() + ": " + event.getMessage());
        }
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        if (plugin.getConfig().getBoolean("log-server-command", true)) {
            plugin.logToDiscord("Server Command", "Command executed: /" + event.getCommand());
            logger.info("Command executed: /" + event.getCommand());
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (plugin.getConfig().getBoolean("log-block-break", false)) {
            plugin.logToDiscord("Block Break", event.getPlayer().getName() + " broke a block at " + event.getBlock().getLocation());
            logger.info(event.getPlayer().getName() + " broke a block at " + event.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (plugin.getConfig().getBoolean("log-block-place", false)) {
            plugin.logToDiscord("Block Place", event.getPlayer().getName() + " placed a block at " + event.getBlock().getLocation());
            logger.info(event.getPlayer().getName() + " placed a block at " + event.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (plugin.getConfig().getBoolean("log-player-death", false)) {
            plugin.logToDiscord("Player Death", event.getDeathMessage());
            logger.info(event.getDeathMessage());
        }
    }
}
