package org.example6.example6.Commands;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.command.CommandSender;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.Utils.PacketManager;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import ru.tehkode.permissions.exceptions.RankingException;

public class GrieferCommand extends example6Command {

	public GrieferCommand() {
		super("griefer", "example6.griefer");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		
		if (args.length > 0)
		{
			String targetPlayer = args[0];
			PermissionUser pu = PermissionsEx.getUser(targetPlayer);
			PlayerConfig pc = example6.getConfigManager().getOfflinePlayerConfig(targetPlayer);
			
			if (args.length > 1)
			{
				if (args[1].equalsIgnoreCase("undo"))
				{
					try {
						pu.promote(null, "creative");
						pc.setCreativeBanMessage(null, sender);
					} catch (RankingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sender.sendMessage("User promoted once on creative rank");
				}
				else
				{
					//string together rest of args to form a reason
					String reason = "";
					for (int i = 1; i < args.length; i++)
					{
						reason += args[i] + " ";
					}
					
					//set greif reason
					pc.setCreativeBanMessage(reason, sender);
					
					//demote from creative access
					try {
						pu.demote(null, "creative");
					} catch (RankingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if (sender.getServer().getPlayer(targetPlayer).isOnline())
					{
						//kick from creative
						PacketManager.SendCrashPacket(sender.getServer().getPlayer(targetPlayer));
					}
					sender.sendMessage("User has been kicked, demoted, and griefing reason logged");
				}
			}
			else
			{
				sender.sendMessage("Griefer: " + targetPlayer);
				sender.sendMessage("Grief Reason: " + pc.getCreativeBanMessage());
				sender.sendMessage("Grief Reporter: " + pc.getCreativeBanAdmin());
				SimpleDateFormat sdf = new SimpleDateFormat ("MMM dd,yyyy HH:mm");
				Date resultDate = new Date(pc.getCreativeBanDate());
				sender.sendMessage("Grief Date: " + sdf.format(resultDate));
				sender.sendMessage("Current 'creative' group: " + pu.getRankLadderGroup("creative").getName());
			}
			
		}
		else
		{
			SendUsage(sender);
		}
		
		return true;
	}

	private void SendUsage(CommandSender sender) {
		sender.sendMessage("Usage:");

		sender.sendMessage("/g [player] [reason] - Instantly demotes from creative access and crashes the person's client.");
		sender.sendMessage("/g [player] undo - Instantly grants access back to the person.");
		sender.sendMessage("/g [player] - Views status of player's creative access.");
	}

}
