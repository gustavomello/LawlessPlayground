package org.example6.example6.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class ChunkGroup implements ConfigurationSerializable {
	private static final long REGEN_MIN_WAIT = 60000;
	private static final long REGEN_WAIT_PER_CHANGE = 30000;
	
	private int changeCount = 0;
	private long lastChange = 0;
	private List<String> chunks = new ArrayList<String>();
	private String groupName;
	private String groupTitle;
	
	public ChunkGroup(String name, int changecount, long lastchange, Chunk base)
	{
		this.lastChange = lastchange;
		this.addChunk(base);
		if (name == null)
		{
			this.setName(base.getX()+";"+base.getZ());
		}
		else
		{
			this.setName(name);
			this.setTitle(name);
		}
	}
	
	public boolean hasTitle() {
		return (this.groupTitle != null);
	}

	public void setTitle(String string) {
		this.groupTitle = string;
	}

	public String getTitle() {
		return this.groupTitle;
	}

	public ChunkGroup(Map<String, Object> serialized) {
		this.deserialize(serialized);
	}
	
	@SuppressWarnings("unchecked")
	public void deserialize(Map<String, Object> serialized) {
		
		if (serialized.containsKey("name"))
		{
			this.setName((String) serialized.get("name"));
		}
		
		if (serialized.containsKey("title") && serialized.get("title") != null)
		{
			this.setTitle((String) serialized.get("title"));
		}
		
		if (serialized.containsKey("changes"))
			this.setChangeCount((int) serialized.get("changes"));
		
		if (serialized.containsKey("lastchange"))
			this.setLastChange((long) serialized.get("lastchange"));
		
		if (serialized.containsKey("chunks"))
			this.chunks = ((List<String>) serialized.get("chunks"));
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String,Object> group = new HashMap<String,Object>();
		group.put("name", this.getName());
		group.put("title", this.getTitle());
		group.put("changes", this.getChangeCount());
		group.put("lastchange", this.getLastChange());
		group.put("chunks", this.chunks);
		
		return group;
	}
	
	public Boolean shouldRegenerate()
	{
		if (this.getChangeCount()==0) return false;
		if (this.getLastChange()==0) return false;
		
		long ctime = Calendar.getInstance().getTimeInMillis();
		if (ctime - this.getLastChange() 
				< REGEN_MIN_WAIT) 
			return false;
		if (ctime - this.getLastChange() 
				< (REGEN_WAIT_PER_CHANGE * this.getChangeCount())) 
			return false;
		
		return true;
	}
		
	public Chunk getBase(World world)
	{
		String[] coords = this.groupName.split(";");
		if (coords.length == 2)
		{
			return world.getChunkAt(
							Integer.parseInt(coords[0]),
							Integer.parseInt(coords[1])
					);
		}
		return null;
	}
	
	public List<Chunk> getChunks(World world) {
		List<Chunk> value = new ArrayList<Chunk>();
		
		for (String chunk : this.chunks)
		{
			String[] coords = chunk.split(";");
			if (coords.length == 2)
			{
				value.add(
						world.getChunkAt(
								Integer.parseInt(coords[0]),
								Integer.parseInt(coords[1]))
						);
			}
		}
		
		return value;
	}
	
	public void addChunk(Chunk chunk)
	{
		String coords = chunk.getX() + ";" + chunk.getZ();
		List<String> coordsList = this.chunks;
		if (!coordsList.contains(coords))
		{
			coordsList.add(coords);
			this.chunks = coordsList;
		}
	}
	
	public void removeChunk(Chunk chunk)
	{
		String coords = chunk.getX() + ";" + chunk.getZ();
		List<String> coordsList = this.chunks;
		if (coordsList.contains(coords))
		{
			coordsList.remove(coords);
			this.chunks = coordsList;
		}
	}

	public int getChangeCount() {
		return changeCount;
	}

	public void setChangeCount(int changeCount) {
		this.changeCount = changeCount;
	}
	
	public long getLastChange() {
		return lastChange;
	}

	public void setLastChange(long lastChange) {
		this.lastChange = lastChange;
	}

	public boolean hasChunk(Chunk chunk) {
		return this.chunks.contains(chunk.getX()+";"+chunk.getZ());
	}

	public void setName(String string) {
		this.groupName = string;
	}
	
	public String getName() {
		return this.groupName;
	}
}
