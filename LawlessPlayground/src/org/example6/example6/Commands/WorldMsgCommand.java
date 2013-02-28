package org.example6.example6.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.Commands.CommandTypes.example6Command;

public class WorldMsgCommand extends example6Command {

	public WorldMsgCommand() {
		super("worldmsg", "example6.worldmsg");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (args.length < 2)
		{
			SendUsage(sender);
		}
		else
		{
			World world = sender.getServer().getWorld(args[0]);
			if (world != null)
			{
				String message = "";
				
				for (int i = 1; i < args.length; i++)
				{
					message += args[i] + " ";
				}
				
				for (Player p : world.getPlayers())
				{
					p.sendMessage(ChatColor.RED + message);
				}
			}
			else
			{
				sender.sendMessage("That world doesn't exist.");
			}
		}
		
		return true;
	}

	private void SendUsage(CommandSender sender) {
		sender.sendMessage("Usage: /worldmsg [world] [message]");
	}

}
