package sujith.findgitrepo;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by sujith on 28/12/17.
 */

public class FindRepoApplication extends Application {

    public FindRepoApplication() {
        super();
    }

    private static final String TAG = FindRepoApplication.class.getSimpleName();

    private static FindRepoApplication instance = null;

    private RequestQueue requestQueue;
    private ImageLoader imageLoader;


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });

    }

    public static FindRepoApplication get() {
        return instance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    /**
     * @param req volley request
     */
    public void cancelRequestQueue(Object req) {
        if (requestQueue != null)
            getRequestQueue().cancelAll(req);
    }
}
