package org.example6.example6;

import java.io.File;

import org.bukkit.entity.Player;
import org.example6.example6.Config.ChunkConfig;
import org.example6.example6.Config.PlayerConfig;

public class ConfigManager {

	private File dataFolder;
	
	private ChunkConfig chunks;

	public ConfigManager(File dataFolder) {
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
	
	public PlayerConfig getPlayerConfig(String name)
	{
		return new PlayerConfig(name, dataFolder);
	}
}
