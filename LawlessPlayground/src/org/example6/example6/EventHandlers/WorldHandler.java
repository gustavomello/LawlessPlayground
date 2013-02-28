package org.example6.example6.EventHandlers;

import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.example6.example6.example6;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDeathHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerTeleportHandler;

public class WorldHandler
	implements
	PlayerTeleportHandler,
	EntityDeathHandler {

	//resetlastlocation
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

	//set last location
	//deny teleport if too soon
	@Override
	public void run(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		
		if (!event.getFrom().equals(event.getTo()))
		{
			logLastLocation(event, example6.getConfigManager().getPlayerConfig(player));
		}
		
		if (!event.getCause().equals(TeleportCause.COMMAND))
			return;
		
		long difference = getTimeSinceLastTeleport(player);
		if (difference < 5000 && !player.hasPermission("example6.bypass"))
		{
			player.sendMessage(ChatColor.RED + "You must wait " + (5000 - difference) + "ms before teleporting again.");
			event.setCancelled(true);
			return;
		}
		
		example6.getTempData().SetLastTeleportTime(player.getName(), Calendar.getInstance().getTimeInMillis());
	}

	public long getTimeSinceLastTeleport(Player player) {
		long difference = Calendar.getInstance().getTimeInMillis()
				- example6.getTempData().GetLastTeleportTime(player.getName());
		return difference;
	}

	public void logLastLocation(PlayerTeleportEvent event, PlayerConfig config) {
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
