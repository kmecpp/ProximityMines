package com.kmecpp.proximitymines;

import java.util.HashMap;
import java.util.Optional;

import org.spongepowered.api.block.BlockType;

import com.kmecpp.proximitymines.mine.AbstractMine;
import com.kmecpp.proximitymines.mine.MineExplosion;

public class MineRegistry {

	//	private static HashMap<BlockType, MineExplosion> customExplosives = new HashMap<>();
	private static HashMap<BlockType, AbstractMine> mines = new HashMap<>();

	/**
	 * Registers the {@link BlockType} to be associated with the given
	 * {@link MineExplosion}. If the given block type is already associated with
	 * an explosion, the previous will be overwritten
	 * 
	 * @param type
	 *            the block type for associate
	 * @param mine
	 */
	public static void register(BlockType type, AbstractMine mine) {
		mines.put(type, mine);
	}

	/**
	 * Tests whether or not the given {@link BlockType} is associated with an
	 * explosion
	 * 
	 * @param type
	 *            the block type to test
	 * @return true if the block type has an explosion, false if it does not
	 */
	public static boolean isCustom(BlockType type) {
		return mines.containsKey(type);
	}

	/**
	 * Tests whether or not a default {@link MineType} associated block has been
	 * overridden by a custom one
	 * 
	 * @param type
	 *            the mine type to test
	 * @return true if the mine has been overridden and false if has not
	 */
	public static boolean isOverriden(BlockType type) {
		return isCustom(type);
	}

	/**
	 * Gets the {@link MineExplosion} associated with the given
	 * {@link BlockType}. Custom mines override default ones.
	 * 
	 * @param type
	 *            the block type
	 * @return the explosion associated with the block type
	 */
	public static Optional<? extends AbstractMine> getMine(BlockType type) {
		AbstractMine mine = mines.get(type);
		return mine != null
				? Optional.of(mine)
				: Optional.empty();

		//		//Search through custom explosions
		//		MineExplosion explosion = mines.get(type);
		//
		//		//Search through default mines
		//		if (explosion == null) {
		//			Optional<MineType> mineType = MineType.fromBlock(type);
		//			if (mineType.isPresent()) {
		//				explosion = mineType.get().getExplosion();
		//			}
		//		}
		//
		//		return explosion != null
		//				? Optional.of(explosion)
		//				: Optional.empty();
	}

	/**
	 * Gets the backing map of all custom explosion associations
	 * 
	 * @return the map of all custom explosives
	 */
	public static HashMap<BlockType, AbstractMine> getCustomExplosives() {
		return mines;
	}

}
