package org.example6.example6.Models;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.example6.example6.example6;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.Config.SpellConfig;
import org.example6.example6.Utils.MiscUtils;

public class Book
{
    /**
	 * 
	 */
	
	public static ItemStack load(String name)
	{
		List<String> contents = example6.getConfigManager().loadBookContents(name);
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta)item.getItemMeta();
    	for (int i = 0; i < contents.size(); i++)
    	{
    		switch (i)
    		{
	    		case 0:
	    			meta.setTitle(contents.get(i));
	    			break;
	    		case 1:
	    			meta.setAuthor(contents.get(i));
	    			break;
    			default:
    				meta.addPage(MiscUtils.colorize(contents.get(i)));
    				break;
    		}
    	}
    	
    	item.setItemMeta(meta);
    	
    	return item;
	}
	
	public static boolean save(ItemStack bookItem, String name)
	{
		List<String> contents = new ArrayList<String>();
		BookMeta meta = (BookMeta) bookItem.getItemMeta();
		contents.add(meta.getTitle());
		contents.add(meta.getAuthor());
		for (String page : meta.getPages())
			contents.add(page);
		
		return example6.getConfigManager().saveBookContents(name, contents);
	}
    
    public static ItemStack copy(ItemStack i)
    {
    	ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta meta = (BookMeta)book.getItemMeta();
		BookMeta oldmeta = (BookMeta)i.getItemMeta();
		meta.setTitle(oldmeta.getTitle());
		meta.setAuthor(oldmeta.getAuthor());
		meta.setPages(oldmeta.getPages());
		book.setItemMeta(meta);
		
		return book;
    }
}