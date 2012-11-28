package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.Event;
import org.bukkit.event.block.BlockPlaceEvent;

public abstract class BlockPlaceHandler extends Handler {

	@Override
	public void run(Event event) {
		this.run((BlockPlaceEvent)event);
	}

	public abstract void run(BlockPlaceEvent event);

}
