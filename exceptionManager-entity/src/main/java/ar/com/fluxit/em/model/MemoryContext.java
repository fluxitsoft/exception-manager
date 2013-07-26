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

}
