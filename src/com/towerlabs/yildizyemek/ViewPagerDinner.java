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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CirclePageIndicator;

public class ViewPagerDinner extends Fragment {

	private String rawText;

	private View view;
	private CirclePageIndicator indicator;
	private ViewPager viewPager;
	private PageAdapter adapter;
	private HttpTask httpTask;
	private String[] urls;
	private File fileDir, file;

	public static ViewPagerDinner newInstance() {

		ViewPagerDinner viewPager = new ViewPagerDinner();

		return viewPager;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		view = inflater.inflate(R.layout.activity_viewpager_dinner, container,
				false);

		try {
			initComponents(view);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LastDayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return view;
	}

	private void initComponents(View view) throws IOException,
			InterruptedException, ExecutionException, JSONException,
			ParseException, LastDayException {

		viewPager = (ViewPager) view.findViewById(R.id.viewpager);

		httpTask = new HttpTask();
		urls = new String[1];
		urls[0] = APILink.link;

		if (fileIsExist()) {

			rawText = readFile();

			adapter = new PageAdapter(getChildFragmentManager(), rawText);
			viewPager.setAdapter(adapter);
			// viewPager.setCurrentItem(3);
		}

		// ----------------------------------
		indicator = (CirclePageIndicator) view.findViewById(R.id.titless);
		// ----------------------------------
		indicator.setViewPager(viewPager);

	}

	public boolean fileIsExist() throws IOException, InterruptedException,
			ExecutionException, NotFoundException, JSONException,
			ParseException {

		MainActivity.fileName = MainActivity.dateFormatFile.format(new Date());

		fileDir = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + MainActivity.folderName);

		file = new File(fileDir, MainActivity.fileName);

		if (!fileDir.exists() && !fileDir.isDirectory()) {
			fileDir.mkdirs();
		}

		if (!file.exists()) {

			Log.d("girdimi", "girdi");

			httpTask.execute(urls[0]);

			rawText = httpTask.get();

			if (httpTask.isError()) {

				if (!correctDate(rawText).equalsIgnoreCase(
						MainActivity.fileName)) {

					MainActivity.errorDialog(getResources().getString(
							R.string.new_list_error_msg));

					return false;
				}

				FileOutputStream fo;
				DataOutputStream dos;

				fo = new FileOutputStream(file);
				dos = new DataOutputStream(fo);
				dos.writeUTF(rawText);
				fo.close();
				dos.close();
				fo = null;
				dos = null;
				return true;

			} else {

				MainActivity.errorDialog(getResources().getString(
						R.string.connection_error_msg));

				return false;
			}

		}
		return true;
	}

	public String readFile() throws IOException {

		FileInputStream fi;
		DataInputStream di;
		String result = null;

		fi = new FileInputStream(file);
		di = new DataInputStream(fi);

		result = di.readUTF();

		fi.close();
		di.close();

		fi = null;
		di = null;

		return result;

	}

	public String getRawText() {
		return rawText;
	}

	public void setRawText(String rawText) {
		this.rawText = rawText;
	}

	public static String correctDate(String rawText) throws JSONException,
			ParseException {

		JSONArray jsonArray;
		Iterator<?> it = null;
		Date tempDate = null;

		jsonArray = new JSONArray(rawText);
		it = jsonArray.getJSONObject(0).keys();

		while (it.hasNext()) {
			// Log.d("iterator", (String) it.next());
			String tempKey = (String) it.next();

			tempDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
					.parse(tempKey);

		}

		return MainActivity.dateFormatFile.format(tempDate);

	}

}
