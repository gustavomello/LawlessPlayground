package org.example6.example6.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;

public class RenameCommand extends example6Command {

	public RenameCommand(String name, String permission, example6 plugin) {
		super("rename", "example6.rename", plugin);
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
		}
		else
		{
			sender.sendMessage(ChatColor.RED + "You must be a player to use this.");
		}
		return null;
	}

}
