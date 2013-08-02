/*******************************************************************************
 * FluxIT 
 * Copyright (c) 2013 
 * Argentina
 * 
 * This is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of 
 * the License, or (at your option) any later version.
 * 
 * This is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General 
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package ar.com.fluxit.em.model;

import lombok.Data;

@Data
public class MemoryContext {

	private long heapInit;

	private long heapUsed;

	private long heapCommitted;

	private long heapMax;

	private long nonHeapInit;

	private long nonHeapUsed;

	private long nonHeapCommitted;

	private long nonHeapMax;

	public long getHeapInit() {
		return heapInit;
	}

	public void setHeapInit(long heapInit) {
		this.heapInit = heapInit;
	}

	public long getHeapUsed() {
		return heapUsed;
	}

	public void setHeapUsed(long heapUsed) {
		this.heapUsed = heapUsed;
	}

	public long getHeapCommitted() {
		return heapCommitted;
	}

	public void setHeapCommitted(long heapCommitted) {
		this.heapCommitted = heapCommitted;
	}

	public long getHeapMax() {
		return heapMax;
	}

	public void setHeapMax(long heapMax) {
		this.heapMax = heapMax;
	}

	public long getNonHeapInit() {
		return nonHeapInit;
	}

	public void setNonHeapInit(long nonHeapInit) {
		this.nonHeapInit = nonHeapInit;
	}

	public long getNonHeapUsed() {
		return nonHeapUsed;
	}

	public void setNonHeapUsed(long nonHeapUsed) {
		this.nonHeapUsed = nonHeapUsed;
	}

	public long getNonHeapCommitted() {
		return nonHeapCommitted;
	}

	public void setNonHeapCommitted(long nonHeapCommitted) {
		this.nonHeapCommitted = nonHeapCommitted;
	}

	public long getNonHeapMax() {
		return nonHeapMax;
	}

	public void setNonHeapMax(long nonHeapMax) {
		this.nonHeapMax = nonHeapMax;
	}

}
