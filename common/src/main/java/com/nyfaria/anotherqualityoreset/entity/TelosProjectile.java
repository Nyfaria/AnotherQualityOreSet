package com.nyfaria.anotherqualityoreset.entity;

import com.nyfaria.anotherqualityoreset.init.EntityInit;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;



public class TelosProjectile extends AbstractHurtingProjectile {

    private static final float DAMAGE = 8.0f;
    private static final int MAX_LIFETIME = 100;
    private int lifetime = 0;

    private static final DustParticleOptions BLUE_DUST = new DustParticleOptions(new Vector3f(0.2f, 0.6f, 1.0f), 1.0f);
    private static final DustParticleOptions GREEN_DUST = new DustParticleOptions(new Vector3f(0.2f, 1.0f, 0.4f), 1.0f);
    private static final DustParticleOptions RED_DUST = new DustParticleOptions(new Vector3f(1.0f, 0.2f, 0.3f), 1.0f);

    public TelosProjectile(EntityType<? extends TelosProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public TelosProjectile(Level level, LivingEntity shooter) {
        super(EntityInit.TELOS_PROJECTILE.get(), shooter, 0, 0, 0, level);
        this.setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
    }

    public void shootWithYawOffset(Entity shooter, float xRot, float yRot, float yawOffset, float velocity, float inaccuracy) {
        float adjustedYaw = yRot + yawOffset;
        float xRotRad = (float) Math.toRadians(xRot);
        float yRotRad = (float) Math.toRadians(adjustedYaw);

        double x = -Math.sin(yRotRad) * Math.cos(xRotRad);
        double y = -Math.sin(xRotRad);
        double z = Math.cos(yRotRad) * Math.cos(xRotRad);

        double power = 0.1 * velocity;
        this.xPower = x * power;
        this.yPower = y * power;
        this.zPower = z * power;

        this.setDeltaMovement(x * velocity, y * velocity, z * velocity);
    }

    @Override
    public void tick() {
        super.tick();

        lifetime++;
        if (lifetime > MAX_LIFETIME) {
            this.discard();
            return;
        }

        if (this.level().isClientSide) {
            spawnParticleTrail();
        }
    }

    private void spawnParticleTrail() {
        Vec3 velocity = this.getDeltaMovement();

        for (int i = 0; i < 3; i++) {
            double offsetX = (this.random.nextDouble() - 0.5) * 0.2;
            double offsetY = (this.random.nextDouble() - 0.5) * 0.2;
            double offsetZ = (this.random.nextDouble() - 0.5) * 0.2;

            double x = this.getX() + offsetX;
            double y = this.getY() + offsetY;
            double z = this.getZ() + offsetZ;

            if (this.random.nextFloat() < 0.6f) {
                this.level().addParticle(BLUE_DUST, x, y, z,
                        -velocity.x * 0.1, -velocity.y * 0.1, -velocity.z * 0.1);
            }

            if (this.random.nextFloat() < 0.3f) {
                this.level().addParticle(GREEN_DUST, x, y, z,
                        offsetX * 0.05, offsetY * 0.05, offsetZ * 0.05);
            }

            if (this.random.nextFloat() < 0.2f) {
                this.level().addParticle(RED_DUST, x, y, z,
                        -velocity.x * 0.05, -velocity.y * 0.05, -velocity.z * 0.05);
            }
        }

        this.level().addParticle(ParticleTypes.END_ROD, this.getX(), this.getY(), this.getZ(),
                0, 0, 0);
    }

    @Override
    protected void onHitEntity( EntityHitResult result) {
        super.onHitEntity(result);

        Entity target = result.getEntity();
        Entity owner = this.getOwner();

        if (target != owner) {
            DamageSource damageSource = this.damageSources().indirectMagic(this, owner);
            target.hurt(damageSource, DAMAGE);

            if (this.level().isClientSide) {
                spawnHitParticles();
            }

            this.discard();
        }
    }

    @Override
    protected void onHitBlock( BlockHitResult result) {
        super.onHitBlock(result);

        if (this.level().isClientSide) {
            spawnHitParticles();
        }

        this.discard();
    }

    private void spawnHitParticles() {
        for (int i = 0; i < 8; i++) {
            double offsetX = (this.random.nextDouble() - 0.5) * 0.5;
            double offsetY = (this.random.nextDouble() - 0.5) * 0.5;
            double offsetZ = (this.random.nextDouble() - 0.5) * 0.5;

            this.level().addParticle(BLUE_DUST,
                    this.getX(), this.getY(), this.getZ(),
                    offsetX, offsetY, offsetZ);
            this.level().addParticle(GREEN_DUST,
                    this.getX(), this.getY(), this.getZ(),
                    offsetX * 0.5, offsetY * 0.5, offsetZ * 0.5);
            this.level().addParticle(RED_DUST,
                    this.getX(), this.getY(), this.getZ(),
                    offsetX * 0.3, offsetY * 0.3, offsetZ * 0.3);
        }
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return BLUE_DUST;
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt( DamageSource source, float amount) {
        return false;
    }
}
