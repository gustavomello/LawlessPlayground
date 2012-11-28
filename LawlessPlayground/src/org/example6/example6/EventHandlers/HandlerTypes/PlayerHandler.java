package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerEvent;

public abstract class PlayerHandler extends Handler {
	@Override
	public void run(Event event) {
		this.run((PlayerEvent) event);
	}
	public abstract void run(PlayerEvent event);
}
