package org.example6.example6.Utils;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
				if (itemsource[i].getType() == Material.WRITTEN_BOOK)
				{
					Book source = new Book(itemsource[i]);
					Book book = new Book(
							source.getTitle(),
							source.getAuthor(),
							source.getPages()
							);
					items[i] = book.generateItemStack();
				}
				else
				{
					ItemStack source = MiscUtils.toCraftBukkit(itemsource[i]);
					NamedItemStack namedSource = new NamedItemStack(source);
					
					ItemStack target = MiscUtils.toCraftBukkit(new ItemStack(itemsource[i]));
					NamedItemStack namedTarget = new NamedItemStack(target);
					
					if (namedSource.getName() != null && !namedSource.getName().isEmpty())
					{
						namedTarget.setName(namedSource.getName());
					}
					
					if (namedSource.getLoresList() != null && !namedSource.getLoresList().isEmpty())
					{
						namedTarget.setLoresList(namedSource.getLoresList());
					}
					
					items[i] = target;
				}
			}
		}
		return items;
	}

}
