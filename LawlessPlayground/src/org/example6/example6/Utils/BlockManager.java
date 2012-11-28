package org.example6.example6.Utils;

import java.util.logging.Logger;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Furnace;
import org.bukkit.block.Sign;

public class BlockManager {
	public static void ApplyBlockChange(BlockState state, Block block) {
		int typeid = state.getTypeId();
		byte data = state.getRawData();
		
		if (block.getState() instanceof Chest)
			((Chest)block.getState()).getInventory().clear();
		if (block.getState() instanceof Dispenser)
			((Dispenser)block.getState()).getInventory().clear();
		if (block.getState() instanceof Furnace)
			((Furnace)block.getState()).getInventory().clear();
		
		block.setTypeIdAndData(
				typeid,
				data,
				false
				);
		
		if (state instanceof DoubleChest)
		{/*
			DoubleChest chest = ((DoubleChest)block.getState());
			chest.getLeftSide().getInventory().setContents(
					InventoryManager.CopyInventory(
							((DoubleChest) state).getLeftSide().getInventory()
					)
				);
			chest.getRightSide().getInventory().setContents(
					InventoryManager.CopyInventory(
							((DoubleChest) state).getRightSide().getInventory()
					)
				);
			
			((BlockState) chest).update();*/
		}
		else if (state instanceof Chest)
		{
			try
			{
			Chest chest = ((Chest)block.getState());
			chest.getInventory().setContents(
					InventoryManager.CopyInventory(
							((Chest) state).getInventory()
					)
				);
			
			chest.update();
			}
			catch (Exception e)
			{
				Logger.getAnonymousLogger().info("HEY!!!!" + state.getX() + "," + state.getZ());
			}
		}
		else if (state instanceof Sign)
		{
			Sign sign = (Sign) block.getState();
			int i = 0;
			for (String line : ((Sign)state).getLines())
			{
				sign.setLine(i, line);
				i++;
			}
			
			sign.update();
		}
		else if (state instanceof Furnace)
		{
			Furnace source = (Furnace) state;
			Furnace furnace = (Furnace) block.getState();
			furnace.getInventory().setContents(
					InventoryManager.CopyInventory(source.getInventory())
					);
			furnace.setBurnTime(source.getBurnTime());
			furnace.setCookTime(source.getCookTime());
			furnace.update();
		}
		else if (state instanceof Dispenser)
		{
			Dispenser source = (Dispenser) state;
			Dispenser dispenser = (Dispenser) block.getState();
			dispenser.getInventory().setContents(
					InventoryManager.CopyInventory(source.getInventory())
					);
			dispenser.update();
		}
	}

}
