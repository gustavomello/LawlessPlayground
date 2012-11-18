package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.example6.example6.example6;

public abstract class PlayerRespawnHandler extends PlayerHandler {

	public PlayerRespawnHandler(example6 plugin) {
		super(plugin);
	}

	@Override
	public void run(PlayerEvent event) {
		if (event instanceof PlayerRespawnEvent)
		{
			this.run((PlayerRespawnEvent)event);
		}
	}
	
	public abstract void run(PlayerRespawnEvent event);

}
