package org.example6.example6.EventHandlers.HandlerTypes;

import java.util.logging.Logger;

import org.bukkit.event.Event;

public abstract class Handler {
	public abstract void run(Event event);
	public void log(String message)
	{
		Logger.getAnonymousLogger().info(message);
	}
}
