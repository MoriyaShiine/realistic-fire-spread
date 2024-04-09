/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.common.init.ModEntityComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
	protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
	private void realisticfirespread$allowFireSpread(Entity target, CallbackInfoReturnable<Boolean> cir) {
		if (!getWorld().isClient) {
			ModEntityComponents.ALLOW_FIRE_SPREAD.get(target).setAllowFireSpread(true);
		}
	}
}
