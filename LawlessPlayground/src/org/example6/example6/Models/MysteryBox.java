package org.example6.example6.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_4_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.example6.example6.example6;
import org.example6.example6.Commands.BoxCommand;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.Utils.MiscUtils;

public class MysteryBox {
	
	private final String value;

	public MysteryBox(String value) {
		this.value = value;
		
	}
	
	private static int ParseInteger(String value)
	{
		int i = -1;
		try
		{
			i = Integer.parseInt(value);
		}
		catch (Exception e)
		{
			return i;
		}
		return i;
	}
	
	public static Boolean CanBeGiven(World world)
	{
		if (world.equals(example6.getWorldManager().getCreativeWorld())
				|| world.equals(example6.getWorldManager().getCityDev()))
			return false;
		return true;
	}
	
	public void giveToPlayer(final Player player)
	{
		if (!CanBeGiven(player.getWorld()))
		{
			PlayerConfig pc = example6.getConfigManager().getPlayerConfig(player);
			pc.addBox(this.value);
			
			player.sendMessage(ChatColor.RED + "You have been given a Mystery Box, but it isn't allowed in the world you're in. Go to a different world to get it.");
		}
		else
		{
			BukkitRunnable r = new BukkitRunnable() {
				@Override
				public void run() {
					String name = "&7Mystery Box";
					ItemStack stack = CraftItemStack.asCraftCopy(new ItemStack(Material.LOCKED_CHEST,1));
					
					int level = ParseInteger(value);
					String valueString = "&2Unknown value";
					if (level == -1)
					{
						valueString = MiscUtils.colorize("&2" + value + " value");
					}
					else
					{
						valueString = MiscUtils.colorize("&2Level " + level);
					}
					
					ArrayList<String> list = new ArrayList<String>();
					list.add(valueString);
					
					ItemDisplay nms = new ItemDisplay(stack);
					nms.setName(MiscUtils.colorize(name));
					nms.setLore(list);
			
					MiscUtils.giveItemSafely(player, stack);
				}
			};
			
			example6.getTaskManager().RunTaskLater(r, 20L);
		}
	}
}
