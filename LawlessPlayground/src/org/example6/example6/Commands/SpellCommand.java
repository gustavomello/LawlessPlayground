package org.example6.example6.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.PlayerCommand;
import org.example6.example6.Commands.CommandTypes.example6Command;

public class SpellCommand extends example6Command {

	public SpellCommand() {
		super("spell", "example6.spell");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		
		if (args.length < 3) return false;
		if (args[0].equalsIgnoreCase("give"))
		{
			example6.getConfigManager().getOfflinePlayerConfig(args[1]).addSpell(args[2]);
		}
		if (args[0].equalsIgnoreCase("take"))
		{
			example6.getConfigManager().getOfflinePlayerConfig(args[1]).deleteSpell(args[2]);
		}
		
		return true;
	}
}
