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

package com.elytradev.phosphorus.capabilities;

import com.elytradev.phosphorus.api.capabilities.IRotaryPowerConsumer;
import com.elytradev.phosphorus.api.capabilities.impl.RotaryPowerConsumer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityRotaryPowerConsumer {

	@CapabilityInject(IRotaryPowerConsumer.class)
	public static Capability<IRotaryPowerConsumer> ROTARY_POWER_CONSUMER_CAPABILITY = null;

	// Serializer code grafted from Thermionics, credit to @Falkreon
	// Check THERMIONICS-LICENSE for more information.
	public static void register() {
		CapabilityManager.INSTANCE.register(IRotaryPowerConsumer.class, new Capability.IStorage<IRotaryPowerConsumer>() {
			@Nullable
			@Override
			public NBTBase writeNBT(Capability<IRotaryPowerConsumer> capability, IRotaryPowerConsumer instance, EnumFacing side) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setFloat("torque", instance.getRequiredTorque());
				if (instance instanceof RotaryPowerConsumer) {
					tag.setFloat("buffer", ((RotaryPowerConsumer)instance).getBufferedRevolutions());
				}

				return tag;
			}

			@Override
			public void readNBT(Capability<IRotaryPowerConsumer> capability, IRotaryPowerConsumer instance, EnumFacing side, NBTBase nbt) {
				if (nbt instanceof NBTTagCompound && instance instanceof RotaryPowerConsumer) {
					NBTTagCompound tag = (NBTTagCompound)nbt;
					RotaryPowerConsumer consumer = (RotaryPowerConsumer)instance;
					if (tag.hasKey("torque")) consumer.setRequiredTorque(tag.getFloat("torque"));
					if (tag.hasKey("buffer")) consumer.setBufferedRevolutions(tag.getFloat("buffer"));
				}
			}
		}, RotaryPowerConsumer::new);
	}
}
