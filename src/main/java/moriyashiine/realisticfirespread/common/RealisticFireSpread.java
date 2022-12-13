/*
 * All Rights Reserved (c) 2022 MoriyaShiine
 */

package moriyashiine.realisticfirespread.common;

import eu.midnightdust.lib.config.MidnightConfig;
import moriyashiine.realisticfirespread.common.registry.ModEntityComponents;
import moriyashiine.realisticfirespread.mixin.FireBlockAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;

public class RealisticFireSpread implements ModInitializer {
	public static final String MOD_ID = "realisticfirespread";

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, ModConfig.class);
		ServerTickEvents.END_WORLD_TICK.register(world -> {
			if (world.getTime() % 20 == 0 && world.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
				world.iterateEntities().forEach(entity -> {
					if (entity.isOnFire() && (ModConfig.shouldSunlitEntitiesSpreadFire || !entity.getComponent(ModEntityComponents.FIRE_FROM_SUN).isFireFromSun())) {
						BlockPos pos = entity.getBlockPos().add(MathHelper.nextInt(world.random, -1, 1), MathHelper.nextInt(world.random, -1, 1), MathHelper.nextInt(world.random, -1, 1));
						if (world.getBlockState(pos).isAir() && ((FireBlockAccessor) Blocks.FIRE).realisticfirespread$areBlocksAroundFlammable(world, pos)) {
							world.setBlockState(pos, AbstractFireBlock.getState(world, pos));
						}
					}
				});
			}
		});
	}

	public static Identifier id(String value) {
		return new Identifier(MOD_ID, value);
	}
}
