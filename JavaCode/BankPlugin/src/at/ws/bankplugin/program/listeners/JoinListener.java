package at.ws.bankplugin.program.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


import at.ws.bankplugin.program.Bank.Account;
import at.ws.bankplugin.program.Bank.Bank;

public class JoinListener implements Listener{

	@EventHandler
	public void handlePlayerJoin(PlayerJoinEvent event) {
		event.setJoinMessage("Loading Bank Accounts");
		Player p = event.getPlayer();
		
		Account a = new Account(p.getName());
		Bank bank;
		bank = Bank.getInstance();
		
		if(!bank.contains(a))
		{
			bank.getInstance().addAccount(a);
		}
		
	}
}
