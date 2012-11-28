package org.example6.example6.Tasks;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.example6.example6.example6;
import org.example6.example6.Config.ChunkConfig;
import org.example6.example6.Models.ChunkGroup;

public class WorldSyncAgent extends BukkitRunnable {
	
	private ChunkConfig cm;
	private World cleanCopy;
	private World instance;
	private example6 plugin;

	public WorldSyncAgent(example6 plugin, World clean, World instance, ChunkConfig cm)
	{
		this.plugin = plugin;
		this.cm = cm;
		this.cleanCopy = clean;
		this.instance = instance;
	}

	@Override
	public void run() {
		long delay = 0;
		for(ChunkGroup group : cm.getChunkGroups())
		{
			if (group.shouldRegenerate())
			{
				ChunkRegenTask task = new ChunkRegenTask();
				for (Chunk chunk : group.getChunks(instance))
				{
					Chunk source = cleanCopy.getChunkAt(
							chunk.getX(), 
							chunk.getZ()
							);
					task.makeChunkRegen(source,  chunk);
				}

				task.runTaskTimer(plugin, delay, 10L);
				group.setChangeCount(0);
				cm.saveChunkGroups();
			}
		}
	}

}
