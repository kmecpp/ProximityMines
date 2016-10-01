package com.kmecpp.proximitymines.mine;

import java.util.Optional;

import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;
import com.kmecpp.proximitymines.MineRegistry;
import com.kmecpp.proximitymines.ProximityMines;

public class MineBlock {

	private BlockType type;
	private Location<World> location;

	public MineBlock(BlockType type, Location<World> location) {
		this.type = type;
		this.location = location;
	}

	public BlockType getType() {
		return type;
	}

	public Location<World> getLocation() {
		return location;
	}

	public Vector3i getPosition() {
		return location.getBlockPosition();
	}

	public double distanceTo(Vector3d vector) {
		return location.getPosition().distance(vector);
	}

	public void explode(Player target) {
		Cause cause = Cause.source(ProximityMines.getPlugin()).named("mine", this).named("target", target).build();
		location.removeBlock(cause); //TNT
		location.add(0, -1, 0).removeBlock(cause); //Type block
		MineRegistry.getMine(type).get().onExplode(target, this, cause);
	}

	/**
	 * Creates a mine instance from the physical mine at the specified location.
	 * If not mine exists at that location the Optional will be empty. The
	 * location MUST BE the location of the actual mine (TNT Block) of the
	 * multiblock structure or it will not be detected.
	 * 
	 * @param location
	 *            the location from which to retrieve the mine instance
	 * @return a mine instance representing the one at the given location,
	 *         assuming it exists
	 */
	public static Optional<MineBlock> fromLocation(Location<World> location) {
		if (location.getBlockType() == BlockTypes.TNT) {
			Optional<? extends AbstractMine> mine = MineRegistry.getMine(location.add(0, -1, 0).getBlockType());
			if (mine.isPresent()) {
				return Optional.of(new MineBlock(mine.get().getBlockType(), location));
			}
			//			Optional<MineType> mineType = MineType.fromBlock(location.add(0, -1, 0).getBlockType());
			//			if (mineType.isPresent()) {
			//				return Optional.of(new MineBlock(mineType.get(), location));
			//			}
		}
		return Optional.empty();
	}

}
