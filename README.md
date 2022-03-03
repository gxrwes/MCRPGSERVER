# MCRPGSERVER

# Quickstart

- Detailed local starterguide .md is located in /documentation
## current launched Testing server
```
195.62.33.38
```

- The server has a whitelist to msg an Admin/Moderator for access [ here ]( #List-of-Admins )
- [discord](https://discord.gg/znAhsb7S)

*Note* PLS ALSO INSTALL GIT LFS: all info here https://git-lfs.github.com/
# Roadmap and current TODOs

## Short Term
- learn and build  CommandAPI ( so we can use Player activated commandblocks with non vanilla commands)
- build basic progession system
- add some basic skills/spells etc
- choose shader/texture pack
- zoneing for permissions (block place/break/interact permissions)
- update doc

## Long Term
- build/balance classes
- differe between jobs/classes
- add races (elves, dwarfs etc) perhaps with locked skills/spells/abilities/stats
- add npc and quests


# Plugins Overall Info

Some of these Plugins are in BETA or no longer supportet and have been dropped/Just dont work anymore. this list will be updated as the project goes on

### MCCore

### CommandApi

#### Links

- [Plugin Homepage](https://www.spigotmc.org/resources/api-commandapi-1-16-5-1-18-1.62353/)
- [Plugin Git](https://github.com/JorelAli/CommandAPI)

#### Description

- The CommandAPI provides full support for the new command UI which was implemented in Minecraft's 1.13 update.
- hoping to add custom commands for commandblocks

### LuckPerms

#### Links

- [Plugin Homepage](https://www.spigotmc.org/resources/luckperms.28140/)

#### Description

- LuckPerms is a permissions plugin for Minecraft servers (Bukkit/Spigot, BungeeCord & more). It allows server admins to control what features players can use by creating groups and assigning permissions.

### MinecraftRPG

### CommandHook

#### Links

- [Plugin Homepage](https://www.spigotmc.org/resources/commandhook.61415/)

#### Description
- This plugin adds the possibility to use vanilla selectors in non-vanilla commands written in CommandBlock.

### etCommon

### HsRails

#### Links

- [Plugin Homepage](https://www.spigotmc.org/resources/hsrails.69584/)

#### Description

- A spigot/bukkit plugin to make minecarts worth building again.
Place a powered rail on a boost block (redstone block by default) to build high-speed rail. Place on any other block to get a regular powered rail.

### Vault

### RedstoneChips

### BasicCircuits

### Dynmap

#### Links

- [Plugin Homepage](https://www.spigotmc.org/resources/dynmap%C2%AE.274/)

#### Description

- View the minecraft map in your [Browser]( http://localhost:8123/) *Note:* Only works if server online
- *Test-Server-Map-View* if the testserver is operating you can view the map [here](http://195.62.33.3:8123)

### WorldEdit
#### Links
- [Plugin Homepage](https://dev.bukkit.org/projects/worldedit)

#### Description
This is worldedit, if u dont know what this is should u be running an mc server?

### SensorLibrary

### Essentials
#### Links
- [Plugin Homepage](https://www.spigotmc.org/resources/essentialsx.9089/)

### EssentialsChat

#### Links
- [Plugin Homepage](https://www.spigotmc.org/resources/essentialsx.9089/)


### EconomyShopGUI

#### Links
- [Plugin Homepage](https://www.spigotmc.org/resources/economyshopgui.69927/)

#### Description
Its the shop plugin bro

### LWC

#### Links
- [Plugin Homepage](https://www.spigotmc.org/resources/modern-lwc-continuation-of-lwc.2162/)

#### Description

- LWC is the longest-lived single block protection plugin, that protects both the blocks themselves and their contents. Originally designed in 2010 for locking chests (hence the name "Lightweight Chests"), it can be configured to lock any block.

### WorldGuard

#### Links

- [Plugin Homepage](https://dev.bukkit.org/projects/worldguard)
- [Plugin Doc](https://worldguard.enginehub.org/en/latest/)

#### Description

- WorldGuard lets you and players guard areas of land against griefers and undesirables
as well as tweak and disable various gameplay features of Minecraft.

### EssentialsDiscord

### ProMCCore

#### Links

- [Plugin Homepage](https://www.spigotmc.org/resources/promccore.93608/)

#### Description

- This is a core requirement for [PROSKILLAPI]()

### ProSkillAPI

#### Links

- [Plugin Homepage](https://www.spigotmc.org/resources/proskillapi-parties.96932/)

#### Description:

- This plugins is responsible for the basic rgp system, here custom classes races and skills are set
- Show information about your party with the built in scoreboard feature in Proskillapi. (if enabled)
- Set the max size allowed in a party.
- Set automatic timeout for invites, if the player does not accept the invite
- Set if a new leader is added to the party if the original leader disconnects
- Set whether or not to use the party scoreboard
- Set whether or not to use the players level or health in the scoreboard if enabled
- Set if only the leader can invite
- Set the experience modification based on the number of members in the party
- Set experience modification based on the difference of levels between party members.
Commands
```
/pt accept: Accept a party invitation
/pt decline: Declines a party invitation
/pt invite <player>: Invites a player to join a party
/pt leave: Leaves your current party
/pt info: Shows basic information about your party
Permissions
party.general: Permission to use the commands
```
#### Developement Status and Notes:

- Sofar added Classes: 
```
Class-Tree: Archer              // Not Tested
Class-Tree: Caster              // Not Tested
Class-Tree: Druid               // Not Tested
Class-Tree: Bard                // Not Tested
Class-Tree: Barbarian           // Not Tested
Class-Tree: Warlock             // Not Tested
Class-Tree: Magician            // Not Tested
Class-Tree: Necromancer         // Not Tested
Class-Tree: Cleric              // Not Tested
Class-Tree: Ranger              // Not Tested

Job-Tree: Miner
Job-Tree: Builder

Skill-Basic: Archer Passive     // No Scalability
Skill-Basic: Rogue Passive      // No Scalability
Skill-Basic: Heal or Shield     // No Scalability
```

### ProSkillAPI Parties


#### Links

- [Plugin Homepage](https://www.spigotmc.org/resources/proskillapi-create-custom-races-classes-skills-spells-magic-spells-with-an-easy-online-editor.91913/)
- [Online Skill Editor](https://promcteam.github.io/proskillapi/)
- [Wiki](https://promcteam.com/wiki/index.php?title=Proskillapi:Proskillapi)

#### Description:

- This plugins is responsible for the basic rgp system, here custom classes races and skills are set

#### Developement Status and Notes:

- Sofar added Classes: 
```
Class-Tree: Archer              // Not Tested
Class-Tree: Caster              // Not Tested
Class-Tree: Druid               // Not Tested
Class-Tree: Bard                // Not Tested
Class-Tree: Barbarian           // Not Tested
Class-Tree: Warlock             // Not Tested
Class-Tree: Magician            // Not Tested
Class-Tree: Necromancer         // Not Tested
Class-Tree: Cleric              // Not Tested
Class-Tree: Ranger              // Not Tested

Job-Tree: Miner
Job-Tree: Builder

Skill-Basic: Archer Passive     // No Scalability
Skill-Basic: Rogue Passive      // No Scalability
Skill-Basic: Heal or Shield     // No Scalability
### CommandButtons

#### Links

- [Plugin Homepage](https://www.spigotmc.org/resources/commandbuttons-execute-commands-on-block-interaction.60991/)

#### Description

- testing phase soo we can minimize console utilisation for players

# Feedback

For errors/help/feedback or any other issues pls contact a Project Administrator or Moderator

### Xaeros Minimap
#### Links

- [Plugin Homepage](https://www.curseforge.com/minecraft/mc-mods/xaeros-minimap)
- [Plugin Homepage2](https://chocolateminecraft.com/minimap2.php#)

#### Description

- testing phase for ingame hud minimap


## List of Admins
- GXR_Wes
    - description: project starter
    - email: wesley.st96@yahoo.de

- Flo //PLS UPDATE TO MC USERNAME
    - description: project starter
    - email: wesley.st96@yahoo.de
## List of Moderators
- there currently are no moderators registered
