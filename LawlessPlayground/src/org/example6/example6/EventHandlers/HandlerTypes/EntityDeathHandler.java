package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityEvent;

public interface EntityDeathHandler {
	public abstract void run(EntityDeathEvent event);
}
