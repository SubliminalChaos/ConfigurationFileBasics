package org.waqe.pl.configfiles;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class MyEvents implements Listener {
    private ConfigFiles plugin;

    public MyEvents(ConfigFiles configFiles) {
        plugin = configFiles;
    }

    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Player player = e.getPlayer();

        boolean enabled = plugin.getConfig().getBoolean("enabled");
        int incidents = plugin.getConfig().getInt("incidents." + player.getUniqueId().toString());

        List<String> wordlist = plugin.getConfig().getStringList("banned-words");
        if (enabled) {
            for (String bannedword : wordlist) {
                if (message.contains(bannedword)) {
                    e.setCancelled(true);
                    player.sendMessage("You cannot say that!");

                    if (incidents != 0) {  // player is in config
                        incidents++;
                        plugin.getConfig().set("incidents." + player.getUniqueId().toString(),incidents);
                        plugin.saveConfig();
                    } else {              // player not in config
                        plugin.getConfig().set("incidents." + player.getUniqueId().toString(),1 );
                        plugin.saveConfig();
                    }
                }
            }
        }
    }
}
