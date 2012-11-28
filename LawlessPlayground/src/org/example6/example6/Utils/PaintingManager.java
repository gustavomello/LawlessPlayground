package org.example6.example6.Utils;

import org.bukkit.Art;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.Painting;

public class PaintingManager {

	public PaintingManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static Location GetAttachingBlock(Location loc, Hanging hanging, BlockFace face)
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
	
	public static int GetAttachingDirection(BlockFace face) {
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
