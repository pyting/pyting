package us.pyting.crawler.pipeline;

import java.util.List;


/**
 * Pipeline that can collect and store results. <br>
 * Used for {@link us.pyting.crawler.Spider#getAll(java.util.Collection)}
 *
 * @author youlink.yi@gmail.com
 * @since 0.1.0
 */
public interface CollectorPipeline<T> extends Pipeline {

    /**
     * Get all results collected.
     *
     * @return collected results
     */
    public List<T> getCollected();
}
