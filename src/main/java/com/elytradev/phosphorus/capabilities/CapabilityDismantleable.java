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

import com.elytradev.phosphorus.api.impl.BlankDismantleable;
import com.elytradev.phosphorus.api.IDismantleable;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityDismantleable {

	@CapabilityInject(IDismantleable.class)
	public static Capability<IDismantleable> DISMANTLEABLE_CAPABILITY = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(IDismantleable.class, new Capability.IStorage<IDismantleable>() {
			@Nullable
			@Override
			public NBTBase writeNBT(Capability<IDismantleable> capability, IDismantleable instance, EnumFacing side) {
				return null;
			}

			@Override
			public void readNBT(Capability<IDismantleable> capability, IDismantleable instance, EnumFacing side, NBTBase nbt) {
			}
		}, BlankDismantleable::new);
	}
}
