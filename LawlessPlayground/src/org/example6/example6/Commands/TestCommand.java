package org.example6.example6.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.ExperienceManager;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;

public class TestCommand extends example6Command {

	public TestCommand(example6 Plugin) {
		super("test", "example6.test", Plugin);
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			ExperienceManager expMan = new ExperienceManager(player);
			expMan.setExp(0);
			player.sendMessage("asdf?");
		}
		return true;
	}

}
