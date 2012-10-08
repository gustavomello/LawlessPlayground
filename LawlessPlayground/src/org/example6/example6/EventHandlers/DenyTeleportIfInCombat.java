package org.example6.example6.EventHandlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.example6.example6.example6;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerTeleportHandler;


public class DenyTeleportIfInCombat extends PlayerTeleportHandler {
	public static final String TELEPORT_DENIED_STRING = "You appear to be in combat.";

	public DenyTeleportIfInCombat(example6 plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(PlayerTeleportEvent event) {
		if (!event.getCause().equals(TeleportCause.COMMAND))
			return;
		
		Player player = event.getPlayer();
		if (plugin.getTempData().IsInCombat(player.getName()))
		{
			event.setCancelled(true);
			player.sendMessage(ChatColor.RED + TELEPORT_DENIED_STRING);
		}
	}
}
