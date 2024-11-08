package com.DiscordLogger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ReloadCommand {

    private final JavaPlugin plugin;

    public ReloadCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (command.getName().equalsIgnoreCase("discordlogger") && args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("discordlogger.reload")) {
                    plugin.reloadConfig();
                    String webhookUrl = plugin.getConfig().getString("discord-webhook-url");
                    sender.sendMessage("[DiscordLogger] Config reloaded successfully!");
                    plugin.getLogger().info("Config reloaded. New webhook URL: " + webhookUrl);
                    return true;
                } else {
                    sender.sendMessage("[DiscordLogger] You do not have permission to reload the config.");
                    return false;
                }
            }
        } catch (Exception e) {
            plugin.getLogger().severe("Error executing reload command: " + e.getMessage());
            e.printStackTrace();
            sender.sendMessage("[DiscordLogger] An error occurred while executing the command.");
        }
        return false;
    }
}