package org.example6.example6.EventHandlers;

import org.bukkit.event.player.PlayerJoinEvent;
import org.example6.example6.example6;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerJoinHandler;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class TryCreative extends PlayerJoinHandler {

	public TryCreative(example6 plugin) {
		super(plugin);
	}

	@Override
	public void run(PlayerJoinEvent event) {
		PermissionUser user = PermissionsEx.getUser(event.getPlayer());
		if (!user.isRanked("creative"))
			user.addGroup("CreativeAccess");
	}

}
