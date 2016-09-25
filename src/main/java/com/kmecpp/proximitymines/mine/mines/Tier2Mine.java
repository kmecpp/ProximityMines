package com.kmecpp.proximitymines.mine.mines;

import org.spongepowered.api.entity.living.player.Player;

import com.kmecpp.proximitymines.ExplosionUtil;
import com.kmecpp.proximitymines.mine.MineBlock;
import com.kmecpp.proximitymines.mine.MineExplosion;

public class Tier2Mine implements MineExplosion {

	@Override
	public void onExplode(Player target, MineBlock mine) {
		ExplosionUtil.tnt(target.getLocation(), 10);
	}

}