package org.example6.example6.EventHandlers;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.example6.example6.example6;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerTeleportHandler;

public class SetLastLocation extends PlayerTeleportHandler {
	public SetLastLocation(example6 plugin) {
		super(plugin);
	}

	@Override
	public void run(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		PlayerConfig config = plugin.getPlayerConfig(player);
		
		switch (event.getFrom().getWorld().getName())
		{
			case "world":
			case "world_nether":
			case "world_the_end":
				config.setSurvivalLocation(event.getFrom());
				break;
			case "zombies":
				config.setZombiesLocation(event.getFrom());
				break;
			case "creative":
				config.setCreativeLocation(event.getFrom());
				break;
		}
	}
}
