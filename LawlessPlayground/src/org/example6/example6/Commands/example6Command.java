package org.example6.example6.Commands;

import org.bukkit.command.CommandSender;
import org.example6.example6.example6;

public abstract class example6Command {
	String Name;
	String Permission;
	example6 Plugin;
	public example6Command(String name, String permission, example6 plugin)
	{
		this.Name = name;
		this.Permission = permission;
		this.Plugin = plugin;
	}
	public String getName()
	{
		return this.Name;
	}
	public abstract Boolean Run(CommandSender sender, String command, String alias, String[] args);
}
