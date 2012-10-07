package org.example6.example6.EventHandlers;

import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.example6.example6.example6;

import org.example6.example6.EventHandlers.HandlerTypes.PlayerTeleportHandler;

public class DenyTeleportIfTooSoon extends PlayerTeleportHandler {

	public DenyTeleportIfTooSoon(example6 plugin) {
		super(plugin);
	}

	@Override
	public void run(PlayerTeleportEvent event) {
		if (!event.getCause().equals(TeleportCause.COMMAND))
			return;
		
		Player player = event.getPlayer();
		long difference = Calendar.getInstance().getTimeInMillis()
				- plugin.getTempData().GetLastTeleportTime(player.getName());
		
		if (difference < 5000)
		{
			player.sendMessage(ChatColor.RED + "You must wait " + (5000 - difference) + "ms before teleporting again.");
			event.setCancelled(true);
			return;
		}
		
		plugin.getTempData().SetLastTeleportTime(player.getName(), Calendar.getInstance().getTimeInMillis());
	}
}
