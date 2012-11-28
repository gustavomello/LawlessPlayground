package org.example6.example6.Models;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class EntityChange {
	
	private Entity entity;
	private Location location;

	public EntityChange(Entity entity, Location location) {
		this.entity = entity;
		this.location = location;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
