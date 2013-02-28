package org.example6.example6.EventHandlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.example6.example6.example6;
import org.example6.example6.EventHandlers.HandlerTypes.ChunkUnloadHandler;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDamageByEntityHandler;
import org.example6.example6.Models.SpellCrystal;

public class SpellCrystalHandler implements EntityDamageByEntityHandler, ChunkUnloadHandler {

	@Override
	public void run(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof EnderCrystal)
		{
			final EnderCrystal c = (EnderCrystal) event.getEntity();
			if (c.hasMetadata("spellname"))
			{
				final String spellname = c.getMetadata("spellname").get(0).asString();
				
				if (event.getDamager() instanceof Arrow)
				{
					if (((Arrow) event.getDamager()).getShooter() instanceof Player)
					{
						Player p = (Player)((Arrow) event.getDamager()).getShooter();
						CrystalBroken(spellname, p);
					}
					
				}
				else if (event.getDamager() instanceof Player)
				{
					Player p = (Player) event.getDamager();
					CrystalBroken(spellname, p);
				}
				
				event.setCancelled(true);
				
			}
			else
			{
				System.out.println("ENDERCRYSTAL didnt have metadata, preventing explosion and removing entity! x:" + event.getEntity().getLocation().getX()
						+ "y: " + event.getEntity().getLocation().getY()
						+ "z: " + event.getEntity().getLocation().getZ());
				event.setCancelled(true);
				event.getEntity().remove();
			}
		}
	}

	@Override
	public void run(ChunkUnloadEvent event) {
		for (SpellCrystal crystal : example6.getConfigManager().getSpellConfig().getAllCrystals())
		{
			if (crystal.getLocation().getChunk().equals(event.getChunk()))
			{
				event.setCancelled(true);
			}
		}
	}

	public void CrystalBroken(final String spellname,
			Player p) {

		if (example6.getConfigManager().getPlayerConfig(p).hasSpell(spellname))
		{
			final SpellCrystal crystal = example6.getConfigManager().getSpellConfig().getCrystal(spellname);
			
			crystal.despawn();
			crystal.scheduleRespawn();
			
			p.getServer().dispatchCommand(
					p.getServer().getConsoleSender(), 
					"cast teach " + p.getName() + " " + spellname
					);
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You have not been invited to this location.");
		}
		
	}

}
