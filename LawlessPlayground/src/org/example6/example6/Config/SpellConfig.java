package org.example6.example6.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.metadata.FixedMetadataValue;
import org.example6.example6.example6;
import org.example6.example6.Models.SpellCrystal;
import org.example6.example6.Utils.EntityManager;

public class SpellConfig extends Config {
	
	interface Keys
	{
		static final String SPELLS = "spells";
		static final String SPELL_LOCATION_X = "spells.%s.location.x";
		static final String SPELL_LOCATION_Y = "spells.%s.location.y";
		static final String SPELL_LOCATION_Z = "spells.%s.location.z";
	}
	
	private List<SpellCrystal> crystals;

	public SpellConfig(File dataFolder) {
		super("spells.yml", dataFolder);
		
		crystals = new ArrayList<SpellCrystal>();
	}
	
	public void reloadFromConfig()
	{
		crystals.clear();
		
		ConfigurationSection cs = this.getConfigurationSection(Keys.SPELLS);
		if (cs == null) return;
		
		Map<String,Object> values = cs.getValues(false);
		if (values == null) return;
		
		for (String key : values.keySet())
		{
			if (this.has("spells."+key))
			{
				Location loc = new Location(
					example6.getWorldManager().getOverworld(),
					this.getDouble(String.format(Keys.SPELL_LOCATION_X, key), 0),
					this.getDouble(String.format(Keys.SPELL_LOCATION_Y, key), 0),
					this.getDouble(String.format(Keys.SPELL_LOCATION_Z, key), 0));
				
				SpellCrystal c = new SpellCrystal(key, loc);
				
				crystals.add(c);
			}
		}
		
		//pick up on any leftover crystals
		for (Entity e : example6.getWorldManager().getOverworld().getEntities())
		{
			if (e.getType().equals(EntityType.ENDER_CRYSTAL) && e.isValid())
			{
				System.out.println("FOUND AN OVERWORLD CRYSTAL at x" + e.getLocation().getX()
						+ ",y"+e.getLocation().getY()
						+ ",z"+e.getLocation().getZ());
				
				SpellCrystal original = this.getCrystalFromLocation(e.getLocation());
				if (original != null)
				{
					System.out.println("TRACKING IT AS " + original.getName());
					original.setCrystal(e);
					original.applyMetadata();
				}
				else
				{
					System.out.println("REMOVING IT");
					e.remove();
				}
			}
		}
		
		for (SpellCrystal c : crystals){
			if (c.getCrystal() == null)
				c.spawn();
		}
	}
	
	public void addCrystal(String name, Block block)
	{
		Location loc = block.getLocation();
		loc.add(0.5,0,0.5);
		this.set(String.format(Keys.SPELL_LOCATION_X, name), loc.getX());
		this.set(String.format(Keys.SPELL_LOCATION_Y, name), loc.getY());
		this.set(String.format(Keys.SPELL_LOCATION_Z, name), loc.getZ());
		this.save();
		
		this.reloadFromConfig();
	}
	
	public SpellCrystal getCrystal(String name)
	{
		for (SpellCrystal crystal : crystals)
		{
			if (crystal.getName().equalsIgnoreCase(name))
			{
				return crystal;
			}
		}
		
		return null;
	}
	
	public SpellCrystal getCrystalFromLocation(Location loc)
	{
		for (SpellCrystal crystal : crystals)
		{
			if (crystal.getLocation().equals(loc))
			{
				return crystal;
			}
		}
		
		return null;
	}
	
	public boolean hasCrystal(String name)
	{
		for (SpellCrystal crystal : crystals)
		{
			if (crystal.getName().equalsIgnoreCase(name))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public List<SpellCrystal> getAllCrystals()
	{
		return this.crystals;
	}
	
	public void removeCrystal(String name)
	{
		this.set("spells."+name, null);
		this.save();
		
		this.reloadFromConfig();
	}
}
