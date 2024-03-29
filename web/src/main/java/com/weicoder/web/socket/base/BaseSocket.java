package com.weicoder.web.socket.base;

import com.weicoder.web.socket.Closed;
import com.weicoder.web.socket.Connected;
import com.weicoder.web.socket.Handler;
import com.weicoder.web.socket.Socket;
import com.weicoder.web.socket.process.Process;

/**
 * 基础Socket
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-30
 */
public abstract class BaseSocket implements Socket {
	// 名称
	protected String	name;
	// 消息处理器
	protected Process	process;

	/**
	 * 构造
	 */
	public BaseSocket(String name) {
		this.name = name;
		process = new Process(name);
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public void addHandler(Handler<?> handler) {
		process.addHandler(handler);
	}

	@Override
	public void connected(Connected connected) {
		process.connected(connected);
	}

	@Override
	public void closed(Closed closed) {
		process.closed(closed);
	}
}