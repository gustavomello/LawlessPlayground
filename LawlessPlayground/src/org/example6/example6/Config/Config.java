package org.example6.example6.Config;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import org.example6.example6.Models.ChunkGroup;

public class Config {
	private String filename;
	private FileConfiguration config;
	private File dataFolder;
	public Config(String inFilename, File dataFolder)
	{
		ConfigurationSerialization.registerClass(ChunkGroup.class);
		
		this.filename = inFilename;
		this.dataFolder = dataFolder;
		
		reload();

		this.config.options().copyDefaults(true);
	}
	
	public void createSection(String path, Map<String, Object> map)
	{
		config.createSection(path, map);
	}
	
	public void set(String path, Object value)
	{
		config.set(path, value);
	}
	
	public Boolean has(String path)
	{
		return (config.get(path, null) != null);
	}
	
	public Object get(String path, Object defaultValue)
	{
		return config.get(path, defaultValue);
	}

	public List<?> getList(String string) {
		return config.getList(string);
	}
	
	public ConfigurationSection getConfigurationSection(String string) {
		return config.getConfigurationSection(string);
	}
	
	public List<String> getStringList(String path)
	{
		return config.getStringList(path);
	}
	
	public Boolean getBoolean(String path)
	{
		return config.getBoolean(path);
	}
	
	public long getLong(String path, long defaultNumber)
	{
		return config.getLong(path, defaultNumber);
	}
	
	public double getDouble(String path, double defaultNumber)
	{
		return config.getDouble(path, defaultNumber);
	}
	
	public void reload() {
	    this.config = YamlConfiguration.loadConfiguration(new File(this.dataFolder, this.filename));
	}
	
	public void save() {
	    File customConfigFile = new File(this.dataFolder, this.filename);
	    if (config != null && customConfigFile != null) {
		    try {
		    	config.save(customConfigFile);
		    } catch (IOException ex) {
		        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
		    }
	    }
	}
}
