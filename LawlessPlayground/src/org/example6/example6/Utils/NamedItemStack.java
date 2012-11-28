package org.example6.example6.Utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NamedItemStack {

	private final ItemStack itemStack;
	
	public NamedItemStack(ItemStack itemStack) {
		this.itemStack = itemStack;
		CraftItemStack cis = ((CraftItemStack)this.itemStack);
		NBTTagCompound tag = cis.getHandle().getTag();
		if (tag == null) {
			cis.getHandle().setTag(new NBTTagCompound());
		}
	}
	
	private boolean hasDisplay() {
		return ((CraftItemStack)this.itemStack).getHandle().getTag().hasKey("display");
	}
	
	private NBTTagCompound getDisplay() {
		return ((CraftItemStack)this.itemStack).getHandle().getTag().getCompound("display");
	}
	
	private void addDisplay() {
		((CraftItemStack)this.itemStack).getHandle().getTag().setCompound("display", new NBTTagCompound());
	}
	
	private boolean hasLores() {
		return getDisplay().hasKey("Lore");
	}
	
	private NBTTagList getLores()
	{
		return getDisplay().getList("Lore");
	}
	
	public List<String> getLoresList()
	{
		List<String> returnVal = new ArrayList<String>();
		if (hasDisplay() == false) {
			return returnVal;
		}
		if (!hasLores())
			return returnVal;
		
		NBTTagList lores = this.getLores();
		for (int i = 0; i < lores.size(); i++)
		{
			returnVal.add(((NBTTagString)lores.get(i)).toString());
		}
		return returnVal;
	}
	
	public void setLoresList(List<String> lores)
	{
		NBTTagList newLores = new NBTTagList();
		
		for (String lore : lores)
		{
			newLores.add(new NBTTagString("", lore));
		}
		
		getDisplay().set("Lore", newLores);
	}

	public void clearLores() {
		getDisplay().remove("Lore");
	}
	
	public String getName() {
		if (hasDisplay() == false) {
			return null;
		}
		String name = getDisplay().getString("Name");
		if (name.equals("")) {
			return null;
		}
		return name;
	}
	
	public void setName(String name) {
		if (hasDisplay() == false) {
			this.addDisplay();
		}
		NBTTagCompound display = this.getDisplay();
		if (name == null) {
			display.remove("Name");
		}
		display.setString("Name", name);
	}
}