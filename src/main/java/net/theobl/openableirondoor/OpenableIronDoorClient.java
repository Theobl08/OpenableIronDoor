package net.theobl.openableirondoor;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = OpenableIronDoor.MODID, dist = Dist.CLIENT)
public class OpenableIronDoorClient {
    public OpenableIronDoorClient(ModContainer container) {
        // This will use NeoForge's ConfigurationScreen to display this mod's configs
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
