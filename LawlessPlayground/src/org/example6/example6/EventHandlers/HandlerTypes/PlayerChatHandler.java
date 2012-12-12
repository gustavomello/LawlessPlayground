package org.example6.example6.EventHandlers.HandlerTypes;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;

public interface PlayerChatHandler {
	
	public void run(AsyncPlayerChatEvent event);
}
