/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2017:
 *      Ethan Brooks (CalmBit),
 *      and contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of
 *  this software and associated documentation files (the "Software"), to deal in
 *  the Software without restriction, including without limitation the rights to
 *  use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do
 *  so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.elytradev.phosphorus;

import com.elytradev.phosphorus.capabilities.CapabilityRotaryPowerConsumer;
import com.elytradev.phosphorus.capabilities.CapabilityRotaryPowerSupply;
import com.elytradev.phosphorus.capabilities.CapabilityHeatStorage;
import com.elytradev.phosphorus.capabilities.CapabilityConfigurable;
import com.elytradev.phosphorus.capabilities.CapabilityDismantleable;
import com.elytradev.phosphorus.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
		modid = Phosphorus.MOD_ID,
		name = Phosphorus.NAME,
		version = Phosphorus.VERSION)
@Mod.EventBusSubscriber
public final class Phosphorus {

	public static final String MOD_ID = "phorphorus";
	public static final String NAME = "Phosphorus";
	public static final String VERSION = "1.12-0.1.0";

	public static final Logger LOG = LogManager.getLogger(Phosphorus.NAME);

	@SidedProxy(
			clientSide = "com.elytradev.phosphorus.proxy.ClientProxy",
			serverSide = "com.elytradev.phosphorus.proxy.CommonProxy")
	public static CommonProxy PROXY;

	@Mod.Instance
	public static Phosphorus INSTANCE;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		PROXY.init();
		CapabilityDismantleable.register();
		CapabilityConfigurable.register();
		CapabilityHeatStorage.register();
		CapabilityRotaryPowerSupply.register();
		CapabilityRotaryPowerConsumer.register();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}



}
