package de.dafuqs.spectrum.items.food.beverages;

import de.dafuqs.spectrum.api.item.*;
import de.dafuqs.spectrum.items.food.beverages.properties.*;
import net.minecraft.client.item.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.*;

public class JadeWineItem extends BeverageItem {
	
	public JadeWineItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public BeverageProperties getBeverageProperties(ItemStack itemStack) {
		return JadeWineBeverageProperties.getFromStack(itemStack);
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);
		if (FermentedItem.isPreviewStack(stack)) {
			String translationKey = getTranslationKey();
			tooltip.add(Text.translatable(translationKey + ".tooltip.preview").formatted(Formatting.GRAY));
			tooltip.add(Text.translatable(translationKey + ".tooltip.preview2").formatted(Formatting.GRAY));
			tooltip.add(Text.translatable("item.spectrum.tooltip.could_use_some_sweetener").formatted(Formatting.GRAY));
		}
	}
	
}
