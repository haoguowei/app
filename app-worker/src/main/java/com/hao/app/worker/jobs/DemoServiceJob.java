package com.hao.app.worker.jobs;

import org.springframework.stereotype.Service;

@Service
public class DemoServiceJob extends AbstractJobService {

	@Override
	public void work() {
		System.out.println("===================");
	}

}
