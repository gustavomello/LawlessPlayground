package org.example6.example6.Commands.CommandTypes;

import org.bukkit.command.CommandSender;
import org.example6.example6.example6;

public abstract class example6Command {
	public String Name;
	public String permission;
	public example6 plugin;
	public example6Command(String name, String permission, example6 plugin)
	{
		this.Name = name;
		this.permission = permission;
		this.plugin = plugin;
	}
	public String getName()
	{
		return this.Name;
	}
	
	public abstract Boolean Run(CommandSender sender, String command, String alias, String[] args);
}
