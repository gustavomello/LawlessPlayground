package org.example6.example6;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.example6.example6.Commands.*;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.multiverseinventories.MultiverseInventories;
public class example6 extends JavaPlugin {
	public MultiverseCore getMultiverseCore()
	{
		Plugin plugin = getServer().getPluginManager().getPlugin("Multiverse-Core");
		 
        if (plugin instanceof MultiverseCore) {
            return (MultiverseCore) plugin;
        }
 
        throw new RuntimeException("MultiVerse not found!");
	}
	public MultiverseInventories getMultiverseInventories()
	{
		Plugin plugin = getServer().getPluginManager().getPlugin("Multiverse-Inventories");
		 
        if (plugin instanceof MultiverseInventories) {
            return (MultiverseInventories) plugin;
        }
 
        throw new RuntimeException("MultiverseInventories not found!");
	}
	
	public void onEnable()
	{
		CommandManager cm = new CommandManager(this);
		//cm.AddCommand(new KitCommand(this));
		cm.AddCommand(new TestCommand(this));
		cm.AddCommand(new HomeCommand(this));
		cm.AddCommand(new SpawnCommand(this));
		cm.AddCommand(new SurvivalCommand(this));
		cm.AddCommand(new CreativeCommand(this));
		cm.AddCommand(new ZombiesCommand(this));
		
		getServer().getPluginManager().registerEvents(new example6Listener(this), this);
	}
	
	public void onDisable()
	{
		
	}
}
