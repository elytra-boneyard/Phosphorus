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

import com.elytradev.phosphorus.api.capabilities.impl.HeatStorage;
import com.elytradev.phosphorus.api.capabilities.IHeatStorage;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityHeatStorage {

	@CapabilityInject(IHeatStorage.class)
	public static Capability<IHeatStorage> HEAT_STORAGE_CAPABILITY = null;

	// Serializer code grafted from Thermionics, credit to @Falkreon
	// Check THERMIONICS-LICENSE for more information.
	public static void register() {
		CapabilityManager.INSTANCE.register(IHeatStorage.class, new Capability.IStorage<IHeatStorage>() {
			@Nullable
			@Override
			public NBTBase writeNBT(Capability<IHeatStorage> capability, IHeatStorage instance, EnumFacing side) {
				return new NBTTagInt(instance.getHeatStored());
			}

			@Override
			public void readNBT(Capability<IHeatStorage> capability, IHeatStorage instance, EnumFacing side, NBTBase nbt) {
				instance.receiveHeat(((NBTTagInt)nbt).getInt(), false);
			}
		}, HeatStorage::new);
	}
}
