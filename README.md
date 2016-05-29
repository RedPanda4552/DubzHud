# DubzHud for Minecraft #

## Introduction ##
DubzHud is a Forge mod for Minecraft that adds HUD components for convenience. HUD components are categorized into two types: **Modules** and **Notifications**.

### Modules ###
DubzHud displays Modules in a line across the top of the screen. Modules can be reordered or removed by manually changing the DubzHud configuration file (.minecraft/config/dubzhud.cfg). Modules include: FPS, TPS (estimated), light levels (sky light and block light), entity count (rendered and loaded), coordinates, and world time.

### Notifications ###
DubzHud displays Notifications under the Modules line. Currently, 5 notifications are implemented: melee strength indicator, and a low durability indicator for each armor piece. Notifications may be turned off in the config file, and the melee strength notification colors can be changed as well.

## Installation ##
[DubzHud Releases Page] (https://github.com/RedPanda4552/DubzHud/releases)

DubzHud is a Forge mod, and therefore requires you have Forge installed. Don't have Forge installed? Head over to the [Minecraft Forge Files page] (http://files.minecraftforge.net) and pick the installer that matches the Minecraft version you have.

### Picking the right file from Forge ###
Mods usually will want Forge to be for the same version of Minecraft. Currently, DubzHud only works for the Minecraft version (Forge version *should not matter*) it is explicitly built for. Consider this while picking your Forge version.

When you pick a game and Forge version you will then have multiple options. The Installer option will work on all operating systems. The Installer-Win option is for Windows only but may be easier for some people to run.

### Installing DubzHud Into Forge ###
After running your Forge client for the first time, a "mods" folder will appear in your .minecraft folder. DubzHud can simply be dropped in and provided the version of DubzHud you are using was built for the version of Minecraft you are using, DubzHud should load nicely when you start your game.

#### Finding the .minecraft Folder ####
##### Windows #####
Open a run box by holding your Windows key and hitting R. Type "%appdata%" and hit enter. A new window will pop up and .minecraft will be at the top.

##### Linux #####
The .minecraft folder should appear in your home folder. It will be hidden by default, but if you show hidden files in your file browser it should show up.

##### OSX #####
From your home folder, navigate to the following: Library/Application Support/minecraft

### Problems, Bugs, All the Not-Fun Stuff ###
If you find a bug, feel free to let me know by creating an issue here on GitHub or [by email] (mailto:redpanda4552@gmail.com).