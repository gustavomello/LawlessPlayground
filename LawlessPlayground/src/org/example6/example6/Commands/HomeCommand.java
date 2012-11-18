package org.example6.example6.Commands;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.TeleportCommand;

public class HomeCommand extends TeleportCommand {

	public HomeCommand(example6 plugin) {
		super("home", "example6.home", plugin);
	}

	@Override
	public Boolean RunIfCanTeleport(Player player, String command,
			String alias, String[] args) {
		if (!PlayerHasValidBed(player))
		{
			player.sendMessage("You don't have a home.");
		}
		else
		{
			player.teleport(GetSafeBedSpawn(player));
		}
		return true;
	}
	
	public static Location GetSafeBedSpawn(Player player)
	{
		return player.getBedSpawnLocation().add(0.5,0.25,0.5);
	}
	
	public static Boolean PlayerHasValidBed(Player player)
	{
		if (player.getBedSpawnLocation()==null) return false;
		//if (GetSafeBedSpawn(player).getBlock()!=Block.BED) return false;
		return true;
	}
}
