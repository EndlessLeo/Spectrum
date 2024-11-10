package de.dafuqs.spectrum.particle.client;

import de.dafuqs.spectrum.particle.*;
import de.dafuqs.spectrum.registries.*;
import net.fabricmc.api.*;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.*;
import net.minecraft.particle.*;

@Environment(EnvType.CLIENT)
public class SpectrumBlockLeakParticles {

	public static class LandingMudFactory implements ParticleFactory<SimpleParticleType> {
		protected final SpriteProvider spriteProvider;
		
		public LandingMudFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			BlockLeakParticle blockLeakParticle = new BlockLeakParticle.Landing(clientWorld, d, e, f, SpectrumFluids.MUD);
			blockLeakParticle.setColor(SpectrumFluids.MUD_COLOR_VEC.x(), SpectrumFluids.MUD_COLOR_VEC.y(), SpectrumFluids.MUD_COLOR_VEC.z());
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}
	
	public static class FallingMudFactory implements ParticleFactory<SimpleParticleType> {
		protected final SpriteProvider spriteProvider;
		
		public FallingMudFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			BlockLeakParticle blockLeakParticle = new BlockLeakParticle.ContinuousFalling(clientWorld, d, e, f, SpectrumFluids.MUD, SpectrumParticleTypes.LANDING_MUD);
			blockLeakParticle.setColor(SpectrumFluids.MUD_COLOR_VEC.x(), SpectrumFluids.MUD_COLOR_VEC.y(), SpectrumFluids.MUD_COLOR_VEC.z());
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}
	
	public static class DrippingMudFactory implements ParticleFactory<SimpleParticleType> {
		protected final SpriteProvider spriteProvider;
		
		public DrippingMudFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			BlockLeakParticle blockLeakParticle = new BlockLeakParticle.Dripping(clientWorld, d, e, f, SpectrumFluids.MUD, SpectrumParticleTypes.FALLING_MUD);
			blockLeakParticle.setColor(SpectrumFluids.MUD_COLOR_VEC.x(), SpectrumFluids.MUD_COLOR_VEC.y(), SpectrumFluids.MUD_COLOR_VEC.z());
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}

	public static class LandingLiquidCrystalFactory implements ParticleFactory<SimpleParticleType> {
		protected final SpriteProvider spriteProvider;
		
		public LandingLiquidCrystalFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			BlockLeakParticle blockLeakParticle = new BlockLeakParticle.Landing(clientWorld, d, e, f, SpectrumFluids.LIQUID_CRYSTAL);
			blockLeakParticle.setColor(SpectrumFluids.LIQUID_CRYSTAL_COLOR_VEC.x(), SpectrumFluids.LIQUID_CRYSTAL_COLOR_VEC.y(), SpectrumFluids.LIQUID_CRYSTAL_COLOR_VEC.z());
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}
	
	public static class FallingLiquidCrystalFactory implements ParticleFactory<SimpleParticleType> {
		protected final SpriteProvider spriteProvider;
		
		public FallingLiquidCrystalFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			BlockLeakParticle blockLeakParticle = new BlockLeakParticle.ContinuousFalling(clientWorld, d, e, f, SpectrumFluids.LIQUID_CRYSTAL, SpectrumParticleTypes.LANDING_LIQUID_CRYSTAL);
			blockLeakParticle.setColor(SpectrumFluids.LIQUID_CRYSTAL_COLOR_VEC.x(), SpectrumFluids.LIQUID_CRYSTAL_COLOR_VEC.y(), SpectrumFluids.LIQUID_CRYSTAL_COLOR_VEC.z());
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}
	
	public static class DrippingLiquidCrystalFactory implements ParticleFactory<SimpleParticleType> {
		protected final SpriteProvider spriteProvider;
		
		public DrippingLiquidCrystalFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			BlockLeakParticle blockLeakParticle = new BlockLeakParticle.Dripping(clientWorld, d, e, f, SpectrumFluids.LIQUID_CRYSTAL, SpectrumParticleTypes.FALLING_LIQUID_CRYSTAL);
			blockLeakParticle.setColor(SpectrumFluids.LIQUID_CRYSTAL_COLOR_VEC.x(), SpectrumFluids.LIQUID_CRYSTAL_COLOR_VEC.y(), SpectrumFluids.LIQUID_CRYSTAL_COLOR_VEC.z());
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}

