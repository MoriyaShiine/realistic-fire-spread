package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.api.component.FireFromSunComponent;
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
	private void onStruckByLightning(ServerWorld world, LightningEntity lightning, CallbackInfo callbackInfo) {
		if (!world.isClient) {
			FireFromSunComponent.get((Entity) (Object) this).setFireFromSun(false);
		}
	}
}
