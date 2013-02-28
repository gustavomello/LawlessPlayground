package org.example6.example6.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.example6.example6.example6;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.Config.SpellConfig;
import org.example6.example6.Models.Book;

public class RandomUtils {
	
	private static short[] woodValues = {0, 1, 2, 3};
	private static short[] saplingValues = {0, 1, 2, 3};
	private static short[] logValues = {0, 1, 2, 3};
	private static short[] leavesValues = {0, 1, 2, 3};
	private static short[] grassValues = {0, 1, 2};
	private static short[] sandstoneValues = {0, 1, 2};
	private static short[] woolValues = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
	private static short[] doubleSlabValues = {0, 1, 2, 3, 4, 5, 6};
	private static short[] slabValues = {0, 1, 2, 3, 4, 5, 6};
	private static short[] monsterEggValues = {0, 1, 2};
	private static short[] stoneBrickValues = {0, 1, 2, 3};
	private static short[] wallValues = {0, 1};
	private static short[] coalValues = {0, 1};
	private static short[] anvilValues = {0, 1, 2};
	private static short[] dyeValues = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
	private static short[] mobEggs = {50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 90, 91, 92, 93, 94, 95, 96, 98, 120};
	private static short[] heads = {0, 1, 2, 3, 4};
	private static short[] potionValues = {16, 32, 64, 
			8193, 8194, 8195, 8196, 8197, 8200, 8201, 8202, 8204, 8225,
			8226, 8228, 8229, 8223, 8236, 8259, 8260, 8264, 8265, 8266,
			16384, 16385, 16386, 16387, 16388, 16389, 16392, 16393, 16394, 16396,
			16417, 16418, 16420, 16421, 16428, 16451, 16452, 16456, 16457, 16458};

	private static String[] tier1Spells = {
		"fireball",
		"combust",
		"leap",
		"forcepush",
		"telekinesis",
		"conjure",
		"confusion",
		"pain",
		"stealth",
		"cripple",
		"purge",
		"heal",
		"silence",
		"lightwalk",
		"lifewalk",
		"disarm",
		"frostwalk",
		"geyser",
		"gills",
		"freeze",
		"haste",
		"roar",
		"armor",
		"empower",
		"wall",
		"entomb"
	};

	private static String[] tier2Spells = {
		"firenova",
		"lightning",
		"flamewalk",
		"phase",
		"mark / recall",
		"blink",
		"walkway",
		"carpet",
		"forcetoss",
		"drainlife",
		"shadowstep",
		"minion",
		"invisibility",
		"tree",
		"invulnerability",
		"prayer",
		"reflect",
		"stonevision"
	};
	
	public static Collection<ItemStack> GenerateDefinedSet(
			String value, Player player) {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		
		if (value.equalsIgnoreCase("starter") || value.equalsIgnoreCase("starting"))
		{
			items.add(new ItemStack(Material.BED, 1));
			items.add(new ItemStack(Material.WOOD, 10));
			items.add(new ItemStack(Material.COBBLESTONE, 8));
			items.add(new ItemStack(Material.RAW_BEEF, 2));
			items.add(Book.load("starter"));
		}
		else if (value.equalsIgnoreCase("initiate"))
		{
			items.add(new ItemStack(Material.COBBLESTONE, 32));
			items.add(new ItemStack(Material.WOOD, 32));
			items.add(new ItemStack(Material.RAW_BEEF, 8));
		}
		else if (value.equalsIgnoreCase("apprentice"))
		{
			items.add(new ItemStack(Material.LEATHER, 12));
			items.add(new ItemStack(Material.SEEDS, 10));
			items.add(new ItemStack(Material.PUMPKIN_SEEDS, 3));
			items.add(new ItemStack(Material.MELON_SEEDS, 1));
			items.add(new ItemStack(Material.POTATO, 1));
			items.add(new ItemStack(Material.CARROT, 1));
			items.add(new ItemStack(Material.SUGAR_CANE, 5));
		}
		else if (value.equalsIgnoreCase("expert"))
		{
			items.add(new ItemStack(Material.COBBLESTONE, 20));
			items.add(new ItemStack(Material.GOLD_INGOT, 8));
			items.add(new ItemStack(Material.IRON_INGOT, 16));
			items.add(new ItemStack(Material.REDSTONE, 32));
		}
		else if (value.equalsIgnoreCase("elite"))
		{
			items.add(new ItemStack(Material.COBBLESTONE, 32));
		}
		else if (value.equalsIgnoreCase("master"))
		{
			items.add(new ItemStack(Material.WOOD, 32));
		}
		else if (value.equalsIgnoreCase("king"))
		{
			items.add(new ItemStack(Material.RAW_BEEF, 8));
		}
		else
		{
			items.add(new ItemStack(Material.BED, 1));
		}
		
		return items;
	}
	
