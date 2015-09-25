package us.pyting.crawler;

/**
 * Interface for identifying different tasks.<br>
 * 
 * public String getUUID()<br>
 * public Site getSite()<br>
 * @author youlink.yi@gmail.com <br>
 * @since 0.1.0
 * @see us.pyting.crawler.scheduler.Scheduler
 * @see us.pyting.crawler.pipeline.Pipeline
 */
public interface Task {

    /**
     * unique id for a task.
     * 
     * @return uuid
     */
    public String getUUID();

    /**
     * site of a task
     *
     * @return site
     */
    public Site getSite();

}
