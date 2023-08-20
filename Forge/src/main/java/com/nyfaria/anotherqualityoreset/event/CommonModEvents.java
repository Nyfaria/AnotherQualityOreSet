package com.nyfaria.anotherqualityoreset.event;

import com.nyfaria.anotherqualityoreset.init.EntityInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CommonModEvents {


    @SubscribeEvent
    public static void attribs(EntityAttributeCreationEvent e) {
        EntityInit.attributeSuppliers.forEach(p -> e.put(p.entityTypeSupplier().get(), p.factory().get().build()));
    }
}