	public static ArrayList<ItemStack> GenerateRandomSet(int value, Player player) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		
		int maxDrops = (int) Math.floor((Math.log(value) / Math.log(2.5))) + 1;
		
		for (int i = 0; i < maxDrops; i++)
		{
			drops.add(GetTieredItem(GetRandomTier(value)));
		}
		
		ItemStack specialDrop = CreateSpellBook(player, value);
		if (specialDrop != null)
		{
			drops.add(specialDrop);
		}
		
		return drops;
	}
	
	public static int GetRandomTier(int value)
	{
		Random generator = new Random();
		float m = 10f;
		float r = 2f;
		float s = 1f;
		
		if (value >= 10 && value < 30)
		{
			m = 20f;
			r = 4f;
			s = 2f;
		}
		else if (value >= 30 && value < 50)
		{
			m = 40f;
			r = 8f;
			s = 4f;
		}
		else if (value >= 50 && value < 100)
		{
			m = 50f;
			r = 10f;
			s = 5f;
		}
		else if (value >= 100)
		{
			m = 50f;
			r = 25f;
			s = 10f;
		}

		float rand = generator.nextFloat() * 100;
		int tier = 0;
		if (rand <= s)
		{
			tier = 3;
		}
		else if (rand <= r + s)
		{
			tier = 2;
		}
		else if (rand <= m + r + s)
		{
			tier = 1;
		}
		
		return tier;
	}

	public static ItemStack GetTieredItem(int tier)
	{
		ArrayList<ItemStack> possibleItems = new ArrayList<ItemStack>();
		switch (tier)
		{
			case 3:
				possibleItems.add(new ItemStack(Material.CHAINMAIL_BOOTS, 1));
				possibleItems.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1));
				possibleItems.add(new ItemStack(Material.CHAINMAIL_HELMET, 1));
				possibleItems.add(new ItemStack(Material.CHAINMAIL_LEGGINGS, 1));
				possibleItems.add(new ItemStack(Material.BEDROCK, 16));
				possibleItems.add(new ItemStack(Material.ENDER_PORTAL_FRAME, 4));
				possibleItems.add(new ItemStack(Material.SPONGE, 16));
				possibleItems.add(new ItemStack(Material.MONSTER_EGG, 1, GetRandomItemData(mobEggs)));
				possibleItems.add(new ItemStack(Material.SKULL_ITEM, 1, GetRandomItemData(heads)));
				possibleItems.add(new ItemStack(Material.NETHER_STAR, 1));
				possibleItems.add(new ItemStack(Material.DRAGON_EGG, 1));
				possibleItems.add(new ItemStack(Material.ENDER_STONE, 16));
				possibleItems.add(new ItemStack(Material.OBSIDIAN, 16));
				possibleItems.add(new ItemStack(Material.ENDER_CHEST, 1));
				possibleItems.add(new ItemStack(Material.EXP_BOTTLE, 64));
				possibleItems.add(new ItemStack(Material.BEACON, 1));
				
				possibleItems.add(new ItemStack(Material.STATIONARY_LAVA, 1));
				possibleItems.add(new ItemStack(Material.STATIONARY_WATER, 1));
				possibleItems.add(new ItemStack(Material.FIRE, 8));
				possibleItems.add(new ItemStack(Material.MOB_SPAWNER, 1));
				possibleItems.add(new ItemStack(Material.RECORD_10, 1));
				possibleItems.add(new ItemStack(Material.RECORD_3, 1));
				possibleItems.add(new ItemStack(Material.RECORD_4, 1));
				possibleItems.add(new ItemStack(Material.RECORD_5, 1));
				possibleItems.add(new ItemStack(Material.RECORD_6, 1));
				possibleItems.add(new ItemStack(Material.RECORD_7, 1));
				possibleItems.add(new ItemStack(Material.RECORD_8, 1));
				possibleItems.add(new ItemStack(Material.RECORD_9, 1));
				possibleItems.add(new ItemStack(Material.RECORD_11, 1));
				possibleItems.add(new ItemStack(Material.RECORD_12, 1));
				possibleItems.add(new ItemStack(Material.ENDER_PORTAL, 1));
				possibleItems.add(new ItemStack(Material.PORTAL, 2));
				possibleItems.add(new ItemStack(Material.LOCKED_CHEST, 1));
				possibleItems.add(new ItemStack(Material.ICE, 16));
				possibleItems.add(new ItemStack(Material.MYCEL, 16));
				possibleItems.add(new ItemStack(Material.HUGE_MUSHROOM_1, 16));
				possibleItems.add(new ItemStack(Material.HUGE_MUSHROOM_2, 16));
				break;
			case 2:
				possibleItems.add(new ItemStack(Material.JUKEBOX, 1));
				possibleItems.add(new ItemStack(Material.FIREBALL, 8));
				possibleItems.add(new ItemStack(Material.DIAMOND_AXE, 1));
				possibleItems.add(new ItemStack(Material.DIAMOND_PICKAXE, 1));
				possibleItems.add(new ItemStack(Material.DIAMOND_SWORD, 1));
				possibleItems.add(new ItemStack(Material.DIAMOND_SPADE, 1));
				possibleItems.add(new ItemStack(Material.DIAMOND_BLOCK, 1));
				possibleItems.add(new ItemStack(Material.EMERALD_BLOCK, 1));
				possibleItems.add(new ItemStack(Material.DIAMOND, 8));
				possibleItems.add(new ItemStack(Material.DIAMOND_BOOTS, 1));
				possibleItems.add(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
				possibleItems.add(new ItemStack(Material.DIAMOND_HELMET, 1));
				possibleItems.add(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
				possibleItems.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				
				possibleItems.add(new ItemStack(Material.ANVIL, 1, GetRandomItemData(anvilValues)));

				possibleItems.add(new ItemStack(Material.NETHERRACK, 8));
				possibleItems.add(new ItemStack(Material.NETHER_BRICK, 8));
				possibleItems.add(new ItemStack(Material.NETHER_FENCE, 8));
				possibleItems.add(new ItemStack(Material.NETHER_BRICK_STAIRS, 8));
				possibleItems.add(new ItemStack(Material.GLOWSTONE, 8));
				possibleItems.add(new ItemStack(Material.BOOKSHELF, 8));
				possibleItems.add(new ItemStack(Material.MOSSY_COBBLESTONE, 8));
				possibleItems.add(new ItemStack(Material.OBSIDIAN, 8));
				possibleItems.add(new ItemStack(Material.SOUL_SAND, 8));

				possibleItems.add(new ItemStack(Material.NETHER_WARTS, 8));
				possibleItems.add(new ItemStack(Material.GLASS_BOTTLE, 4));
				possibleItems.add(new ItemStack(Material.POTION, 2, GetRandomItemData(potionValues)));
				possibleItems.add(new ItemStack(Material.FERMENTED_SPIDER_EYE, 8));
				possibleItems.add(new ItemStack(Material.BLAZE_ROD, 2));
				possibleItems.add(new ItemStack(Material.GOLD_NUGGET, 8));
				possibleItems.add(new ItemStack(Material.BLAZE_POWDER, 8));
				possibleItems.add(new ItemStack(Material.MAGMA_CREAM, 8));
				possibleItems.add(new ItemStack(Material.GHAST_TEAR, 4));
				possibleItems.add(new ItemStack(Material.GLOWSTONE_DUST, 8));
				possibleItems.add(new ItemStack(Material.SPECKLED_MELON, 8));
				possibleItems.add(new ItemStack(Material.GOLDEN_CARROT, 2));
				possibleItems.add(new ItemStack(Material.GOLDEN_APPLE, 1));
				possibleItems.add(new ItemStack(Material.BREWING_STAND_ITEM, 1));
				possibleItems.add(new ItemStack(Material.EYE_OF_ENDER, 2));
				possibleItems.add(new ItemStack(Material.CAULDRON, 1));
				break;
			case 1:
				possibleItems.add(new ItemStack(Material.BOOK_AND_QUILL, 1));
				possibleItems.add(new ItemStack(Material.SUGAR, 8));
				possibleItems.add(new ItemStack(Material.SADDLE, 1));
				possibleItems.add(new ItemStack(Material.LEATHER, 8));
				possibleItems.add(new ItemStack(Material.IRON_DOOR, 1));
				possibleItems.add(new ItemStack(Material.SNOW_BALL, 32));
				possibleItems.add(new ItemStack(Material.SUGAR_CANE, 8));
				possibleItems.add(new ItemStack(Material.CLAY, 8));
				possibleItems.add(new ItemStack(Material.CACTUS, 8));
				possibleItems.add(new ItemStack(Material.SNOW_BLOCK, 8));
				possibleItems.add(new ItemStack(Material.WEB, 8));
				possibleItems.add(new ItemStack(Material.DIAMOND_HOE, 1));
				possibleItems.add(new ItemStack(Material.MONSTER_EGGS, 8, GetRandomItemData(monsterEggValues)));
				possibleItems.add(new ItemStack(Material.IRON_BLOCK, 1));
				possibleItems.add(new ItemStack(Material.GOLD_BLOCK, 1));
				possibleItems.add(new ItemStack(Material.LAPIS_BLOCK, 4));
				possibleItems.add(new ItemStack(Material.LAPIS_ORE, 4));
				possibleItems.add(new ItemStack(Material.WOOL, 8, GetRandomItemData(woolValues)));
				possibleItems.add(new ItemStack(Material.DOUBLE_STEP, 8, GetRandomItemData(doubleSlabValues)));
				possibleItems.add(new ItemStack(Material.STEP, 8, GetRandomItemData(slabValues)));
				possibleItems.add(new ItemStack(Material.WOOD_STEP, 8, GetRandomItemData(woodValues)));
				possibleItems.add(new ItemStack(Material.SMOOTH_BRICK, 8, GetRandomItemData(stoneBrickValues)));
				possibleItems.add(new ItemStack(Material.IRON_ORE, 8));
				possibleItems.add(new ItemStack(Material.GOLD_ORE, 4));
				possibleItems.add(new ItemStack(Material.IRON_INGOT, 8));
				possibleItems.add(new ItemStack(Material.GOLD_INGOT, 4));
				possibleItems.add(new ItemStack(Material.IRON_FENCE, 8));
				possibleItems.add(new ItemStack(Material.EMERALD, 1));
				possibleItems.add(new ItemStack(Material.PUMPKIN, 4));
				possibleItems.add(new ItemStack(Material.JACK_O_LANTERN, 1));
				
				possibleItems.add(new ItemStack(Material.IRON_AXE, 1));
				possibleItems.add(new ItemStack(Material.IRON_PICKAXE, 1));
				possibleItems.add(new ItemStack(Material.IRON_SWORD, 1));
				possibleItems.add(new ItemStack(Material.IRON_SPADE, 1));
				possibleItems.add(new ItemStack(Material.IRON_HOE, 1));

				possibleItems.add(new ItemStack(Material.IRON_BOOTS, 1));
				possibleItems.add(new ItemStack(Material.IRON_CHESTPLATE, 1));
				possibleItems.add(new ItemStack(Material.IRON_HELMET, 1));
				possibleItems.add(new ItemStack(Material.IRON_LEGGINGS, 1));
				possibleItems.add(new ItemStack(Material.COMPASS, 1));
				possibleItems.add(new ItemStack(Material.WATCH, 1));
				possibleItems.add(new ItemStack(Material.EMPTY_MAP, 1));
				possibleItems.add(new ItemStack(Material.GOLD_AXE, 1));
				possibleItems.add(new ItemStack(Material.GOLD_PICKAXE, 1));
				possibleItems.add(new ItemStack(Material.GOLD_SWORD, 1));
				possibleItems.add(new ItemStack(Material.GOLD_SPADE, 1));
				possibleItems.add(new ItemStack(Material.GOLD_HOE, 1));

				possibleItems.add(new ItemStack(Material.GOLD_BOOTS, 1));
				possibleItems.add(new ItemStack(Material.GOLD_CHESTPLATE, 1));
				possibleItems.add(new ItemStack(Material.GOLD_HELMET, 1));
				possibleItems.add(new ItemStack(Material.GOLD_LEGGINGS, 1));
				possibleItems.add(new ItemStack(Material.FLINT_AND_STEEL, 1));
				possibleItems.add(new ItemStack(Material.BOW, 1));
				possibleItems.add(new ItemStack(Material.SHEARS, 1));
				possibleItems.add(new ItemStack(Material.BUCKET, 1));
				possibleItems.add(new ItemStack(Material.WATER_BUCKET, 1));
				possibleItems.add(new ItemStack(Material.LAVA_BUCKET, 1));
				possibleItems.add(new ItemStack(Material.MILK_BUCKET, 1));
				possibleItems.add(new ItemStack(Material.TNT, 8));
				possibleItems.add(new ItemStack(Material.FISHING_ROD, 1));
				possibleItems.add(new ItemStack(Material.CARROT_STICK, 1));

				possibleItems.add(new ItemStack(Material.REDSTONE, 32));
				possibleItems.add(new ItemStack(Material.REDSTONE_LAMP_OFF, 4));
				possibleItems.add(new ItemStack(Material.REDSTONE_TORCH_ON, 6));
				possibleItems.add(new ItemStack(Material.DIODE, 6));
				possibleItems.add(new ItemStack(Material.TRIPWIRE_HOOK, 6));
				possibleItems.add(new ItemStack(Material.PISTON_BASE, 6));
				possibleItems.add(new ItemStack(Material.PISTON_STICKY_BASE, 6));
				
				possibleItems.add(new ItemStack(Material.RAILS, 16));
				possibleItems.add(new ItemStack(Material.MINECART, 1));
				possibleItems.add(new ItemStack(Material.POWERED_MINECART, 1));
				possibleItems.add(new ItemStack(Material.STORAGE_MINECART, 1));
				possibleItems.add(new ItemStack(Material.POWERED_RAIL, 6));
				possibleItems.add(new ItemStack(Material.DETECTOR_RAIL, 6));

				possibleItems.add(new ItemStack(Material.SLIME_BALL, 6));
				possibleItems.add(new ItemStack(Material.INK_SACK, 6, GetRandomItemData(dyeValues)));
				possibleItems.add(new ItemStack(Material.COBBLE_WALL, 8, GetRandomItemData(wallValues)));
				break;
			case 0:
			default://basic blocks
				possibleItems.add(new ItemStack(Material.LOG, 8, GetRandomItemData(logValues)));
				possibleItems.add(new ItemStack(Material.WOOD, 8, GetRandomItemData(woodValues)));
				possibleItems.add(new ItemStack(Material.SAPLING, 1, GetRandomItemData(saplingValues)));
				possibleItems.add(new ItemStack(Material.LEAVES, 8, GetRandomItemData(leavesValues)));
				possibleItems.add(new ItemStack(Material.WATER_LILY, 8));
				possibleItems.add(new ItemStack(Material.GRASS, 8, GetRandomItemData(grassValues)));
				possibleItems.add(new ItemStack(Material.SANDSTONE, 8, GetRandomItemData(sandstoneValues)));
				possibleItems.add(new ItemStack(Material.DIRT, 8));
				possibleItems.add(new ItemStack(Material.COBBLESTONE, 8));
				possibleItems.add(new ItemStack(Material.STONE, 8));
				possibleItems.add(new ItemStack(Material.GLASS, 8));
				possibleItems.add(new ItemStack(Material.THIN_GLASS, 8));
				possibleItems.add(new ItemStack(Material.SAND, 8));
				possibleItems.add(new ItemStack(Material.GRAVEL, 8));
				possibleItems.add(new ItemStack(Material.BRICK, 16));
				possibleItems.add(new ItemStack(Material.CLAY_BALL, 16));
				possibleItems.add(new ItemStack(Material.PAPER, 16));
				possibleItems.add(new ItemStack(Material.BOOK, 6));
				
				//basic tools
				possibleItems.add(new ItemStack(Material.WOOD_AXE, 1));
				possibleItems.add(new ItemStack(Material.WOOD_PICKAXE, 1));
				possibleItems.add(new ItemStack(Material.WOOD_SWORD, 1));
				possibleItems.add(new ItemStack(Material.WOOD_SPADE, 1));
				possibleItems.add(new ItemStack(Material.WOOD_HOE, 1));
				
				//basic appliances
				possibleItems.add(new ItemStack(Material.BOAT, 1));
				possibleItems.add(new ItemStack(Material.FURNACE, 1));
				possibleItems.add(new ItemStack(Material.CHEST, 1));
				possibleItems.add(new ItemStack(Material.BED, 1));
				possibleItems.add(new ItemStack(Material.WOOD_DOOR, 1));
				possibleItems.add(new ItemStack(Material.LADDER, 8));
				possibleItems.add(new ItemStack(Material.COBBLESTONE_STAIRS, 8));
				possibleItems.add(new ItemStack(Material.BRICK_STAIRS, 8));
				possibleItems.add(new ItemStack(Material.SMOOTH_STAIRS, 8));
				possibleItems.add(new ItemStack(Material.SANDSTONE_STAIRS, 8));
				possibleItems.add(new ItemStack(Material.WOOD, GetRandomItemData(woodValues)));
				possibleItems.add(new ItemStack(Material.FENCE, 8));
				possibleItems.add(new ItemStack(Material.FENCE_GATE, 8));
				possibleItems.add(new ItemStack(Material.LEVER, 1));
				possibleItems.add(new ItemStack(Material.STONE_PLATE, 1));
				possibleItems.add(new ItemStack(Material.STONE_BUTTON, 1));
				possibleItems.add(new ItemStack(Material.WOOD_BUTTON, 1));
				possibleItems.add(new ItemStack(Material.WOOD_PLATE, 1));
				possibleItems.add(new ItemStack(Material.WORKBENCH, 1));
				possibleItems.add(new ItemStack(Material.DISPENSER, 1));
				possibleItems.add(new ItemStack(Material.NOTE_BLOCK, 1));
				
				//food
				possibleItems.add(new ItemStack(Material.PORK, 4));
				possibleItems.add(new ItemStack(Material.RAW_BEEF, 4));
				possibleItems.add(new ItemStack(Material.RAW_CHICKEN, 4));
				possibleItems.add(new ItemStack(Material.RAW_FISH, 4));
				possibleItems.add(new ItemStack(Material.GRILLED_PORK, 3));
				possibleItems.add(new ItemStack(Material.COOKED_BEEF, 3));
				possibleItems.add(new ItemStack(Material.COOKED_CHICKEN, 3));
				possibleItems.add(new ItemStack(Material.COOKED_FISH, 3));
				
				possibleItems.add(new ItemStack(Material.APPLE, 2));
				possibleItems.add(new ItemStack(Material.CAKE, 1));
				possibleItems.add(new ItemStack(Material.EGG, 4));
				possibleItems.add(new ItemStack(Material.MELON_BLOCK, 1));
				possibleItems.add(new ItemStack(Material.MELON, 5));
				possibleItems.add(new ItemStack(Material.COCOA, 4));
				possibleItems.add(new ItemStack(Material.BOWL, 1));
				possibleItems.add(new ItemStack(Material.MUSHROOM_SOUP, 1));
				possibleItems.add(new ItemStack(Material.SEEDS, 4));
				possibleItems.add(new ItemStack(Material.MELON_SEEDS, 2));
				possibleItems.add(new ItemStack(Material.PUMPKIN_SEEDS, 2));
				possibleItems.add(new ItemStack(Material.WHEAT, 4));
				possibleItems.add(new ItemStack(Material.BREAD, 4));
				possibleItems.add(new ItemStack(Material.CARROT, 4));
				possibleItems.add(new ItemStack(Material.POTATO, 4));
				possibleItems.add(new ItemStack(Material.BAKED_POTATO, 2));
				possibleItems.add(new ItemStack(Material.POISONOUS_POTATO, 1));
				possibleItems.add(new ItemStack(Material.PUMPKIN_PIE, 1));
				possibleItems.add(new ItemStack(Material.COOKIE, 4));

				possibleItems.add(new ItemStack(Material.STONE_AXE, 1));
				possibleItems.add(new ItemStack(Material.STONE_PICKAXE, 1));
				possibleItems.add(new ItemStack(Material.STONE_SWORD, 1));
				possibleItems.add(new ItemStack(Material.STONE_SPADE, 1));
				possibleItems.add(new ItemStack(Material.STONE_HOE, 1));

				possibleItems.add(new ItemStack(Material.LEATHER_BOOTS, 1));
				possibleItems.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
				possibleItems.add(new ItemStack(Material.LEATHER_HELMET, 1));
				possibleItems.add(new ItemStack(Material.LEATHER_LEGGINGS, 1));
				
				possibleItems.add(new ItemStack(Material.ROTTEN_FLESH, 1));
				possibleItems.add(new ItemStack(Material.YELLOW_FLOWER, 2));
				possibleItems.add(new ItemStack(Material.PAINTING, 1));
				possibleItems.add(new ItemStack(Material.ITEM_FRAME, 1));
				possibleItems.add(new ItemStack(Material.FLOWER_POT_ITEM, 1));
				possibleItems.add(new ItemStack(Material.SIGN, 1));
				possibleItems.add(new ItemStack(Material.FLINT, 5));
				possibleItems.add(new ItemStack(Material.RED_ROSE, 2));
				possibleItems.add(new ItemStack(Material.RED_MUSHROOM, 2));
				possibleItems.add(new ItemStack(Material.BROWN_MUSHROOM, 2));
				possibleItems.add(new ItemStack(Material.VINE, 4));
				possibleItems.add(new ItemStack(Material.BONE, 2));
				possibleItems.add(new ItemStack(Material.ARROW, 2));
				possibleItems.add(new ItemStack(Material.STRING, 2));
				possibleItems.add(new ItemStack(Material.SPIDER_EYE, 1));
				possibleItems.add(new ItemStack(Material.SULPHUR, 2));
				possibleItems.add(new ItemStack(Material.ENDER_PEARL, 1));
				possibleItems.add(new ItemStack(Material.EXP_BOTTLE, 4));
				possibleItems.add(new ItemStack(Material.COAL, 8, GetRandomItemData(coalValues)));
				possibleItems.add(new ItemStack(Material.COAL_ORE, 8));
				break;
		}
		
		ItemStack stack = GetRandomItemStack(possibleItems);
		
		return RandomizeStackSize(stack);	
	}
	
    public static ItemStack CreateSpellBook(Player p, int currentLevel)
    {
    	PlayerConfig pc = example6.getConfigManager().getPlayerConfig(p);
    	SpellConfig sc = example6.getConfigManager().getSpellConfig();

		int spellsEarned = (int) (Math.log(currentLevel) / Math.log(1.5));
		List<String> available = GetAvailableSpells(p);
		
		if (spellsEarned > pc.getSpells().size()
				&& currentLevel % 5 == 0
				&& available.size() > 0)
		{
			String newSpell = GetRandomString(available);
			
			Location loc = sc.getCrystal(newSpell).getLocation();
			pc.addSpell(newSpell);
			
			String[] spellPages = {
					"x: " + loc.getBlockX() + "\n" +
					"y: " + loc.getBlockY() + "\n" +
					"z: " + loc.getBlockZ() + "\n"
			};
			
			ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta meta = (BookMeta)item.getItemMeta();
			meta.setTitle("Secret Location");
			meta.setAuthor("Anonymous");
			meta.setPages(spellPages);
			item.setItemMeta(meta);
			return item;
		}
    	
    	return null;
    }
    
    public static List<String> GetAvailableSpells(Player player)
    {
    	PlayerConfig pc = example6.getConfigManager().getPlayerConfig(player);
    	
    	List<String> available = new ArrayList<String>();
    	
    	for (String spell : tier1Spells)
    	{
    		if (!pc.getSpells().contains(spell))
    			available.add(spell);
    	}
    	
    	if (pc.getVotes() > 99)
    	{
        	for (String spell : tier2Spells)
        	{
        		if (!pc.getSpells().contains(spell))
        			available.add(spell);
        	}
    	}
    	
    	return available;
    }
	
	public static short GetRandomItemData(short[] values)
	{
		Random generator = new Random();
		return values[generator.nextInt(values.length)];
	}
	
	public static ItemStack GetRandomItemStack(Collection<ItemStack> items)
	{
		ItemStack[] mats = new ItemStack[items.size()];
		items.toArray(mats);
		Random generator = new Random();
		return mats[generator.nextInt(items.size())];
	}
	
	public static String GetRandomString(List<String> available)
	{
		String[] spells = new String[available.size()];
		available.toArray(spells);
		Random generator = new Random();
		return spells[generator.nextInt(available.size())];
	}
	
	public static ItemStack RandomizeStackSize(ItemStack stack)
	{
		if (stack.getAmount() > 1)
		{
			Random generator = new Random();
			boolean addition = generator.nextBoolean();
			int delta = generator.nextInt(stack.getAmount());
			
			if (addition)
				stack.setAmount(stack.getAmount() + delta);
			else
				stack.setAmount(stack.getAmount() - delta);
		}
		return stack;
	}

}
