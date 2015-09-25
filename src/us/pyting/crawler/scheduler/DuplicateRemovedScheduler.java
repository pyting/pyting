package us.pyting.crawler.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import us.pyting.crawler.Request;
import us.pyting.crawler.Task;
import us.pyting.crawler.scheduler.component.DuplicateRemover;
import us.pyting.crawler.scheduler.component.HashSetDuplicateRemover;

/**
 * Remove duplicate urls and only push urls which are not duplicate.<br>
 * </br>
 *
 * @author code4crafer@gmail.com
 * @since 0.1.0
 */
public abstract class DuplicateRemovedScheduler implements Scheduler {

//	 protected  Logger logger = LoggerFactory.getLogger(getClass());
	private static final Logger logger = LogManager.getLogger(DuplicateRemovedScheduler.class.getName());
	private DuplicateRemover duplicatedRemover = new HashSetDuplicateRemover();

	public DuplicateRemover getDuplicateRemover() {
		return duplicatedRemover;
	}

	public DuplicateRemovedScheduler setDuplicateRemover(DuplicateRemover duplicatedRemover) {
		this.duplicatedRemover = duplicatedRemover;
		return this;
	}

	@Override
	public void push(Request request, Task task) {
		logger.trace("get a candidate url {}", request.getUrl());
		if (!duplicatedRemover.isDuplicate(request, task) || shouldReserved(request)) {
			logger.debug("push to queue {}", request.getUrl());
			pushWhenNoDuplicate(request, task);
		}
	}

	protected boolean shouldReserved(Request request) {
		return request.getExtra(Request.CYCLE_TRIED_TIMES) != null;
	}

	protected void pushWhenNoDuplicate(Request request, Task task) {

	}
}
