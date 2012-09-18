package org.example6.example6.Commands;

import org.bukkit.command.CommandSender;

public class KitCommand extends example6Command {

	public KitCommand() {
		super("kit");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		sender.sendMessage("This is a kit message");
		return true;
	}

}
