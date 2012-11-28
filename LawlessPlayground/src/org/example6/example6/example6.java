package org.example6.example6;

import org.bukkit.plugin.java.JavaPlugin;
import org.example6.example6.Commands.*;
import org.example6.example6.EventHandlers.ApplyPendingChanges;
import org.example6.example6.EventHandlers.DenyTeleportIfInCombat;
import org.example6.example6.EventHandlers.DenyTeleportIfTooSoon;
import org.example6.example6.EventHandlers.GreenText;
import org.example6.example6.EventHandlers.HandleChunkGroupBreak;
import org.example6.example6.EventHandlers.HandleChunkGroupPlace;
import org.example6.example6.EventHandlers.HandleLockedChest;
import org.example6.example6.EventHandlers.HandleZombieChunkChanged;
import org.example6.example6.EventHandlers.LogLastDamageTimePVP;
import org.example6.example6.EventHandlers.LoseStuffIfInCombat;
import org.example6.example6.EventHandlers.RandomRespawnIfNoHome;
import org.example6.example6.EventHandlers.RandomizeSpawnIfNew;
import org.example6.example6.EventHandlers.ResetLastLocation;
import org.example6.example6.EventHandlers.SendToNearbyPlayersOnly;
import org.example6.example6.EventHandlers.SetLastLocation;
import org.example6.example6.EventHandlers.Sparks;
import org.example6.example6.EventHandlers.TryCreative;

import com.onarandombox.MultiverseCore.MultiverseCore;

public class example6 extends JavaPlugin {
	
	private static TempManager tmp;
	
	private static CommandManager cmd;
	
	private static EventManager evt;
	
	private static ConfigManager cfg;
	
	private static WorldManager wrd;
	
	private static transient MultiverseCore mvcore;

	private static TaskManager task;
	
	public void onEnable()
	{
		tmp = new TempManager(this);
		cmd = new CommandManager(this);
		evt = new EventManager(this);
		cfg = new ConfigManager(this.getDataFolder());
		wrd = new WorldManager(this);
		task = new TaskManager(this);
		mvcore = (MultiverseCore) getServer().getPluginManager().getPlugin("Multiverse-Core");
		
		cmd.AddCommand(new HomeCommand());
		cmd.AddCommand(new SpawnCommand());
		cmd.AddCommand(new SurvivalCommand());
		cmd.AddCommand(new CreativeCommand());
		cmd.AddCommand(new ZombiesCommand());
		cmd.AddCommand(new RandomCommand());
		cmd.AddCommand(new ModChatCommand());
		cmd.AddCommand(new AdminChatCommand());
		cmd.AddCommand(new HelpCommand());
		cmd.AddCommand(new RenameCommand());
		cmd.AddCommand(new LoreCommand());
		cmd.AddCommand(new ZombieChunkCommand());
		cmd.AddCommand(new TestCommand());
		cmd.AddCommand(new CrashCommand());
		cmd.AddCommand(new SparksCommand());
		
		//stuff for new people
		evt.OnPlayerJoin.add(new TryCreative());
		evt.OnPlayerJoin.add(new RandomizeSpawnIfNew());
		evt.OnPlayerRespawn.add(new RandomRespawnIfNoHome());
		
		//world management / teleportation
		evt.OnTeleport.add(new SetLastLocation());
		evt.OnEntityDeath.add(new ResetLastLocation());
		
		//pvp logging / escape countermeasures 
		evt.OnTeleport.add(new DenyTeleportIfInCombat());
		evt.OnPlayerQuit.add(new LoseStuffIfInCombat());
		evt.OnTeleport.add(new DenyTeleportIfTooSoon());
		evt.OnEntityDamageByEntity.add(new LogLastDamageTimePVP());
		evt.OnPlayerJoin.add(new ApplyPendingChanges());
		
		//random
		evt.OnPlayerChat.add(new GreenText());
		evt.OnPlayerMove.add(new Sparks());
		evt.OnBlockPlace.add(new HandleLockedChest());
		
		//zombies
		evt.OnPlayerChat.add(new SendToNearbyPlayersOnly());
		evt.OnBlockBreak.add(new HandleChunkGroupBreak());
		evt.OnBlockPlace.add(new HandleChunkGroupPlace());
		evt.OnPlayerMove.add(new HandleZombieChunkChanged());		
	}
	
	public void onDisable()
	{
	}
	
	public static TempManager getTempData()
	{
		return tmp;
	}
	
	public static ConfigManager getConfigManager()
	{
		return cfg;
	}
	
	public static TaskManager getTaskManager()
	{
		return task;
	}
	
	public static MultiverseCore getMultiverseCore()
	{
		return mvcore;
	}

	public static WorldManager getWorldManager() {
		return wrd;
	}
}
