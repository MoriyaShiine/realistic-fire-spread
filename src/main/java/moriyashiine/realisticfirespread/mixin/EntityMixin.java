package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.common.registry.ModComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@Inject(method = "onStruckByLightning", at = @At("HEAD"))
	private void realisticfirespread$removeFireFromSun(ServerWorld world, LightningEntity lightning, CallbackInfo ci) {
		if (!world.isClient) {
			ModComponents.FIRE_FROM_SUN_COMPONENT.get(this).setFireFromSun(false);
		}
	}
}
