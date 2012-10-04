package org.example6.example6.Config;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;


public class PlayerConfig extends Config {
	interface PlayerConfigKeys
	{
		static final String KITS = "kits";
		
		static final String LAST_TELEPORT = "lastteleport";

		static final String SURVIVAL_LOCATION = "location.survival";
		static final String SURVIVAL_LOCATION_WORLD = "location.survival.world";
		static final String SURVIVAL_LOCATION_X = "location.survival.x";
		static final String SURVIVAL_LOCATION_Y = "location.survival.y";
		static final String SURVIVAL_LOCATION_Z = "location.survival.z";

		static final String ZOMBIES_LOCATION = "location.zombies";
		static final String ZOMBIES_LOCATION_X = "location.zombies.x";
		static final String ZOMBIES_LOCATION_Y = "location.zombies.y";
		static final String ZOMBIES_LOCATION_Z = "location.zombies.z";

		static final String CREATIVE_LOCATION = "location.creative";
		static final String CREATIVE_LOCATION_X = "location.creative.x";
		static final String CREATIVE_LOCATION_Y = "location.creative.y";
		static final String CREATIVE_LOCATION_Z = "location.creative.z";
	}

	//make this be player specific
	public PlayerConfig(String player, JavaPlugin inPlugin) {
		super("userdata/" + player.toLowerCase() + ".yml", inPlugin);
	}
	
	public List<String> getKits()
	{
		return this.getStringList(PlayerConfigKeys.KITS);
	}
	
	public void setKits(List<String> newKits)
	{
		this.set(PlayerConfigKeys.KITS, newKits);
		this.save();
	}

	public Location getSurvivalLocation()
	{
		if (!this.has(PlayerConfigKeys.SURVIVAL_LOCATION))
			return null;
		
		String worldName = this.get(PlayerConfigKeys.SURVIVAL_LOCATION_WORLD, "world").toString();
		World world = this.getServer().getWorld(worldName);
		double x = this.getDouble(PlayerConfigKeys.SURVIVAL_LOCATION_X, 0);
		double y = this.getDouble(PlayerConfigKeys.SURVIVAL_LOCATION_Y, 64);
		double z = this.getDouble(PlayerConfigKeys.SURVIVAL_LOCATION_Z, 0);
		
		return new Location(world, x, y, z);
	}
	
	public void setSurvivalLocation(Location location)
	{
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_WORLD, location.getWorld().getName());
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_X, location.getX());
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_Y, location.getY());
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_Z, location.getZ());
		this.save();
	}

	public Location getZombiesLocation()
	{
		if (!this.has(PlayerConfigKeys.ZOMBIES_LOCATION))
			return null;
		
		World world = this.getServer().getWorld("zombies");
		double x = this.getDouble(PlayerConfigKeys.ZOMBIES_LOCATION_X, 0);
		double y = this.getDouble(PlayerConfigKeys.ZOMBIES_LOCATION_Y, 64);
		double z = this.getDouble(PlayerConfigKeys.ZOMBIES_LOCATION_Z, 0);
		
		return new Location(world, x, y, z);
	}
	
	public void setZombiesLocation(Location location)
	{
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION_X, location.getX());
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION_Y, location.getY());
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION_Z, location.getZ());
		this.save();
	}

	public Location getCreativeLocation()
	{
		if (!this.has(PlayerConfigKeys.CREATIVE_LOCATION))
			return null;
		
		World world = this.getServer().getWorld("creative");
		double x = this.getDouble(PlayerConfigKeys.CREATIVE_LOCATION_X, 0);
		double y = this.getDouble(PlayerConfigKeys.CREATIVE_LOCATION_Y, 64);
		double z = this.getDouble(PlayerConfigKeys.CREATIVE_LOCATION_Z, 0);
		
		return new Location(world, x, y, z);
	}
	
	public void setCreativeLocation(Location location)
	{
		this.set(PlayerConfigKeys.CREATIVE_LOCATION_X, location.getX());
		this.set(PlayerConfigKeys.CREATIVE_LOCATION_Y, location.getY());
		this.set(PlayerConfigKeys.CREATIVE_LOCATION_Z, location.getZ());
		this.save();
	}
	
	public long getLastTeleport()
	{
		long time = this.getLong(PlayerConfigKeys.LAST_TELEPORT, 0);
		return time;
	}
	
	public void setLastTeleport(long time)
	{
		this.set(PlayerConfigKeys.LAST_TELEPORT, time);
		this.save();
	}
}
