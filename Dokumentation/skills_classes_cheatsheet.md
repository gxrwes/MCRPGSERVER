# Skills and Classes Useful commands

```
/class acc <accountId>	player
```

Changes the current active account for the player

```
/class ap [player] <amount>	player/console
```
Gives attribute points to yourself or a specified player

```
/class attr	player	
```
Opens the menu to invest points in and view attributes

```
/class backup	player/console	
```
Saves all data from SQL database to the local files (requires SQL to be enabled, will fail if using invalid credentials)

```
/class bar	player	
```
Toggles skill bar on/off (if enabled in the config)

```
/class bind <skill>	player	
```
Binds the skill to the player's held item if they have the skill unlocked

```
/class cast <skill>	player	
```
Causes the player to cast the skill if all conditions are met

```
/class mobcast <mob.uuid> <skill>	console	
```
When using plugins like MythicMobs you can use this command to let the mob cast Proskillapi skills.

```
/class changeclass <player> <group> <class>	player	
```
Changes the class of a player within the specified group to the target class. Levels remain the same.

```
/class clearbind	player	
```
Clears all bound skills the player has set

```
/class combo <skill> <combo>	player	
```
Sets the click combo for the skill

```
/class customize	player	
```
Opens an in-game editor for customizing all of the plugin's GUIs with options for pages, dimensions, positioning, which icons to include, etc.
Use tools.yml to create extra items for GUIs.

```
/class exp [player] <amount>	player/console	
```
Gives experience to yourself or a specified player

```
/class info [player]	player/console	
```
Displays class information about yourself or a specified player

```
/class level [player] <amount>	player/console	
```
Gives levels to yourself or a specified player

```
/class list [player]	player/console	
```
Provides a list of the player's accounts along with what their main class and level is

```
/class lore <lore>	player/console	
```
Adds a line of lore to the held item (mostly for testing purposes)

```
/class mana [player] <amount>	player/console	
```
Gives yourself or a specified player mana that cannot exceed their max amount

```
/class options	player	
```
Displays the available classes that the player can turn into

```
/class points [player] <amount>	player/console	
```
Gives a player skill points for all classes that accept command points

```
/class profess <class>	player	
```
Changes the player's class to the specified one unless they are not able to do so at the time

```
/class refund	player	
```
Refunds all spent skill points for the player

```
/class reload	player/console	
```
Unloads all data then reloads it to apply config changes (VERY SLOW)

```
/class reset	player	
```
Resets the player's active account data, wiping all their professed classes

```
/class skill	player	
```
Opens the skill tree for the player if available

```
/class switch <class>	player	
```
Changes the active account of the player to be the account with the given class

```
/class unbind	player	
```
Removes any binds on the player's held item

```
/class world <world>	player	
```
Teleports the player to the given world

```
/class forceaccount <player> <accountId>	console	
```
Sets the active account for a specified player

```
/class forceattr <player> [attr] [amount]	console	
```
Resets all attributes for a specified player
Resets a specific attribute for a player if an attribute is specified
Gives a player attribute points if an attribute and an amount is specified

```
/class forcecast <player> <skill> [level]	console	
```
Causes a player to cast a skill, ignoring any requirements to do so

```
/class forceprofess <player> <class>	console	
```
Forces a player to try to profess as the specified class

```
/class forcereset <player> [accountId]	console	
```
Forces a player to reset all of their progress on their current or specified account

```
/class forceskill <player> <up/down/reset> <skill>	console	
```
"up" causes the skill to be upgraded
"down" causes the skill to be downgraded
"reset" causes the skill to be fully refunded