package org.example6.example6.EventHandlers;

import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.example6.example6.example6;
import org.example6.example6.EventHandlers.HandlerTypes.BlockPlaceHandler;
import org.example6.example6.Tasks.BreakLockedChest;

public class HandleLockedChest extends BlockPlaceHandler {

	@Override
	public void run(BlockPlaceEvent event) {
		if (event.getBlock().getType() == Material.LOCKED_CHEST)
		{
			example6.getTaskManager().RunTaskLater(
						new BreakLockedChest(event.getBlock().getLocation()), 20L
					);
		}
	}
}
