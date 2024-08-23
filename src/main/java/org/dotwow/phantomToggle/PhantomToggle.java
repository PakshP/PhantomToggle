package org.dotwow.phantomToggle;

import org.bukkit.plugin.java.JavaPlugin;

public class PhantomToggle extends JavaPlugin {

    private PhantomManager phantomManager;

    @Override
    public void onEnable() {
        // Initialize the PhantomManager
        this.phantomManager = new PhantomManager(this);

        // Register the command and event listener
        this.getCommand("togglephantoms").setExecutor(new PhantomToggleCommand(phantomManager));
        getServer().getPluginManager().registerEvents(new PhantomSpawnListener(phantomManager), this);
    }

    @Override
    public void onDisable() {
        // Save player data when the plugin is disabled
        phantomManager.savePlayerData();
    }

    public PhantomManager getPhantomManager() {
        return phantomManager;
    }
}
