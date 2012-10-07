package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.example6.example6.example6;

public abstract class PlayerTeleportHandler extends PlayerHandler {
	public PlayerTeleportHandler(example6 plugin) {
		super(plugin);
	}

	@Override
	public void run(PlayerEvent event) {
		this.run((PlayerTeleportEvent) event);
	}
	
	public abstract void run(PlayerTeleportEvent event);
}
