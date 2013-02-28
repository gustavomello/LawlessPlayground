package org.example6.example6.Models;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemDisplay {
	
	private final ItemStack i;

	public ItemDisplay(ItemStack itemStack) {
		this.i = itemStack;
	}
	
	public int getColor() {
		if (i.getItemMeta() instanceof LeatherArmorMeta)
			return ((LeatherArmorMeta)i.getItemMeta()).getColor().asRGB();
		return 0;
	}
	public void setColor(int color) {
		if (i.getItemMeta() instanceof LeatherArmorMeta)
		{
			LeatherArmorMeta l = ((LeatherArmorMeta)i.getItemMeta());
			l.setColor(Color.fromRGB(color));
		}
	}
	
	public String getName() {
		return i.getItemMeta().getDisplayName();
	}
	
	public void setName(String name) {
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(name);
		i.setItemMeta(m);
	}
	
	public List<String> getLore() {
		if (i.getItemMeta().getLore() != null)
			return i.getItemMeta().getLore();
		return new ArrayList<String>();
	}
	
	public void setLore(List<String> lore) {
		ItemMeta m = i.getItemMeta();
		m.setLore(lore);
		i.setItemMeta(m);
	}
}
