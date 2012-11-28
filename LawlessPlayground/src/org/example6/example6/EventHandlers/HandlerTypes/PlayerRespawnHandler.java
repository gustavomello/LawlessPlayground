package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public abstract class PlayerRespawnHandler extends PlayerHandler {

	@Override
	public void run(PlayerEvent event) {
		if (event instanceof PlayerRespawnEvent)
		{
			this.run((PlayerRespawnEvent)event);
		}
	}
	
	public abstract void run(PlayerRespawnEvent event);

}
