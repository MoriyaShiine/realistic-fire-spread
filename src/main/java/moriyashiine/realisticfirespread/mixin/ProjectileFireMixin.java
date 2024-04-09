/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.common.init.ModEntityComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PersistentProjectileEntity.class, SmallFireballEntity.class})
public abstract class ProjectileFireMixin extends Entity {
	public ProjectileFireMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
	private void realisticfirespread$allowFireSpread(EntityHitResult entityHitResult, CallbackInfo ci) {
		if (!getWorld().isClient) {
			ModEntityComponents.ALLOW_FIRE_SPREAD.get(entityHitResult.getEntity()).setAllowFireSpread(true);
		}
	}
}
