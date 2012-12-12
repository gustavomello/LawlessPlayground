package org.example6.example6.EventHandlers;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;
import org.example6.example6.example6;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerMoveHandler;
import org.example6.example6.Utils.PacketManager;

public class SparksHandler implements PlayerMoveHandler {

	@Override
	public void run(PlayerMoveEvent event) {
		if (event.getPlayer().hasPermission("example6.sparks")
				&& example6.getTempData().HasSparksEnabled(event.getPlayer()))
		{
			int id = 11;
			Location loc = event.getPlayer().getLocation();
			loc.setY(loc.getY()+1);
			PacketManager.SendBlockBreakPacket(loc, id);
		}
	}
}
