package sujith.findgitrepo.webview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import sujith.findgitrepo.R;


public class WebViewActivity extends AppCompatActivity {

    public String TAG = WebViewActivity.class.getSimpleName();

    @BindView(R.id.web_view)
    WebView web_view;
    @BindView(R.id.web_view_progress)
    ProgressBar web_view_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        web_view.getSettings().setJavaScriptEnabled(true);

        Bundle data = getIntent().getExtras();
        web_view.setWebViewClient(new GitRepoWebViewClient());

        if (!data.isEmpty()) {
            if (data.containsKey("repo_name")) {
                getSupportActionBar().setTitle(data.getString("repo_name"));
            }

            if (data.containsKey("web_link")) {
                String url = data.getString("web_link");
                web_view.loadUrl(url);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;

    }

    public class GitRepoWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.i(TAG, "started");
            web_view_progress.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.i(TAG, "finished");
            web_view_progress.setVisibility(View.GONE);
        }
    }
}
