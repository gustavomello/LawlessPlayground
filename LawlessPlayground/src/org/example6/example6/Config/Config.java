package org.example6.example6.Config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
	private String filename;
	private FileConfiguration config;
	private JavaPlugin plugin;
	public Config(String inFilename, JavaPlugin inPlugin)
	{
		filename = inFilename;
		plugin = inPlugin;
		config = load(filename, plugin);
		config.options().copyDefaults(true);
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
	
	public void save()
	{
		save(config, filename, plugin);
	}
	
	public void load()
	{
		config = load(filename, plugin);
	}
	
	private static FileConfiguration load(String _filename, JavaPlugin _plugin) {
	    File customConfigFile = new File(_plugin.getDataFolder(), _filename);
	    FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = _plugin.getResource(_filename);
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        customConfig.setDefaults(defConfig); 
	        if (!customConfigFile.exists())
		    {
		    	_plugin.getLogger().info("No config file " + _filename + " exists, creating it now.");
		    	save(defConfig, _filename, _plugin);
			    customConfig = defConfig;
		    }
	    }
	    
	    return customConfig;
	}
	
	private static void save(FileConfiguration customConfig, String _filename, JavaPlugin _plugin) {
	    File customConfigFile = new File(_plugin.getDataFolder(), _filename);
	    if (customConfig == null || customConfigFile == null) {
	    	return;
	    }
	    try {
	        customConfig.save(customConfigFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
	    }
	}
}
