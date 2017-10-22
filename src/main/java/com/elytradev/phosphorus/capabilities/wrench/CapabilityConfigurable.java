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

package com.elytradev.phosphorus.capabilities.wrench;

import com.elytradev.phosphorus.api.wrench.BlankConfigurable;
import com.elytradev.phosphorus.api.wrench.IConfigurable;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityConfigurable {

	@CapabilityInject(IConfigurable.class)
	public static Capability<IConfigurable> CONFIGURABLE_CAPABILITY = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(IConfigurable.class, new Capability.IStorage<IConfigurable>() {
			@Nullable
			@Override
			public NBTBase writeNBT(Capability<IConfigurable> capability, IConfigurable instance, EnumFacing side) {
				return null;
			}

			@Override
			public void readNBT(Capability<IConfigurable> capability, IConfigurable instance, EnumFacing side, NBTBase nbt) {
			}
		},  BlankConfigurable::new);
	}
}
