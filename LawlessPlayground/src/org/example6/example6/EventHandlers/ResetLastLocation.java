package org.example6.example6.EventHandlers;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.example6.example6.example6;

import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDeathHandler;

public class ResetLastLocation extends EntityDeathHandler {

	@Override
	public void run(EntityDeathEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		
		Player player = (Player) event.getEntity();
		PlayerConfig config = example6.getConfigManager().getPlayerConfig(player);
		
		switch (player.getWorld().getName())
		{
			case "world":
			case "world_nether":
			case "world_the_end":
				config.clearSurvivalLocation();
				break;
			case "zombies":
				config.clearZombiesLocation();
				break;
			case "creative":
				config.clearCreativeLocation();
				break;
		}
	}

}
