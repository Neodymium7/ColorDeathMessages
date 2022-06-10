package dev.neodymium.colordeathmessages.commands;

import dev.neodymium.colordeathmessages.ColorDeathMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
                sender.sendMessage(ChatColor.GRAY + "Reloaded ColorDeathMessages");
            }
            if (args[0].equalsIgnoreCase("color")) {
                String[] colors = {"black", "dark_blue", "dark_green", "dark_aqua", "dark_red", "dark_purple", "gold", "gray", "dark_gray", "blue", "green", "aqua", "red", "light_purple", "yellow", "white"};
                String[] codes = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

                if (args.length > 1 && Arrays.asList(colors).contains(args[1].toLowerCase())) {
                    Map<String, String> map = new HashMap<>();
                    for (int i = 0; i < colors.length; i++) {
                        map.put(colors[i], codes[i]);
                    }
                    String code = map.get(args[1].toLowerCase());
                    plugin.getConfig().set("color-code", code);
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    sender.sendMessage(ChatColor.GRAY + "Set death message color to " + ChatColor.getByChar(code) + args[1].toLowerCase());
                }
                else if (args.length > 1 && Arrays.asList(codes).contains(args[1].toLowerCase().substring(0, 1)) && ChatColor.getByChar(args[1].toLowerCase().charAt(0)) != null) {
                    plugin.getConfig().set("color-code", args[1].toLowerCase().charAt(0));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    sender.sendMessage(ChatColor.GRAY + "Set death message color to " + ChatColor.getByChar(args[1].toLowerCase().charAt(0)) + args[1].toLowerCase().charAt(0));
                }
                else  {
                    sender.sendMessage(ChatColor.GRAY + "You must provide a valid color name or color code");
                }
            }
            if (args[0].equalsIgnoreCase("displayname")) {
                if (args.length > 1 && (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false"))) {
                    plugin.getConfig().set("use-display-name", Boolean.parseBoolean(args[1]));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    sender.sendMessage(ChatColor.GRAY + (Boolean.parseBoolean(args[1]) ? "Enabled" : "Disabled") + " display names in death messages");
                }
                else {
                    sender.sendMessage(ChatColor.GRAY + "Provided value must be 'true' or 'false'");
                }
            }
            return true;
        }
        return false;
    }
}
