package org.example6.example6.Commands;

import net.minecraft.server.EntityBoat;
import net.minecraft.server.EntityItemFrame;
import net.minecraft.server.EntityMinecart;
import net.minecraft.server.EntityPainting;
import net.minecraft.server.EnumArt;
import net.minecraft.server.World;

import org.bukkit.Art;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Furnace;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftItemFrame;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;
import org.example6.example6.Utils.Book;
import org.example6.example6.Utils.MiscUtils;
import org.example6.example6.Utils.NamedItemStack;

public class ZombieWorldRegenCommand extends example6Command {

	public ZombieWorldRegenCommand(example6 Plugin) {
		super("zregen", "example6.zregen", Plugin);
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (player.getWorld().getName().equalsIgnoreCase("zombies"))
			{
				Chunk target = player.getLocation().getChunk();
				Chunk real = plugin.getServer().getWorld("citydev").getChunkAt(target.getX(), target.getZ());
				CopyChunk(real, target);
			}
		}
		return true;
	}
	
	public static Location getAttachingBlock(Location loc, Hanging hanging, BlockFace face)
	{
		if(hanging instanceof Painting)
		{
			Art art = ((Painting) hanging).getArt();

			if(art.getBlockHeight() + art.getBlockWidth() < 5)
			{
				int i = 0, j = 0, k = art.getBlockWidth() - 1;
				switch(face){
				case EAST:
					
					j= -2;//art.getBlockWidth() -1; //new
					i = -k;
					break;
				case WEST:
					j = 2;//art.getBlockWidth()+1; //new
					i = 0;//-1+art.getBlockWidth();
					break;
				case NORTH:
					j = -k;
					break;
				case SOUTH:
					break;
				default:
					break;
				}
				loc.add(i, 1-art.getBlockHeight(), j);
			}
			else 
			{ 
				if(art.getBlockHeight() == 4)
					loc.add(0, -1, 0);
				if(art.getBlockWidth() == 4)
				{
					switch(face){
					case EAST:
						loc.add(-1, 0, -2);
						break;
					case WEST:
						loc.add(0, 0, 2); //-1,0,0
						break;
					case NORTH:
						loc.add(0, 0, -1);
						break;
					case SOUTH:
						break;
					default:
						break;
					}
				}
			}
		}
		else
		{
			switch(face) {
			case EAST:
				loc.add(0, 0, -2);
				break;
			case NORTH:
				loc.add(0, 0, 0);
				break;
			case SOUTH:
				loc.add(0, 0, 0);
				break;
			case WEST:
				loc.add(0, 0, 2);
				break;
			default:
				break;

			}
		}

		return loc;
	}
	
	public void CopyChunk(Chunk src, Chunk dst)
	{
		ClearAllEntities(dst);

		CopyBlocks(src, dst);
		
		CopyEntities(src, dst);
	}

	private static void ClearAllEntities(Chunk dst) {
		for (Entity e : dst.getEntities())
		{
			if (!(e instanceof Player))
			{
				e.remove();
			}
		}
	}

	private static void CopyBlocks(Chunk src, Chunk dst) {
		for (int x = 0; x < 16; x++)
		{
			for (int y = 0; y < 256; y++)
			{
				for (int z = 0; z < 16; z++)
				{
					
					Block srcBlock = src.getBlock(x, y, z);
					Block dstBlock = dst.getBlock(x,  y,  z);
					int typeid = srcBlock.getTypeId();
					byte data = srcBlock.getData();
					
					if (dstBlock.getState() instanceof Chest)
						((Chest)dstBlock.getState()).getInventory().clear();
					if (dstBlock.getState() instanceof Dispenser)
						((Dispenser)dstBlock.getState()).getInventory().clear();
					if (dstBlock.getState() instanceof Furnace)
						((Furnace)dstBlock.getState()).getInventory().clear();
					
					dstBlock.setTypeIdAndData(
							typeid,
							data,
							false
							);

					if (dstBlock.getState() instanceof Chest)
					{
						Chest source = ((Chest)srcBlock.getState());
						Chest chest = ((Chest)dstBlock.getState());
						chest.getInventory().setContents(GetCopiedInventory(source.getInventory()));
						
						chest.update();
					}
					else if (dstBlock.getState() instanceof Sign)
					{
						Sign sign = (Sign) dstBlock.getState();
						int i = 0;
						for (String line : ((Sign)srcBlock.getState()).getLines())
						{
							sign.setLine(i, line);
							i++;
						}
						
						sign.update();
					}
					else if (dstBlock.getState() instanceof Furnace)
					{
						Furnace source = (Furnace) srcBlock.getState();
						Furnace furnace = (Furnace) dstBlock.getState();
						furnace.getInventory().setContents(
								GetCopiedInventory(source.getInventory())
								);
						furnace.setBurnTime(source.getBurnTime());
						furnace.setCookTime(source.getCookTime());
						furnace.update();
					}
					else if (dstBlock.getState() instanceof Dispenser)
					{
						Dispenser source = (Dispenser) srcBlock.getState();
						Dispenser dispenser = (Dispenser) dstBlock.getState();
						dispenser.getInventory().setContents(
								GetCopiedInventory(source.getInventory())
								);
						dispenser.update();
					}
				}
			}
		}
	}

	private static ItemStack[] GetCopiedInventory(Inventory inventory) {
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

	private void CopyEntities(Chunk src, Chunk dst) {
		Entity[] entities = src.getEntities();
		for (Entity entity : entities)
		{
			if (entity instanceof Hanging)
			{
				BlockFace face = ((Hanging)entity).getAttachedFace().getOppositeFace();
				Location loc = ((Hanging)entity).getLocation().getBlock().getRelative(face.getOppositeFace()).getLocation();
				
				loc = getAttachingBlock(loc, (Hanging) entity, face);
				int dir = getAttachingDirection(face);
				
				loc.setWorld(this.plugin.getServer().getWorld("zombies"));
				if (entity instanceof Painting)
					AddPainting((Painting) entity, loc, dir);
				else if (entity instanceof ItemFrame)
					AddItemFrame((CraftItemFrame) entity, loc, dir);
			}
			else if (entity instanceof StorageMinecart)
			{
				StorageMinecart source = ((StorageMinecart)entity);
				Location loc = source.getLocation();
				loc.setWorld(this.plugin.getServer().getWorld("zombies"));

				CraftWorld world = (CraftWorld) loc.getWorld();
				
				EntityMinecart minecart = new EntityMinecart(
						(World) world.getHandle(),
						loc.getX(),
						loc.getY(),
						loc.getZ(),
						1);
				((StorageMinecart)minecart.getBukkitEntity()).setDamage(source.getDamage());
				((StorageMinecart)minecart.getBukkitEntity()).setDerailedVelocityMod(source.getDerailedVelocityMod());
				((StorageMinecart)minecart.getBukkitEntity()).setFlyingVelocityMod(source.getDerailedVelocityMod());
				((StorageMinecart)minecart.getBukkitEntity()).setMaxSpeed(source.getMaxSpeed());
				((StorageMinecart)minecart.getBukkitEntity()).setSlowWhenEmpty(source.isSlowWhenEmpty());
				((StorageMinecart)minecart.getBukkitEntity()).setVelocity(source.getVelocity());
				((StorageMinecart)minecart.getBukkitEntity()).getInventory().setContents(
						GetCopiedInventory(source.getInventory()));
				world.getHandle().addEntity(minecart);
			}
			else if (entity instanceof PoweredMinecart)
			{
				PoweredMinecart source = ((PoweredMinecart)entity);
				Location loc = source.getLocation();
				loc.setWorld(this.plugin.getServer().getWorld("zombies"));
				CraftWorld world = (CraftWorld) loc.getWorld();
				
				EntityMinecart minecart = new EntityMinecart(
						(World) world.getHandle(),
						loc.getX(),
						loc.getY(),
						loc.getZ(),
						2);
				PoweredMinecart cart = (PoweredMinecart) minecart.getBukkitEntity();
				cart.setDamage(source.getDamage());
				cart.setDerailedVelocityMod(source.getDerailedVelocityMod());
				cart.setFlyingVelocityMod(source.getDerailedVelocityMod());
				cart.setMaxSpeed(source.getMaxSpeed());
				cart.setSlowWhenEmpty(source.isSlowWhenEmpty());
				cart.setVelocity(source.getVelocity());
				
				world.getHandle().addEntity(minecart);
			}
			else if (entity instanceof Minecart)
			{
				Minecart source = ((Minecart)entity);
				Location loc = source.getLocation();
				loc.setWorld(this.plugin.getServer().getWorld("zombies"));

				CraftWorld world = (CraftWorld) loc.getWorld();
				
				EntityMinecart minecart = new EntityMinecart(
						(World) world.getHandle(),
						loc.getX(),
						loc.getY(),
						loc.getZ(),
						0);
				
				((Minecart)minecart.getBukkitEntity()).setDamage(source.getDamage());
				((Minecart)minecart.getBukkitEntity()).setDerailedVelocityMod(source.getDerailedVelocityMod());
				((Minecart)minecart.getBukkitEntity()).setFlyingVelocityMod(source.getDerailedVelocityMod());
				((Minecart)minecart.getBukkitEntity()).setMaxSpeed(source.getMaxSpeed());
				((Minecart)minecart.getBukkitEntity()).setSlowWhenEmpty(source.isSlowWhenEmpty());
				((Minecart)minecart.getBukkitEntity()).setVelocity(source.getVelocity());
				
				world.getHandle().addEntity(minecart);
			}
			else if (entity instanceof Boat)
			{
				Boat source = ((Boat)entity);
				Location loc = source.getLocation();
				loc.setWorld(this.plugin.getServer().getWorld("zombies"));

				CraftWorld world = (CraftWorld) loc.getWorld();
				
				EntityBoat boat = new EntityBoat(
						(World) world.getHandle(),
						loc.getX(),
						loc.getY(),
						loc.getZ());
				
				Boat bBoat = (Boat) boat.getBukkitEntity();
				bBoat.setMaxSpeed(source.getMaxSpeed());
				bBoat.setOccupiedDeceleration(source.getOccupiedDeceleration());
				bBoat.setUnoccupiedDeceleration(source.getUnoccupiedDeceleration());
				bBoat.setWorkOnLand(source.getWorkOnLand());
				bBoat.setVelocity(source.getVelocity());
				
				world.getHandle().addEntity(boat);
			}
		}
	}

	private void AddItemFrame(CraftItemFrame f, Location loc, int dir) {
		CraftWorld world = ((CraftWorld)loc.getWorld());
		EntityItemFrame frame = new EntityItemFrame(world.getHandle(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), dir);
		net.minecraft.server.ItemStack stack = new net.minecraft.server.ItemStack(((ItemFrame)f).getItem().getTypeId(), 1, 0);
		frame.a(stack);
		//set item rotation, direction
		((ItemFrame)frame.getBukkitEntity()).setRotation(f.getRotation());
		
		if(!frame.survives()) {
			frame = null;
			return;
		}
		world.getHandle().addEntity(frame);
	}

	private void AddPainting(Painting p, Location loc, int dir) {
		
		CraftWorld world = ((CraftWorld)loc.getWorld());
		
		EntityPainting paint = new EntityPainting(world.getHandle(), 
				loc.getBlockX(), 
				loc.getBlockY(), 
				loc.getBlockZ(), 
				dir);
		
		paint.art = EnumArt.values()[p.getArt().getId()];
		paint.setDirection(paint.direction);
		if (!paint.survives())
		{
			paint = null;
			return;
		}
		
		world.getHandle().addEntity(paint);
	}

	private int getAttachingDirection(BlockFace face) {
		int dir;
		switch(face) {
		case EAST:
		default:
			dir = 0;
			break;
		case NORTH:
			dir = 1;
			break;
		case WEST:
			dir = 2;
			break;
		case SOUTH:
			dir = 3;;
			break;
		}
		return dir;
	}
}
