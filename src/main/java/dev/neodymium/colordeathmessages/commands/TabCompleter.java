package dev.neodymium.colordeathmessages.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length < 1) {
            return new ArrayList<>();
        }
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            completions.add("reload");
            completions.add("color");
            completions.add("displayname");
            return completions;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("color")) {
            String[] colors = {"black", "dark_blue", "dark_green", "dark_aqua", "dark_red", "dark_purple", "gold", "gray", "dark_gray", "blue", "green", "aqua", "red", "light_purple", "yellow", "white"};
            List<String> completions = new ArrayList<>();
            Collections.addAll(completions, colors);
            return completions;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("displayname")) {
            List<String> completions = new ArrayList<>();
            completions.add("true");
            completions.add("false");
            return completions;
        }
        return new ArrayList<>();
    }
}