	public static class LandingMidnightSolutionFactory implements ParticleFactory<SimpleParticleType> {
		protected final SpriteProvider spriteProvider;
		
		public LandingMidnightSolutionFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			BlockLeakParticle blockLeakParticle = new BlockLeakParticle.Landing(clientWorld, d, e, f, SpectrumFluids.MIDNIGHT_SOLUTION);
			blockLeakParticle.setColor(SpectrumFluids.MIDNIGHT_SOLUTION_COLOR_VEC.x(), SpectrumFluids.MIDNIGHT_SOLUTION_COLOR_VEC.y(), SpectrumFluids.MIDNIGHT_SOLUTION_COLOR_VEC.z());
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}
	
	public static class FallingMidnightSolutionFactory implements ParticleFactory<SimpleParticleType> {
		protected final SpriteProvider spriteProvider;
		
		public FallingMidnightSolutionFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			BlockLeakParticle blockLeakParticle = new BlockLeakParticle.ContinuousFalling(clientWorld, d, e, f, SpectrumFluids.MIDNIGHT_SOLUTION, SpectrumParticleTypes.LANDING_MIDNIGHT_SOLUTION);
			blockLeakParticle.setColor(SpectrumFluids.MIDNIGHT_SOLUTION_COLOR_VEC.x(), SpectrumFluids.MIDNIGHT_SOLUTION_COLOR_VEC.y(), SpectrumFluids.MIDNIGHT_SOLUTION_COLOR_VEC.z());
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}
	
	public static class DrippingMidnightSolutionFactory implements ParticleFactory<SimpleParticleType> {
		protected final SpriteProvider spriteProvider;
		
		public DrippingMidnightSolutionFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			BlockLeakParticle blockLeakParticle = new BlockLeakParticle.Dripping(clientWorld, d, e, f, SpectrumFluids.MIDNIGHT_SOLUTION, SpectrumParticleTypes.FALLING_MIDNIGHT_SOLUTION);
			blockLeakParticle.setColor(SpectrumFluids.MIDNIGHT_SOLUTION_COLOR_VEC.x(), SpectrumFluids.MIDNIGHT_SOLUTION_COLOR_VEC.y(), SpectrumFluids.MIDNIGHT_SOLUTION_COLOR_VEC.z());
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}

	public static class LandingDragonrotFactory implements ParticleFactory<SimpleParticleType> {
		protected final SpriteProvider spriteProvider;

		public LandingDragonrotFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			BlockLeakParticle blockLeakParticle = new BlockLeakParticle.Landing(clientWorld, d, e, f, SpectrumFluids.DRAGONROT);
			blockLeakParticle.setColor(SpectrumFluids.DRAGONROT_COLOR_VEC.x(), SpectrumFluids.DRAGONROT_COLOR_VEC.y(), SpectrumFluids.DRAGONROT_COLOR_VEC.z());
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}

	public static class FallingDragonrotFactory implements ParticleFactory<SimpleParticleType> {
		protected final SpriteProvider spriteProvider;

		public FallingDragonrotFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			BlockLeakParticle blockLeakParticle = new BlockLeakParticle.ContinuousFalling(clientWorld, d, e, f, SpectrumFluids.DRAGONROT, SpectrumParticleTypes.LANDING_DRAGONROT);
			blockLeakParticle.setColor(SpectrumFluids.DRAGONROT_COLOR_VEC.x(), SpectrumFluids.DRAGONROT_COLOR_VEC.y(), SpectrumFluids.DRAGONROT_COLOR_VEC.z());
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}

	public static class DrippingDragonrotFactory implements ParticleFactory<SimpleParticleType> {
		protected final SpriteProvider spriteProvider;

		public DrippingDragonrotFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			BlockLeakParticle blockLeakParticle = new BlockLeakParticle.Dripping(clientWorld, d, e, f, SpectrumFluids.DRAGONROT, SpectrumParticleTypes.FALLING_DRAGONROT);
			blockLeakParticle.setColor(SpectrumFluids.DRAGONROT_COLOR_VEC.x(), SpectrumFluids.DRAGONROT_COLOR_VEC.y(), SpectrumFluids.DRAGONROT_COLOR_VEC.z());
			blockLeakParticle.setSprite(this.spriteProvider);
			return blockLeakParticle;
		}
	}

}
