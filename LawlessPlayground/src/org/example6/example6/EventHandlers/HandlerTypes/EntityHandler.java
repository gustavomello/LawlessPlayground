package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityEvent;

public abstract class EntityHandler extends Handler {

	@Override
	public void run(Event event) {
		this.run((EntityEvent) event);
	}
	public abstract void run(EntityEvent event);
}
