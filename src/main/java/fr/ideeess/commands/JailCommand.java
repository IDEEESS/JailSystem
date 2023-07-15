package fr.ideeess.commands;

import fr.ideeess.main.JailSystem;
import fr.ideeess.manager.PrisonersManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JailCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length >= 1 ){

            String playerName = args[0];
            Player player = Bukkit.getPlayer(playerName);

            if (player != null && player.isOnline()){

                if (!player.hasPermission(JailSystem.JAILBYPASSPERMISSION) && !player.isOp()){

                    if(!JailSystem.DEFINEJAILZONE && !JailSystem.DEFINEJAILSPAWN && !JailSystem.DEFINEJAILEXITSPAWN){
                        PrisonersManager prisonersManager = new PrisonersManager();
                        if (!prisonersManager.isPrisoner(playerName)){

                            return true;
                        }
                        sender.sendMessage(ChatColor.GREEN + "Ce joueur est déjà prisonnier");
                        return false;
                    }

                    sender.sendMessage(ChatColor.RED + "Il reste des choses à installer et à définir");
                    return false;
                }
                sender.sendMessage(ChatColor.RED + "Vous ne pouvez pas mettre en prison cette personne");
                return false;
            }
            sender.sendMessage(ChatColor.RED + "Le joueur spécifié n'existe pas ou n'est pas en ligne");
            return false;
        }
        sender.sendMessage(ChatColor.RED + "Il faut spécifier le nom du joueur");
        return false;
    }
}
