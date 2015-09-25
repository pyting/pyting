package us.pyting.crawler.scheduler.component;

import us.pyting.crawler.Request;
import us.pyting.crawler.Task;

/**
 * Remove duplicate requests.
 * @author code4crafer@gmail.com
 * @since 0.1.0
 */
public interface DuplicateRemover {
    /**
     *
     * Check whether the request is duplicate.
     *
     * @param request
     * @param task
     * @return
     */
    public boolean isDuplicate(Request request, Task task);

    /**
     * Reset duplicate check.
     * @param task
     */
    public void resetDuplicateCheck(Task task);

    /**
     * Get TotalRequestsCount for monitor.
     * @param task
     * @return
     */
    public int getTotalRequestsCount(Task task);

}
