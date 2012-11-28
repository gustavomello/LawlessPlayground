package org.example6.example6.Commands.CommandTypes;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public abstract class WorldChangeCommand extends TeleportCommand {

	public WorldChangeCommand(String name, String permission) {
		super(name, permission);
	}
	
	@Override
	public final Boolean RunIfCanTeleport(Player player, String command, String alias,
			String[] args)
	{		
		Location location = getLastLocation(player);
		if (location != null)
		{
			player.teleport(location, TeleportCause.COMMAND);
		}
		else
		{
			player.teleport(getSpawn(), TeleportCause.COMMAND);
		}
		
		return true;
	}
		
	public abstract Location getSpawn();
	
	public abstract Location getLastLocation(Player player);
}
