package org.example6.example6.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;
import org.example6.example6.Models.Book;

public class BookCommand extends example6Command {

	public BookCommand() {
		super("book", "example6.book");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (args.length == 2)
		{
			//load / save / delete
			if (args[0].equalsIgnoreCase("load"))
			{
				if (!(sender instanceof Player))
				{
					sender.sendMessage(ChatColor.RED + "You must be a player.");
					return true;
				}
				((Player)sender).getInventory().addItem(this.loadBook(sender, args[1]));
			}
			else if (args[0].equalsIgnoreCase("save"))
			{
				if (!(sender instanceof Player))
				{
					sender.sendMessage(ChatColor.RED + "You must be a player.");
					return true;
				}
				
				this.saveBook(((Player)sender), args[1]);
			}
			else if (args[0].equalsIgnoreCase("delete"))
			{
				this.deleteBook(sender, args[1]);
			}
		}
		else if (args.length == 1)
		{
			if (args[0].equalsIgnoreCase("help"))
			{
				this.displayHelp(sender);
			}
			else if (args[0].equalsIgnoreCase("list"))
			{
				this.listBooks(sender);
			}
		}
		else
		{
			this.displayHelp(sender);
		}
		
		return true;
	}

	private void listBooks(CommandSender sender) {
		sender.sendMessage(ChatColor.GREEN + "All Books: ");
		for (String book : example6.getConfigManager().listBooks())
		{
			sender.sendMessage(ChatColor.YELLOW + "  " + book);
		}
	}

	private void deleteBook(CommandSender sender, String string) {
		if (example6.getConfigManager().deleteBook(string))
		{
			sender.sendMessage(ChatColor.YELLOW + "Book deleted.");
		}
		else
		{
			sender.sendMessage(ChatColor.YELLOW + "Book couldn't be deleted. Does it exist?");
		}
	}

	private void saveBook(Player sender, String string) {
		if (sender.getItemInHand().getType().equals(Material.WRITTEN_BOOK))
		{
			if (Book.save(sender.getItemInHand(), string))
			{
				sender.sendMessage(ChatColor.YELLOW + "Book saved.");
			}
			else
			{
				sender.sendMessage(ChatColor.YELLOW + "Book couldn't be saved. Does another book exist with this name?");
			}
		}
		else
		{
			sender.sendMessage(ChatColor.YELLOW + "You must be holding a written book.");
		}
	}

	private ItemStack loadBook(CommandSender sender, String string) {
		return Book.load(string);
	}

	public void displayHelp(CommandSender sender)
	{
		sender.sendMessage(ChatColor.YELLOW + "Usage: ");
		sender.sendMessage(ChatColor.YELLOW + "  /book save [name] - store the book");
		sender.sendMessage(ChatColor.YELLOW + "  /book load [name] - get a copy of the book");
		sender.sendMessage(ChatColor.YELLOW + "  /book delete [name] - delete the book");
		sender.sendMessage(ChatColor.YELLOW + "  /book list - list all books");
		sender.sendMessage(ChatColor.YELLOW + "  /book help - show this screen");
	}
}
