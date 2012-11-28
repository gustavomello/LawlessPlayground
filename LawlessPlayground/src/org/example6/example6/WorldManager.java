package org.example6.example6;

import org.bukkit.World;

public class WorldManager {
	
	private final World Overworld;
	private final World Nether;
	private final World End;
	private final World ZombieWorld;
	private final World CityDev;
	private final World CreativeWorld;

	public WorldManager(example6 plugin) {
		Overworld = plugin.getServer().getWorld("world");
		Nether = plugin.getServer().getWorld("world_nether");
		End = plugin.getServer().getWorld("world_the_end");
		ZombieWorld = plugin.getServer().getWorld("zombies");
		CityDev = plugin.getServer().getWorld("citydev");
		CreativeWorld = plugin.getServer().getWorld("creative");
	}

	public World getOverworld() {
		return Overworld;
	}

	public World getNether() {
		return Nether;
	}

	public World getEnd() {
		return End;
	}

	public World getZombieWorld() {
		return ZombieWorld;
	}

	public World getCreativeWorld() {
		return CreativeWorld;
	}

	public World getCityDev() {
		return CityDev;
	}

}
