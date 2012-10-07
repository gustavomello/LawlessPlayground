package org.example6.example6.Commands;

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
		player.teleport(player.getBedSpawnLocation());
		return true;
	}
}
