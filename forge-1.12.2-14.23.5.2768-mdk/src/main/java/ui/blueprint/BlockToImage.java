package ui.blueprint;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BlockToImage {

    public static Image getImageForBlock(Block block) {
        TextureAtlasSprite textureAtlasSprite = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(block.getDefaultState());
        ResourceLocation resourceLocation = getResourceLocation(textureAtlasSprite);
        IResource resource = null;
        try {
            resource = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Image img = null;
        try {
            img = ImageIO.read(resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // some of these sprites are different sizes :(
        if(((BufferedImage) img).getHeight() > 16 || ((BufferedImage) img).getWidth() > 16){
            img = ((BufferedImage) img).getSubimage(0, 0, 16, 16);
        }

        return img;
    }

    // stole this private method from TextureMap.java
    private static ResourceLocation getResourceLocation(TextureAtlasSprite textureAtlasSprite)
    {
        ResourceLocation resourcelocation = new ResourceLocation(textureAtlasSprite.getIconName());
        return new ResourceLocation(resourcelocation.getResourceDomain(), String.format("%s/%s%s", "textures", resourcelocation.getResourcePath(), ".png"));
    }
}
