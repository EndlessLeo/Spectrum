package de.dafuqs.spectrum.blocks.mob_head.client.models;

import de.dafuqs.spectrum.blocks.mob_head.client.*;
import net.fabricmc.api.*;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;

@Environment(EnvType.CLIENT)
public class BatHeadModel extends SpectrumSkullModel {
	
	public BatHeadModel(ModelPart root) {
		super(root);
	}
	
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		
		ModelPartData head = modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F), ModelTransform.NONE);
		head.addChild("right_ear", ModelPartBuilder.create().uv(24, 0).cuboid(-4.0F, -9.0F, -2.0F, 3.0F, 4.0F, 1.0F), ModelTransform.NONE);
		head.addChild("left_ear", ModelPartBuilder.create().uv(24, 0).mirrored().cuboid(1.0F, -9.0F, -2.0F, 3.0F, 4.0F, 1.0F), ModelTransform.NONE);
		
		return TexturedModelData.of(modelData, 64, 64);
	}
	
}