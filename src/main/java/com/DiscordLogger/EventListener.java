package com.DiscordLogger;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class EventListener implements Listener {

    private final Main plugin;
    private final Map<UUID, String> recentKickers = new HashMap<>();
    private final Map<UUID, List<String>> commandOutputs = new HashMap<>();

    // Advancement localization map loaded from JSON inside plugin resources
    private final Map<String, String> advancementNames;

    public EventListener(Main plugin) {
        this.plugin = plugin;
        this.advancementNames = plugin.loadAdvancementNames();
    }

    private boolean cfg(String path) {
        return plugin.getConfig().getBoolean(path);
    }

    private void log(String title, String msg) {
        plugin.logToDiscord(title, msg);
        plugin.getLogger().info(msg);
    }

    // Helper to get localized advancement name
    private String getAdvancementName(String key) {
        return advancementNames.getOrDefault(key, key);
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
        log("Block Break", e.getPlayer().getName() + " broke " + e.getBlock().getType() + " at " + formatLocation(e.getBlock().getLocation()));
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (!cfg("log.block.place")) return;
        log("Block Place", e.getPlayer().getName() + " placed " + e.getBlock().getType() + " at " + formatLocation(e.getBlock().getLocation()));
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        if (!cfg("log.player.teleport")) return;
        log("Teleport", e.getPlayer().getName() + " teleported from " + formatLocation(e.getFrom()) + " to " + formatLocation(e.getTo()));
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
        log("Respawn", e.getPlayer().getName() + " respawned at " + formatLocation(e.getRespawnLocation()));
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
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent e) {
        if (!cfg("log.player.advancement")) return;

        Advancement adv = e.getAdvancement();
        String key = adv.getKey().toString(); // e.g. minecraft:story/mine_stone
        String readable = getAdvancementName(key);
        log("Advancement", e.getPlayer().getName() + " made advancement: " + readable);
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if (!cfg("log.staff.executed-command")) return;

        Player p = e.getPlayer();
        String msg = e.getMessage();

        // Parse known staff commands for extra logging (kick/ban/op/etc)
        String lower = msg.toLowerCase();

        if (lower.startsWith("/kick ")) {
            String target = lower.substring(6).split(" ")[0];
            recentKickers.put(Bukkit.getOfflinePlayer(target).getUniqueId(), p.getName());
            if (cfg("log.staff.kicked-player")) {
                log("Staff Action", p.getName() + " kicked " + target);
            }
        } else if (lower.startsWith("/ban ") && cfg("log.staff.banned-player")) {
            log("Staff Action", p.getName() + " banned " + msg.substring(5));
        } else if (lower.startsWith("/mute ") && cfg("log.staff.muted-player")) {
            log("Staff Action", p.getName() + " muted " + msg.substring(6));
        } else if (lower.startsWith("/unmute ") && cfg("log.staff.unmuted-player")) {
            log("Staff Action", p.getName() + " unmuted " + msg.substring(8));
        } else if (lower.startsWith("/warn ") && cfg("log.staff.warned-player")) {
            log("Staff Action", p.getName() + " warned " + msg.substring(6));
        } else if (lower.startsWith("/op ") && cfg("log.staff.opped-player")) {
            log("Staff Action", p.getName() + " gave OP to " + msg.substring(4));
        } else if (lower.startsWith("/deop ") && cfg("log.staff.deopped-player")) {
            log("Staff Action", p.getName() + " removed OP from " + msg.substring(6));
        } else if ((lower.startsWith("/tp ") || lower.startsWith("/teleport ")) && cfg("log.staff.teleport-other")) {
            log("Staff Action", p.getName() + " used teleport: " + msg);
        } else if (lower.startsWith("/clear ") && cfg("log.staff.cleared-inventory")) {
            log("Staff Action", p.getName() + " cleared inventory of " + msg.substring(7));
        } else if (lower.startsWith("/give ") && cfg("log.staff.given-items")) {
            log("Staff Action", p.getName() + " gave: " + msg.substring(6));
        } else if (lower.startsWith("/gamemode ") && cfg("log.staff.set-gamemode-other")) {
            log("Staff Action", p.getName() + " set gamemode: " + msg);
        }

        // Always log the command itself (catch-all)
        log("Command Executed", p.getName() + ": " + msg);

        // Schedule a task to capture console output after command execution
        // (Assuming plugin has a method to get last console output or similar, else skip)
        new BukkitRunnable() {
            @Override
            public void run() {
                List<String> outputs = plugin.getLastConsoleOutputForPlayer(p.getUniqueId());
                if (outputs != null && !outputs.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Console output after command:");
                    for (String line : outputs) {
                        sb.append("\n").append(line);
                    }
                    log("Command Output", sb.toString());
                }
            }
        }.runTaskLater(plugin, 2L); // Delay a few ticks to allow command to run
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
        if (!cfg("log.staff.kicked-player")) return;

        UUID uuid = e.getPlayer().getUniqueId();
        String kicker = recentKickers.getOrDefault(uuid, "Unknown");
        log("Kick", e.getPlayer().getName() + " was kicked by " + kicker + " for: " + e.getReason());

        recentKickers.remove(uuid);
    }

    private String formatLocation(org.bukkit.Location loc) {
        return String.format("world=%s, x=%.1f, y=%.1f, z=%.1f",
                loc.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ());
    }
}
