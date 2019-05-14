package org.waqe.pl.configfiles;

import org.bukkit.plugin.java.JavaPlugin;

public final class ConfigFiles extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        getServer().getPluginManager().registerEvents(new MyEvents(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
