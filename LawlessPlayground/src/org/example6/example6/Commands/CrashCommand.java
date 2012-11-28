package org.example6.example6.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.Commands.CommandTypes.example6Command;
import org.example6.example6.Utils.PacketManager;

public class CrashCommand extends example6Command {

	public CrashCommand() {
		super("crash", "example6.crash");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (args.length > 0)
		{
			Player target = sender.getServer().getPlayer(args[0]);
			if (target == null)
			{
				sender.sendMessage(ChatColor.RED + args[0] + " was not found.");
				return true;
			}
			
			if (target.hasPermission("example6.crash"))
			{
				sender.sendMessage(ChatColor.RED + args[0] + " cannot be crashed.");
				return true;
			}
			
			PacketManager.SendCrashPacket(target);
		}
		return true;
	}

}
