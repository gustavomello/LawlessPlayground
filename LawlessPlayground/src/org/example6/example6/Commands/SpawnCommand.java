package org.example6.example6.Commands;

import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.TeleportCommand;


public class SpawnCommand extends TeleportCommand {

	public SpawnCommand(example6 plugin) {
		super("spawn", "example6.spawn", plugin);
	}

	@Override
	public Boolean RunIfCanTeleport(Player player, String command,
			String alias, String[] args) {
		player.teleport(plugin.getMultiverseCore().getMVWorldManager().getMVWorld(player.getWorld()).getSpawnLocation());
		return true;
	}

}
