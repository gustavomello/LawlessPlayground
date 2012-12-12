package org.example6.example6.Tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.example6.example6.Models.BlockChange;
import org.example6.example6.Models.EntityChange;
import org.example6.example6.Utils.BlockManager;
import org.example6.example6.Utils.EntityManager;

public class ChunkRegenTask extends BukkitRunnable {
	List<BlockChange> blocks = new ArrayList<BlockChange>();
	List<EntityChange> entities = new ArrayList<EntityChange>();
	
	public ChunkRegenTask() {
	}
	
	public void makeChunkRegen(Chunk src, Chunk dst)
	{
		List<BlockChange> srcBlocks = new ArrayList<BlockChange>();
		List<EntityChange> srcEntities = new ArrayList<EntityChange>();
		for (int y = 0; y < 256; y++)
		{
			for (int x = 0; x < 16; x++)
			{
				for (int z = 0; z < 16; z++)
				{
					BlockState srcState = src.getBlock(x, y, z).getState();
					BlockState dstState = dst.getBlock(x, y, z).getState();
					if (srcState.getType().equals(dstState.getType())
							&& srcState.getRawData() == dstState.getRawData()
							&& !(srcState instanceof Chest)
							&& !(srcState instanceof DoubleChest)
							&& !(srcState instanceof Dispenser)
							&& !(srcState instanceof Furnace))
						continue;
					srcBlocks.add(
							new BlockChange(
									srcState, 
								dst.getBlock(x, y, z).getLocation()
							)
					);
				}
			}
		}
		
		for (Entity e : src.getEntities())
		{
			Location loc = e.getLocation();
			loc.setWorld(dst.getWorld());
			srcEntities.add(new EntityChange(e, loc));
		}
		
		ClearAllEntities(dst);
		
		this.entities.addAll(srcEntities);
		
		this.blocks.addAll(srcBlocks);
	}

	private void ClearAllEntities(Chunk dst) {
		for(Entity e : dst.getEntities())
		{
			if (!(e instanceof Player))
				e.remove();
		}
	}

	@Override
	public void run() {
		try
		{
			int i = 0;
			Iterator<BlockChange> iter = blocks.iterator();
			while (iter.hasNext()) {
				BlockChange change = iter.next();
				BlockManager.ApplyBlockChange(change.getChange(), change.getLocation().getBlock());
				iter.remove();
				i++;
				if (i == 64) break;
			}
			if (blocks.size()==0)
			{
				for (EntityChange ec : entities)
				{				
					EntityManager.DuplicateEntity(ec.getEntity(), ec.getLocation());
				}
				this.cancel();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
