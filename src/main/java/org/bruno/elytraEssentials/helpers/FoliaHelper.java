package org.bruno.elytraEssentials.helpers;

import org.bruno.elytraEssentials.ElytraEssentials;
import org.bruno.elytraEssentials.utils.CancellableTask;
import org.bruno.elytraEssentials.helpers.scheduler.SchedulerAdapter;
import org.bruno.elytraEssentials.helpers.scheduler.FoliaSchedulerAdapter;
import org.bruno.elytraEssentials.helpers.scheduler.BukkitSchedulerAdapter;
import org.bukkit.entity.Entity;

public final class FoliaHelper {
    private final ElytraEssentials plugin;
    private final boolean isFolia;
    private final SchedulerAdapter scheduler;

    public FoliaHelper(ElytraEssentials plugin) {
        this.plugin = plugin;
        boolean folia = false;
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            folia = true;
        } catch (ClassNotFoundException e) {
            // Not Folia
        }
        this.isFolia = folia;

        if (isFolia) {
            this.scheduler = new FoliaSchedulerAdapter();
        } else {
            this.scheduler = new BukkitSchedulerAdapter();
        }
    }

    public boolean isFolia() {
        return isFolia;
    }

    public CancellableTask runTask(Entity entity, Runnable task) {
        return scheduler.runTask(plugin, entity, task);
    }

    public CancellableTask runTaskTimerForEntity(Entity entity, Runnable task, long delay, long period) {
        return scheduler.runTaskTimer(plugin, entity, task, delay, period);
    }

    public CancellableTask runTaskLater(Entity entity, Runnable task, long delay) {
        return scheduler.runTaskLater(plugin, entity, task, delay);
    }

    public CancellableTask runTaskTimerGlobal(Runnable task, long delay, long period) {
        return scheduler.runTaskTimerGlobal(plugin, task, delay, period);
    }

    public CancellableTask runAsyncTask(Runnable task) {
        return scheduler.runAsyncTask(plugin, task);
    }

    public void runTaskOnMainThread(Runnable task) {
        scheduler.runTaskOnMainThread(plugin, task);
    }
}
