package org.example6.example6;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.example6.example6.Commands.example6Command;

public class CommandManager implements CommandExecutor {
	private example6 plugin;
	private List<example6Command> commands;
	
	public CommandManager(example6 _plugin)
	{
		plugin = _plugin;
		commands = new ArrayList<example6Command>();
	}
	
	public void AddCommand(example6Command command)
	{
		commands.add(command);
	}
	
	public void RegisterCommands()
	{
		for (example6Command command : commands)
		{
			plugin.getCommand(command.getName()).setExecutor(this);
		}
	}
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}
}
