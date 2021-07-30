package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.api.component.FireFromSunComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin extends Entity {
	public PersistentProjectileEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}
	
	@Inject(method = "onEntityHit", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
	private void onEntityHit(EntityHitResult entityHitResult, CallbackInfo callbackInfo) {
		if (!world.isClient) {
			FireFromSunComponent.get(entityHitResult.getEntity()).setFireFromSun(false);
		}
	}
}
