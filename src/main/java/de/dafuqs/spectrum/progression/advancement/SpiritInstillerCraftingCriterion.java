package de.dafuqs.spectrum.progression.advancement;

import com.google.gson.*;
import de.dafuqs.spectrum.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.item.*;
import net.minecraft.predicate.*;
import net.minecraft.predicate.entity.*;
import net.minecraft.predicate.item.*;
import net.minecraft.server.network.*;
import net.minecraft.util.*;

import java.util.*;

public class SpiritInstillerCraftingCriterion extends AbstractCriterion<SpiritInstillerCraftingCriterion.Conditions> {

	public static final Identifier ID = SpectrumCommon.locate("crafted_with_spirit_instiller");

	public static Conditions create(ItemPredicate[] item, NumberRange.IntRange experienceRange) {
		return new Conditions(LootContextPredicate.EMPTY, item, experienceRange);
	}

	@Override
	public Identifier getId() {
		return ID;
	}

	@Override
	public Conditions conditionsFromJson(JsonObject jsonObject, LootContextPredicate extended, AdvancementEntityPredicateDeserializer advancementEntityPredicateDeserializer) {
		ItemPredicate[] itemPredicates = ItemPredicate.deserializeAll(jsonObject.get("items"));
		NumberRange.IntRange experienceRange = NumberRange.IntRange.fromJson(jsonObject.get("gained_experience"));
		return new Conditions(extended, itemPredicates, experienceRange);
	}

	public void trigger(ServerPlayerEntity player, ItemStack itemStack, int experience) {
		this.trigger(player, (conditions) -> conditions.matches(itemStack, experience));
	}

	public record Conditions implements AbstractCriterion.Conditions {
		private final ItemPredicate[] itemPredicates;
		private final NumberRange.IntRange experienceRange;

		public Conditions(LootContextPredicate player, ItemPredicate[] itemPredicates, NumberRange.IntRange experienceRange) {
			super(ID, player);
			this.itemPredicates = itemPredicates;
			this.experienceRange = experienceRange;
		}

		@Override
		public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer) {
			JsonObject jsonObject = super.toJson(predicateSerializer);
			jsonObject.addProperty("items", Arrays.toString(this.itemPredicates));
			jsonObject.add("gained_experience", this.experienceRange.toJson());
			return jsonObject;
		}

		public boolean matches(ItemStack itemStack, int experience) {
			if (this.experienceRange.test(experience)) {
				List<ItemPredicate> list = new ObjectArrayList<>(this.itemPredicates);
				if (list.isEmpty()) {
					return true;
				} else {
					if (!itemStack.isEmpty()) {
						list.removeIf((itemPredicate) -> itemPredicate.test(itemStack));
					}
					return list.isEmpty();
				}
			} else {
				return false;
			}
		}
	}

}