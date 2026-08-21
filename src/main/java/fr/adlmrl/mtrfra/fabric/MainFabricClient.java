package fr.adlmrl.mtrfra.fabric;

//? if fabric {

import fr.adlmrl.mtrfra.mod.Init;
import net.fabricmc.api.ClientModInitializer;

public class MainFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Init.initClient();
    }

}

//? }
