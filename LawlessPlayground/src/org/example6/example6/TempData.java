package org.example6.example6;

import java.util.HashMap;

public class TempData {
	example6 plugin;
	HashMap<String,Long> lastCombatTime = new HashMap<String,Long>();
	
	public TempData(example6 plugin) {
		this.plugin = plugin;
	}
	
	public long GetLastCombatTime(String player)
	{
		Long result = lastCombatTime.get(player);
		if (result == null)
		{
			return 0;
		}
		return (long) result;
	}
	
	public void SetLastCombatTime(String player, long time)
	{
		lastCombatTime.put(player, time);
	}
}
