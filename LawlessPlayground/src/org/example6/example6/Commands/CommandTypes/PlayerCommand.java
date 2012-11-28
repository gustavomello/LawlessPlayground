package org.example6.example6.Commands.CommandTypes;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerCommand extends example6Command {

	public PlayerCommand(String name, String permission) {
		super(name, permission);
	}

	@Override
	public final Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (sender instanceof Player)
		{
			return RunAsPlayer((Player) sender, command, alias, args);
		}
		else
		{
			sender.sendMessage(ChatColor.RED + "Only a player may use that command.");
			return true;
		}
	}

	public abstract Boolean RunAsPlayer(Player player, String command, String alias, String[] args);
}
