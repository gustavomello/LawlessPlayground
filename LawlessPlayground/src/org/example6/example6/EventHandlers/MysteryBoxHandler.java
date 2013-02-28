package org.example6.example6.EventHandlers;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_4_R1.inventory.CraftItemStack;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.example6.example6.example6;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.EventHandlers.HandlerTypes.BlockBreakHandler;
import org.example6.example6.EventHandlers.HandlerTypes.BlockPlaceHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerTeleportHandler;
import org.example6.example6.Models.ItemDisplay;
import org.example6.example6.Models.MysteryBox;
import org.example6.example6.Tasks.BreakLockedChest;
import org.example6.example6.Utils.MiscUtils;
import org.example6.example6.Utils.RandomUtils;

public class MysteryBoxHandler 
	implements BlockPlaceHandler, BlockBreakHandler, PlayerTeleportHandler {

	//give kept chests
	@Override
	public void run(PlayerTeleportEvent event) {
		if ((!event.isCancelled()) && MysteryBox.CanBeGiven(event.getTo().getWorld()))
		{
			PlayerConfig cfg = example6.getConfigManager().getPlayerConfig(event.getPlayer());
			String nextValue = cfg.getFirstBox();
			cfg.deleteBox(nextValue);
			if (nextValue != null)
			{
				MysteryBox box = new MysteryBox(nextValue);
				box.giveToPlayer(event.getPlayer());
				event.getPlayer().sendMessage(ChatColor.YELLOW + "Here's your Mystery Box; we kept it safe for you.");
			}
		}
	}

	//dont break a placed chest
	@Override
	public void run(BlockBreakEvent event) {
		if (event.getBlock().getType() == Material.LOCKED_CHEST)
		{
			event.setCancelled(true);
		}
	}

	//handle locked chest
	@Override
	public void run(BlockPlaceEvent event) {
		if (!event.isCancelled()
				&& event.getBlock().getType() == Material.LOCKED_CHEST)
		{
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			ItemDisplay nms = new ItemDisplay(CraftItemStack.asCraftCopy(event.getItemInHand()));
			for (String lore : nms.getLore())
			{
				if (lore.startsWith(MiscUtils.colorize("&2Level ")))
				{
					String levelString = lore.substring(8, lore.length());
					int level = 0;
					
					try {
						level = Integer.parseInt(levelString);
					}
					catch (Exception e)
					{
						level = 75;
					}
					
					items.addAll(RandomUtils.GenerateRandomSet(level, event.getPlayer()));
				}
				else if (lore.startsWith(MiscUtils.colorize("&2"))
						&& lore.endsWith(" value"))
				{
					String value = lore.substring(2, lore.indexOf(" value"));
					items.addAll(RandomUtils.GenerateDefinedSet(value, event.getPlayer()));
				}
			}
			example6.getTaskManager().RunTaskLater(
						new BreakLockedChest(event.getBlock().getLocation(),
					    items),
					    20L
					);
		}
	}

}
