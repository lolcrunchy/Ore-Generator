package dev.lolcrunchy.oregenerator.commands;

import dev.lolcrunchy.oregenerator.item.Generators;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("generator")) {
            if (args.length <= 0) {
                player.sendMessage("§8§l[ §c§lGENERATOR §8§l] §8§nCommands:");
                player.sendMessage("§8§l[ §c§lGENERATOR §8§l] §7/Generator Coal §8- Giver dig en coal generator.");
            } else if (args.length <= 1) {
                if (args[0].equalsIgnoreCase("coal")) {
                    player.sendMessage("§8§l[ §c§lGENERATOR §8§l] §7Du modtog en coal generator.");
                    player.getInventory().addItem(Generators.getCoalgenerator());
                } else if (!(args[0].isEmpty())) {
                    player.sendMessage("§8§l[ §c§lGENERATOR §8§l] §7Ugyldig argument.");
                }
             }
        }
        return false;
    }
}