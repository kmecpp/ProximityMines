package com.kmecpp.proximitymines.explosion;

import org.spongepowered.api.entity.living.player.Player;

import com.kmecpp.proximitymines.ExplosionUtil;
import com.kmecpp.proximitymines.mine.Mine;
import com.kmecpp.proximitymines.mine.MineExplosion;

public class Tier2Explosion implements MineExplosion {

	@Override
	public void onExplode(Player target, Mine mine) {
		ExplosionUtil.tnt(target.getLocation(), 10);
	}

}