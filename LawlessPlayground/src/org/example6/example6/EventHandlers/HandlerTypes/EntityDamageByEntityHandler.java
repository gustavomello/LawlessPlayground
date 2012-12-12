package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;

public interface EntityDamageByEntityHandler {
	public abstract void run(EntityDamageByEntityEvent event);
}
