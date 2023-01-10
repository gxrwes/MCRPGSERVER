package at.ws.bankplugin.program.FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.ws.bankplugin.program.Bank.Account;
import at.ws.bankplugin.program.Bank.Bank;

public class BankAccountFileManager {
	Path path;
	String spath;
	public BankAccountFileManager() 
	{
		spath = "F:\\CodeProjects\\MCRPGSERVER\\MinecraftServer\\plugins\\wbank\\accounts.cfg";
		path = Paths.get(spath);
		
	}
	
	public void writeToFile() {
		try {
			Bank bank;
			bank = Bank.getInstance();
			
			String str = "";
			for(Account a : bank.getAccounts())
			{
				str +="Name:"+ a.getName() + ";Value:" + a.getAccountValue() + ";OverdrawLimit" + a.getOverdrawLimit()+ ";isLocked:" + a.islocked() +";\n";
			}
			
			Files.writeString(path, str, StandardCharsets.UTF_8);
			System.out.println("Writing to file");
		}
		catch(IOException ex)
		{
			System.out.print("Invalid  " + ex.getMessage());
		}
	}
	
	public void readFromFile()
	{
		
		try {
			Scanner scanner = new Scanner(new File(spath));

			while (scanner.hasNextLine()) {
				
				String line = scanner.nextLine();
				Pattern patternName = Pattern.compile("Name:(.*);Value:.*;OverdrawLimit.*;isLocked:.*;", Pattern.CASE_INSENSITIVE);
				Pattern patternValue = Pattern.compile("Name:.*;Value:(.*);OverdrawLimit.*;isLocked:.*;", Pattern.CASE_INSENSITIVE);
				Pattern patternOverdraw = Pattern.compile("Name:.*;Value:.*;OverdrawLimit(.*);isLocked:.*;", Pattern.CASE_INSENSITIVE);
				Pattern patternIsLocked = Pattern.compile("Name:.*;Value:.*;OverdrawLimit.*;isLocked:(.*);", Pattern.CASE_INSENSITIVE);
				
				Matcher matchName = patternName.matcher(line);
				Matcher matchValue = patternValue.matcher(line);
				Matcher matchOverdraw = patternOverdraw.matcher(line);
				Matcher matchIsLocked = patternIsLocked.matcher(line);
				
				int count = 1;
				
				
				String accountName = "";
				float overdrawLimit = 0;
				float accountValue = 0;
				boolean isLocked = false;
				
				while(matchName.find()) {
			         
			         accountName = matchName.group(count);
			         System.out.println( "Found:" + matchName.group(count) );
			    }
				while(matchValue.find()) {
			        
			         accountValue = Float.parseFloat( matchValue.group(count) );
			         System.out.println( "Found:" + matchValue.group(count) );
			      }
				while(matchOverdraw.find()) {
			         
			         overdrawLimit = Float.parseFloat(matchOverdraw.group(count));
			         System.out.println( "Found:" + matchOverdraw.group(count) );
			      }
				while(matchIsLocked.find()) {
			        
			         isLocked = Boolean.parseBoolean(matchIsLocked.group(count));
			         System.out.println( "Found:" + matchIsLocked.group(count) );
			      }
				Account a = new Account(accountName,overdrawLimit,accountValue);
				a.setLocked(isLocked);
				Bank.getInstance().addAccount(a);
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
