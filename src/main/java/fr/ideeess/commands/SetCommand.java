package fr.ideeess.commands;

import fr.ideeess.main.JailSystem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static fr.ideeess.main.JailSystem.*;

public class SetCommand implements CommandExecutor , TabCompleter {
    JailSystem jailSystem;

    public SetCommand(JailSystem jailSystem) {
        this.jailSystem = jailSystem;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player player){

            if (args.length >= 1){
                String setting = args[0];
                if (player.hasPermission(JAILCOMMANDPERMISSION)) {

                    if (setting.equals("jailspawn")) { // Option pour définir JailSpawn
                        int x = player.getLocation().getBlockX();
                        int y = player.getLocation().getBlockY();
                        int z = player.getLocation().getBlockZ();

                        JailSystem.JAILSPAWNX = x;
                        JailSystem.JAILSPAWNY = y;
                        JailSystem.JAILSPAWNZ = z;
                        JailSystem.DEFINEJAILSPAWN = false;

                        jailSystem.getConfig().set("setup.jailSpawnLocation.x", x);
                        jailSystem.getConfig().set("setup.jailSpawnLocation.y", y);
                        jailSystem.getConfig().set("setup.jailSpawnLocation.z", z);

                        jailSystem.saveConfig();

                        player.sendMessage(ChatColor.GREEN + "Le point de spawn de la prison est " + x + " , " + y + " , " + z);

                        return false;
                    } else if (setting.equals("jailzone1")) {
                        int x = player.getLocation().getBlockX();
                        int y = player.getLocation().getBlockY();
                        int z = player.getLocation().getBlockZ();

                        JailSystem.JAILZONEX = x;
                        JailSystem.JAILZONEY = y;
                        JailSystem.JAILZONEZ = z;
                        if (JAILZONEX1 == 0 && JAILZONEY1 == 0 && JAILZONEZ1 == 0) {
                            JailSystem.DEFINEJAILZONE = true;
                            player.sendMessage(ChatColor.GREEN + "La première sélection de la zone de la prison " + x + " , " + y + " , " + z + ". Pensez à faire /set jailzone2");
                        } else {
                            JailSystem.DEFINEJAILZONE = false;
                            player.sendMessage(ChatColor.GREEN + "La première sélection de la zone de la prison " + x + " , " + y + " , " + z);
                        }

                        jailSystem.getConfig().set("setup.jail.x", x);
                        jailSystem.getConfig().set("setup.jail.y", y);
                        jailSystem.getConfig().set("setup.jail.z", z);

                        jailSystem.saveConfig();

                        player.sendMessage(ChatColor.GREEN + "La première sélection de la zone de la prison " + x + " , " + y + " , " + z + ". Pensez à faire /set jailzone2");

                        return false;
                    } else if (setting.equals("jailzone2")) {
                        int x = player.getLocation().getBlockX();
                        int y = player.getLocation().getBlockY();
                        int z = player.getLocation().getBlockZ();

                        JAILZONEX1 = x;
                        JAILZONEY1 = y;
                        JAILZONEZ1 = z;

                        if (JAILZONEX == 0 && JAILZONEY == 0 && JAILZONEZ == 0) {
                            JailSystem.DEFINEJAILZONE = true;
                            player.sendMessage(ChatColor.GREEN + "La première sélection de la zone de la prison " + x + " , " + y + " , " + z + ". Pensez à faire /set jailzone1");
                        } else {
                            JailSystem.DEFINEJAILZONE = false;
                            player.sendMessage(ChatColor.GREEN + "La première sélection de la zone de la prison " + x + " , " + y + " , " + z);
                        }

                        jailSystem.getConfig().set("setup.jail.x", x);
                        jailSystem.getConfig().set("setup.jail.y", y);
                        jailSystem.getConfig().set("setup.jail.z", z);

                        jailSystem.saveConfig();

                        return false;
                    } else if (setting.equals("jailexit")) {
                        int x = player.getLocation().getBlockX();
                        int y = player.getLocation().getBlockY();
                        int z = player.getLocation().getBlockZ();

                        JAILEXITSPAWNX = x;
                        JAILEXITSPAWNY = y;
                        JAILEXITSPAWNZ = z;
                        DEFINEJAILEXITSPAWN = false;

                        jailSystem.getConfig().set("setup.jailexit.x", x);
                        jailSystem.getConfig().set("setup.jailexit.y", y);
                        jailSystem.getConfig().set("setup.jailexit.z", z);

                        jailSystem.saveConfig();

                        player.sendMessage(ChatColor.GREEN + "Le point de spawn de sortie de prison est " + x + " , " + y + " , " + z);
                        return false;
                    } else if (setting.equals("block1")) {
                        int x = player.getLocation().getBlockX();
                        int y = player.getLocation().getBlockY();
                        int z = player.getLocation().getBlockZ();

                        BLOCK1X = x;
                        BLOCK1Y = y + 2;
                        BLOCK1Z = z;
                        DEFINEBLOCKBREAKPOINT1 = false;

                        jailSystem.getConfig().set("setup.block1.x", x);
                        jailSystem.getConfig().set("setup.block1.y", y);
                        jailSystem.getConfig().set("setup.block1.z", z);

                        jailSystem.saveConfig();

                        player.sendMessage(ChatColor.GREEN + "Le bloc n°1 spawnera en " + BLOCK1X + " " + BLOCK1Y + " " + BLOCK1Z);
                        return false;
                    } else if (setting.equals("block2")) {
                        int x = player.getLocation().getBlockX();
                        int y = player.getLocation().getBlockY();
                        int z = player.getLocation().getBlockZ();

                        BLOCK2X = x;
                        BLOCK2Y = y + 2;
                        BLOCK2Z = z;
                        DEFINEBLOCKBREAKPOINT2 = false;

                        jailSystem.getConfig().set("setup.block2.x", x);
                        jailSystem.getConfig().set("setup.block2.y", y);
                        jailSystem.getConfig().set("setup.block2.z", z);

                        jailSystem.saveConfig();

                        player.sendMessage(ChatColor.GREEN + "Le bloc n°2 spawnera en " + BLOCK1X + " " + BLOCK1Y + " " + BLOCK1Z);
                        return false;
                    }else if (setting.equals("block3")) {
                        int x = player.getLocation().getBlockX();
                        int y = player.getLocation().getBlockY();
                        int z = player.getLocation().getBlockZ();

                        BLOCK3X = x;
                        BLOCK3Y = y + 2;
                        BLOCK3Z = z;
                        DEFINEBLOCKBREAKPOINT3 = false;

                        jailSystem.getConfig().set("setup.block3.x", x);
                        jailSystem.getConfig().set("setup.block3.y", y);
                        jailSystem.getConfig().set("setup.block3.z", z);

                        jailSystem.saveConfig();

                        player.sendMessage(ChatColor.GREEN + "Le bloc n°3 spawnera en " + BLOCK1X + " " + BLOCK1Y + " " + BLOCK1Z);
                        return false;
                    }
                    player.sendMessage(ChatColor.RED + "Option introuvable");
                    return false;
                }
                player.sendMessage(ChatColor.RED + "Vous n'avez pas la permission requise pour exécuter cette commande");
                return false;
            }
            player.sendMessage(ChatColor.RED + "Il doit y avoir une option");
            return false;
        }
        sender.sendMessage(ChatColor.RED + "Vous devez être un joueur pour exécuter cette commande");

        return false;
    }

    List<String> arguments = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (arguments.isEmpty()){
            arguments.add("jailspawn");
            arguments.add("jailzone1");
            arguments.add("jailzone2");
            arguments.add("jailexit");
            arguments.add("block1");
            arguments.add("block2");
            arguments.add("block3");
        }

        List<String> results = new ArrayList<>();

        if (args.length == 1){
            for (String str : arguments){
                if (str.toLowerCase().startsWith(args[0].toLowerCase())){
                    results.add(str);
                }
            }

            return results;
        }

        return null;
    }
}
