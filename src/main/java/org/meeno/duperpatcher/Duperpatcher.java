package org.meeno.duperpatcher;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Duperpatcher implements ModInitializer {
    private static final Logger LOGGER = LogManager.getLogger("DuperPatcher");

    @Override
    public void onInitialize() {
        LOGGER.info("DuperPatcher is initializing...");
    }
}
