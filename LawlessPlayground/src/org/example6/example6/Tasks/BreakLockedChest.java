package org.example6.example6.Tasks;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.example6.example6.Utils.PacketManager;

public class BreakLockedChest extends BukkitRunnable {
	
	private Location location;

	public BreakLockedChest(Location loc)
	{
		this.location = loc;	
	}

	@Override
	public void run() {
		this.location.getBlock().setType(Material.AIR);
		
		PacketManager.SendExplosionPacket(this.location, 0.5f);
		
		PacketManager.SendBlockBreakPacket(this.location, 8);
		PacketManager.SendBlockBreakPacket(this.location, 11);
		PacketManager.SendBlockBreakPacket(this.location, 133);
		
		for (ItemStack i : GenerateItems(0))
		{
			this.location.getWorld().dropItemNaturally(this.location, i);
		}
		
		this.location.add(0.5,0.5,0.5);
		PacketManager.SendExplosionPacket(this.location, 0.5f);
	}
	


	public ArrayList<ItemStack> GenerateItems(int id) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		if (id <= 0)
		{
			drops.add(new ItemStack(Material.EXP_BOTTLE, 18));
		}
		return drops;
	}


}
