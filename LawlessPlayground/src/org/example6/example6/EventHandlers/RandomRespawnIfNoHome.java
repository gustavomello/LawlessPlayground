package org.example6.example6.EventHandlers;

import org.bukkit.event.player.PlayerRespawnEvent;
import org.example6.example6.example6;
import org.example6.example6.Commands.RandomCommand;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerRespawnHandler;

public class RandomRespawnIfNoHome extends PlayerRespawnHandler {

	public RandomRespawnIfNoHome(example6 plugin) {
		super(plugin);
	}

	@Override
	public void run(PlayerRespawnEvent event) {
		if (!event.isBedSpawn() 
				&& event.getPlayer().getWorld().getName().equalsIgnoreCase("world")
				&& !event.getPlayer().hasPermission("example6.spawn.vanilla"))
		{
			event.setRespawnLocation(RandomCommand.GetRandomLocation(event.getPlayer(), 2000));
		}
	}
}
