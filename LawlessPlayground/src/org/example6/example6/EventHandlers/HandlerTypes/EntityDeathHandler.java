package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityEvent;
import org.example6.example6.example6;

public abstract class EntityDeathHandler extends EntityHandler {

	public EntityDeathHandler(example6 plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(EntityEvent event) {
		this.run((EntityDeathEvent) event);
	}

	public abstract void run(EntityDeathEvent event);
}
