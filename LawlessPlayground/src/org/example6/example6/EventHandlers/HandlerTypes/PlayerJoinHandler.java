package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public abstract class PlayerJoinHandler extends PlayerHandler {

	@Override
	public void run(PlayerEvent event) {
		// TODO Auto-generated method stub
		this.run((PlayerJoinEvent) event);
	}
	
	public abstract void run(PlayerJoinEvent event);
}
