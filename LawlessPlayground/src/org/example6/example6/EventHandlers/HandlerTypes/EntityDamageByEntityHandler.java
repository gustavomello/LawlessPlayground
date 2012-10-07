package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.example6.example6.example6;

public abstract class EntityDamageByEntityHandler extends EntityHandler {

	public EntityDamageByEntityHandler(example6 plugin) {
		super(plugin);
	}

	@Override
	public void run(EntityEvent event) {
		this.run((EntityDamageByEntityEvent) event);
	}

	public abstract void run(EntityDamageByEntityEvent event);
}
