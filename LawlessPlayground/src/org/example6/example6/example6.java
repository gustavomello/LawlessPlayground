package org.example6.example6;

import org.bukkit.plugin.java.JavaPlugin;
import org.example6.example6.Commands.*;
import org.example6.example6.EventHandlers.ChunkGroupHandler;
import org.example6.example6.EventHandlers.MysteryBoxHandler;
import org.example6.example6.EventHandlers.ServerJoinHandler;
import org.example6.example6.EventHandlers.PVPLogHandler;
import org.example6.example6.EventHandlers.PlayerDisposeHandler;
import org.example6.example6.EventHandlers.GreenTextHandler;
import org.example6.example6.EventHandlers.PluginHandler;
import org.example6.example6.EventHandlers.RandomRespawnHandler;
import org.example6.example6.EventHandlers.SpellCrystalHandler;
import org.example6.example6.EventHandlers.ZombieChatHandler;
import org.example6.example6.EventHandlers.SparksHandler;
import org.example6.example6.EventHandlers.WorldHandler;
import org.example6.example6.EventHandlers.ZombieGameHandler;
import org.example6.example6.Tasks.MobMovement;
import org.example6.example6.Tasks.ZombiesCheckupTask;

public class example6 extends JavaPlugin {
	
	private static TempManager tmp;
	
	private static CommandManager cmd;
	
	private static EventManager evt;
	
	private static ConfigManager cfg;
	
	private static PluginManager plug;
	
	private static WorldManager wrd;

	private static TaskManager task;
	
	public void onEnable()
	{
		tmp = new TempManager(this);
		cmd = new CommandManager(this);
		evt = new EventManager(this);
		wrd = new WorldManager(this);
		task = new TaskManager(this);
		plug = new PluginManager(this);
		cfg = new ConfigManager(this.getDataFolder());
		
		cmd.addCommand(new HomeCommand());
		cmd.addCommand(new SpawnCommand());
		cmd.addCommand(new SurvivalCommand());
		cmd.addCommand(new CreativeCommand());
		cmd.addCommand(new ZombiesCommand());
		cmd.addCommand(new RandomCommand());
		cmd.addCommand(new ModChatCommand());
		cmd.addCommand(new BoxCommand());
		cmd.addCommand(new AdminChatCommand());
		cmd.addCommand(new HelpCommand());
		cmd.addCommand(new RenameCommand());
		cmd.addCommand(new LoreCommand());
		cmd.addCommand(new ZombieChunkCommand());
		cmd.addCommand(new TestCommand());
		cmd.addCommand(new CrashCommand());
		cmd.addCommand(new SparksCommand());
		cmd.addCommand(new VoteCommand());
		cmd.addCommand(new StatsCommand());
		cmd.addCommand(new CrystalCommand());
		cmd.addCommand(new SpellCommand());
		cmd.addCommand(new GrieferCommand());
		cmd.addCommand(new WorldMsgCommand());
		cmd.addCommand(new BookCommand());
		
		//world management / teleportation
		evt.registerHandler(new WorldHandler());
		
		//chat management
		evt.registerHandler(new GreenTextHandler());
		evt.registerHandler(new ZombieChatHandler());
		
		//random
		evt.registerHandler(new SparksHandler());
		evt.registerHandler(new RandomRespawnHandler());
		evt.registerHandler(new PVPLogHandler());
		evt.registerHandler(new MysteryBoxHandler());
		evt.registerHandler(new SpellCrystalHandler());
		
		//background
		evt.registerHandler(new ServerJoinHandler());
		evt.registerHandler(new PlayerDisposeHandler());
		//evt.registerHandler(new PluginHandler());
		
		//zombies
		evt.registerHandler(new ChunkGroupHandler());
		evt.registerHandler(new ZombieGameHandler());
		
		PluginHandler.RegisterOtherPlugins(this.getServer());
		evt.registerHandler(new PluginHandler());
		
		getTaskManager().RunTaskTimer(new MobMovement(), 5L, 5L);
		getTaskManager().RunTaskTimer(new ZombiesCheckupTask(), 1200L, 1200L);
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
	
	public static WorldManager getWorldManager() {
		return wrd;
	}

	public static PluginManager getPlug() {
		return plug;
	}

	public static void setPlug(PluginManager plug) {
		example6.plug = plug;
	}
}
