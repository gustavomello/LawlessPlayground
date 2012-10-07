package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.Event;
import org.example6.example6.example6;

public abstract class Handler {
	public example6 plugin;
	public Handler(example6 plugin)
	{
		this.plugin = plugin;
	}
	public abstract void run(Event event);
}
