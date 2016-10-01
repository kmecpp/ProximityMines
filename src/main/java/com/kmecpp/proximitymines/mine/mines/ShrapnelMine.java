package com.kmecpp.proximitymines.mine.mines;

import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;

import com.kmecpp.proximitymines.ExplosionUtil;
import com.kmecpp.proximitymines.mine.AbstractMine;
import com.kmecpp.proximitymines.mine.MineBlock;

public class ShrapnelMine extends AbstractMine {

	@Override
	public String getName() {
		return "Shrapnel";
	}

	@Override
	public BlockType getBlockType() {
		return BlockTypes.GRAVEL;
	}

	@Override
	public void onExplode(Player target, MineBlock mine, Cause cause) {
		ExplosionUtil.tnt(target.getLocation(), 5, cause);
		ExplosionUtil.shrapnel(target.getLocation(), 10, 100, cause);
	}

}
