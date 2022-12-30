package com.kenza.discholder;

import com.kenza.discholder.registry.ModRegistriesClient;
import io.github.cottonmc.cotton.gui.impl.client.LibGuiClient;
import net.fabricmc.api.ClientModInitializer;


//
public class DiscHolderFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModRegistriesClient.INSTANCE.onInit();
    }
}


