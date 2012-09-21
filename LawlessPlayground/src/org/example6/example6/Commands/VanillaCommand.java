package org.example6.example6.Commands;

import org.bukkit.command.CommandSender;
import org.example6.example6.example6;

public class VanillaCommand extends example6Command {

	public VanillaCommand(example6 plugin) {
		super("vanilla", "example6.vanilla", plugin);
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		return null;
	}

}
