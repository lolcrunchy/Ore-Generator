package dev.lolcrunchy.oregenerator;

import dev.lolcrunchy.oregenerator.commands.Commands;
import dev.lolcrunchy.oregenerator.destroy.BlockStatus;
import dev.lolcrunchy.oregenerator.destroy.CoalGeneratorBreak;
import dev.lolcrunchy.oregenerator.files.Data;
import org.bukkit.plugin.java.JavaPlugin;

public final class OreGenerator extends JavaPlugin {

    private BlockStatus blockStatus;

    @Override
    public void onEnable() {

        this.blockStatus = new BlockStatus();

        this.getServer().getPluginManager().registerEvents((new CoalGeneratorBreak(this)), this);
        this.getCommand("generator").setExecutor(new Commands());

        Data.setup();
        Data.save();

    }

    public BlockStatus getBlockStatus() {
        return blockStatus;
    }

    @Override
    public void onDisable() {

    }
}
