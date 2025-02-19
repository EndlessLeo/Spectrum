package de.dafuqs.spectrum.items.tooltip;

import net.fabricmc.api.*;
import net.minecraft.client.font.*;
import net.minecraft.client.gui.*;
import net.minecraft.item.*;

import java.util.*;

@Environment(EnvType.CLIENT)
public class PresentTooltipComponent extends SpectrumTooltipComponent {
	
	private final List<ItemStack> itemStacks;
	
	public PresentTooltipComponent(PresentTooltipData data) {
		this.itemStacks = data.getItemStacks();
	}
	
	@Override
	public int getHeight() {
		return 20 + 2 + 4;
	}
	
	@Override
	public int getWidth(TextRenderer textRenderer) {
		return this.itemStacks.size() * 20 + 2 + 4;
	}
	
	@Override
	public void drawItems(TextRenderer textRenderer, int x, int y, DrawContext context) {
		int n = x + 1;
		int o = y + 1;
		for (int i = 0; i < this.itemStacks.size(); i++) {
			this.drawSlot(context, n + i * 18, o, i, this.itemStacks.get(i), textRenderer);
		}
		this.drawOutline(context, x, y, this.itemStacks.size(), 1);
	}
	
}
