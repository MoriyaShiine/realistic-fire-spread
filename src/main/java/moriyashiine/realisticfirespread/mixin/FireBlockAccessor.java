package moriyashiine.realisticfirespread.mixin;

import net.minecraft.block.FireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FireBlock.class)
public interface FireBlockAccessor {
	@Invoker("areBlocksAroundFlammable")
	boolean rfs_areBlocksAroundFlammable(BlockView world, BlockPos pos);
}
