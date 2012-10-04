package org.example6.example6.Commands.CommandTypes;

import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Config.PlayerConfig;

public abstract class TeleportCommand extends PlayerCommand {

	public TeleportCommand(String name, String permission, example6 plugin) {
		super(name, permission, plugin);
	}

	@Override
	public final Boolean RunAsPlayer(Player player, String command, String alias,
			String[] args) {
		PlayerConfig config = new PlayerConfig(player.getName(), plugin);
		long difference = Calendar.getInstance().getTimeInMillis() - config.getLastTeleport();
		if (difference < 5000)
		{
			player.sendMessage(ChatColor.RED + "You must wait " + (5000 - difference) + "ms before teleporting again.");
			return true;
		}
		return RunIfCanTeleport(player, command, alias, args);
	}
	
	public abstract Boolean RunIfCanTeleport(Player player, String command, String alias,
			String[] args);
}
