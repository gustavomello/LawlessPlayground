package org.example6.example6.Config;

import java.util.Arrays;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;


public class PlayerConfig extends Config {
	interface PlayerConfigKeys
	{
		static final String KITS = "kits";
		static final String VANILLA_LOCATION = "location.vanilla";
		static final String ZOMBIES_LOCATION = "location.zombies";
		static final String CREATIVE_LOCATION = "location.creative";
	}

	//make this be player specific
	//now
	public PlayerConfig(String player, JavaPlugin inPlugin) {
		super(player.toLowerCase() + ".yml", inPlugin);
	}
	
	public List<String> getKits()
	{
		return this.getStringList(PlayerConfigKeys.KITS);
	}
	
	public void setKits(List<String> newKits)
	{
		this.set(PlayerConfigKeys.KITS, newKits);
		this.save();
	}

}
