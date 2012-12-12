package org.example6.example6.Utils;

import java.awt.Color;

import net.minecraft.server.EntityBoat;
import net.minecraft.server.EntityItemFrame;
import net.minecraft.server.EntityMinecart;
import net.minecraft.server.EntityPainting;
import net.minecraft.server.EnumArt;
import net.minecraft.server.World;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftItemFrame;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Painting;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.ItemStack;
import org.example6.example6.Models.ColoredArmor;

public class EntityManager {

	public static void DuplicateEntity(Entity entity, Location targetLoc) {
		if (entity instanceof Hanging)
		{
			Hanging source = ((Hanging)entity);
			BlockFace face = source.getAttachedFace().getOppositeFace();
			Location loc = source.getLocation().getBlock().getRelative(face.getOppositeFace()).getLocation();
			
			loc = PaintingManager.GetAttachingBlock(loc, source, face);
			int dir = PaintingManager.GetAttachingDirection(face);
			
			loc.setWorld(targetLoc.getWorld());
			if (entity instanceof Painting)
			{
				EntityManager.SpawnPainting((Painting) entity, loc, dir);
			}
			else if (entity instanceof ItemFrame)
			{
				EntityManager.SpawnItemFrame((ItemFrame) entity, loc, dir);
			}
		}
		else if (entity instanceof StorageMinecart)
		{
			EntityManager.SpawnStorageMinecart((StorageMinecart)entity, targetLoc);
		}
		else if (entity instanceof PoweredMinecart)
		{
			EntityManager.SpawnPoweredMinecart((PoweredMinecart)entity, targetLoc);
		}
		else if (entity instanceof Minecart)
		{
			EntityManager.SpawnMinecart((Minecart)entity, targetLoc);
		}
		else if (entity instanceof Boat)
		{
			EntityManager.SpawnBoat((Boat)entity, targetLoc);
		}
	}
	
	public static void SpawnBoat(Boat source, Location loc) {
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

	public static void SpawnMinecart(Minecart source, Location loc) {
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

	public static void SpawnPoweredMinecart(PoweredMinecart source,
			Location loc) {
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

	public static void SpawnStorageMinecart(StorageMinecart source,
			Location loc) {
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
				InventoryManager.CopyInventory(source.getInventory()));
		world.getHandle().addEntity(minecart);
	}

	public static void SpawnPainting(Painting p, Location loc, int dir) {
		
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

	public static void SpawnItemFrame(ItemFrame entity, Location loc, int dir) {
		CraftWorld world = ((CraftWorld)loc.getWorld());
		EntityItemFrame frame = new EntityItemFrame(world.getHandle(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), dir);
		//set item rotation, direction
		((ItemFrame)frame.getBukkitEntity()).setRotation(entity.getRotation());
		
		if(!frame.survives()) {
			frame = null;
			return;
		}
		world.getHandle().addEntity(frame);
		
		if (entity.getItem() != null)
		{
			ItemStack sourceItem = new CraftItemStack(((CraftItemFrame)entity).getHandle().i());
			ItemStack destItem = InventoryManager.CopyItem(new ItemStack(entity.getItem()));
			
			if (sourceItem != null)
			{
				ColoredArmor colorSource = 
						new ColoredArmor(sourceItem);
				ColoredArmor colorDest = 
						new ColoredArmor(MiscUtils.toCraftBukkit(destItem));
				
				colorDest.setColor(colorSource.getColor());
				((ItemFrame)frame.getBukkitEntity()).setItem(destItem);
			}
			else
			{
				System.out.println("Weird bug: x:"+entity.getLocation().getX()
						+",y:"+entity.getLocation().getY()
						+",z:"+entity.getLocation().getZ());
			}
		}
	}

}
