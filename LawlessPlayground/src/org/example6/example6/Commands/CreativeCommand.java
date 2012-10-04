package org.example6.example6.Commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.WorldChangeCommand;
import org.example6.example6.Config.PlayerConfig;

public class CreativeCommand extends WorldChangeCommand {

	public CreativeCommand(example6 plugin) {
		super("creative", "example6.creative", plugin);
	}

	@Override
	public Location getSpawn() {
		return plugin.getMultiverseCore()
				.getMVWorldManager()
				.getMVWorld(plugin.getServer().getWorld("creative"))
				.getSpawnLocation();
	}

	@Override
	public Location getLastLocation(Player player) {
		PlayerConfig config = new PlayerConfig(player.getName(), plugin);
		return config.getCreativeLocation();
	}
}
