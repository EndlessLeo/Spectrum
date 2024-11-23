package de.dafuqs.spectrum.enchantments;

import net.minecraft.entity.*;
import net.minecraft.util.*;

public class PestControlEnchantment extends SpectrumEnchantment {
	
	public PestControlEnchantment(Rarity weight, Identifier unlockAdvancementIdentifier, EquipmentSlot... slotTypes) {
		super(weight, EnchantmentTarget.DIGGER, slotTypes, unlockAdvancementIdentifier);
	}
	
	@Override
	public int getMinPower(int level) {
		return 10;
	}
	
	@Override
	public int getMaxPower(int level) {
		return super.getMinPower(level) + 20;
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
}