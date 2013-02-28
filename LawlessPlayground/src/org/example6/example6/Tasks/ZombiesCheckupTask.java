package org.example6.example6.Tasks;

import java.util.Calendar;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.example6.example6.example6;
import org.example6.example6.Config.PlayerConfig;

public class ZombiesCheckupTask extends BukkitRunnable {

	public ZombiesCheckupTask() {
	}

	@Override
	public void run() {
		DespawnZombies();
		
		for (Player p : example6.getWorldManager().getZombieWorld().getPlayers())
		{
			ApplyZombification(p);
		}
	}

	private void DespawnZombies() {
		World w = example6.getWorldManager().getZombieWorld();
		List<Player> ps = w.getPlayers();
		int i = 0;
		for (Entity e : w.getEntities())
		{
			if (!e.getType().equals(EntityType.ZOMBIE))
				continue;
			boolean despawn = true;
			for (Player p : ps)
			{
				if (p.getLocation().distance(e.getLocation()) < 128 && !p.isDead())
				{
					despawn = false;
					break;
				}
			}
			if (despawn)
			{
				e.remove();
				i++;
			}
		}
		if (i > 0)
			System.out.println("Despawned " + i + " zombies.");
	}

	public static void ApplyZombification(Player p) {
		PlayerConfig cfg = example6.getConfigManager().getPlayerConfig(p);
		long zombifiedTime = cfg.getZombified();
		
		if (zombifiedTime == 0L) return;
		
		long infTime = Calendar.getInstance().getTimeInMillis() - zombifiedTime;
		
		int ticksLeft = (int) (((600000 - infTime) / 1000) * 20);
		if (infTime >= 600000) //10 minutes
		{
			p.setHealth(0);
			p.sendMessage(ChatColor.RED + "Your conscious mind has died, but your body continues on.");
		}
		else if (infTime >= 300000) //5 minutes
		{
			if (infTime < 360000)
			{
				p.sendMessage(ChatColor.RED + "You have begun dying from the inside.");
			}
			p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, ticksLeft, 0, true));
		}
		else if (infTime >= 180000) // 3 minutes
		{
			if (infTime < 240000)
			{
				p.sendMessage(ChatColor.RED + "Hunger pains begin tormenting your stomach.");
			}
			p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, ticksLeft, 0, true));
		}
		if (infTime >= 60000) // 1 minute
		{
			if (infTime < 120000)
			{
				p.sendMessage(ChatColor.RED + "You start to have trouble focusing.");
			}
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, ticksLeft, 0, true));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, ticksLeft, 0, true));
		}
	}

}
