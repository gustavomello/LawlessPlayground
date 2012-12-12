package org.example6.example6.Commands;

import java.util.Calendar;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.WorldChangeCommand;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.EventHandlers.ZombieStatsHandler;

public class ZombiesCommand extends WorldChangeCommand {

	public ZombiesCommand() {
		super("zombies", "example6.zombies");
	}

	@Override
	public Location getSpawn() {
		return example6.getPlug().getMvcore()
				.getMVWorldManager()
				.getMVWorld(example6.getWorldManager().getZombieWorld())
				.getSpawnLocation();
	}

	@Override
	public Location getLastLocation(Player player) {
		return example6.getConfigManager().getPlayerConfig(player).getZombiesLocation();
	}

	@Override
	public boolean runAsCommand(Player player, String command, String alias,
			String[] args) {
		if (args.length > 0)
		{
			if (args[0].equalsIgnoreCase("time"))
			{
				PlayerConfig cfg = example6.getConfigManager().getPlayerConfig(player);
				
				long lifetime = cfg.getZombiesLifeTime() + 
						(Calendar.getInstance().getTimeInMillis()
								- cfg.getZombiesLastEnter());
				
				double seconds = lifetime / 1000;
				
				ZombieStatsHandler.SendSurvivalTimeMessage(player, seconds);
				
				return true;
			}
			if (args[0].equalsIgnoreCase("kills"))
			{
				PlayerConfig cfg = example6.getConfigManager().getPlayerConfig(player);
				
				int kills = cfg.getZombiesKills();
				
				ZombieStatsHandler.SendKillsMessage(player, kills);
				
				return true;
			}
		}
		return false;
	}
}
