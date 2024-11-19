package de.dafuqs.spectrum.compat.modonomicon.client.pages;

import com.mojang.blaze3d.systems.RenderSystem;
import de.dafuqs.spectrum.SpectrumCommon;
import de.dafuqs.spectrum.compat.modonomicon.pages.BookGatedRecipePage;
import de.dafuqs.spectrum.recipe.anvil_crushing.AnvilCrushingRecipe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class BookAnvilCrushingPageRenderer extends BookGatedRecipePageRenderer<AnvilCrushingRecipe, BookGatedRecipePage<AnvilCrushingRecipe>> {

    private static final Identifier BACKGROUND_TEXTURE = SpectrumCommon.locate("textures/gui/container/anvil_crushing.png");

    public BookAnvilCrushingPageRenderer(BookGatedRecipePage<AnvilCrushingRecipe> page) {
        super(page);
    }

    @Override
    protected int getRecipeHeight() {
        return 73;
    }

    @Override
    protected void drawRecipe(DrawContext drawContext, RecipeEntry<AnvilCrushingRecipe> recipeEntry, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {
        AnvilCrushingRecipe recipe = recipeEntry.value();
        World world = MinecraftClient.getInstance().world;
        if (world == null) return;

        RenderSystem.enableBlend();
        drawContext.drawTexture(BACKGROUND_TEXTURE, recipeX, recipeY + 4, 0, 0, 84, 48, 256, 256);

        renderTitle(drawContext, recipeY, second);

        // the ingredients
        parentScreen.renderIngredient(drawContext, recipeX + 16, recipeY + 35, mouseX, mouseY, recipe.getIngredients().get(0));

        // the anvil
        parentScreen.renderItemStack(drawContext, recipeX + 16, recipeY + 15, mouseX, mouseY, recipe.createIcon());

        // the output
        parentScreen.renderItemStack(drawContext, recipeX + 64, recipeY + 29, mouseX, mouseY, recipe.getResult(world.getRegistryManager()));
    }

}
