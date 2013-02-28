package org.example6.example6.EventHandlers;

import org.bukkit.event.player.PlayerQuitEvent;
import org.example6.example6.example6;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerQuitHandler;

public class PlayerDisposeHandler implements PlayerQuitHandler {

	@Override
	public void run(PlayerQuitEvent event) {
		example6.getConfigManager().dropPlayerConfig(event.getPlayer().getName());
	}

}
