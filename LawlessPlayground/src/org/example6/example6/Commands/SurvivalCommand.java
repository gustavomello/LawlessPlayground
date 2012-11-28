package org.example6.example6.Commands;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.WorldChangeCommand;

public class SurvivalCommand extends WorldChangeCommand {
	
	public SurvivalCommand() {
		super("survival", "example6.survival");
	}

	@Override
	public Location getSpawn() {
		return example6.getMultiverseCore()
				.getMVWorldManager()
				.getMVWorld(example6.getWorldManager().getOverworld())
				.getSpawnLocation();
	}

	@Override
	public Location getLastLocation(Player player) {
		return example6.getConfigManager().getPlayerConfig(player)
				.getSurvivalLocation();
	}
}
