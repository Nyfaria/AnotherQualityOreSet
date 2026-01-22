package com.nyfaria.anotherqualityoreset;

import com.nyfaria.anotherqualityoreset.client.renderer.TelosProjectileRenderer;
import com.nyfaria.anotherqualityoreset.init.EntityInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class AnotherQualityOreSetClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        // Register entity renderers
        EntityRendererRegistry.register(EntityInit.TELOS_PROJECTILE.get(), TelosProjectileRenderer::new);
    }
}
