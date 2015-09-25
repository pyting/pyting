package us.pyting.crawler.pipeline;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.annotation.ThreadSafe;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import us.pyting.crawler.ResultItems;
import us.pyting.crawler.Task;
import us.pyting.crawler.utils.FilePersistentBase;

/**
 * Store results in files.<br>
 *
 * @author youlink.yi@gmail.com <br>
 * @since 0.1.0
 */
@ThreadSafe
public class FilePipeline extends FilePersistentBase implements Pipeline {

	// private Logger logger = LoggerFactory.getLogger(getClass());
	private static final Logger logger = LogManager.getLogger(FilePipeline.class.getName());

	/**
	 * create a FilePipeline with default path"/data/crawler/"
	 */
	public FilePipeline() {
		setPath("/data/crawler/");
	}

	public FilePipeline(String path) {
		setPath(path);
	}

	@Override
	public void process(ResultItems resultItems, Task task) {
		String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
		try {
			PrintWriter printWriter = new PrintWriter(
					new OutputStreamWriter(
							new FileOutputStream(
									getFile(path + DigestUtils.md5Hex(resultItems.getRequest().getUrl()) + ".html")),
							"UTF-8"));
			printWriter.println("url:\t" + resultItems.getRequest().getUrl());
			for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
				if (entry.getValue() instanceof Iterable) {
					Iterable<?> value = (Iterable<?>) entry.getValue();
					printWriter.println(entry.getKey() + ":");
					for (Object o : value) {
						printWriter.println(o);
					}
				} else {
					printWriter.println(entry.getKey() + ":\t" + entry.getValue());
				}
			}
			printWriter.close();
		} catch (IOException e) {
			logger.warn("write file error", e);
		}
	}
}
