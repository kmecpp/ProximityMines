package com.kmecpp.proximitymines.mine;

import org.spongepowered.api.block.BlockType;

import com.kmecpp.proximitymines.MineRegistry;

public abstract class AbstractMine implements MineExplosion {

	public abstract BlockType getBlockType();

	public final void register() {
		MineRegistry.register(getBlockType(), this);
	}

}
