package com.hao.app.worker.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hao.app.worker.JobService;

/**
 * 执行定时任务
 * 
 * @author haoguowei
 *
 */
public abstract class AbstractJobService implements JobService {

	private final Logger logger = LoggerFactory.getLogger(AbstractJobService.class);

	public abstract String jobName();

	public abstract void work();

	@Override
	public void execute() {
		logger.info("定时任务【{}】执行开始!", jobName());
		long start = System.currentTimeMillis();
		work();
		logger.info("定时任务【{}】执行结束,共耗时{}ms!", jobName(), (System.currentTimeMillis() - start));
	}

}
