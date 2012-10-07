package org.example6.example6.EventHandlers;

import java.util.Calendar;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.example6.example6.example6;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDamageByEntityHandler;

public class LogLastDamageTime extends EntityDamageByEntityHandler {

	public LogLastDamageTime(example6 plugin) {
		super(plugin);
	}
	
	public void SetLastDamageTimeIfPlayer(Entity entity)
	{
		if (entity instanceof Player)
		{
			Player player = (Player) entity;
			plugin.getTempData().SetLastCombatTime(player.getName(), Calendar.getInstance().getTimeInMillis());
		}
	}

	@Override
	public void run(EntityDamageByEntityEvent event) {
		this.SetLastDamageTimeIfPlayer(event.getEntity());
		this.SetLastDamageTimeIfPlayer(event.getDamager());
	}

}
