package org.example6.example6;

import java.util.Calendar;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.example6.example6.Config.PlayerConfig;

public class example6Listener implements Listener {
	private example6 plugin;
	
	public example6Listener(example6 plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void OnTeleport(PlayerTeleportEvent event)
	{
		Player player = event.getPlayer();
		PlayerConfig config = new PlayerConfig(player.getName(), plugin);
		config.setLastTeleport(Calendar.getInstance().getTimeInMillis());
		SaveLastLocation(event, config);
	}
	
	@EventHandler
	public void OnChangedWorld(PlayerChangedWorldEvent event)
	{
	}

	private void SaveLastLocation(PlayerTeleportEvent event, PlayerConfig config) {
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
