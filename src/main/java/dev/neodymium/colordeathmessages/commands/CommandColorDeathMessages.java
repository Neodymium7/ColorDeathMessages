package dev.neodymium.colordeathmessages.commands;

import dev.neodymium.colordeathmessages.ColorDeathMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandColorDeathMessages implements CommandExecutor {
    private final ColorDeathMessages plugin;

    public CommandColorDeathMessages(ColorDeathMessages instance) {
        plugin = instance;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                sender.sendMessage("Reloaded ColorDeathMessages");
            }
            if (args[0].equalsIgnoreCase("color")) {
                if (args.length > 1 && ChatColor.getByChar(args[1]) != null) {
                    plugin.getConfig().set("color-code", args[1].charAt(0));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    sender.sendMessage("Set death message color to " + ChatColor.getByChar(args[1]) + args[1].charAt(0));
                }
                else  {
                    sender.sendMessage("You must provide a valid color code");
                }
            }
            if (args[0].equalsIgnoreCase("displayname")) {
                if (args.length > 1 && (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false"))) {
                    plugin.getConfig().set("use-display-name", Boolean.parseBoolean(args[1]));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    sender.sendMessage((Boolean.parseBoolean(args[1]) ? "Enabled" : "Disabled") + " display names in death messages");
                }
                else {
                    sender.sendMessage("Provided value must be 'true' or 'false'");
                }
            }
            return true;
        }
        return false;
    }
}
