package org.example6.example6.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MiscUtils {
	public static ItemStack toCraftBukkit(ItemStack stack) {
		if (!(stack instanceof CraftItemStack))
	        return new CraftItemStack(stack);
	    else
	        return stack;
	}
	
	public static void giveItemSafely(Player player, ItemStack stack)
	{
		ItemStack[] contents = player.getInventory().getContents();
		for (int i = 0; i < contents.length; i++)
		{
			if (contents[i] == null || contents[i].getType() == Material.AIR)
			{
				contents[i] = stack;
				player.getInventory().setContents(contents);
				return;
			}
		}
		player.getWorld().dropItemNaturally(player.getLocation(), stack);
	}

	public static String colorize(String string)
	{
		string = string.replace("&l", ChatColor.BOLD.toString());
		string = string.replace("&n", ChatColor.UNDERLINE.toString());
		string = string.replace("&o", ChatColor.ITALIC.toString());
		string = string.replace("&k", ChatColor.MAGIC.toString());
		string = string.replace("&m", ChatColor.STRIKETHROUGH.toString());
		string = string.replace("&r", ChatColor.RESET.toString());
		return string.replaceAll("(?i)&([a-k0-9])", "\u00A7$1");
	}
}
