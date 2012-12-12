package org.example6.example6;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.CharSet;
import org.bukkit.entity.Player;
import org.example6.example6.Config.ChunkConfig;
import org.example6.example6.Config.PlayerConfig;

import com.google.common.io.Files;

public class ConfigManager {

	private File dataFolder;
	
	private HashMap<String, PlayerConfig> players;
	
	private ChunkConfig chunks;

	public ConfigManager(File dataFolder) {
		this.players = new HashMap<String, PlayerConfig>();
		this.dataFolder = dataFolder;
		this.chunks = new ChunkConfig(this.dataFolder);
	}
	
	public File getDataFolder() {
		return this.dataFolder;
	}

	public ChunkConfig getChunkConfig() {
		return this.chunks;
	}
	
	public PlayerConfig getPlayerConfig(Player player)
	{
		return getPlayerConfig(player.getName());
	}
	
	public Boolean hasPlayerConfig(String name)
	{
		File f = new File(dataFolder + "/userdata/", name + ".yml");
		return f.exists();
	}
	
	public List<String> getBookContents(String name)
	{
		List<String> contents = new ArrayList<String>();
		File d = new File(dataFolder + "/books");
		if (!d.exists())
			d.mkdir();
		File f = new File(dataFolder + "/books", name + ".txt");
		if (f.exists())
		{
			try {
				contents.addAll(Files.readLines(f, Charset.defaultCharset()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return contents;
	}
	
	public PlayerConfig getPlayerConfig(String name)
	{
		if (!this.players.containsKey(name))
			this.players.put(name, new PlayerConfig(name, dataFolder));
		return this.players.get(name);
	}
	
	public void dropPlayerConfig(String name)
	{
		this.players.remove(name);
	}
}
