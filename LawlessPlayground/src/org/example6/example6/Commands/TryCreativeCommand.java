package org.example6.example6.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.PlayerCommand;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class TryCreativeCommand extends PlayerCommand {

	public TryCreativeCommand(example6 plugin) {
		super("trycreative", "example6.trycreative", plugin);
	}

	@Override
	public Boolean RunAsPlayer(Player player, String command, String alias,
			String[] args) {
		PermissionUser user = PermissionsEx.getUser(player);
		if (!user.isRanked("creative"))
		{
			user.addGroup("CreativeAccess");
			player.sendMessage(ChatColor.RED + "You have been given access build in the creative world. If you grief, it will be taken.");
		}
		else
		{
			player.sendMessage(ChatColor.RED + "You were already given access to the creative world. If you can't build, it may have been revoked.");
		}
		return true;
	}
}
