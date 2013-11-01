package se.jourhavandekompis;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String URL = "http://m.jourhavandekompis.se";

	private WebView webView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_main);

		webView = (WebView) findViewById(R.id.webView);

		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);

		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(final WebView view, final int progress) {
				MainActivity.this.setProgress(progress * 100);
			}
		});

		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedError(final WebView view, final int errorCode, final String description,
					final String failingUrl) {
				Toast.makeText(getApplicationContext(), "ERROR: " + description, Toast.LENGTH_SHORT).show();
			}
		});

		webView.loadUrl(URL);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_refresh:
			webView.loadUrl(URL);
			break;
		default:
			break;
		}
		return true;
	}

}
