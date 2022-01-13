package dev.lolcrunchy.oregenerator.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Generators {

    private static ItemStack coalGenerator;

    static {
        coalGenerator();
    }

    private static void coalGenerator() {
        ItemStack item = new ItemStack(Material.COAL_ORE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§8§lCOAL GENERATOR");
        List<String> lore = makeLore(
                "",
                "&8&nGenerator:",
                "&7Coal Generator",
                "");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        coalGenerator = item;
    }

    public static ArrayList<String> makeLore(String ... str) {
        ArrayList<String> lore = new ArrayList<>();
        for(String s : str) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return lore;
    }

    public static ItemStack getCoalgenerator() {

        return coalGenerator.clone();

    }

}