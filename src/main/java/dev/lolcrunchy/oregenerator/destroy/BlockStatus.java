package dev.lolcrunchy.oregenerator.destroy;

import org.bukkit.block.Block;

import java.util.HashSet;

public class BlockStatus {

    private HashSet<Block> blocks;

    public BlockStatus() {

        blocks = new HashSet<>();
    }

    public void addBlock(Block block) {

        blocks.add(block);
    }

    public boolean isGeneratedBlock(Block block) {

        return blocks.contains(block);
    }

    public void removeBlock(Block block) {

        blocks.remove(block);
    }

}