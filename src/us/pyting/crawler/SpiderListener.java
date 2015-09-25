package us.pyting.crawler;

/**
 * Listener of Spider on page processing. Used for monitor and such on.
 *
 * @author code4crafer@gmail.com
 * @since 0.1.0
 */
public interface SpiderListener {

    public void onSuccess(Request request);

    public void onError(Request request);
}
