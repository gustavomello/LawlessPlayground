package org.example6.example6.EventHandlers;

import java.util.Calendar;

import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.example6.example6.example6;
import org.example6.example6.Config.ChunkConfig;
import org.example6.example6.EventHandlers.HandlerTypes.BlockBreakHandler;
import org.example6.example6.EventHandlers.HandlerTypes.BlockPlaceHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerMoveHandler;
import org.example6.example6.Models.ChunkGroup;
import org.example6.example6.Utils.MiscUtils;

public class ChunkGroupHandler 
	implements
	BlockBreakHandler,
	BlockPlaceHandler,
	PlayerMoveHandler {

	//zombies chunk changed
	@Override
	public void run(PlayerMoveEvent event) {
		if (event.getTo().getWorld().equals(example6.getWorldManager().getZombieWorld())
				|| event.getTo().getWorld().equals(example6.getWorldManager().getCityDev()))
		{

			ChunkConfig cfg = example6.getConfigManager().getChunkConfig();
			if (event.getTo().getChunk().equals(event.getFrom().getChunk())) return;
			
			ChunkGroup dest = cfg.getChunkGroupByChunk(event.getTo().getChunk());
			
			if (dest == null) return;
			
			if (dest.equals(example6.getTempData().GetLastChunkGroup(event.getPlayer()))) return;
			
			example6.getTempData().SetLastChunkGroup(event.getPlayer(), dest);

			if (!dest.hasTitle()) return;
			
			event.getPlayer().sendMessage(MiscUtils.colorize(dest.getTitle()));
		}
	}

	//zombies block placed
	@Override
	public void run(BlockPlaceEvent event) {
		this.handleZombiesBlockChange(event.getBlock());
	}

	//zombies block broken
	@Override
	public void run(BlockBreakEvent event) {
		this.handleZombiesBlockChange(event.getBlock());
	}

	public void handleZombiesBlockChange(Block block)
	{
		if (block.getWorld().equals(example6.getWorldManager().getZombieWorld()))
		{
			ChunkConfig cfg = example6.getConfigManager().getChunkConfig();
			Chunk chunk = block.getChunk();
			ChunkGroup group = cfg.getChunkGroupByChunk(chunk);
			
			if (group == null)
				group = cfg.createSingleChunkGroup(chunk);
			
			group.setChangeCount(group.getChangeCount()+1);
			group.setLastChange(Calendar.getInstance().getTimeInMillis());
			cfg.saveChunkGroups();
		}
	}
}
