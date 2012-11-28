package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;

public abstract class BlockBreakHandler extends Handler {

	@Override
	public void run(Event event) {
		this.run((BlockBreakEvent)event);
	}

	public abstract void run(BlockBreakEvent event);

}