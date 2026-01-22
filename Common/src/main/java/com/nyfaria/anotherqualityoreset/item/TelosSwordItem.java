package com.nyfaria.anotherqualityoreset.item;

import com.nyfaria.anotherqualityoreset.entity.TelosProjectile;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class TelosSwordItem extends SwordItem {

    private static final float PROJECTILE_VELOCITY = 1.5f;
    private static final float PROJECTILE_INACCURACY = 0.0f;

    public TelosSwordItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }


    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        Level level = entity.level();

        if (!level.isClientSide) {
            shootProjectile(level, entity, 0.0f);
            shootProjectile(level, entity, 30.0f);
            shootProjectile(level, entity, -30.0f);
        }

        return false;
    }

    private void shootProjectile(Level level, LivingEntity shooter, float yawOffset) {
        TelosProjectile projectile = new TelosProjectile(level, shooter);
        projectile.shootWithYawOffset(shooter, shooter.getXRot(), shooter.getYRot(), yawOffset,
                PROJECTILE_VELOCITY, PROJECTILE_INACCURACY);
        level.addFreshEntity(projectile);
    }
}
