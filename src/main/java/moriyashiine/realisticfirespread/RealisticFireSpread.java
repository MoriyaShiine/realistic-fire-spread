package moriyashiine.realisticfirespread;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import moriyashiine.realisticfirespread.accessor.IsFireFromSunAccessor;
import moriyashiine.realisticfirespread.mixin.FireBlockAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.world.WorldTickCallback;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.Random;

public class RealisticFireSpread implements ModInitializer, WorldTickCallback {
	public static RFSConfig config;
	
	@Override
	public void onInitialize() {
		AutoConfig.register(RFSConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(RFSConfig.class).getConfig();
		WorldTickCallback.EVENT.register(this);
	}
	
	@Override
	public void tick(World world) {
		if (!world.isClient && world instanceof ServerWorld && world.getTime() % 20 == 0 && world.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
			((ServerWorld) world).iterateEntities().forEach(entity -> {
				if (entity.isOnFire() && (config.shouldSunlitEntitiesSpreadFire || !((IsFireFromSunAccessor) entity).getIsFireFromSun())) {
					BlockPos pos = entity.getBlockPos();
					Random rand = world.random;
					pos = pos.add(MathHelper.nextInt(rand, -1, 1), MathHelper.nextInt(rand, -1, 1), MathHelper.nextInt(rand, -1, 1));
					if (world.getBlockState(pos).isAir() && ((FireBlockAccessor) Blocks.FIRE).rfs_areBlocksAroundFlammable(world, pos)) {
						world.setBlockState(pos, AbstractFireBlock.getState(world, pos));
					}
				}
			});
		}
	}
}
