package org.example6.example6.Config;

import java.io.File;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.example6.example6.example6;


public class PlayerConfig extends Config {
	public interface PendingChange
	{
		static final String CLEAR_EXPERIENCE = "clearexp";
		static final String CLEAR_WORN_ITEMS = "clearwornitems";
		static final String CLEAR_INVENTORY = "clearinventory";
	}
	interface PlayerConfigKeys
	{
		static final String KITS = "kits";
		
		static final String PENDING_CHANGES = "pendingchanges";

		static final String SURVIVAL_LOCATION = "location.survival";
		static final String SURVIVAL_LOCATION_WORLD = "location.survival.world";
		static final String SURVIVAL_LOCATION_X = "location.survival.x";
		static final String SURVIVAL_LOCATION_Y = "location.survival.y";
		static final String SURVIVAL_LOCATION_Z = "location.survival.z";
		static final String SURVIVAL_LOCATION_PITCH = "location.survival.pitch";
		static final String SURVIVAL_LOCATION_YAW = "location.survival.yaw";

		static final String ZOMBIES_LOCATION = "location.zombies";
		static final String ZOMBIES_LOCATION_X = "location.zombies.x";
		static final String ZOMBIES_LOCATION_Y = "location.zombies.y";
		static final String ZOMBIES_LOCATION_Z = "location.zombies.z";
		static final String ZOMBIES_LOCATION_PITCH = "location.zombies.pitch";
		static final String ZOMBIES_LOCATION_YAW = "location.zombies.yaw";

		static final String CREATIVE_LOCATION = "location.creative";
		static final String CREATIVE_LOCATION_X = "location.creative.x";
		static final String CREATIVE_LOCATION_Y = "location.creative.y";
		static final String CREATIVE_LOCATION_Z = "location.creative.z";
		static final String CREATIVE_LOCATION_PITCH = "location.creative.pitch";
		static final String CREATIVE_LOCATION_YAW = "location.creative.yaw";
	}

	//make this be player specific
	public PlayerConfig(String player, File dataFolder) {
		super("userdata/" + player.toLowerCase() + ".yml", dataFolder);
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
	
	public void addPendingChange(String change)
	{
		List<String> pendingchanges = this.getStringList(PlayerConfigKeys.PENDING_CHANGES);
		pendingchanges.add(change);
		this.set(PlayerConfigKeys.PENDING_CHANGES, pendingchanges);
		this.save();
	}
	
	public void clearPendingChanges()
	{
		this.set(PlayerConfigKeys.PENDING_CHANGES, null);
		this.save();
	}
	
	public List<String> getPendingChanges()
	{
		return this.getStringList(PlayerConfigKeys.PENDING_CHANGES);
	}

	public Location getSurvivalLocation()
	{
		if (!this.has(PlayerConfigKeys.SURVIVAL_LOCATION))
			return null;
		
		String worldName = this.get(PlayerConfigKeys.SURVIVAL_LOCATION_WORLD, "world").toString();
		World world = null;
		if (worldName.equals("world"))
			world = example6.getWorldManager().getOverworld();
		else if (worldName.equals("world_nether"))
			world = example6.getWorldManager().getNether();
		else if (worldName.equals("world_the_end"))
			world = example6.getWorldManager().getEnd();
		double x = this.getDouble(PlayerConfigKeys.SURVIVAL_LOCATION_X, 0);
		double y = this.getDouble(PlayerConfigKeys.SURVIVAL_LOCATION_Y, 64);
		double z = this.getDouble(PlayerConfigKeys.SURVIVAL_LOCATION_Z, 0);
		float pitch = (float) this.getDouble(PlayerConfigKeys.SURVIVAL_LOCATION_PITCH, 0);
		float yaw = (float) this.getDouble(PlayerConfigKeys.SURVIVAL_LOCATION_YAW, 0);
		
		Location location = new Location(world, x, y, z);
		location.setPitch(pitch);
		location.setYaw(yaw);
		
		return location;
	}
	
	public void setSurvivalLocation(Location location)
	{
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_WORLD, location.getWorld().getName());
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_X, location.getX());
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_Y, location.getY());
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_Z, location.getZ());
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_PITCH, location.getPitch());
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_YAW, location.getYaw());
		this.save();
	}
	
	public void clearSurvivalLocation()
	{
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION, null);
		this.save();
	}

	public Location getZombiesLocation()
	{
		if (!this.has(PlayerConfigKeys.ZOMBIES_LOCATION))
			return null;
		
		double x = this.getDouble(PlayerConfigKeys.ZOMBIES_LOCATION_X, 0);
		double y = this.getDouble(PlayerConfigKeys.ZOMBIES_LOCATION_Y, 64);
		double z = this.getDouble(PlayerConfigKeys.ZOMBIES_LOCATION_Z, 0);
		float pitch = (float) this.getDouble(PlayerConfigKeys.ZOMBIES_LOCATION_PITCH, 0);
		float yaw = (float) this.getDouble(PlayerConfigKeys.ZOMBIES_LOCATION_YAW, 0);
		
		Location location = new Location(
				example6.getWorldManager().getZombieWorld(), x, y, z
				);
		location.setPitch(pitch);
		location.setYaw(yaw);
		
		return location;
	}
	
	public void setZombiesLocation(Location location)
	{
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION_X, location.getX());
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION_Y, location.getY());
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION_Z, location.getZ());
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION_PITCH, location.getPitch());
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION_YAW, location.getYaw());
		this.save();
	}
	
	public void clearZombiesLocation()
	{
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION, null);
		this.save();
	}

	public Location getCreativeLocation()
	{
		if (!this.has(PlayerConfigKeys.CREATIVE_LOCATION))
			return null;
		
		double x = this.getDouble(PlayerConfigKeys.CREATIVE_LOCATION_X, 0);
		double y = this.getDouble(PlayerConfigKeys.CREATIVE_LOCATION_Y, 64);
		double z = this.getDouble(PlayerConfigKeys.CREATIVE_LOCATION_Z, 0);
		float pitch = (float) this.getDouble(PlayerConfigKeys.CREATIVE_LOCATION_PITCH, 0);
		float yaw = (float) this.getDouble(PlayerConfigKeys.CREATIVE_LOCATION_YAW, 0);
		
		Location location = new Location(
				example6.getWorldManager().getCreativeWorld(), x, y, z
				);
		location.setPitch(pitch);
		location.setYaw(yaw);
		
		return location;
	}
	
	public void setCreativeLocation(Location location)
	{
		this.set(PlayerConfigKeys.CREATIVE_LOCATION_X, location.getX());
		this.set(PlayerConfigKeys.CREATIVE_LOCATION_Y, location.getY());
		this.set(PlayerConfigKeys.CREATIVE_LOCATION_Z, location.getZ());
		this.set(PlayerConfigKeys.CREATIVE_LOCATION_PITCH, location.getPitch());
		this.set(PlayerConfigKeys.CREATIVE_LOCATION_YAW, location.getYaw());
		this.save();
	}
	
	public void clearCreativeLocation()
	{
		this.set(PlayerConfigKeys.CREATIVE_LOCATION, null);
		this.save();
	}
}
