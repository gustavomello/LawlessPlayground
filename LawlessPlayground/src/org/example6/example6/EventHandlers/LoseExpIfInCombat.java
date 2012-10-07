package org.example6.example6.EventHandlers;

import org.bukkit.World;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.example6.example6.example6;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerQuitHandler;

public class LoseExpIfInCombat extends PlayerQuitHandler {

	public LoseExpIfInCombat(example6 plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(PlayerQuitEvent event) {
		// TODO Auto-generated method stub
		Player player = event.getPlayer();
		World world = player.getWorld();
		if (plugin.getTempData().IsInCombat(player.getName()))
		{
			int exp = (int) Math.round(player.getExp());
			player.setExp(0);
			plugin.getServer().broadcastMessage("exp was " + exp);
			world.spawn(player.getLocation(), ExperienceOrb.class).setExperience(exp);
		}
	}

}
