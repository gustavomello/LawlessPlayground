package org.example6.example6.Commands.CommandTypes;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.example6.example6.example6;

public abstract class WorldChangeCommand extends TeleportCommand {

	public WorldChangeCommand(String name, String permission, example6 plugin) {
		super(name, permission, plugin);
	}
	
	@Override
	public final Boolean RunIfCanTeleport(Player player, String command, String alias,
			String[] args)
	{		
		Location location = getLastLocation(player);
		if (location != null)
		{
			player.teleport(location);
		}
		else
		{
			player.teleport(getSpawn());
		}
		
		return true;
	}
		
	public abstract Location getSpawn();
	
	public abstract Location getLastLocation(Player player);
}
