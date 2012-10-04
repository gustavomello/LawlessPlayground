package org.example6.example6.Commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.WorldChangeCommand;
import org.example6.example6.Config.PlayerConfig;

public class SurvivalCommand extends WorldChangeCommand {
	
	public SurvivalCommand(example6 plugin) {
		super("survival", "example6.survival", plugin);
	}

	@Override
	public Location getSpawn() {
		return plugin.getMultiverseCore()
				.getMVWorldManager()
				.getMVWorld(plugin.getServer().getWorld("world"))
				.getSpawnLocation();
	}

	@Override
	public Location getLastLocation(Player player) {
		PlayerConfig config = new PlayerConfig(player.getName(), plugin);
		return config.getSurvivalLocation();
	}
}
