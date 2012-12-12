package org.example6.example6.EventHandlers;

import org.bukkit.event.player.PlayerRespawnEvent;
import org.example6.example6.Commands.RandomCommand;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerRespawnHandler;

public class RandomRespawnHandler implements PlayerRespawnHandler {

	@Override
	public void run(PlayerRespawnEvent event) {
		if (!event.isBedSpawn() 
				&& event.getRespawnLocation().getWorld().getName().equalsIgnoreCase("world")
				&& !event.getPlayer().hasPermission("example6.spawn.vanilla"))
		{
			event.setRespawnLocation(RandomCommand.GetRandomLocation(event.getPlayer(), 100));
		}
	}
}
