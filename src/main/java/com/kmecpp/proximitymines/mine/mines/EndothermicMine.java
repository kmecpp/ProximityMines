package com.kmecpp.proximitymines.mine.mines;

import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;

import com.kmecpp.proximitymines.mine.AbstractMine;
import com.kmecpp.proximitymines.mine.MineBlock;

public class EndothermicMine extends AbstractMine {

	@Override
	public String getName() {
		return "Endothermic";
	}

	@Override
	public BlockType getBlockType() {
		return BlockTypes.ICE;
	}

	@Override
	public void onExplode(Player target, MineBlock mine, Cause cause) {
		//TODO
	}

}