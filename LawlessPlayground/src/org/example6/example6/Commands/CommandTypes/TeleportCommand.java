package org.example6.example6.Commands.CommandTypes;

import org.bukkit.entity.Player;

public abstract class TeleportCommand extends PlayerCommand {

	public TeleportCommand(String name, String permission) {
		super(name, permission);
	}

	@Override
	public final Boolean RunAsPlayer(Player player, String command, String alias,
			String[] args) {
		//old place of anti-teleport logic
		return RunIfCanTeleport(player, command, alias, args);
	}
	
	public abstract Boolean RunIfCanTeleport(Player player, String command, String alias,
			String[] args);
}
