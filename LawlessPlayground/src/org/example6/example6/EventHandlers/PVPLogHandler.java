package org.example6.example6.EventHandlers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.example6.example6.example6;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.Config.PlayerConfig.PendingChange;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDamageByEntityHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerJoinHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerQuitHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerTeleportHandler;
import org.example6.example6.Utils.EntityManager;
import org.example6.example6.Utils.ExperienceManager;

public class PVPLogHandler implements 
	PlayerQuitHandler, 
	EntityDamageByEntityHandler, 
	PlayerTeleportHandler,
	PlayerJoinHandler {

	public static final String TELEPORT_DENIED_STRING = "You appear to be in combat.";

	//deny teleport if in combat
	@Override
	public void run(PlayerTeleportEvent event) {
		if (!event.getCause().equals(TeleportCause.COMMAND))
			return;
		
		Player player = event.getPlayer();
		if (example6.getTempData().IsInCombat(player.getName()))
		{
			event.setCancelled(true);
			player.sendMessage(ChatColor.RED + TELEPORT_DENIED_STRING);
		}
	}

	//log last damage time PVP
	@Override
	public void run(EntityDamageByEntityEvent event) {
		Entity attacker = event.getDamager();
		Entity defender = event.getEntity();
		if (attacker instanceof Player
				&& defender instanceof Player)
		{
			long time = Calendar.getInstance().getTimeInMillis();
			example6.getTempData().SetLastCombatTime(((Player) attacker).getName(), time);
			example6.getTempData().SetLastCombatTime(((Player) defender).getName(), time);
		}
	}

	//apply pending changes
	@Override
	public void run(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		PlayerConfig pcfg = example6.getConfigManager().getPlayerConfig(player);
		List<String> changes = pcfg.getPendingChanges();
		
		if (changes.size()==0) return;
		
		for (String change : changes)
		{			
			switch (change)
			{
				case PendingChange.CLEAR_EXPERIENCE:
					ExperienceManager expMan = new ExperienceManager(player);
					expMan.setExp(0);
					player.setTotalExperience(0);
					example6.getTempData().SetLastCombatTime(player.getName(),  Calendar.getInstance().getTimeInMillis());
					player.sendMessage(ChatColor.RED + "You logged off while in combat, and ended up losing all your experience!");
				break;
				case PendingChange.CLEAR_WORN_ITEMS:
					player.getInventory().setArmorContents(null);
					example6.getTempData().SetLastCombatTime(player.getName(),  Calendar.getInstance().getTimeInMillis());
					player.sendMessage(ChatColor.RED + "You logged off while in combat, and ended up losing your worn items!");
				break;
				case PendingChange.CLEAR_INVENTORY:
					player.getInventory().clear();
					example6.getTempData().SetLastCombatTime(player.getName(),  Calendar.getInstance().getTimeInMillis());
					player.sendMessage(ChatColor.RED + "You logged off while in combat, and ended up losing your entire inventory!");
				break;
			}
		}
		pcfg.clearPendingChanges();
	}
	
	//lose stuff in combat
	@Override
	public void run(PlayerQuitEvent event) {
		// TODO Auto-generated method stub
		Player player = event.getPlayer();
		World world = player.getWorld();
		Location location = player.getLocation();
		
		if (example6.getTempData().IsInCombat(player.getName()))
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
		example6.getConfigManager().getPlayerConfig(player)
			.addPendingChange(PendingChange.CLEAR_INVENTORY);
	}

	private void dropWornItems(Player player, World world, Location location,
			List<ItemStack> wornItems) {
		dropItems(world, location, wornItems);
		example6.getConfigManager().getPlayerConfig(player)
			.addPendingChange(PendingChange.CLEAR_WORN_ITEMS);
	}

	private void dropItems(World world, Location location, List<ItemStack> wornItems) {
		for (ItemStack item : wornItems)
			world.dropItemNaturally(location, item);
	}

	private void dropExperience(Player player, World world, Location location) {
		ExperienceManager expMan = new ExperienceManager(player);
		int exp = expMan.getCurrentExp();
		if (exp == 0) return;
		
		EntityManager.SpawnXpOrbs(exp, location);
		
		example6.getConfigManager().getPlayerConfig(player)
			.addPendingChange(PendingChange.CLEAR_EXPERIENCE);
	}

}
