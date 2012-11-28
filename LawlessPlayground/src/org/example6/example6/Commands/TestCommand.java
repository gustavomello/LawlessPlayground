package org.example6.example6.Commands;

import java.util.ArrayList;
import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.Commands.CommandTypes.example6Command;

public class TestCommand extends example6Command {
	public static ArrayList<String> ordered = new ArrayList<String>();
	
	public TestCommand() {
		super("test", "example6.test");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		Material[] materials = Material.values();
		
		for (int i = 0; i < materials.length; i++)
		{
			System.out.println(materials[i].toString());
		}
		
		if (sender instanceof Player)
		{
			/*
			if (args.length < 1 || !args[0].equalsIgnoreCase("citydev")) return true;
			Player player = (Player)sender;
			
			doStuff(player.getLocation().getChunk());
			*/
		}
		return true;
	}

	public static void doStuff(Chunk chunk) {
		for (int y = 1; y < 256; y++)
		{
			for (int x = 0; x < 15; x++)
			{
				for (int z = 0; z < 15; z++)
				{
					if (IsAntiPillarSection(chunk, x, y, z))
					{
						RegenAntiPillarSection(chunk, x, y, z);
					}
					if (chunk.getBlock(x, y, z).getType()==Material.IRON_BLOCK)
					{
						chunk.getBlock(x, y, z).setType(Material.AIR);
					}
					if (chunk.getBlock(x, y, z).getType()==Material.GOLD_BLOCK)
					{
						chunk.getBlock(x, y, z).setType(Material.AIR);
					}
					if (chunk.getBlock(x, y, z).getType()==Material.DIAMOND_BLOCK)
					{
						chunk.getBlock(x, y, z).setType(Material.AIR);
					}
				}
			}
		}
	}
	
	

	public static boolean IsAntiPillarSection(Chunk chunk, int x, int y, int z) {
		Block block = chunk.getBlock(x, y, z);
		for (int x2 = 0; x2 <= 3; x2++)
		{
			for (int z2 = 0; z2 <= 3; z2++)
			{
				if (x2 == 1 || x2 == 2
						|| z2 == 1 || z2 == 2)
				{
					//shoudl be air or liquid
					if (!block.getRelative(x2, 0, z2).isEmpty()
							&& !block.getRelative(x2, 0, z2).isLiquid())
					{
						return false;
					}
				}
				else
				{
					if (block.getType() != Material.BEDROCK)
						return false;
				}
			}
		}
		
		return true;
	}
	public static void RegenAntiPillarSection(Chunk chunk, int x, int y, int z) {
		Block block = chunk.getBlock(x, y, z);
		for (int x2 = 0; x2 <= 3; x2++)
		{
			for (int z2 = 0; z2 <= 3; z2++)
			{
				block.getRelative(x2,0,z2).setType(PickMaterialForY(y));
			}
		}
	}
	
	public static Material PickMaterialForY(int y)
	{
		Random r = new Random();
		float rand = r.nextFloat() * 100;
		if (y >= 70)
		{
			return Material.STONE;
		}
		else if (y >= 60 && y < 70)
		{
			if (rand < 0.2)
			{
				return Material.IRON_ORE;
			}
			else if (rand < 0.8)
			{
				return Material.COAL_ORE;
			}
		}
		else if (y >= 30 && y < 60)
		{
			if (rand < 0.6)
			{
				return Material.IRON_ORE;
			}
			else if (rand < 1.6)
			{
				return Material.COAL_ORE;
			}
		}
		else if (y >= 20 && y < 30)
		{
			if (rand < 0.05)
			{
				return Material.LAPIS_ORE;
			}
			else if (rand < 0.15)
			{
				return Material.GOLD_ORE;
			}
			else if (rand < 0.75)
			{
				return Material.IRON_ORE;
			}
			else if (rand < 1.75)
			{
				return Material.COAL_ORE;
			}
		}
		else if (y >= 10 && y < 20)
		{
			if (rand < 0.1)
			{
				return Material.LAPIS_ORE;
			}
			else if (rand < 0.2)
			{
				return Material.DIAMOND_ORE;
			}
			else if (rand < 0.35)
			{
				return Material.GOLD_ORE;
			}
			else if (rand < 0.75)
			{
				return Material.REDSTONE_ORE;
			}
			else if (rand < 1.35)
			{
				return Material.IRON_ORE;
			}
			else if (rand < 2.35)
			{
				return Material.COAL_ORE;
			}
		}
		else if (y > 0 && y < 10)
		{
			if (rand < 0.05)  
			{
				return Material.LAPIS_ORE;
			}
			else if (rand < 0.12)
			{
				return Material.DIAMOND_ORE;
			}
			else if (rand < 0.22)
			{
				return Material.GOLD_ORE;
			}
			else if (rand < 0.82)
			{
				return Material.REDSTONE_ORE;
			}
			else if (rand < 1.17)
			{
				return Material.IRON_ORE;
			}
			else if (rand < 1.77)
			{
				return Material.COAL_ORE;
			}
		}
		
		return Material.STONE;
	}
}
