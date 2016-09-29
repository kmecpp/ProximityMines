package com.kmecpp.proximitymines.mine.mines;

import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;

import com.kmecpp.proximitymines.mine.AbstractMine;
import com.kmecpp.proximitymines.mine.ExplosionThread;
import com.kmecpp.proximitymines.mine.MineBlock;

public class ExothermicMine extends AbstractMine {

	@Override
	public String getName() {
		return "Exothermic";
	}

	@Override
	public BlockType getBlockType() {
		return BlockTypes.LAVA;
	}

	@Override
	public void onExplode(Player target, MineBlock mine, Cause cause) {
		//		int radius = 10;
		//		for (int theta = 0; theta < 360; theta++) {
		//			target.getLocation().add(radius * Math.cos(theta), 0, radius * Math.sin(theta)).setBlockType(BlockTypes.DIAMOND_BLOCK, cause);
		//		}
		ExplosionThread.start((thread) -> {
			int startRadius = thread.getTicks();
			for (int r = startRadius; r < startRadius + 5; r++) {
				for (int theta = 0; theta < 360; theta++) {
					target.getLocation().add(r * Math.cos(theta), 0, r * Math.sin(theta));
				}
			}
		}, 10, 3);
		//TODO
	}

	//	private static Location<World> highestBlockLocation(Location<World> location) {
	//		int upper = location.getExtent().getBlockMax().getY();
	//		int lastMargin = 0;
	//		Location<World> loc = new Location<World>(location.getExtent(),
	//				location.getX(), upper / 2, location.getZ());
	//
	//		while (true) {
	//			if (loc.getBlockType() == BlockTypes.AIR) {
	//				if (loc.add(0, -1, 0).getBlockType() != BlockTypes.AIR) {
	//					return loc.add(0, -1, 0);
	//				}
	//				loc.add(0, upper - loc.getY() / 2, 0);
	//			} else {
	//				if (loc.add(0, 1, 0).getBlockType() != BlockTypes.AIR) {
	//					return loc;
	//				}
	//
	//			}
	//		}
	//	}
	//
	//	private static void burnBlock(Location<World> location, Cause cause) {
	//		location.setBlockType(RAND.nextFloat() < 0.80
	//				? BlockTypes.NETHERRACK : RAND.nextFloat() < 0.20 ? BlockTypes.SOUL_SAND : BlockTypes.DIRT, cause);
	//		if (RAND.nextFloat() < 0.30) {
	//			location.getBlock().with(Keys.IS_AFLAME, true);
	//		}
	//	}

}
