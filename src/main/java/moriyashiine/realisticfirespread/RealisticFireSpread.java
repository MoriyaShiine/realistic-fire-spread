package moriyashiine.realisticfirespread;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.world.WorldTickCallback;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.Random;

public class RealisticFireSpread implements ModInitializer, WorldTickCallback {
	@Override
	public void onInitialize() {
		WorldTickCallback.EVENT.register(this);
	}
	
	@Override
	public void tick(World world) {
		if (!world.isClient && world instanceof ServerWorld && world.getTime() % 20 == 0 && world.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
			((ServerWorld) world).iterateEntities().forEach(entity -> {
				if (entity.isOnFire()) {
					BlockPos pos = entity.getBlockPos();
					Random rand = world.random;
					pos = pos.add(MathHelper.nextInt(rand, -1, 1), MathHelper.nextInt(rand, -1, 1), MathHelper.nextInt(rand, -1, 1));
					if (world.getBlockState(pos).isAir()) {
						for (Direction direction : Direction.values()) {
							if (AbstractFireBlock.method_30032(world, pos.offset(direction))) {
								world.setBlockState(pos, AbstractFireBlock.getState(world, pos));
								break;
							}
						}
					}
				}
			});
		}
	}
}