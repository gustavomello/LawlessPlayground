package org.example6.example6;

import org.bukkit.scheduler.BukkitRunnable;

public class TaskManager {

	private example6 plugin;

	public TaskManager(example6 plugin) {
		this.plugin = plugin;
	}
	
	public void RunTaskAsync(BukkitRunnable r)
	{
		r.runTaskAsynchronously(this.plugin);
	}
	
	public void RunTaskLater(BukkitRunnable r, long delay)
	{
		r.runTaskLater(this.plugin, delay);
	}

	public void RunTaskTimer(BukkitRunnable r, long delay, long period) {
		r.runTaskTimer(this.plugin, delay, period);
	}

}
