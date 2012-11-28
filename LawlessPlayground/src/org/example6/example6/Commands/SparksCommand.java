package org.example6.example6.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;

public class SparksCommand extends example6Command {

	public SparksCommand() {
		super("sparks", "example6.sparks");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (sender instanceof Player)
		{
			Player player = (Player)sender;
			boolean enabled = example6.getTempData().HasSparksEnabled(player);
			if (enabled)
				example6.getTempData().SetSparksEnabled(player, false);
			else
				example6.getTempData().SetSparksEnabled(player, true);
		}
		return true;
	}

}
