package org.example6.example6.EventHandlers;

import java.util.Calendar;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.example6.example6.example6;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDamageByEntityHandler;

public class LogLastDamageTimePVP extends EntityDamageByEntityHandler {

	public LogLastDamageTimePVP(example6 plugin) {
		super(plugin);
	}
	
	public void SetLastDamageTimeIfPVP(Entity attacker, Entity defender)
	{
		if (attacker instanceof Player
				&& defender instanceof Player)
		{
			long time = Calendar.getInstance().getTimeInMillis();
			plugin.getTempData().SetLastCombatTime(((Player) attacker).getName(), time);
			plugin.getTempData().SetLastCombatTime(((Player) defender).getName(), time);
		}
	}

	@Override
	public void run(EntityDamageByEntityEvent event) {
		this.SetLastDamageTimeIfPVP(event.getDamager(), event.getEntity());
	}
}
