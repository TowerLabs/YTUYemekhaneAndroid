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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class HttpTask extends AsyncTask<String, Void, String> {

	private boolean error;

	private HttpClient httpClient;
	private HttpResponse httpResponse;
	private InputStream inputStream;
	private StringBuilder builder;
	private BufferedReader bufferedReader;
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		httpClient = new DefaultHttpClient();
		builder = new StringBuilder();

	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub

		try {

			httpResponse = httpClient.execute(new HttpGet(params[0]));
			inputStream = httpResponse.getEntity().getContent();
			bufferedReader = new BufferedReader(new InputStreamReader(
					inputStream));

			String line;
			while ((line = bufferedReader.readLine()) != null)
				builder.append(line);
			line = null;

			error = true;

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = false;

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = false;

		} catch (UnknownHostException e) {
			// TODO: handle exception
			Log.d("Bağlantı", "Bağlantı hatası.");
			error = false;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = false;

		}

		return builder.toString();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

	}

	public boolean isError() {
		return error;
	}

}
