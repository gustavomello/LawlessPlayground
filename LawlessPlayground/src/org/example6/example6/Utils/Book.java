package org.example6.example6.Utils;

import java.io.Serializable;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.example6.example6.example6;

import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;

public class Book implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4816224606314511987L;
	private String author;
    private String title;
    private String[] pages;
    
    public Book(String name)
    {
    	this.author = "example6";
    	this.title = "Something went wrong.";
    	this.pages = new String[50];
    	List<String> contents = example6.getConfigManager().getBookContents(name);
    	for (int i = 0; i < contents.size(); i++)
    	{
    		switch (i)
    		{
	    		case 0:
	    			this.setTitle(contents.get(i));
	    			break;
	    		case 1:
	    			this.setAuthor(contents.get(i));
	    			break;
    			default:
    				this.addPage(contents.get(i));
    				break;
    		}
    	}
    }
 
    public Book(ItemStack bookItem){
        NBTTagCompound bookData = ((CraftItemStack) bookItem).getHandle().tag;
       
        this.author = bookData.getString("author");
        this.title = bookData.getString("title");
               
        NBTTagList nPages = bookData.getList("pages");
 
        String[] sPages = new String[nPages.size()];
        for(int i = 0;i<nPages.size();i++)
        {
            sPages[i] = nPages.get(i).toString();
        }
               
        this.pages = sPages;
    }
 
    public Book(String title, String author, String[] pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
   
    public String getAuthor()
    {
        return author;
    }
 
    public void setAuthor(String sAuthor)
    {
        author = sAuthor;
    }
   
    public void setTitle(String title)
    {
        this.title = title;
    }
   
    public String getTitle()
    {
        return title;
    }
    
    public void addPage(String page)
    {
    	
    	for (int i = 0; i < pages.length; i++)
    	{
    		if (pages[i] == null)
    		{
    			pages[i] = page;
    			return;
    		}
    	}
    }
    
    public void clearPages()
    {
    	pages = new String[pages.length];
    }
   
    public String[] getPages()
    {
        return pages;
    }
 
    public ItemStack generateItemStack(){
        CraftItemStack newbook = new CraftItemStack(Material.WRITTEN_BOOK);
       
        NBTTagCompound newBookData = new NBTTagCompound();
       
        newBookData.setString("author",author);
        newBookData.setString("title",title);
               
        NBTTagList nPages = new NBTTagList();
        for(int i = 0;i<pages.length;i++)
        { 
        	if (pages[i] != null && pages[i].length() != 0)
        	{
        		String page = MiscUtils.colorize(pages[i].replace("\\n", "\n"));
        		nPages.add(new NBTTagString(page,page));
        	}
        }
       
        newBookData.set("pages", nPages);
 
        newbook.getHandle().tag = newBookData;
       
        return (ItemStack) newbook;
    }
}