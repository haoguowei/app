package com.hao.app.worker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hao.app.worker.container.SpringContainer;

/**
 * 程序启动入口
 * 
 * @author haoguowei
 *
 */
public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	private static volatile boolean running = true;

	public static void main(String[] args) {
		try {
			final List<Container> containers = new ArrayList<Container>();
			Container springContainer = new SpringContainer();
			containers.add(springContainer);

			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					for (Container container : containers) {
						try {
							container.stop();
							logger.info("app-worker " + container.getClass().getSimpleName() + " stopped!");
						} catch (Throwable t) {
							logger.error(t.getMessage(), t);
						}
						synchronized (Main.class) {
							running = false;
							Main.class.notify();
						}
					}
				}
			});

			for (Container container : containers) {
				container.start();
				logger.info("app-worker " + container.getClass().getSimpleName() + " started!");
			}
			System.out
					.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + "app-worker started!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			System.exit(1);
		}

		synchronized (Main.class) {
			while (running) {
				try {
					Main.class.wait();
				} catch (Throwable e) {
				}
			}
		}

	}
}
