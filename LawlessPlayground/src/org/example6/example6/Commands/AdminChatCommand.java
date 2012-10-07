package org.example6.example6.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;

public class AdminChatCommand extends example6Command {

	public AdminChatCommand(example6 plugin) {
		super("admin", "example6.adminchat", plugin);
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		//join the args to form the sentence
		String message = "";
		for (String s : args)
		{
			message += s + " ";
		}
		
		//send to everyone that has the permission
		for (Player player : plugin.getServer().getOnlinePlayers())
		{
			if (player.hasPermission("example6.adminchat"))
				player.sendMessage(ChatColor.RED + "[Admin:" + sender.getName() + "] " + message);
		}
		
		return true;
	}
}