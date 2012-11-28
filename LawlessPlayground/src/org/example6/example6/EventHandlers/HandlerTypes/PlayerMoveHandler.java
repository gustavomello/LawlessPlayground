package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public abstract class PlayerMoveHandler extends PlayerHandler {
	@Override
	public void run(PlayerEvent event) {
		this.run((PlayerMoveEvent)event);
	}
	
	public abstract void run(PlayerMoveEvent event);

}
