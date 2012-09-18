package org.example6.example6;

import java.awt.List;
import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;
import org.example6.example6.Commands.*;
public class example6 extends JavaPlugin {
	public void onEnable()
	{
		CommandManager cm = new CommandManager(this);
		cm.AddCommand(new KitCommand());
		cm.RegisterCommands();
	}
	
	public void onDisable()
	{
		
	}
}
