package sujith.findgitrepo.core;

import com.android.volley.Response;


public interface ResponseListener<T> extends Response.Listener<T>, Response.ErrorListener {
}
