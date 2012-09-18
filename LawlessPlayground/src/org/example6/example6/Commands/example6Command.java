package org.example6.example6.Commands;

import org.bukkit.command.CommandSender;

public abstract class example6Command {
	String Name;
	public example6Command(String name)
	{
		this.Name = name;
	}
	public String getName()
	{
		return this.Name;
	}
	public abstract Boolean Run(CommandSender sender, String command, String alias, String[] args);
}
