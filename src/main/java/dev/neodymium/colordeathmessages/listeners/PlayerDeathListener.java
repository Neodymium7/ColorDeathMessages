package dev.neodymium.colordeathmessages.listeners;

import dev.neodymium.colordeathmessages.ColorDeathMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    private final ColorDeathMessages plugin;
    public PlayerDeathListener(ColorDeathMessages instance) {
        plugin = instance;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        FileConfiguration config = plugin.getConfig();
        ChatColor color = ChatColor.getByChar(config.getString("color-code")) != null ? ChatColor.getByChar(config.getString("color-code")) : ChatColor.WHITE;
        Player player = event.getEntity();
        Player killer = player.getKiller();
        String deathMessage = event.getDeathMessage();

        if (config.getBoolean("use-display-name") && deathMessage != null) {
            String[] words = deathMessage.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(player.getName())) {
                    words[i] = player.getDisplayName() + color;
                }
                else if (killer != null && words[i].equals(killer.getName())) {
                    words[i] = killer.getDisplayName() + color;
                }
            }
            deathMessage = String.join(" ", words);
        }
        event.setDeathMessage(color + deathMessage);
    }
}
