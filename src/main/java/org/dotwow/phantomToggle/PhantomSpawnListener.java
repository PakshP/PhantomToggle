package org.dotwow.phantomToggle;

import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class PhantomSpawnListener implements Listener {

    private final PhantomManager phantomManager;

    public PhantomSpawnListener(PhantomManager phantomManager) {
        this.phantomManager = phantomManager;
    }

    @EventHandler
    public void onPhantomTarget(EntityTargetEvent event) {
        if (event.getTarget() instanceof Player && event.getEntity() instanceof Phantom) {
            Player player = (Player) event.getTarget();
            if (phantomManager.isPhantomSpawningDisabled(player)) {
                event.getEntity().remove(); // Deletes entity.
                event.setCancelled(true);
            }
        }
    }
}
