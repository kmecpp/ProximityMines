package com.kmecpp.proximitymines.mine.mines;

import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.living.player.Player;

import com.kmecpp.proximitymines.mine.AbstractMine;
import com.kmecpp.proximitymines.mine.MineBlock;

public class ContagiousMine extends AbstractMine {

	@Override
	public String getName() {
		return "Contagious";
	}

	@Override
	public BlockType getBlockType() {
		return BlockTypes.MYCELIUM;
	}

	@Override
	public void onExplode(Player target, MineBlock mine) {
		//TODO
	}

}
