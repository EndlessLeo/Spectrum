package de.dafuqs.spectrum.progression.advancement;

import com.google.gson.*;
import com.klikli_dev.modonomicon.api.multiblock.*;
import de.dafuqs.spectrum.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.predicate.entity.*;
import net.minecraft.server.network.*;
import net.minecraft.util.*;

public class CompletedMultiblockCriterion extends AbstractCriterion<CompletedMultiblockCriterion.Conditions> {
	
	static final Identifier ID = SpectrumCommon.locate("completed_multiblock");
	
	public static CompletedMultiblockCriterion.Conditions create(Identifier id) {
		return new CompletedMultiblockCriterion.Conditions(LootContextPredicate.EMPTY, id);
	}
	
	@Override
	public Identifier getId() {
		return ID;
	}
	
	@Override
	public CompletedMultiblockCriterion.Conditions conditionsFromJson(JsonObject jsonObject, LootContextPredicate extended, AdvancementEntityPredicateDeserializer advancementEntityPredicateDeserializer) {
		Identifier identifier = Identifier.of(JsonHelper.getString(jsonObject, "multiblock_identifier"));
		return new CompletedMultiblockCriterion.Conditions(extended, identifier);
	}
	
	public void trigger(ServerPlayerEntity player, Multiblock iMultiblock) {
		this.trigger(player, (conditions) -> conditions.matches(iMultiblock));
	}
	
	public static class Conditions extends AbstractCriterionConditions {
		private final Identifier identifier;
		
		public Conditions(LootContextPredicate player, Identifier identifier) {
			super(ID, player);
			this.identifier = identifier;
		}
		
		@Override
		public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer) {
			JsonObject jsonObject = super.toJson(predicateSerializer);
			jsonObject.addProperty("multiblock_identifier", this.identifier.toString());
			return jsonObject;
		}
		
		public boolean matches(Multiblock multiblock) {
			return multiblock.getId().equals(identifier);
		}
	}
	
}
