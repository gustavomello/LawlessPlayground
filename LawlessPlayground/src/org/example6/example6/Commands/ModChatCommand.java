package org.example6.example6.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;

public class ModChatCommand extends example6Command {

	public ModChatCommand(example6 plugin) {
		super("mod", "example6.modchat", plugin);
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
			if (player.hasPermission("example6.modchat"))
				player.sendMessage(ChatColor.AQUA + "[Mod:" + sender.getName() + "] " + message);
		}
		
		return true;
	}

}
