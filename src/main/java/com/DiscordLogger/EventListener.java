package com.DiscordLogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
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
            plugin.logToDiscord("Player Join", event.getPlayer().getName() + " joined the server.");
            plugin.getLogger().info(event.getPlayer().getName() + " joined the server.");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (plugin.getConfig().getBoolean("log-player-quit", true)) {
            plugin.logToDiscord("Player Quit", event.getPlayer().getName() + " left the server.");
            plugin.getLogger().info(event.getPlayer().getName() + " left the server.");
        }
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        if (plugin.getConfig().getBoolean("log-server-command", true)) {
            String sender = event.getSender().getName();
            plugin.logToDiscord("Server Command", "Command executed: /" + event.getCommand() + " by " + sender);
            plugin.getLogger().info("Command executed: /" + event.getCommand() + " by " + sender);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (plugin.getConfig().getBoolean("log-block-break", false)) {
            plugin.logToDiscord("Block Break", event.getPlayer().getName() + " broke a block at " + event.getBlock().getLocation());
            plugin.getLogger().info(event.getPlayer().getName() + " broke a block at " + event.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (plugin.getConfig().getBoolean("log-block-place", false)) {
            plugin.logToDiscord("Block Place", event.getPlayer().getName() + " placed a block at " + event.getBlock().getLocation());
            plugin.getLogger().info(event.getPlayer().getName() + " placed a block at " + event.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (plugin.getConfig().getBoolean("log-player-death", false)) {
            plugin.logToDiscord("Player Death", event.getDeathMessage());
            plugin.getLogger().info(event.getDeathMessage());
        }
    }
}
