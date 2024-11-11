package de.dafuqs.spectrum.progression.advancement;

import com.google.gson.*;
import de.dafuqs.spectrum.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.item.*;
import net.minecraft.predicate.entity.*;
import net.minecraft.predicate.item.*;
import net.minecraft.server.network.*;
import net.minecraft.util.*;

public class ConsumedTeaWithSconeCriterion extends AbstractCriterion<ConsumedTeaWithSconeCriterion.Conditions> {

	public static final Identifier ID = SpectrumCommon.locate("consumed_tea_with_scone");

	public static Conditions create(ItemPredicate teaItemPredicate, ItemPredicate sconeItemPredicate) {
		return new Conditions(LootContextPredicate.EMPTY, teaItemPredicate, sconeItemPredicate);
	}

	@Override
	public Identifier getId() {
		return ID;
	}

	@Override
	public Conditions conditionsFromJson(JsonObject jsonObject, LootContextPredicate extended, AdvancementEntityPredicateDeserializer deserializer) {
		ItemPredicate teaItemPredicate = ItemPredicate.fromJson(jsonObject.get("tea_items"));
		ItemPredicate sconeItemPredicate = ItemPredicate.fromJson(jsonObject.get("scone_items"));
		return new Conditions(extended, teaItemPredicate, sconeItemPredicate);
	}

	public void trigger(ServerPlayerEntity player, ItemStack teaStack, ItemStack sconeStack) {
		this.trigger(player, (conditions) -> conditions.matches(teaStack, sconeStack));
	}

	public static class Conditions extends AbstractCriterionConditions {
		private final ItemPredicate teaItemPredicate;
		private final ItemPredicate sconeItemPredicate;

		public Conditions(LootContextPredicate player, ItemPredicate teaItemPredicate, ItemPredicate sconeItemPredicate) {
			super(ID, player);
			this.teaItemPredicate = teaItemPredicate;
			this.sconeItemPredicate = sconeItemPredicate;
		}

		@Override
		public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer) {
			JsonObject jsonObject = super.toJson(predicateSerializer);
			jsonObject.addProperty("tea_items", this.teaItemPredicate.toString());
			jsonObject.addProperty("scone_items", this.sconeItemPredicate.toString());
			return jsonObject;
		}

		public boolean matches(ItemStack teaStack, ItemStack sconeStack) {
			return teaItemPredicate.test(teaStack) && sconeItemPredicate.test(sconeStack);
		}

	}

}