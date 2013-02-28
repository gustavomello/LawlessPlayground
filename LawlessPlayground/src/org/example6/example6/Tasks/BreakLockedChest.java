package org.example6.example6.Tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import net.minecraft.server.v1_4_R1.NBTTagCompound;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_4_R1.inventory.CraftItemStack;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.example6.example6.example6;
import org.example6.example6.Models.MysteryBox;
import org.example6.example6.Utils.PacketManager;

public class BreakLockedChest extends BukkitRunnable {
	
	
	private Collection<ItemStack> items;
	private Location location;

	public BreakLockedChest(Location loc, Collection<ItemStack> items)
	{
		this.items = items;
		this.location = loc;	
	}

	@Override
	public void run() {
		if (this.location.getBlock().getType().equals(Material.LOCKED_CHEST))
		{
			this.location.getBlock().setType(Material.AIR);
			
			PacketManager.SendExplosionPacket(this.location, 0.5f);
			
			PacketManager.SendBlockBreakPacket(this.location, 8);
			PacketManager.SendBlockBreakPacket(this.location, 11);
			PacketManager.SendBlockBreakPacket(this.location, 133);
			
			for (ItemStack i : items)
			{
				Item dropped = this.location.getWorld().dropItemNaturally(this.location, i);
				if (i.getType().equals(Material.WRITTEN_BOOK))
				{
					NBTTagCompound newBookData = CraftItemStack.asNMSCopy(i).tag;
					CraftItemStack.asNMSCopy(dropped.getItemStack()).setTag(newBookData);
				}
			}
			
			this.location.add(0.5,0.5,0.5);
			PacketManager.SendExplosionPacket(this.location, 0.5f);
		}
	}
}
