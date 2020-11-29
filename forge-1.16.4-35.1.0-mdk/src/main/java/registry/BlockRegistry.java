package registry;

import com.minecraftcivproject.mcp.server.managers.building.construction.resource.ResourceBinInventory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class BlockRegistry {

    private static Map<String, Block> registry = new HashMap<>();

    public static void addBlock(Block block, String id) {
        registry.put(id, block);
    }

    public static Block getBlock(String id) {
        return registry.get(id);
    }

    // Blocks -> RegistryNamespacedDefaultedByKey getObject -> RegistryNamespaced getObject -> RegistrySimple getObject
    // Block.REGISTRY is a RegistryNamespacedDefaultedByKey<ResourceLocation, Block>, and is set to GameData.getWrapperDefaulted(Block.class) in Block
    @Nullable
    public static Block getRegisteredBlock(String registryName) {
        return (Block)Block.REGISTRY.getObject(new ResourceLocation(registryName));
    }

}
