/*
 * MIT License
 *
 * Copyright (c) 2017 Isaac Ellingson (Falkreon) and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.elytradev.phosphorus.api.capabilities.impl;

import com.elytradev.phosphorus.api.capabilities.IHeatStorage;
import com.google.common.primitives.Ints;
import org.apache.commons.lang3.Validate;

import javax.annotation.Nonnull;
import java.util.ArrayList;

/**
 * Basic, insecure implementation of IHeatStorage.
 */
public class HeatStorage implements IHeatStorage {
	private int cur = 0;
	private int max = 200;
	private int minReceive = 0;
	private int maxReceive = 200;
	private int maxExtract = 200;

	private ArrayList<Runnable> listeners = new ArrayList<>();

	public HeatStorage() {}

	public HeatStorage(int max) {
		this.max = max;
		this.maxReceive = max;
		this.maxExtract = max;
	}

	@Override
	public int getHeatStored() {
		return cur;
	}

	@Override
	public int getMaxHeatStored() {
		return max;
	}

	@Override
	public boolean canReceiveHeat() {
		return true;
	}

	@Override
	public boolean canExtractHeat() {
		return true;
	}

	@Override
	public int receiveHeat(int amount, boolean simulate) {
		int toReceive = Ints.min(amount, max-cur, maxReceive);
		if (toReceive<minReceive) toReceive = 0;
		if (!simulate) {
			cur += toReceive;
			markDirty();
		}

		return toReceive;
	}

	@Override
	public int extractHeat(int amount, boolean simulate) {
		int toExtract = Ints.min(amount, cur, maxExtract);
		if (!simulate) {
			cur -= toExtract;
			markDirty();
		}
		return toExtract;
	}


	private void markDirty() {
		for(Runnable listener : listeners) {
			listener.run();
		}
	}

	public void listen(@Nonnull Runnable r) {
		Validate.notNull(r);

		listeners.add(r);
	}

	public HeatStorage withTransferAttributes(int maxExtract, int minReceive, int maxReceive) {
		this.maxExtract = maxExtract;
		this.minReceive = minReceive;
		this.maxReceive = maxReceive;

		return this;
	}
}