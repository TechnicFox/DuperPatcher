package org.meeno.duperpatcher.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends Entity
{
    private static final Logger LOGGER = LoggerFactory.getLogger("DragonEggPatcher");
    @Shadow
    private BlockState blockState;

    @Shadow
    public abstract BlockPos getFallingBlockPos();

    public FallingBlockEntityMixin(EntityType<? extends FallingBlockEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    shift = At.Shift.AFTER,
                    target = "Lnet/minecraft/entity/FallingBlockEntity;tickPortalTeleportation()V"
            ),
            cancellable = true
    )
    private void afterMove(CallbackInfo ci)
    {
        if (this.isRemoved() && blockState.isOf(Blocks.DRAGON_EGG)) {
            LOGGER.info("Prevented a gravity block dupe attempt at: {}", this.getFallingBlockPos());
            ci.cancel();
        }
    }
}
