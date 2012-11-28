package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityEvent;

public abstract class EntityDeathHandler extends EntityHandler {

	@Override
	public void run(EntityEvent event) {
		this.run((EntityDeathEvent) event);
	}

	public abstract void run(EntityDeathEvent event);
}
