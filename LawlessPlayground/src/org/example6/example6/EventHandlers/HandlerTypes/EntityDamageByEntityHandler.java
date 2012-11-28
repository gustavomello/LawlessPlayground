package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;

public abstract class EntityDamageByEntityHandler extends EntityHandler {

	@Override
	public void run(EntityEvent event) {
		this.run((EntityDamageByEntityEvent) event);
	}

	public abstract void run(EntityDamageByEntityEvent event);
}
