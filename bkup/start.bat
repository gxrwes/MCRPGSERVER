:: Start without GUI
echo STARTING MC-Server without GUI
@echo off
java -Xms2G -Xmx2G -XX:+UseG1GC -jar spigot-1.18.1.jar nogui
pause


:: Start with GUI
::echo STARTING MC-Server with GUI
::@echo off
::java -Xms2G -Xmx2G -XX:+UseG1GC -jar spigot-1.18.jar
::pause