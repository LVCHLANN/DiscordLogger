package com.DiscordLogger;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.*;

public class EventListener implements Listener {

    private final Main plugin;
    private final Map<UUID, String> recentKickers = new HashMap<>();

    public EventListener(Main plugin) {
        this.plugin = plugin;
    }

    private boolean cfg(String path) {
        return plugin.getConfig().getBoolean(path);
    }

    private void log(String title, String msg) {
        plugin.logToDiscord(title, msg);
        plugin.getLogger().info(msg);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!cfg("log.player.join")) return;
        log("Player Join", e.getPlayer().getName() + " joined the server.");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if (!cfg("log.player.quit")) return;
        log("Player Quit", e.getPlayer().getName() + " left the server.");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (!cfg("log.player.chat")) return;
        log("Chat", e.getPlayer().getName() + ": " + e.getMessage());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (!cfg("log.player.death")) return;
        log("Death", e.getDeathMessage());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!cfg("log.block.break")) return;
        log("Block Break", e.getPlayer().getName() + " broke " + e.getBlock().getType() + " at " + e.getBlock().getLocation());
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (!cfg("log.block.place")) return;
        log("Block Place", e.getPlayer().getName() + " placed " + e.getBlock().getType() + " at " + e.getBlock().getLocation());
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        if (!cfg("log.player.teleport")) return;
        log("Teleport", e.getPlayer().getName() + " teleported from " + e.getFrom() + " to " + e.getTo());
    }

    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent e) {
        if (!cfg("log.player.xp-change")) return;
        log("XP Change", e.getPlayer().getName() + " gained " + e.getAmount() + " XP.");
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e) {
        if (!cfg("log.player.drop-item")) return;
        log("Drop Item", e.getPlayer().getName() + " dropped " + e.getItemDrop().getItemStack().getType());
    }

    @EventHandler
    public void onPlayerPickup(PlayerPickupItemEvent e) {
        if (!cfg("log.player.pickup-item")) return;
        log("Pickup Item", e.getPlayer().getName() + " picked up " + e.getItem().getItemStack().getType());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!cfg("log.player.interact")) return;
        String target = (e.getClickedBlock() != null) ? e.getClickedBlock().getType().toString() : "air";
        log("Interact", e.getPlayer().getName() + " interacted with " + target + " (" + e.getAction() + ")");
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        if (!cfg("log.player.respawn")) return;
        log("Respawn", e.getPlayer().getName() + " respawned at " + e.getRespawnLocation());
    }

    @EventHandler
    public void onGameModeChange(PlayerGameModeChangeEvent e) {
        if (!cfg("log.player.gamemode-change")) return;
        log("Gamemode Change", e.getPlayer().getName() + " changed gamemode to " + e.getNewGameMode());
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player) || !cfg("log.player.damage")) return;
        log("Damage", ((Player) e.getEntity()).getName() + " took " + e.getDamage() + " damage from " + e.getCause());
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (!cfg("log.inventory.open")) return;
        log("Inventory Open", e.getPlayer().getName() + " opened " + e.getInventory().getType());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!cfg("log.inventory.click")) return;
        log("Inventory Click", e.getWhoClicked().getName() + " clicked slot " + e.getSlot() + " in " + e.getInventory().getType());
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (!cfg("log.inventory.close")) return;
        log("Inventory Close", e.getPlayer().getName() + " closed " + e.getInventory().getType());
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        if (!cfg("log.inventory.drag")) return;
        log("Inventory Drag", e.getWhoClicked().getName() + " dragged items in " + e.getInventory().getType());
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent e) {
        if (!cfg("log.server.command")) return;
        log("Server Command", "Console ran: /" + e.getCommand());
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if (!cfg("log.staff.executed-command")) return;
        String msg = e.getMessage().toLowerCase();
        Player p = e.getPlayer();

        if (msg.startsWith("/kick ")) {
            String target = msg.substring(6).split(" ")[0];
            recentKickers.put(Bukkit.getOfflinePlayer(target).getUniqueId(), p.getName());
            if (cfg("log.staff.kicked-player")) {
                log("Staff Action", p.getName() + " kicked " + target);
            }
        } else if (msg.startsWith("/ban ") && cfg("log.staff.banned-player")) {
            log("Staff Action", p.getName() + " banned " + msg.substring(5));
        } else if (msg.startsWith("/mute ") && cfg("log.staff.muted-player")) {
            log("Staff Action", p.getName() + " muted " + msg.substring(6));
        } else if (msg.startsWith("/unmute ") && cfg("log.staff.unmuted-player")) {
            log("Staff Action", p.getName() + " unmuted " + msg.substring(8));
        } else if (msg.startsWith("/warn ") && cfg("log.staff.warned-player")) {
            log("Staff Action", p.getName() + " warned " + msg.substring(6));
        } else if (msg.startsWith("/op ") && cfg("log.staff.opped-player")) {
            log("Staff Action", p.getName() + " gave OP to " + msg.substring(4));
        } else if (msg.startsWith("/deop ") && cfg("log.staff.deopped-player")) {
            log("Staff Action", p.getName() + " removed OP from " + msg.substring(6));
        } else if ((msg.startsWith("/tp ") || msg.startsWith("/teleport ")) && cfg("log.staff.teleport-other")) {
            log("Staff Action", p.getName() + " used teleport: " + msg);
        } else if (msg.startsWith("/clear ") && cfg("log.staff.cleared-inventory")) {
            log("Staff Action", p.getName() + " cleared inventory of " + msg.substring(7));
        } else if (msg.startsWith("/give ") && cfg("log.staff.given-items")) {
            log("Staff Action", p.getName() + " gave: " + msg.substring(6));
        } else if (msg.startsWith("/gamemode ") && cfg("log.staff.set-gamemode-other")) {
            log("Staff Action", p.getName() + " set gamemode: " + msg);
        }
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
        if (!cfg("log.staff.kicked-player")) return;

        UUID uuid = e.getPlayer().getUniqueId();
        String kicker = recentKickers.getOrDefault(uuid, "Unknown");
        log("Kick", e.getPlayer().getName() + " was kicked by " + kicker + " for: " + e.getReason());

        recentKickers.remove(uuid);
    }
}
