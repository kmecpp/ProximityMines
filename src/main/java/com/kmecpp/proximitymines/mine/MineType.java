package com.kmecpp.proximitymines.mine;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;

import com.kmecpp.jlib.StringUtil;
import com.kmecpp.proximitymines.explosion.Tier1Explosion;
import com.kmecpp.proximitymines.explosion.Tier2Explosion;
import com.kmecpp.proximitymines.explosion.Tier3Explosion;
import com.kmecpp.proximitymines.explosion.Tier4Explosion;

public enum MineType {

	TIER_ONE(BlockTypes.IRON_BLOCK, new Tier1Explosion()),
	TIER_TWO(BlockTypes.GOLD_BLOCK, new Tier2Explosion()),
	TIER_THREE(BlockTypes.DIAMOND_BLOCK, new Tier3Explosion()),
	TIER_FOUR(BlockTypes.EMERALD_BLOCK, new Tier4Explosion()),
	SHRAPNEL(BlockTypes.GRAVEL, new Tier1Explosion()),
	SONIC(BlockTypes.NOTEBLOCK, new Tier1Explosion()),
	CONTAGIOUS(BlockTypes.MYCELIUM, new Tier1Explosion()),
	EXOTHERMIC(BlockTypes.LAVA, new Tier1Explosion());

	private BlockType type;
	private MineExplosion explosion;

	private MineType(BlockType type, MineExplosion explosion) {
		this.type = type;
		this.explosion = explosion;
	}

	/**
	 * Gets the {@link BlockType} associated with this {@link MineType}
	 * 
	 * @return the block type for this mine type
	 */
	public BlockType getType() {
		return type;
	}

	/**
	 * Gets the {@link MineExplosion} associated with this mine type
	 * 
	 * @return this mine type's explosion
	 */
	public MineExplosion getExplosion() {
		return explosion;
	}

	/**
	 * Gets the name of the {@link MineType}, as a capitalized String
	 * 
	 * @return the name of the mine type
	 */
	public String getName() {
		return Stream.of(name().split("_"))
				.map((str) -> StringUtil.capitalize(str))
				.collect(Collectors.joining(" "));
	}

	/**
	 * Gets a {@link MineType} from the given {@link BlockSnapshot} if one
	 * exists
	 * 
	 * @param blockSnapshot
	 *            the block snapshot
	 * @return the mine type for the given block snapshot
	 */
	public static Optional<MineType> fromBlock(BlockSnapshot blockSnapshot) {
		return fromBlock(blockSnapshot.getState().getType());
	}

	/**
	 * Gets a {@link MineType} from the given {@link BlockType} if one exists
	 * 
	 * @param blockType
	 *            the block type whose mine to get
	 * @return the mine type for the given block type
	 */
	public static Optional<MineType> fromBlock(BlockType blockType) {
		return Arrays.stream(values())
				.filter((mine) -> mine.type == blockType)
				.findFirst();
	}

}
