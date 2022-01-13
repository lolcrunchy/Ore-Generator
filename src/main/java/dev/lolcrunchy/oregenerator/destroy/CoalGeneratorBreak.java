package dev.lolcrunchy.oregenerator.destroy;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import dev.lolcrunchy.oregenerator.files.Data;
import dev.lolcrunchy.oregenerator.item.Generators;
import dev.lolcrunchy.oregenerator.OreGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CoalGeneratorBreak implements Listener {

    public Map<Location, Material> CGM = new HashMap<Location, Material>();

    private final OreGenerator plugin;
    public CoalGeneratorBreak(OreGenerator plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        final Block block = e.getBlock();
        final Material material = block.getType();
        final Player player = e.getPlayer();
        if (material == Material.COAL_ORE) {
            if (material.getData().getName().equalsIgnoreCase("§8§lCOAL GENERATOR")) {
                block.setType(Material.COAL_BLOCK);
                plugin.getBlockStatus().addBlock(block);
                player.sendMessage("§8§l[ §c§lGenerator §8§l] §7Du har placeret en coal generator!");
                Data.get().set("Generator.List" + block.getLocation(), block);
                Data.save();
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        final Player p = event.getPlayer();
        if (p.isSneaking()) {
            event.getBlock().setType(Material.AIR);
            event.setCancelled(true);
            p.getLocation().getWorld().dropItemNaturally(p.getLocation(), Generators.getCoalgenerator());
            Data.get().set("Generator.List" + event.getBlock(), "false");
        } else {
            final Location loca = event.getBlock().getLocation().clone();
            loca.setY(loca.getY() + 1.6);
            loca.setZ(loca.getZ() + 0.5);
            loca.setX(loca.getX() + 0.5);
            Hologram holo = HologramsAPI.createHologram(plugin, loca);
            holo.appendTextLine("§8§l* COAL GENERATOR *");
            holo.appendTextLine("§7Respawn om 3 sekunder.");
            CGM.put(event.getBlock().getLocation(), event.getBlock().getType());
            for (Map.Entry<Location, Material> entry : CGM.entrySet()) {
                Location loc = entry.getKey();
                Material mat = entry.getValue();
                loc.getBlock().setType(mat);
            }
            event.getBlock().setType(Material.STAINED_GLASS);
            event.getBlock().setData((byte)15);
            event.setCancelled(true);
            p.getInventory().addItem(new ItemStack(Material.COAL, 1));
            event.setCancelled(true);
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    holo.delete();
                    event.getBlock().setType(Material.COAL_BLOCK);
                }
            }, 20 * 3);
        }
    }

}
