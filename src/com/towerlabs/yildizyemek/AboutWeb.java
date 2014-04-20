/*Copyright (C) 2014  Tower Labs

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.*/
package com.towerlabs.yildizyemek;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutWeb extends Fragment implements OnKeyListener {
	public static String URL_KEY = "url_key";
	private WebView webView;
	private ProgressDialog webProgress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.fragment_web_view, container,
				false);

		initComponents(view);

		return view;
	}

	private void initComponents(View view) {

		view.setOnKeyListener(this);
		webProgress = new ProgressDialog(getActivity());
		webProgress.setMessage("Sayfa Yükleniyor...");
		webProgress.show();

		webView = (WebView) view.findViewById(R.id.web_view);
		webView.setWebViewClient(new CustomWebClient());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setHorizontalScrollBarEnabled(true);

		webView.loadUrl(getArguments().getString(URL_KEY));

	}

	public static AboutWeb newInstance(String url) {

		AboutWeb aboutWeb = new AboutWeb();

		Bundle arg = new Bundle();
		arg.putString(URL_KEY, url);

		aboutWeb.setArguments(arg);

		return aboutWeb;

	}

	private class CustomWebClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			if (!webProgress.isShowing()) {
				webProgress.show();
			}
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {

			if (webProgress.isShowing()) {
				webProgress.dismiss();
			}

		}

	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (event.getAction() == KeyEvent.ACTION_UP
				&& keyCode == KeyEvent.KEYCODE_BACK) {
			if (webView.canGoBack()) {
				webView.goBack();
			} else {
				// getActivity().finish();

				MainActivity.exitDialog();
			}

		}

		return true;
	}

}
