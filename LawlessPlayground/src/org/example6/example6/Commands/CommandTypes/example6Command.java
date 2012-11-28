package org.example6.example6.Commands.CommandTypes;

import org.bukkit.command.CommandSender;

public abstract class example6Command {
	public String Name;
	public String permission;
	public example6Command(String name, String permission)
	{
		this.Name = name;
		this.permission = permission;
	}
	public String getName()
	{
		return this.Name;
	}
	
	public abstract Boolean Run(CommandSender sender, String command, String alias, String[] args);
}
