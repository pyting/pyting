package us.pyting.crawler.scheduler;

import us.pyting.crawler.Task;

/**
 * The scheduler whose requests can be counted for monitor.
 *
 * @author youlink.yi@gmail.com
 * @since 0.1.0
 */
public interface MonitorableScheduler extends Scheduler {

    public int getLeftRequestsCount(Task task);

    public int getTotalRequestsCount(Task task);

}