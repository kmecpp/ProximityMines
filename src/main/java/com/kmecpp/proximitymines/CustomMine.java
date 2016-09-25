package com.kmecpp.proximitymines;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import com.kmecpp.proximitymines.mine.AbstractMine;
import com.kmecpp.proximitymines.mine.MineBlock;

public class CustomMine extends AbstractMine {

	@Override
	public BlockType getBlockType() {
		return BlockTypes.PUMPKIN;
	}

	@Override
	public void onExplode(Player target, MineBlock mine) {
		Sponge.getServer().getBroadcastChannel().send(Text.of("Boooooom!"));
	}

}
