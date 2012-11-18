package org.example6.example6.EventHandlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.example6.example6.example6;
import org.example6.example6.Config.PlayerConfig.PendingChange;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerQuitHandler;
import org.example6.example6.Utils.ExperienceManager;

public class LoseStuffIfInCombat extends PlayerQuitHandler {

	public LoseStuffIfInCombat(example6 plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(PlayerQuitEvent event) {
		// TODO Auto-generated method stub
		Player player = event.getPlayer();
		World world = player.getWorld();
		Location location = player.getLocation();
		
		if (plugin.getTempData().IsInCombat(player.getName()))
		{			
			List<ItemStack> wornItems = this.removeAir(player.getInventory().getArmorContents());
			
			if (wornItems.size() == 0)
			{
				List<ItemStack> inventory = this.removeAir(player.getInventory().getContents());
				if (inventory.size() == 0)
					killPlayer(player);
				else
					dropInventory(player, world, location, inventory);
			}
			else
			{
				dropWornItems(player, world, location, wornItems);
			}
			
			dropExperience(player, world, location);
		}
	}
	
	private List<ItemStack> removeAir(ItemStack[] oldItems)
	{
		List<ItemStack> stack = new ArrayList<ItemStack>();
		for (ItemStack item : oldItems)
		{
			if (item != null && !item.getType().equals(Material.AIR))
				stack.add(item);
		}
		return stack;
	}

	private void killPlayer(Player player) {
		player.setHealth(0);
	}

	private void dropInventory(Player player, World world,
			Location location, List<ItemStack> inventory) {
		dropItems(world, location, inventory);
		plugin.getPlayerConfig(player).addPendingChange(PendingChange.CLEAR_INVENTORY);
	}

	private void dropWornItems(Player player, World world, Location location,
			List<ItemStack> wornItems) {
		dropItems(world, location, wornItems);
		plugin.getPlayerConfig(player).addPendingChange(PendingChange.CLEAR_WORN_ITEMS);
	}

	private void dropItems(World world, Location location, List<ItemStack> wornItems) {
		for (ItemStack item : wornItems)
			world.dropItemNaturally(location, item);
	}

	private void dropExperience(Player player, World world, Location location) {
		ExperienceManager expMan = new ExperienceManager(player);
		int exp = expMan.getCurrentExp();
		if (exp == 0) return;
		
		int xpAmountMod = 1;
		if (exp > 10000)
			xpAmountMod = 1000;
		if (exp > 1000)
			xpAmountMod = 100;
		else if (exp > 100)
			xpAmountMod = 10;
		
		int orbs = exp / xpAmountMod;
		for (int i = 1; i <= orbs; i++)
			((ExperienceOrb)world.spawn(location, ExperienceOrb.class)).setExperience(xpAmountMod-1);
		
		plugin.getPlayerConfig(player).addPendingChange(PendingChange.CLEAR_EXPERIENCE);
	}
}
