package moriyashiine.realisticfirespread;

import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Random;

@Mod("realisticfirespread")
public class RealisticFireSpread {
	public RealisticFireSpread() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new Handler());
	}
	
	private static class Handler {
		@SubscribeEvent
		public void worldTick(TickEvent.WorldTickEvent event) {
			World world = event.world;
			if (!world.isRemote && world instanceof ServerWorld && world.getGameTime() % 20 == 0 && world.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) ((ServerWorld) world).getEntities().forEach(entity -> {
				if (entity.isBurning()) {
					BlockPos pos = entity.getPosition();
					Random rand = world.rand;
					pos = pos.add(MathHelper.nextInt(rand, -1, 1), MathHelper.nextInt(rand, -1, 1), MathHelper.nextInt(rand, -1, 1));
					if (world.getBlockState(pos).isAir(world, pos)) {
						for (Direction direction : Direction.values()) {
							if (world.getBlockState(pos.offset(direction)).isFlammable(world, pos.offset(direction), direction)) {
								world.setBlockState(pos, ((FireBlock) Blocks.FIRE).getStateForPlacement(world, pos));
								break;
							}
						}
					}
				}
			});
		}
	}
}