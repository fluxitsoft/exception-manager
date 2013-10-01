package ar.com.fluxit.em.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.LogbackException;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.Status;

public final class LastLinesAppender implements Appender {

	static LinkedList<String> queue = new LimitedQueue<String>(20);
	private Context context;

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}

	@Override
	public boolean isStarted() {
		return true;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public Context getContext() {
		return context;
	}

	@Override
	public void addStatus(Status status) {
		queue.add(status.toString());
	}

	@Override
	public void addInfo(String msg) {
		queue.add(msg);
	}

	@Override
	public void addInfo(String msg, Throwable ex) {
		queue.add(msg);
	}

	@Override
	public void addWarn(String msg) {
		queue.add(msg);
	}

	@Override
	public void addWarn(String msg, Throwable ex) {
		queue.add(msg);
	}

	@Override
	public void addError(String msg) {
		queue.add(msg);
	}

	@Override
	public void addError(String msg, Throwable ex) {
		queue.add(msg);

	}

	@Override
	public void addFilter(Filter newFilter) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearAllFilters() {
		// TODO Auto-generated method stub

	}

	@Override
	public List getCopyOfAttachedFiltersList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilterReply getFilterChainDecision(Object event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "LASTLINES";
	}

	@Override
	public void doAppend(Object event) throws LogbackException {
		queue.add(event.toString());
	}

	@Override
	public void setName(String name) {
	}

	public static String getLog() {

		StringBuffer stringBuffer = new StringBuffer();
		List<String> copyQueue = new ArrayList<String>(queue);
		for (String log : copyQueue) {
			stringBuffer.append(log).append("\n");
		}
		return stringBuffer.toString();
	}
}