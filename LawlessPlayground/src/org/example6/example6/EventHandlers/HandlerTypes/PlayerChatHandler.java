package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import org.example6.example6.example6;

public abstract class PlayerChatHandler extends PlayerHandler {

	public PlayerChatHandler(example6 plugin) {
		super(plugin);
	}

	@Override
	public void run(PlayerEvent event) {
		// TODO Auto-generated method stub
		this.run((AsyncPlayerChatEvent) event);
	}
	
	public abstract void run(AsyncPlayerChatEvent event);
}
