package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public abstract class PlayerQuitHandler extends PlayerHandler {

	@Override
	public void run(PlayerEvent event) {
		// TODO Auto-generated method stub
		this.run((PlayerQuitEvent) event);
	}
	
	public abstract void run(PlayerQuitEvent event);
}
