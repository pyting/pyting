package us.pyting.crawler.pipeline;

import us.pyting.crawler.ResultItems;
import us.pyting.crawler.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youlink.yi@gmail.com
 * @since 0.1.0
 */
public class ResultItemsCollectorPipeline implements CollectorPipeline<ResultItems> {

    private List<ResultItems> collector = new ArrayList<ResultItems>();

    @Override
    public synchronized void process(ResultItems resultItems, Task task) {
        collector.add(resultItems);
    }

    @Override
    public List<ResultItems> getCollected() {
        return collector;
    }
}
