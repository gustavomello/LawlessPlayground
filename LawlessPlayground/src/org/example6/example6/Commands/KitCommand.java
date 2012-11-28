package org.example6.example6.Commands;

import org.bukkit.command.CommandSender;
import org.example6.example6.Commands.CommandTypes.example6Command;

public class KitCommand extends example6Command {

	public KitCommand() {
		super("kit", "example6.kit");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		//handle /kit give <player> <value>
		if (args.length == 3 && args[0].equalsIgnoreCase("give"))
		{
			if (!sender.hasPermission("example6.kit.give")) return false;
			String player = args[1];
			String value = args[2];

			/*PlayerConfig playerConfig = new PlayerConfig(player, this.plugin);
			
			List<String> newKits = playerConfig.getKits();
			newKits.add(value);
			playerConfig.setKits(newKits);
			*/
			sender.sendMessage("Player \"" + player + "\" has been given a kit with a value of " + value);
		}
		else
		{
			/*
			PlayerConfig playerConfig = new PlayerConfig(sender.getName(), this.plugin);
			List<String> kits = playerConfig.getKits();
			if (kits.size() == 0)
			{
				sender.sendMessage("You don't have any kits.");
			}
			else
			{
				String value = kits.get(0);
				kits.remove(0);
				sender.sendMessage("You have opened a kit with a value of " + value);
				//Player player = (Player) sender;
				//player.getInventory().
				sender.sendMessage("You have " + kits.size() + " kits left.");
			}
			playerConfig.setKits(kits);
			*/
		}
		return true;
	}
	public void GenerateKit(String value)
	{
		// 11 categories
		// building
		// decoration
		// redstone
		// transportation
		// miscellaneous
		// foodstuffs
		// tools
		// combat
		// brewing
		// materials
		// technical
		//stack.get
	}
}
