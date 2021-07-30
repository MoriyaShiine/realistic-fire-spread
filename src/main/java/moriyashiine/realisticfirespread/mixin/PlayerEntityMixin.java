package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.api.component.FireFromSunComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {
	public PlayerEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}
	
	@Inject(method = "attack", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, ordinal = 1, target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
	private void attack(Entity target, CallbackInfo callbackInfo) {
		if (!world.isClient) {
			FireFromSunComponent.get(target).setFireFromSun(false);
		}
	}
}
