package dev.neodymium.colordeathmessages;

import dev.neodymium.colordeathmessages.commands.CommandColorDeathMessages;
import dev.neodymium.colordeathmessages.commands.TabCompleter;
import dev.neodymium.colordeathmessages.listeners.PlayerDeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ColorDeathMessages extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        CommandColorDeathMessages executor = new CommandColorDeathMessages(this);
        getCommand("colordeathmessages").setExecutor(executor);
        getCommand("colordeathmessages").setTabCompleter(new TabCompleter());
        new PlayerDeathListener(this);
    }

    @Override
    public void onDisable() {

    }
}
