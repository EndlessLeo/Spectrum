package de.dafuqs.spectrum.particle.client;

import net.fabricmc.api.*;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.*;
import net.minecraft.particle.*;

@Environment(EnvType.CLIENT)
public class HardcoreParticle extends AnimatedParticle {
	
	HardcoreParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
		super(world, x, y, z, spriteProvider, 1.25F);
		this.velocityMultiplier = 0.6F;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.velocityZ = velocityZ;
		this.scale *= 0.75F;
		this.maxAge = 60 + this.random.nextInt(12);
		this.setSpriteForAge(spriteProvider);
		if (this.random.nextInt(4) == 0) {
			this.setColor(0.0F, 0.0F, 0.0F);
		} else {
			this.setColor(0.2F + this.random.nextFloat() * 0.5F, this.random.nextFloat() * 0.2F, this.random.nextFloat() * 0.2F);
		}
		
	}
	
	@Environment(EnvType.CLIENT)
	public static class Factory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider spriteProvider;
		
		public Factory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			return new HardcoreParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
		}
	}
}
