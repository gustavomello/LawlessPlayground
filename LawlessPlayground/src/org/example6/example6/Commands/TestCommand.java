package org.example6.example6.Commands;

import org.bukkit.command.CommandSender;
import org.example6.example6.example6;

public class TestCommand extends example6Command {

	public TestCommand(example6 Plugin) {
		super("test", "example6.test", Plugin);
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		// TODO Auto-generated method stub
		return true;
	}

}
