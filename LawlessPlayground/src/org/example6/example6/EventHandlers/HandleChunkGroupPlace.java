package org.example6.example6.EventHandlers;

import java.util.Calendar;
import org.bukkit.Chunk;
import org.bukkit.event.block.BlockPlaceEvent;
import org.example6.example6.example6;
import org.example6.example6.Config.ChunkConfig;
import org.example6.example6.EventHandlers.HandlerTypes.BlockPlaceHandler;
import org.example6.example6.Models.ChunkGroup;

public class HandleChunkGroupPlace extends BlockPlaceHandler {

	@Override
	public void run(BlockPlaceEvent event) {
		if (event.getBlock().getWorld().getName().equalsIgnoreCase("zombies"))
		{
			ChunkConfig cfg = example6.getConfigManager().getChunkConfig();
			long ctime = Calendar.getInstance().getTimeInMillis();
			Chunk chunk = event.getBlock().getChunk();
			ChunkGroup group = cfg.getChunkGroupByChunk(
					chunk
					);
			
			if (group == null)
				group = new ChunkGroup(null, 0, ctime, chunk);
			
			group.setChangeCount(group.getChangeCount()+1);
			group.setLastChange(ctime);
			cfg.saveChunkGroups();
		}
	}

}
