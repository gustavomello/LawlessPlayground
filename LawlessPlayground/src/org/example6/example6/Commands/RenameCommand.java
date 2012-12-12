package org.example6.example6.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.example6.example6.Commands.CommandTypes.example6Command;
import org.example6.example6.Models.NamedItemStack;
import org.example6.example6.Utils.MiscUtils;

public class RenameCommand extends example6Command {

	public RenameCommand() {
		super("rename", "example6.rename");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (sender instanceof Player)
		{
			String name = "";
			for (String arg : args)
			{
				if (name.length() > 0)
					name += " ";
				name += arg;
			}
			
			Player player = (Player) sender;
			if (player.getItemInHand()!=null)
			{
				ItemStack itemInHand = MiscUtils.toCraftBukkit(player.getItemInHand());
				NamedItemStack namedItem = new NamedItemStack(itemInHand);
				
				namedItem.setName(MiscUtils.colorize(name));
				
				player.setItemInHand(itemInHand);
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
