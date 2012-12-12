package org.example6.example6.Commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftItemFrame;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.example6.example6.Commands.CommandTypes.example6Command;
import org.example6.example6.Models.ColoredArmor;
import org.example6.example6.Utils.Book;
import org.example6.example6.Utils.InventoryManager;
import org.example6.example6.Utils.MiscUtils;

public class TestCommand extends example6Command {
	
	public TestCommand() {
		super("test", "example6.test");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (sender instanceof Player)
		{
		}
		return true;
	}
}