/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.realisticfirespread.common.event;

import moriyashiine.realisticfirespread.common.ModConfig;
import moriyashiine.realisticfirespread.common.registry.ModEntityComponents;
import moriyashiine.realisticfirespread.mixin.FireBlockAccessor;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;

public class FireSpreadEvent implements ServerTickEvents.EndWorldTick {
	@Override
	public void onEndTick(ServerWorld world) {
		if (world.getTime() % 20 == 0 && world.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
			for (Entity entity : world.iterateEntities()) {
				if (entity.isOnFire() && (ModConfig.spreadFireFromSun || !ModEntityComponents.FIRE_FROM_SUN.get(entity).isFireFromSun())) {
					BlockPos pos = entity.getBlockPos().add(MathHelper.nextInt(world.random, -1, 1), MathHelper.nextInt(world.random, -1, 1), MathHelper.nextInt(world.random, -1, 1));
					if (world.getBlockState(pos).isAir() && ((FireBlockAccessor) Blocks.FIRE).realisticfirespread$areBlocksAroundFlammable(world, pos)) {
						world.setBlockState(pos, AbstractFireBlock.getState(world, pos));
					}
				}
			}
		}
	}
}
