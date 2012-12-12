package org.example6.example6;

import java.util.Calendar;
import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.example6.example6.Models.ChunkGroup;

public class TempManager {
	example6 plugin;
	HashMap<String,Long> lastCombatTimes = new HashMap<String,Long>();
	HashMap<String,Long> lastTeleportTimes = new HashMap<String,Long>();
	HashMap<Player,ChunkGroup> lastChunkGroup = new HashMap<Player,ChunkGroup>();
	HashMap<Player,Boolean> sparksEnabled = new HashMap<Player,Boolean>();
	World zombies;
	World creative;
	
	public TempManager(example6 plugin) {
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
		this.lastTimeOrZero(lastCombatTimes, player) + 20000);
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
	
	public ChunkGroup GetLastChunkGroup(Player player)
	{
		return this.lastChunkGroup.get(player);
	}
	
	public void SetLastChunkGroup(Player player, ChunkGroup chunk)
	{
		this.lastChunkGroup.put(player, chunk);
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
	
	public Boolean HasSparksEnabled(Player player)
	{
		if (!this.sparksEnabled.containsKey(player))
			return false;
		return this.sparksEnabled.get(player);
	}
	
	public void SetSparksEnabled(Player player, boolean enabled)
	{
		this.sparksEnabled.put(player, enabled);
	}
}
