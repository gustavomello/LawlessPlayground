package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerEvent;
import org.example6.example6.example6;

public abstract class PlayerHandler extends Handler {
	public PlayerHandler(example6 plugin) {
		super(plugin);
	}
	@Override
	public void run(Event event) {
		this.run((PlayerEvent) event);
	}
	public abstract void run(PlayerEvent event);
}
