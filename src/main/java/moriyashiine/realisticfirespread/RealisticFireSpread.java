package moriyashiine.realisticfirespread;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import moriyashiine.realisticfirespread.accessor.IsFireFromSunAccessor;
import moriyashiine.realisticfirespread.mixin.FireBlockAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;

public class RealisticFireSpread implements ModInitializer, ServerTickEvents.EndWorldTick {
	public static RFSConfig config;
	
	@Override
	public void onInitialize() {
		AutoConfig.register(RFSConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(RFSConfig.class).getConfig();
		ServerTickEvents.END_WORLD_TICK.register(this);
	}
	
	@Override
	public void onEndTick(ServerWorld world) {
		if (world.getTime() % 20 == 0 && world.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
			world.iterateEntities().forEach(entity -> {
				if (entity.isOnFire() && (config.shouldSunlitEntitiesSpreadFire || !((IsFireFromSunAccessor) entity).getIsFireFromSun())) {
					BlockPos pos = entity.getBlockPos().add(MathHelper.nextInt(world.random, -1, 1), MathHelper.nextInt(world.random, -1, 1), MathHelper.nextInt(world.random, -1, 1));
					if (world.getBlockState(pos).isAir() && ((FireBlockAccessor) Blocks.FIRE).rfs_areBlocksAroundFlammable(world, pos)) {
						world.setBlockState(pos, AbstractFireBlock.getState(world, pos));
					}
				}
			});
		}
	}
}
