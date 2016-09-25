package com.kmecpp.proximitymines.mine;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;

import com.kmecpp.jlib.utils.StringUtil;
import com.kmecpp.proximitymines.mine.mines.Tier1Mine;
import com.kmecpp.proximitymines.mine.mines.Tier2Mine;
import com.kmecpp.proximitymines.mine.mines.Tier3Mine;
import com.kmecpp.proximitymines.mine.mines.Tier4Mine;

public enum MineType {

	TIER_ONE(BlockTypes.IRON_BLOCK, new Tier1Mine()),
	TIER_TWO(BlockTypes.GOLD_BLOCK, new Tier2Mine()),
	TIER_THREE(BlockTypes.DIAMOND_BLOCK, new Tier3Mine()),
	TIER_FOUR(BlockTypes.EMERALD_BLOCK, new Tier4Mine()),
	SHRAPNEL(BlockTypes.GRAVEL, new Tier1Mine()),
	SONIC(BlockTypes.NOTEBLOCK, new Tier1Mine()),
	CONTAGIOUS(BlockTypes.MYCELIUM, new Tier1Mine()),
	EXOTHERMIC(BlockTypes.LAVA, new Tier1Mine());

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
