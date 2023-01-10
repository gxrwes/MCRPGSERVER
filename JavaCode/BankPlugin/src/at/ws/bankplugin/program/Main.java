package at.ws.bankplugin.program;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import at.ws.bankplugin.program.Bank.Account;
import at.ws.bankplugin.program.Bank.Bank;
import at.ws.bankplugin.program.FileIO.BankAccountFileManager;
import at.ws.bankplugin.program.commands.OneCommand;
import at.ws.bankplugin.program.listeners.JoinListener;
import at.ws.bankplugin.*;

public class Main extends JavaPlugin{
	
	public void onEnable() {
		System.out.println("Running WBank");
		getCommand("test").setExecutor(new OneCommand());
		
		// Start listeners
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new JoinListener(), this);
		
		//Run Bank Code
		Bank bank;
		bank = Bank.getInstance();
		
		// Create an Account for the Bank itself and fill it with some base cash
		Account mainBank = new Account("Bank", 1000,1000);
		bank.addAccount(mainBank);
		
		for(Player p: Bukkit.getOnlinePlayers()){
			Account a = new Account(p.getName());
			if(!bank.contains(a))
			{
				bank.getInstance().addAccount(a);
			}
		}
		
		// Test write to file
		BankAccountFileManager bfile = new BankAccountFileManager();
		bfile.readFromFile();
		bfile.writeToFile();
		
	}
}
