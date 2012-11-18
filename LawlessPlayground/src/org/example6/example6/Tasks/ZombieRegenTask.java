package org.example6.example6.Tasks;

import java.util.Calendar;
import java.util.HashMap;

import org.example6.example6.example6;

public class ZombieRegenTask {
	example6 plugin;
	public ZombieRegenTask(example6 plugin) {
		this.plugin = plugin;
	}
	
	public int getChunkCurrentInteractCount(int x, int z)
	{
		int count = 0;
		if (this.plugin.getSerializableData().ChunkCurrentInteractCount.containsKey(x))
		{
			if (this.plugin.getSerializableData().ChunkCurrentInteractCount.containsKey(z))
			{
				return this.plugin.getSerializableData().ChunkCurrentInteractCount.get(x).get(z);
			}
			else
			{
				this.plugin.getSerializableData().ChunkCurrentInteractCount.get(x).put(z, count);
			}
		}
		else
		{
			HashMap<Integer,Integer> value = new HashMap<Integer,Integer>();
			value.put(z, count);
			this.plugin.getSerializableData().ChunkCurrentInteractCount.put(x, value);
		}
		return count;
	}
	
	public void setChunkCurrentInteractCount(int x, int z, int count)
	{
		if (this.plugin.getSerializableData().ChunkCurrentInteractCount.containsKey(x))
		{
			this.plugin.getSerializableData().ChunkCurrentInteractCount.get(x).put(z, count);
		}
		else
		{
			HashMap<Integer,Integer> value = new HashMap<Integer,Integer>();
			value.put(z, count);
			this.plugin.getSerializableData().ChunkCurrentInteractCount.put(x, value);
		}
	}
	
	public long getChunkLastInteract(int x, int z)
	{
		long ctime = Calendar.getInstance().getTimeInMillis();
		if (this.plugin.getSerializableData().ChunkLastInteractTime.containsKey(x))
		{
			if (this.plugin.getSerializableData().ChunkLastInteractTime.containsKey(z))
			{
				return this.plugin.getSerializableData().ChunkLastInteractTime.get(x).get(z);
			}
			else
			{
				this.plugin.getSerializableData().ChunkLastInteractTime.get(x).put(z, ctime);
			}
		}
		else
		{
			HashMap<Integer,Long> value = new HashMap<Integer,Long>();
			value.put(z, ctime);
			this.plugin.getSerializableData().ChunkLastInteractTime.put(x, value);
		}
		return ctime;
	}
	
	public void setChunkLastInteract(int x, int z, long time)
	{
		if (this.plugin.getSerializableData().ChunkLastInteractTime.containsKey(x))
		{
			this.plugin.getSerializableData().ChunkLastInteractTime.get(x).put(z, time);
		}
		else
		{
			HashMap<Integer,Long> value = new HashMap<Integer,Long>();
			value.put(z, time);
			this.plugin.getSerializableData().ChunkLastInteractTime.put(x, value);
		}
	}
	
	public long getChunkLastRefreshTime(int x, int z)
	{
		long ctime = Calendar.getInstance().getTimeInMillis();
		if (this.plugin.getSerializableData().ChunkLastRefreshTime.containsKey(x))
		{
			if (this.plugin.getSerializableData().ChunkLastRefreshTime.containsKey(z))
			{
				return this.plugin.getSerializableData().ChunkLastRefreshTime.get(x).get(z);
			}
			else
			{
				this.plugin.getSerializableData().ChunkLastRefreshTime.get(x).put(z, ctime);
			}
		}
		else
		{
			HashMap<Integer,Long> value = new HashMap<Integer,Long>();
			value.put(z, ctime);
			this.plugin.getSerializableData().ChunkLastRefreshTime.put(x, value);
		}
		return ctime;
	}
	
	public void setChunkLastRefreshTime(int x, int z, long time)
	{
		if (this.plugin.getSerializableData().ChunkLastRefreshTime.containsKey(x))
		{
			this.plugin.getSerializableData().ChunkLastRefreshTime.get(x).put(z, time);
		}
		else
		{
			HashMap<Integer,Long> value = new HashMap<Integer,Long>();
			value.put(z, time);
			this.plugin.getSerializableData().ChunkLastRefreshTime.put(x, value);
		}
	}
	
}
