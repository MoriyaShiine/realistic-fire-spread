/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.common.init.ModEntityComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@Shadow
	public abstract World getWorld();

	@Inject(method = "onStruckByLightning", at = @At("TAIL"))
	private void realisticfirespread$allowFireSpread(ServerWorld world, LightningEntity lightning, CallbackInfo ci) {
		ModEntityComponents.ALLOW_FIRE_SPREAD.get(this).setAllowFireSpread(true);
	}

	@Inject(method = "setOnFireFromLava", at = @At("TAIL"))
	private void realisticfirespread$allowFireSpread(CallbackInfo ci) {
		if (!getWorld().isClient) {
			ModEntityComponents.ALLOW_FIRE_SPREAD.get(this).setAllowFireSpread(true);
		}
	}
}
