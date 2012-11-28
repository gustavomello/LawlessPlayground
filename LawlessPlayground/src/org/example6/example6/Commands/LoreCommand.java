package org.example6.example6.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.example6.example6.Commands.CommandTypes.example6Command;
import org.example6.example6.Utils.MiscUtils;
import org.example6.example6.Utils.NamedItemStack;

public class LoreCommand extends example6Command {

	public LoreCommand() {
		super("lore", "example6.lore");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (player.getItemInHand()!=null)
			{
				if (args.length == 0)
				{
					sender.sendMessage(ChatColor.RED + "Usage: /lore (add/clear) [message]");
					return true;
				}

				ItemStack itemInHand = MiscUtils.toCraftBukkit(player.getItemInHand());
				NamedItemStack namedItem = new NamedItemStack(itemInHand);
				
				if (args[0].equalsIgnoreCase("add"))
				{
					if (args.length < 2)
					{
						sender.sendMessage(ChatColor.RED + "Usage: /lore (add/clear) [message]");
						return true;
					}
					String lore = "";
					for (int i = 1; i < args.length; i++)
					{
						if (lore.length() > 0)
							lore += " ";
						lore += args[i];
					}
					
					List<String> lores = namedItem.getLoresList();
					lores.add(MiscUtils.colorize(lore));
					namedItem.setLoresList(lores);
					
					player.setItemInHand(itemInHand);
				}
				else if (args[0].equalsIgnoreCase("clear"))
				{
					namedItem.clearLores();
					player.setItemInHand(itemInHand);
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "Usage: /lore (add/clear) [message]");
					return true;
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "Hold something first.");
			}
		}
		else
		{
			sender.sendMessage(ChatColor.RED + "You must be a player to use this.");
		}
		return true;
	}

}
