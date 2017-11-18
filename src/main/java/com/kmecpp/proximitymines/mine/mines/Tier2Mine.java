package com.kmecpp.proximitymines.mine.mines;

import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.living.player.Player;

import com.kmecpp.proximitymines.ExplosionUtil;
import com.kmecpp.proximitymines.mine.AbstractMine;
import com.kmecpp.proximitymines.mine.MineBlock;

public class Tier2Mine extends AbstractMine {

	@Override
	public String getName() {
		return "Tier 2";
	}

	@Override
	public BlockType getBlockType() {
		return BlockTypes.GOLD_BLOCK;
	}

	@Override
	public void onExplode(Player target, MineBlock mine) {
		ExplosionUtil.tnt(target.getLocation(), 10);
	}

}
