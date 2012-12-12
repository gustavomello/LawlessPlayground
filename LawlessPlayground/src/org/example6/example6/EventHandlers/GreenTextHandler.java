package org.example6.example6.EventHandlers;

import org.bukkit.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerChatHandler;

public class GreenTextHandler implements PlayerChatHandler {

	@Override
	public void run(AsyncPlayerChatEvent event) {
		String temp = event.getMessage();
		if(temp.charAt(0) == '>')
			temp = ChatColor.GREEN + temp;
		event.setMessage(temp);
	}
}
