package org.example6.example6;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.CharSet;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.example6.example6.Config.ChunkConfig;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.Config.SpellConfig;

import com.google.common.io.Files;

public class ConfigManager {

	private File dataFolder;
	
	private HashMap<String, PlayerConfig> players;
	
	private ChunkConfig chunks;
	
	private SpellConfig spells;

	public ConfigManager(File dataFolder) {
		this.players = new HashMap<String, PlayerConfig>();
		this.dataFolder = dataFolder;
		this.chunks = new ChunkConfig(this.dataFolder);
		this.spells = new SpellConfig(this.dataFolder);
		
		example6.getTaskManager().RunTaskLater(new BukkitRunnable()
		{

			@Override
			public void run() {
				spells.reloadFromConfig();
			}
			
		}, 0);
		this.spells.reloadFromConfig();
	}
	
	public File getDataFolder() {
		return this.dataFolder;
	}

	public ChunkConfig getChunkConfig() {
		return this.chunks;
	}

	public SpellConfig getSpellConfig() {
		return this.spells;
	}
	
	public List<String> loadBookContents(String name) // should be separated by page, containing newlines within strings
	{
		List<String> contents = new ArrayList<String>();
		File d = new File(dataFolder + "/books");
		if (!d.exists())
			d.mkdir();
		File f = new File(dataFolder + "/books", name + ".txt");
		if (f.exists())
		{
			try {
				String allpages = "";
				for (String line : Files.readLines(f, Charset.defaultCharset()))
				{
					allpages += line + '\n';
				}
				
				contents = Arrays.asList(allpages.split("~"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return contents;
	}
	
	public boolean saveBookContents(String name, List<String> contents)
	{
		File d = new File(dataFolder + "/books");
		if (!d.exists())
			d.mkdir();
		File f = new File(dataFolder + "/books", name + ".txt");
		if (!f.exists())
		{
			try {
				f.createNewFile();
				FileWriter fw = new FileWriter(f.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				for (String line : contents)
				{
					bw.write(line + "~");
				}
				bw.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean deleteBook(String name)
	{
		File f = new File(dataFolder + "/books", name + ".txt");
		if (f.exists())
		{
			f.delete();
			return true;
		}
		
		return false;
	}
	
	public List<String> listBooks()
	{
		File d = new File(dataFolder + "/books");
		if (!d.exists())
			d.mkdir();
		
		List<String> books = new ArrayList<String>();
		
		for (String book : d.list())
		{
			books.add(book.replace(".txt", ""));
		}
		
		return books;
	}
	
	//adds if doesnt exist
	public PlayerConfig getPlayerConfig(Player player)
	{
		String name = player.getName().toLowerCase();
		if (!this.players.containsKey(name))
		{
			this.players.put(name, new PlayerConfig(name, dataFolder));
		}
		return this.players.get(name);
	}
	
	//does not add if doesnt exist
	public PlayerConfig getOfflinePlayerConfig(String name)
	{
		name = name.toLowerCase();
		if (this.players.containsKey(name))
			return this.players.get(name);
		else
			return new PlayerConfig(name, dataFolder);
	}
	
	public Boolean hasPlayerConfig(String name)
	{
		File f = new File(dataFolder + "/userdata/", name.toLowerCase() + ".yml");
		return f.exists();
	}
	
	public void dropPlayerConfig(String name)
	{
		this.players.remove(name);
	}
}
