package org.example6.example6.EventHandlers;

import org.bukkit.event.player.PlayerMoveEvent;
import org.example6.example6.example6;
import org.example6.example6.Config.ChunkConfig;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerMoveHandler;
import org.example6.example6.Models.ChunkGroup;
import org.example6.example6.Utils.MiscUtils;

public class HandleZombieChunkChanged extends PlayerMoveHandler {
	@Override
	public void run(PlayerMoveEvent event) {
		if (event.getTo().getWorld().equals(example6.getWorldManager().getZombieWorld())
				|| event.getTo().getWorld().equals(example6.getWorldManager().getCityDev()))
		{

			ChunkConfig cfg = example6.getConfigManager().getChunkConfig();
			if (event.getTo().getChunk().equals(event.getFrom().getChunk())) return;
			
			ChunkGroup dest = cfg.getChunkGroupByChunk(event.getTo().getChunk());
			
			if (dest == null) return;
			
			if (dest.equals(example6.getTempData().GetLastChunkGroup(event.getPlayer()))) return;
			
			example6.getTempData().SetLastChunkGroup(event.getPlayer(), dest);

			if (!dest.hasTitle()) return;
			
			event.getPlayer().sendMessage(MiscUtils.colorize(dest.getTitle()));
		}
	}

}
