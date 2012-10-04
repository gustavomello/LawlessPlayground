package org.example6.example6;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.example6.example6.Commands.CommandTypes.example6Command;

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
		PluginCommand serverCommand = plugin.getCommand(command.getName());
		serverCommand.setExecutor(this);
	}
	
	public void RemoveCommand(example6Command command)
	{
		commands.remove(command);
		plugin.getCommand(command.getName()).setExecutor(null);
	}
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		for (example6Command command : commands)
		{
			if (command.getName().equalsIgnoreCase(arg1.getName()))
			{
				String name = arg1.getName();
				return command.Run(arg0, name, arg2, arg3);
			}
		}
		return false;
	}
}
