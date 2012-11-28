package org.example6.example6.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Chunk;
import org.bukkit.configuration.ConfigurationSection;
import org.example6.example6.Models.ChunkGroup;

public class ChunkConfig extends Config {
	
	interface Keys
	{
		static final String CHUNKGROUPS = "chunkgroups";
	}
	
	private Map<String, ChunkGroup> chunkgroups = new HashMap<String, ChunkGroup>();

	public ChunkConfig(File dataFolder) {
		super("zombies/chunks.yml", dataFolder);
		this.loadChunkGroups();
	}

	public ChunkGroup getChunkGroupByChunk(Chunk chunk) {
		for (ChunkGroup group : this.getChunkGroups())
		{
			if (group.hasChunk(chunk)) return group;
		}
		return null;
	}
	
	public ChunkGroup getChunkGroupByName(String name)
	{
		return chunkgroups.get(name);
	}
	
	public List<ChunkGroup> getChunkGroups()
	{
		return new ArrayList<ChunkGroup>(this.chunkgroups.values());
	}
	
	public ChunkGroup createNamedChunkGroup (String name, Chunk base)
	{
		ChunkGroup group = new ChunkGroup(name, 0, Calendar.getInstance().getTimeInMillis(), base);
		this.chunkgroups.put(group.getName(), group);
		this.saveChunkGroups();
		return group;
	}
	
	public ChunkGroup createSingleChunkGroup(Chunk base)
	{
		ChunkGroup group = new ChunkGroup(null, 0, Calendar.getInstance().getTimeInMillis(), base);
		this.chunkgroups.put(group.getName(), group);
		this.saveChunkGroups();
		return group;
	}
	
	public void loadChunkGroups()
	{
		this.chunkgroups.clear();
		ConfigurationSection cs = this.getConfigurationSection(Keys.CHUNKGROUPS);
		if (cs == null) return;
		Map<String,Object> values = cs.getValues(false);
		if (values == null) return;
		for (String key : values.keySet())
		{
			this.chunkgroups.put(key, (ChunkGroup) values.get(key));
		}
	}
	
	public void saveChunkGroups()
	{
		this.createSection(Keys.CHUNKGROUPS, new HashMap<String,Object>(chunkgroups));
		this.save();
	}
	
	public void deleteChunkGroup(String name)
	{
		this.chunkgroups.remove(name);
		this.saveChunkGroups();
	}
}
