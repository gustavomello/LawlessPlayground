package org.example6.example6.EventHandlers;

import java.util.Calendar;
import org.bukkit.Chunk;
import org.bukkit.event.block.BlockBreakEvent;
import org.example6.example6.example6;
import org.example6.example6.Config.ChunkConfig;
import org.example6.example6.EventHandlers.HandlerTypes.BlockBreakHandler;
import org.example6.example6.Models.ChunkGroup;

public class HandleChunkGroupBreak extends BlockBreakHandler {

	@Override
	public void run(BlockBreakEvent event) {
		if (event.getBlock().getWorld().getName().equalsIgnoreCase("zombies"))
		{
			ChunkConfig cfg = example6.getConfigManager().getChunkConfig();
			Chunk chunk = event.getBlock().getChunk();
			ChunkGroup group = cfg.getChunkGroupByChunk(chunk);
			
			if (group == null)
				group = cfg.createSingleChunkGroup(chunk);
			
			group.setChangeCount(group.getChangeCount()+1);
			group.setLastChange(Calendar.getInstance().getTimeInMillis());
			cfg.saveChunkGroups();
		}
	}

}
