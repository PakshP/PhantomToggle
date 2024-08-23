package org.dotwow.phantomToggle;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PhantomManager {

    private final PhantomToggle plugin;
    private Set<UUID> phantomDisabledPlayers;
    private File playerDataFile;
    private FileConfiguration playerData;

    public PhantomManager(PhantomToggle plugin) {
        this.plugin = plugin;
        loadPlayerData();
    }

    private void loadPlayerData() {
        playerDataFile = new File(plugin.getDataFolder(), "players.yml");
        if (!playerDataFile.exists()) {
            playerDataFile.getParentFile().mkdirs();
            try {
                playerDataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        playerData = YamlConfiguration.loadConfiguration(playerDataFile);
        phantomDisabledPlayers = new HashSet<>();
        for (String key : playerData.getKeys(false)) {
            phantomDisabledPlayers.add(UUID.fromString(key));
        }
    }

    public void savePlayerData() {
        for (UUID uuid : phantomDisabledPlayers) {
            playerData.set(uuid.toString(), true);
        }

        try {
            playerData.save(playerDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isPhantomSpawningDisabled(Player player) {
        return phantomDisabledPlayers.contains(player.getUniqueId());
    }

    public void togglePhantomSpawning(Player player) {
        if (phantomDisabledPlayers.contains(player.getUniqueId())) {
            phantomDisabledPlayers.remove(player.getUniqueId());
            player.sendMessage("Phantom spawning enabled.");
        } else {
            phantomDisabledPlayers.add(player.getUniqueId());
            player.sendMessage("Phantom spawning disabled.");
        }
        savePlayerData();
    }
}
