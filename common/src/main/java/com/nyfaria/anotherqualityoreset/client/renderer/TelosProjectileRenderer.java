package com.nyfaria.anotherqualityoreset.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.nyfaria.anotherqualityoreset.Constants;
import com.nyfaria.anotherqualityoreset.entity.TelosProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class TelosProjectileRenderer extends EntityRenderer<TelosProjectile> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/telos_projectile.png");
    private static final RenderType RENDER_TYPE = RenderType.entityTranslucentEmissive(TEXTURE);

    public TelosProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(TelosProjectile entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, entity.yRotO, entity.getYRot()) - 90.0f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, entity.xRotO, entity.getXRot())));

        float scale = 0.3f;
        poseStack.scale(scale, scale, scale);

        float spinAngle = (entity.tickCount + partialTick) * 20.0f;
        poseStack.mulPose(Axis.XP.rotationDegrees(spinAngle));

        VertexConsumer vertexConsumer = buffer.getBuffer(RENDER_TYPE);
        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix4f = pose.pose();
        Matrix3f matrix3f = pose.normal();

        renderQuad(vertexConsumer, matrix4f, matrix3f, packedLight);

        poseStack.popPose();

        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    private void renderQuad(VertexConsumer consumer, Matrix4f matrix4f, Matrix3f matrix3f, int packedLight) {
        float size = 1.0f;

        int r = 100;
        int g = 220;
        int b = 255;
        int a = 255;

        int light = 15728880;

        vertex(consumer, matrix4f, matrix3f, -size, -size, 0, 0, 1, r, g, b, a, light);
        vertex(consumer, matrix4f, matrix3f, size, -size, 0, 1, 1, r, g, b, a, light);
        vertex(consumer, matrix4f, matrix3f, size, size, 0, 1, 0, r, g, b, a, light);
        vertex(consumer, matrix4f, matrix3f, -size, size, 0, 0, 0, r, g, b, a, light);

        vertex(consumer, matrix4f, matrix3f, size, -size, 0, 0, 1, r, g, b, a, light);
        vertex(consumer, matrix4f, matrix3f, -size, -size, 0, 1, 1, r, g, b, a, light);
        vertex(consumer, matrix4f, matrix3f, -size, size, 0, 1, 0, r, g, b, a, light);
        vertex(consumer, matrix4f, matrix3f, size, size, 0, 0, 0, r, g, b, a, light);
    }

    private void vertex(VertexConsumer consumer, Matrix4f matrix4f, Matrix3f matrix3f,
                        float x, float y, float z, float u, float v,
                        int r, int g, int b, int a, int light) {
        consumer.vertex(matrix4f, x, y, z)
                .color(r, g, b, a)
                .uv(u, v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(matrix3f, 0.0f, 1.0f, 0.0f)
                .endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(TelosProjectile entity) {
        return TEXTURE;
    }
}
