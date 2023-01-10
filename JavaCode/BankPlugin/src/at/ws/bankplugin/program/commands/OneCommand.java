package at.ws.bankplugin.program.commands;

import javax.swing.text.html.parser.Entity;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.ws.bankplugin.program.Bank.Bank;

public class OneCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		System.out.println("TestCommand");
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			player.sendMessage("TestCommand");
		}
		Player p = (Player)sender;
		p.getLocation();
		Bank b = Bank.getInstance();
		b.listAccounts();
		return false;
	}

}
