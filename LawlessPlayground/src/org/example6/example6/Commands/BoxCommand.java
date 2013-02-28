package org.example6.example6.Commands;

import org.bukkit.command.CommandSender;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;
import org.example6.example6.Models.MysteryBox;

public class BoxCommand extends example6Command {

	public BoxCommand() {
		super("box", "example6.box");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		//handle /kit give <player> <value>
		if (args.length == 3 && args[0].equalsIgnoreCase("give"))
		{
			if (!sender.hasPermission("example6.box.give")) return false;
			String player = args[1];
			String value = args[2];

			if (sender.getServer().getPlayer(player) != null
					&& MysteryBox.CanBeGiven(sender.getServer().getPlayer(player).getWorld()))
			{
				MysteryBox box = new MysteryBox(value);
				box.giveToPlayer(sender.getServer().getPlayer(player));
			}
			else
			{
				example6.getConfigManager().getOfflinePlayerConfig(player).addBox(value);
			}
			
			sender.sendMessage("Player \"" + player + "\" has been given a box with a value of " + value);
		}
		return true;
	}
}
