/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.common.init.ModEntityComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends HostileEntity {
	protected ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(F)V"))
	private void realisticfirespread$allowFireSpread(Entity target, CallbackInfoReturnable<Boolean> cir) {
		if (!getWorld().isClient) {
			ModEntityComponents.ALLOW_FIRE_SPREAD.get(target).setAllowFireSpread(true);
		}
	}
}
