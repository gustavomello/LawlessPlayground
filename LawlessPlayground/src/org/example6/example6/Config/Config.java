package org.example6.example6.Config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Server;
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
		
		saveDefaults();
		reload();

		this.config.options().copyDefaults(true);
	}
	
	public Server getServer()
	{
		return plugin.getServer();
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
	
	private YamlConfiguration getDefaultConfig()
	{
	    InputStream defConfigStream = this.plugin.getResource(this.filename);
	    if (defConfigStream == null)
	    	return null;
	    else
	    	return YamlConfiguration.loadConfiguration(defConfigStream);
	}

	private void saveDefaults() {
	    File configFile = new File(this.plugin.getDataFolder(), this.filename);
	    YamlConfiguration defConfig = getDefaultConfig();
	    
	    if (defConfig == null) return;
	    
	    if (!configFile.exists())
	    {
	    	this.plugin.getLogger().info("No config file " + this.filename + " exists, creating it now.");
		    this.config = defConfig;
	    	this.save();
	    }
	    this.config.setDefaults(defConfig);
	}
	
	public void reload() {
	    this.config = YamlConfiguration.loadConfiguration(new File(this.plugin.getDataFolder(), this.filename));
	}
	
	public void save() {
	    File customConfigFile = new File(this.plugin.getDataFolder(), this.filename);
	    if (config != null && customConfigFile != null) {
		    try {
		    	config.save(customConfigFile);
		    } catch (IOException ex) {
		        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
		    }
	    }
	}
}
