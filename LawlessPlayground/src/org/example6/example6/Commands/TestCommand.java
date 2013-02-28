package org.example6.example6.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;
import org.example6.example6.Models.ItemDisplay;

public class TestCommand extends example6Command {
	
	public TestCommand() {
		super("test", "example6.test");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if (p.hasMetadata("testvalue"))
			{
				MetadataValue v = p.getMetadata("testvalue").get(0);
				System.out.println("Had value: " + v.asString());
			}
			else
			{
				p.setMetadata("testvalue", new FixedMetadataValue(example6.getPlug().getExample6(), "lol"));
			}
		}
		return true;
	}
}