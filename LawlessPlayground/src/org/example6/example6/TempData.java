package org.example6.example6;

import java.util.Calendar;
import java.util.HashMap;

import org.bukkit.World;

public class TempData {
	example6 plugin;
	HashMap<String,Long> lastCombatTimes = new HashMap<String,Long>();
	HashMap<String,Long> lastTeleportTimes = new HashMap<String,Long>();
	World zombies;
	World creative;
	
	public TempData(example6 plugin) {
		this.plugin = plugin;
		this.zombies = plugin.getServer().getWorld("zombies");
		this.creative = plugin.getServer().getWorld("creative");
	}
	
	public World getZombieWorld()
	{
		return this.zombies;
	}
	
	public Boolean IsInCombat(String player)
	{
		return (Calendar.getInstance().getTimeInMillis() <
		this.lastTimeOrZero(lastCombatTimes, player) + 10000);
	}
	
	public void SetLastCombatTime(String player, long time)
	{
		lastCombatTimes.put(player, time);
	}
	
	public long GetLastTeleportTime(String player)
	{
		return this.lastTimeOrZero(lastTeleportTimes, player);
	}
	
	public void SetLastTeleportTime(String player, long time)
	{
		lastTeleportTimes.put(player, time);
	}
	
	private long lastTimeOrZero(HashMap<String,Long> map, String player)
	{
		Long result = map.get(player);
		if (result == null)
		{
			return 0;
		}
		return (long) result;
	}
}
