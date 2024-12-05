package com.DiscordLogger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final Main plugin;

    public ReloadCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("discordlogger.reload")) {
                plugin.reloadConfig();
                plugin.setDiscordWebhookUrl(plugin.getConfig().getString("discord-webhook-url"));
                plugin.setServerName(plugin.getConfig().getString("server-name", "")); // Update serverName
                sender.sendMessage("[DiscordLogger] Config reloaded successfully!");
                return true;
            } else {
                sender.sendMessage("[DiscordLogger] You do not have permission to reload the config.");
                return false;
            }
        }
        sender.sendMessage("[DiscordLogger] Usage: /discordlogger reload");
        return false;
    }
}
