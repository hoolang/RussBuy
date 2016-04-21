package com.hoolang.spider.DuplicateRemover;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

public class DonothingDuplicateRemover implements DuplicateRemover {

	@Override
	public boolean isDuplicate(Request request, Task task) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetDuplicateCheck(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTotalRequestsCount(Task task) {
		// TODO Auto-generated method stub
		return 0;
	}



}
