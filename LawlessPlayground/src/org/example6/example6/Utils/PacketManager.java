package org.example6.example6.Utils;

import java.util.ArrayList;

import net.minecraft.server.Packet;
import net.minecraft.server.Packet60Explosion;
import net.minecraft.server.Packet61WorldEvent;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PacketManager {
	
	public static void SendNearby(Location loc, Packet packet)
	{
		for (Entity e : loc.getWorld().getEntities())
		{
			if (e instanceof Player)
			{
				if (e.getLocation().distance(loc) < 256)
					((CraftPlayer)e).getHandle().netServerHandler.sendPacket(packet);
			}
		}
	}

	public static void SendBlockBreakPacket(Location loc, int id) {
		Packet61WorldEvent packet = new Packet61WorldEvent(2001, 
				(int)loc.getX(), 
				(int)loc.getY(), 
				(int)loc.getZ(), 
				id,
				false);
		SendNearby(loc, packet);
	}

	public static void SendCrashPacket(Player target) {
		Location loc = target.getLocation();
		int id = 200;
		Packet61WorldEvent packet = new Packet61WorldEvent(2001, 
				(int)loc.getX(), 
				(int)loc.getY(), 
				(int)loc.getZ(), 
				id,
				false);
		((CraftPlayer)target).getHandle().netServerHandler.sendPacket(packet);
	}

	public static void SendExplosionPacket(Location loc, float size) {
        final Packet60Explosion boom = new Packet60Explosion(loc.getX(), 
        		loc.getY(), 
        		loc.getZ(), 
        		size, 
        		new ArrayList<Block>(), 
        		null);
        SendNearby(loc, boom);
	}
}
