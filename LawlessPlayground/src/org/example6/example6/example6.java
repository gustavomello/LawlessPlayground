package org.example6.example6;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.example6.example6.Commands.*;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.EventHandlers.ApplyPendingChanges;
import org.example6.example6.EventHandlers.DenyTeleportIfInCombat;
import org.example6.example6.EventHandlers.DenyTeleportIfTooSoon;
import org.example6.example6.EventHandlers.LogLastDamageTimePVP;
import org.example6.example6.EventHandlers.LoseStuffIfInCombat;
import org.example6.example6.EventHandlers.ResetLastLocation;
import org.example6.example6.EventHandlers.SetLastLocation;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.multiverseinventories.MultiverseInventories;
public class example6 extends JavaPlugin {
	
	TempData data;
	
	CommandManager cm;
	
	EventManager em;
	
	public void onEnable()
	{
		data = new TempData(this);
		
		cm = new CommandManager(this);
		cm.AddCommand(new TestCommand(this));
		cm.AddCommand(new SpawnCommand(this));
		cm.AddCommand(new SurvivalCommand(this));
		cm.AddCommand(new CreativeCommand(this));
		cm.AddCommand(new ZombiesCommand(this));
		cm.AddCommand(new TryCreativeCommand(this));
		cm.AddCommand(new RandomCommand(this));
		cm.AddCommand(new ModChatCommand(this));
		cm.AddCommand(new AdminChatCommand(this));
		//cm.AddCommand(new WhisperCommand(this));
		//cm.AddCommand(new KitCommand(this));
		//cm.AddCommand(new HomeCommand(this));
		
		em = new EventManager(this);
		em.OnTeleport.add(new SetLastLocation(this));
		em.OnTeleport.add(new DenyTeleportIfInCombat(this));
		em.OnTeleport.add(new DenyTeleportIfTooSoon(this));
		em.OnEntityDamageByEntity.add(new LogLastDamageTimePVP(this));
		em.OnEntityDeath.add(new ResetLastLocation(this));
		em.OnPlayerJoin.add(new ApplyPendingChanges(this));
		em.OnPlayerQuit.add(new LoseStuffIfInCombat(this));
	}
	
	public void onDisable()
	{
		
	}
	
	public TempData getTempData()
	{
		return data;
	}
	
	public PlayerConfig getPlayerConfig(Player player)
	{
		return getPlayerConfig(player.getName());
	}
	
	public PlayerConfig getPlayerConfig(String name)
	{
		return new PlayerConfig(name, this);
	}
	
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
}
