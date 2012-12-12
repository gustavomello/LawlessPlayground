package org.example6.example6.Utils;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.example6.example6.Models.ColoredArmor;
import org.example6.example6.Models.NamedItemStack;

public class InventoryManager {

	public InventoryManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static ItemStack[] CopyInventory(Inventory inventory) {
		ItemStack[] itemsource = inventory.getContents();
		ItemStack[] items = new ItemStack[itemsource.length];
		for (int i = 0; i < items.length; i++)
		{
			if (itemsource[i]!=null)
			{
				ItemStack duplicatedItem = null;
				ItemStack sourceItem = itemsource[i];
				duplicatedItem = CopyItem(sourceItem);
				items[i] = duplicatedItem;
			}
		}
		return items;
	}

	public static ItemStack CopyItem(ItemStack sourceItem) {
		ItemStack duplicatedItem = new ItemStack(sourceItem);
		if (sourceItem.getType() == Material.WRITTEN_BOOK)
		{
			Book source = new Book(sourceItem);
			Book book = new Book(
					source.getTitle(),
					source.getAuthor(),
					source.getPages()
					);
			duplicatedItem = book.generateItemStack();
		}
		else
		{
			ItemStack source = MiscUtils.toCraftBukkit(sourceItem);
			ItemStack target = MiscUtils.toCraftBukkit(duplicatedItem);
			
			//copy armor color if necessary
			if (sourceItem.getType().equals(Material.LEATHER_BOOTS)
					|| sourceItem.getType().equals(Material.LEATHER_LEGGINGS)
					|| sourceItem.getType().equals(Material.LEATHER_CHESTPLATE)
					|| sourceItem.getType().equals(Material.LEATHER_HELMET))
			{
				ColoredArmor coloredSource = new ColoredArmor(source);
				ColoredArmor coloredTarget = new ColoredArmor(target);
				
				if (coloredSource.getColor() != 0)
				{
					coloredTarget.setColor(coloredSource.getColor());//coloredSource.getColor());
				}
			}
			
			//copy names / lores
			NamedItemStack namedSource = new NamedItemStack(source);
			
			NamedItemStack namedTarget = new NamedItemStack(target);
			
			if (namedSource.getName() != null && !namedSource.getName().isEmpty())
			{
				namedTarget.setName(namedSource.getName());
			}
			
			if (namedSource.getLoresList() != null && !namedSource.getLoresList().isEmpty())
			{
				namedTarget.setLoresList(namedSource.getLoresList());
			}
			
			duplicatedItem = target;
		}
		return duplicatedItem;
	}

}
