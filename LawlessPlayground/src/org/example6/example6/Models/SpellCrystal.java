package org.example6.example6.Models;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.example6.example6.example6;
import org.example6.example6.Utils.EntityManager;

public class SpellCrystal {
	private Entity crystal;
	private Location location;
	private String name;
	private BukkitRunnable respawnTask;
	
	public SpellCrystal(String name, Location location) {
		this.name = name;
		this.location = location;
		this.location.getChunk().load();
	}

	public void spawn()
	{
		this.despawn();
		crystal = EntityManager.SpawnEnderCrystal(
				this.location
				);
		crystal.setMetadata("spellname", new FixedMetadataValue(example6.getPlug().getExample6(), this.name));
	}
	
	public void applyMetadata()
	{
		crystal.setMetadata("spellname", new FixedMetadataValue(example6.getPlug().getExample6(), this.name));
	}
	
	public void despawn()
	{
		if (this.respawnTask != null)
		{
			this.respawnTask.cancel();
			this.respawnTask = null;
		}
		
		if (crystal != null)
		{
			crystal.remove();
			crystal = null;
		}
	}
	
	public void scheduleRespawn()
	{
		this.respawnTask = new BukkitRunnable()
		{
			@Override
			public void run() {
				spawn();
			}
		};
		example6.getTaskManager().RunTaskLater(this.respawnTask, 600L);
	}
	
	public Entity getCrystal()
	{
		return this.crystal;
	}
	
	public void setCrystal(Entity crystal)
	{
		this.crystal = crystal;
	}
	
	public Location getLocation()
	{
		return this.location;
	}
	
	public String getName()
	{
		return this.name;
	}

	public BukkitRunnable getRespawnTask() {
		return respawnTask;
	}
}
