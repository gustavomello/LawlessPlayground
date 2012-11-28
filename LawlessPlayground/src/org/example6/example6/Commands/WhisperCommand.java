package org.example6.example6.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.Commands.CommandTypes.example6Command;

public class WhisperCommand extends example6Command {

	public WhisperCommand() {
		super("whisper", "example6.whisper");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (args.length < 2)
		{
			sender.sendMessage(ChatColor.GREEN + "Format: /" + alias + " [player] [message]");
			return true;
		}
		
		String message = "";
		for(int i = 1; i < args.length; i++)
		{
			message += args[i] + " ";
		}

		Player targetPlayer = sender.getServer().getPlayer(args[0]);
		
		if (targetPlayer == null || !targetPlayer.isOnline())
		{
			sender.sendMessage(ChatColor.RED + "Couldn't find a player by that name");
		}
		
		if (!sender.hasPermission("example6.allchat"))
		{
			Player player = (Player) sender;
			if (!player.getNearbyEntities(6, 6, 6).contains(targetPlayer))
			{
				player.sendMessage(ChatColor.RED + "You must be closer to the player to talk to them.");
				return true;
			}
		}
		
		targetPlayer.sendMessage(ChatColor.RED + sender.getName() + ChatColor.WHITE + " says: "
				+ ChatColor.WHITE + message);
		
		sender.sendMessage(ChatColor.WHITE + "To " + 
				ChatColor.RED + targetPlayer.getName() +
				ChatColor.WHITE + ": " + message);
		
		return true;
	}

}
