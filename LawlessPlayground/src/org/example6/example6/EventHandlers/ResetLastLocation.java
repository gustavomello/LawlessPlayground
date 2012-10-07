package org.example6.example6.EventHandlers;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.example6.example6.example6;

import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDeathHandler;

public class ResetLastLocation extends EntityDeathHandler {

	public ResetLastLocation(example6 plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(EntityDeathEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		
		Player player = (Player) event.getEntity();
		PlayerConfig config = plugin.getPlayerConfig(player);
		
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
