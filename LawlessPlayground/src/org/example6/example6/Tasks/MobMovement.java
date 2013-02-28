package org.example6.example6.Tasks;

import net.minecraft.server.v1_4_R1.PathEntity;
import net.minecraft.server.v1_4_R1.PathPoint;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_4_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_4_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_4_R1.entity.CraftZombie;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;
import org.example6.example6.example6;

public class MobMovement extends BukkitRunnable {
    
    public static void handleNoise(Entity e, float radius)
    {
    	for (Entity other : e.getNearbyEntities(radius, radius, radius))
    	{
    		if (other instanceof Zombie)
    		{
    			setEntityTarget(other, e, radius);
    		}
    	}
    }
    
    public static PathEntity findPath(Entity e, Entity source, float maxLength)
    {
		net.minecraft.server.v1_4_R1.EntityZombie ec = ((CraftZombie) e).getHandle();
		net.minecraft.server.v1_4_R1.Entity esource = ((CraftEntity) source).getHandle();
	    
		World w = e.getWorld();
		
		PathEntity pe = ((CraftWorld)w).getHandle().findPath(ec, esource, maxLength, true, false, false, true);
		
		return pe;
    }
    
    public static void setEntityTarget(final Entity e, final Entity source, final float radius)
    {
    	if (e instanceof Zombie && !e.isDead() && (((Zombie) e).getTarget() == null
    			|| ((Zombie) e).getTarget().getLocation().distance(e.getLocation()) > 16)) {
    		example6.getTaskManager().RunTaskAsync(new BukkitRunnable()
    		{
				@Override
				public void run() {
					// TODO Auto-generated method stub
		    		PathEntity pe = findPath(e, source, radius * 1.5f);
		    		
					example6.getTempData().SetPathEntity(e,  pe);
				}
    		});
    	}
    }

	@Override
	public void run() {
		//System.out.println("-----");
		for (Entity e : example6.getWorldManager().getZombieWorld().getEntities())
		{
			if (e instanceof Zombie)
			{
				if (((Zombie) e).getTarget() != null)
				{
					example6.getTempData().SetPathEntity(e, null);
					return;
				}
				
	    		PathEntity pe = example6.getTempData().GetPathEntity(e);
	    		if (pe != null)
	    		{
	    			//System.out.println(pe.e() + " / " + pe.d() + " - X: " + e.getLocation().getX()
	    			//		+ ", Y: " + e.getLocation().getY() + ", Z: " + e.getLocation().getZ());
	    			if (pe.b())
    				{
		    			//System.out.println("my path is over");
    					example6.getTempData().SetPathEntity(e, null);
    				}
	    			else
	    			{	    				
	    				PathPoint c = pe.a(pe.e());
		    			Location dest = new Location(example6.getWorldManager().getZombieWorld(), c.a, c.b, c.c);
		    			
		    			if (dest.distance(e.getLocation()) < 2)
		    			{
		    				pe.a();
		    				if (pe.e() < pe.d())
		    				{
		    					c = pe.a(pe.e());
				    			dest = new Location(example6.getWorldManager().getZombieWorld(), c.a, c.b, c.c);
		    				}
		    			}
		    			
		    			if (dest.getBlock().isEmpty()
		    					|| dest.getBlock().getType().equals(Material.WOODEN_DOOR)
		    					|| dest.getBlock().getType().equals(Material.SNOW)
		    					|| dest.getBlock().getType().equals(Material.LONG_GRASS)
		    					|| dest.getBlock().getType().equals(Material.YELLOW_FLOWER)
		    					|| dest.getBlock().getType().equals(Material.RED_ROSE)
		    					|| dest.getBlock().getType().equals(Material.SAPLING)
		    					|| dest.getBlock().getType().equals(Material.POWERED_RAIL)
		    					|| dest.getBlock().getType().equals(Material.DETECTOR_RAIL)
		    					|| dest.getBlock().getType().equals(Material.BROWN_MUSHROOM)
		    					|| dest.getBlock().getType().equals(Material.RED_MUSHROOM)
		    					|| dest.getBlock().getType().equals(Material.STEP)
		    					|| dest.getBlock().getType().equals(Material.TORCH)
		    					|| dest.getBlock().getType().equals(Material.FIRE)
		    					|| dest.getBlock().getType().equals(Material.WOOD_STAIRS)
		    					|| dest.getBlock().getType().equals(Material.REDSTONE_WIRE)
		    					|| dest.getBlock().getType().equals(Material.CROPS)
		    					|| dest.getBlock().getType().equals(Material.SIGN_POST)
		    					|| dest.getBlock().getType().equals(Material.LADDER)
		    					|| dest.getBlock().getType().equals(Material.RAILS)
		    					|| dest.getBlock().getType().equals(Material.COBBLESTONE_STAIRS)
		    					|| dest.getBlock().getType().equals(Material.WALL_SIGN)
		    					|| dest.getBlock().getType().equals(Material.LEVER)
		    					|| dest.getBlock().getType().equals(Material.STONE_PLATE)
		    					|| dest.getBlock().getType().equals(Material.WOOD_PLATE)
		    					|| dest.getBlock().getType().equals(Material.REDSTONE_TORCH_ON)
		    					|| dest.getBlock().getType().equals(Material.REDSTONE_TORCH_OFF)
		    					|| dest.getBlock().getType().equals(Material.STONE_BUTTON)
		    					|| dest.getBlock().getType().equals(Material.SUGAR_CANE_BLOCK)
		    					|| dest.getBlock().getType().equals(Material.PORTAL)
		    					|| dest.getBlock().getType().equals(Material.DIODE_BLOCK_OFF)
		    					|| dest.getBlock().getType().equals(Material.DIODE_BLOCK_ON)
		    					|| dest.getBlock().getType().equals(Material.TRAP_DOOR)
		    					|| dest.getBlock().getType().equals(Material.VINE)
		    					|| dest.getBlock().getType().equals(Material.FENCE_GATE)
		    					|| dest.getBlock().getType().equals(Material.BRICK_STAIRS)
		    					|| dest.getBlock().getType().equals(Material.SMOOTH_STAIRS)
		    					|| dest.getBlock().getType().equals(Material.WATER_LILY)
		    					|| dest.getBlock().getType().equals(Material.NETHER_BRICK_STAIRS)
		    					|| dest.getBlock().getType().equals(Material.NETHER_WARTS)
		    					|| dest.getBlock().getType().equals(Material.WOOD_STEP)
		    					|| dest.getBlock().getType().equals(Material.COCOA)
		    					|| dest.getBlock().getType().equals(Material.SANDSTONE_STAIRS)
		    					|| dest.getBlock().getType().equals(Material.TRIPWIRE_HOOK)
		    					|| dest.getBlock().getType().equals(Material.TRIPWIRE)
		    					|| dest.getBlock().getType().equals(Material.CARROT)
		    					|| dest.getBlock().getType().equals(Material.POTATO)
		    					|| dest.getBlock().getType().equals(Material.WOOD_BUTTON)
		    					|| dest.getBlock().getType().equals(Material.SKULL))
		    			{
		    	    		((CraftZombie) e).getHandle().getNavigation().a(c.a, c.b, c.c, 0.25f);
		    			}
		    			else
		    			{
		    				example6.getTempData().SetPathEntity(e, null);
		    			}
	    			}	    			
	    		}
			}
		}
	}
}
