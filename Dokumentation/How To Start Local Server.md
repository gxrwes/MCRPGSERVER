# How To Start The Local Server For Testing

*NOTE;* This Server is setup for Windows

## 0. The Broken Test Server
An Online server is currently beeing setup, therfore upstate is not garanteed, especially because its in its early steps. But if you want to try to connect the IP is
```
195.62.33.38
```
*NOTE:*the server is Whitelisted, so one can only connect after regitering with an Admin/Moderator

## 1. DOWNLOADING THE FILES

- make a github account and get invited by an admin
- git adress: https://github.com/gxrwes/MCRPGSERVER

### 1.1 Via Zip

- click on the green 'CODE' button
- in dropdown select Zip
- unzip ur files in ur desired foulder
- *NOTE:* to get updated files you will need to repeat thsi step, also version control is difficult

### 1.2 Via Git (Recommended)

- install git on ur computer (HOW TO GUIDE: https://git-scm.com/book/en/v2/Getting-Started-Installing-Git   Section: Installing on Windows, for eas e of use perhaps add it to windowds context menu when promted)
- then open a console window (CMD, Gitbash, Powershell) and navigate to your desired location (git bash can be opend with in the correct folder by navigating in explorer: rightclick->GitBash Here ; or type

```
cd "-Your complete filepath here-" 
```

- now some commands for git: (more info https://www.edureka.co/blog/git-commands-with-example/)
- type: ( unless logged in you will be promted to sign into with your git account)

```
git pull https://github.com/gxrwes/MCRPGSERVER.git'
```

- you now have downloaded the git, if you check in file explorer it should have the files and structure
- to update repeat git pull again, to check for updates type git status

# 2. Launching The Server

## 2.1 Check our system

- Ill be refering to some of the steps that we need from this guide, feel free to read the whole thing to get an idea bout mc servers, we are not running default MC JAVA SERVER, but a Spitgot Server. Guide : https://www.idtech.com/blog/creating-minecraft-server
- Follow Step 1 to check Java version
- open a console window and type:

```
ipconfig
```

- then locate the this line and `<b>`write down`</b>` your IP Adress, it should look something like this

```
IPv4 Address. . . . . . . . . . . : 192.168.***.**
```

## 2.2 Start the server

- Locate The File *start.bat* in

```
\MCRPGSERVER\MinecraftServer\
```

- Doubleclickt it, a window should oopen and within 20 seconds the server should start

## 2.3 Launch Minecraft

- go to multiplayer and either direct connect or add a server using your written down ip adress
- connect!!!!

# 3. Giving yourself admin rights

- in the server console (that you should be running if the server is running) type

```
op USERNAME
```

- now u should be able to do all the admin stuff

# 4. More Info

## 4.1 Plugins

- info will come in future

## 4.2 Issues

- this is a very early version including of the guide, with issues please contact the person who invited you to this thing in the first plalce
  -rip issue section
