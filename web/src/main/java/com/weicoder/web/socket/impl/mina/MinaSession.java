package com.weicoder.web.socket.impl.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.weicoder.common.lang.Conversion;
import com.weicoder.core.log.Logs;
import com.weicoder.web.socket.Session;
import com.weicoder.web.socket.base.BaseSession;

/**
 * Mina实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public final class MinaSession extends BaseSession implements Session {
	// Mina Session
	private IoSession	session;

	/**
	 * 构造方法
	 * @param id sessionId
	 * @param session
	 */
	public MinaSession(IoSession session) {
		this.id = Conversion.toInt(session.getId());
		this.session = session;
		address(session.getRemoteAddress());
	}

	@Override
	public boolean isConnect() {
		return session.isConnected();
	}

	@Override
	public boolean isClose() {
		return session.isClosing();
	}

	@Override
	public void send(byte[] data) {
		// 发送数据过多
		if (session.getScheduledWriteBytes() > Short.MAX_VALUE || session.getScheduledWriteMessages() > Byte.MAX_VALUE) {
			Logs.info("message num many close=" + id);
			close();
			return;
		}
		// 发送数据
		session.write(IoBuffer.wrap(data));
	}

	@Override
	protected void close0() {
		session.close(false);
	}
}
