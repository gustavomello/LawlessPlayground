package org.example6.example6.EventHandlers;

import java.util.Calendar;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.example6.example6.example6;
import org.example6.example6.Config.PlayerConfig.PendingChange;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerJoinHandler;
import org.example6.example6.Utils.ExperienceManager;

public class ApplyPendingChanges extends PlayerJoinHandler {

	public ApplyPendingChanges(example6 plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		List<String> changes = plugin.getPlayerConfig(player).getPendingChanges();
		
		if (changes.size()==0) return;
		
		for (String change : changes)
		{			
			switch (change)
			{
				case PendingChange.CLEAR_EXPERIENCE:
					ExperienceManager expMan = new ExperienceManager(player);
					expMan.setExp(0);
					player.setTotalExperience(0);
					plugin.getTempData().SetLastCombatTime(player.getName(),  Calendar.getInstance().getTimeInMillis());
					player.sendMessage(ChatColor.RED + "You logged off while in combat, and ended up losing all your experience!");
				break;
				case PendingChange.CLEAR_WORN_ITEMS:
					player.getInventory().setArmorContents(null);
					plugin.getTempData().SetLastCombatTime(player.getName(),  Calendar.getInstance().getTimeInMillis());
					player.sendMessage(ChatColor.RED + "You logged off while in combat, and ended up losing your worn items!");
				break;
				case PendingChange.CLEAR_INVENTORY:
					player.getInventory().clear();
					plugin.getTempData().SetLastCombatTime(player.getName(),  Calendar.getInstance().getTimeInMillis());
					player.sendMessage(ChatColor.RED + "You logged off while in combat, and ended up losing your entire inventory!");
				break;
			}
		}
		plugin.getPlayerConfig(player).clearPendingChanges();
	}
}
