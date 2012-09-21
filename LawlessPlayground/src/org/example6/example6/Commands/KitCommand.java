package org.example6.example6.Commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Config.PlayerConfig;

public class KitCommand extends example6Command {

	public KitCommand(example6 Plugin) {
		super("kit", "example6.kit", Plugin);
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

			PlayerConfig playerConfig = new PlayerConfig(player, this.Plugin);
			
			List<String> newKits = playerConfig.getKits();
			newKits.add(value);
			playerConfig.setKits(newKits);
			
			sender.sendMessage("Player \"" + player + "\" has been given a kit with a value of " + value);
		}
		else
		{
			PlayerConfig playerConfig = new PlayerConfig(sender.getName(), this.Plugin);
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
		}
		return true;
	}
	public void GenerateKit(String value)
	{
		
	}
}
