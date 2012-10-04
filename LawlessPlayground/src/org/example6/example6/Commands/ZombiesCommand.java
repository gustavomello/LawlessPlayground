package org.example6.example6.Commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.WorldChangeCommand;
import org.example6.example6.Config.PlayerConfig;

public class ZombiesCommand extends WorldChangeCommand {

	public ZombiesCommand(example6 plugin) {
		super("zombies", "example6.zombies", plugin);
	}

	@Override
	public Location getSpawn() {
		return plugin.getMultiverseCore()
				.getMVWorldManager()
				.getMVWorld(plugin.getServer().getWorld("zombies"))
				.getSpawnLocation();
	}

	@Override
	public Location getLastLocation(Player player) {
		PlayerConfig config = new PlayerConfig(player.getName(), plugin);
		return config.getZombiesLocation();
	}
}
