package org.example6.example6.Commands;

import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.TeleportCommand;


public class SpawnCommand extends TeleportCommand {

	public SpawnCommand() {
		super("spawn", "example6.spawn");
	}

	@Override
	public Boolean RunIfCanTeleport(Player player, String command,
			String alias, String[] args) {
		player.teleport(example6.getPlug().getMvcore().getMVWorldManager().getMVWorld(player.getWorld()).getSpawnLocation());
		return true;
	}

}
