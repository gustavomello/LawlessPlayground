package org.example6.example6.Models;

import org.bukkit.Location;
import org.bukkit.block.BlockState;

public class BlockChange {

	private BlockState change;
	private Location location;

	public BlockChange(BlockState change, Location location) {
		this.setChange(change);
		this.setLocation(location);
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public BlockState getChange() {
		return change;
	}

	public void setChange(BlockState change) {
		this.change = change;
	}

}
