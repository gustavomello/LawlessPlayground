package org.example6.example6.Commands;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.WorldChangeCommand;

public class CreativeCommand extends WorldChangeCommand {

	public CreativeCommand() {
		super("creative", "example6.creative");
	}

	@Override
	public Location getSpawn() {
		return example6.getPlug().getMvcore()
				.getMVWorldManager()
				.getMVWorld(example6.getWorldManager().getCreativeWorld())
				.getSpawnLocation();
	}

	@Override
	public Location getLastLocation(Player player) {
		return example6.getConfigManager().getPlayerConfig(player)
				.getCreativeLocation();
	}

	@Override
	public boolean runAsCommand(Player player, String command, String alias,
			String[] args) {
		// TODO Auto-generated method stub
		return false;
	}
}
