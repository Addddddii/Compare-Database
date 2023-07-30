package com.example.compare_db.utils;

import lombok.extern.slf4j.Slf4j;
import java.io.Closeable;

/**
 * 资源关闭工具类
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Slf4j
public class CloseUtils {

	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				log.error("关闭错误", e);
			}
		}
	}

	public static void close(Closeable... closeables) {
		for (Closeable closeable : closeables) {
			close(closeable);
		}
	}

	public static void close(AutoCloseable... autoCloseables) {
		for (AutoCloseable closeable : autoCloseables) {
			close(closeable);
		}
	}

	public static void close(AutoCloseable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				log.error("关闭错误", e);
			}
		}
	}

}
