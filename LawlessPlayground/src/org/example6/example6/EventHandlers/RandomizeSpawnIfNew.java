package org.example6.example6.EventHandlers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.example6.example6.example6;
import org.example6.example6.Commands.RandomCommand;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerJoinHandler;

public class RandomizeSpawnIfNew extends PlayerJoinHandler {

	public RandomizeSpawnIfNew(example6 plugin) {
		super(plugin);
	}

	@Override
	public void run(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPlayedBefore())
		{
			Location newloc = RandomCommand.GetRandomLocation(player, 2000);
			player.teleport(newloc);
		}
	}
}
