package org.example6.example6.Utils;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_4_R1.inventory.CraftItemStack;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.example6.example6.Models.Book;
import org.example6.example6.Models.ItemDisplay;

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
			duplicatedItem = Book.copy(duplicatedItem);
		}
		else
		{
			ItemStack source = CraftItemStack.asCraftCopy(sourceItem);
			ItemStack target = CraftItemStack.asCraftCopy(duplicatedItem);
			
			//copy armor color if necessary
			if (sourceItem.getType().equals(Material.LEATHER_BOOTS)
					|| sourceItem.getType().equals(Material.LEATHER_LEGGINGS)
					|| sourceItem.getType().equals(Material.LEATHER_CHESTPLATE)
					|| sourceItem.getType().equals(Material.LEATHER_HELMET))
			{
				ItemDisplay coloredSource = new ItemDisplay(source);
				ItemDisplay coloredTarget = new ItemDisplay(target);
				
				if (coloredSource.getColor() != 0)
				{
					coloredTarget.setColor(coloredSource.getColor());//coloredSource.getColor());
				}
			}
			
			//copy names / lores
			ItemDisplay namedSource = new ItemDisplay(source);
			
			ItemDisplay namedTarget = new ItemDisplay(target);
			
			if (namedSource.getName() != null && !namedSource.getName().isEmpty())
			{
				namedTarget.setName(namedSource.getName());
			}
			
			if (namedSource.getLore() != null && !namedSource.getLore().isEmpty())
			{
				namedTarget.setLore(namedSource.getLore());
			}
			
			duplicatedItem = target;
		}
		return duplicatedItem;
	}

}
