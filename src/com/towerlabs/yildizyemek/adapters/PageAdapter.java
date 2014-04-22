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
package com.towerlabs.yildizyemek.adapters;

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

import com.towerlabs.yildizyemek.FOOD;
import com.towerlabs.yildizyemek.JSonProcess;
import com.towerlabs.yildizyemek.MainActivity;
import com.towerlabs.yildizyemek.exceptions.LastDayException;
import com.towerlabs.yildizyemek.fragments.DinnerPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class PageAdapter extends FragmentStatePagerAdapter {

	private JSonProcess jsonProcess;
	private JSONObject[] jsonObj;
	private String[] texts;
	private String uselessString = "VJT.YEMEK:";

	public PageAdapter(FragmentManager fm, String rawText)
			throws JSONException, ParseException, LastDayException {
		super(fm);
		// TODO Auto-generated constructor stub
		jsonProcess = new JSonProcess(rawText);

		jsonProcess.init();
		jsonObj = jsonProcess.getJsonObject();

		texts = new String[5];

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return jsonProcess.getDatesDate().size() - jsonProcess.getIndexPlus();
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub

		Log.d("Item", String.valueOf(arg0));

		texts = new String[5];

		texts[0] = MainActivity.dateFormatMonth.format(jsonProcess
				.getDatesDate().get(arg0 + jsonProcess.getIndexPlus()));
		try {
			texts[1] = jsonObj[arg0 + jsonProcess.getIndexPlus()]
					.getJSONObject(
							jsonProcess.getDatesString().get(
									arg0 + jsonProcess.getIndexPlus()))
					.getString(FOOD.MAIN_LUNCH.getValue());
			texts[2] = jsonObj[arg0 + jsonProcess.getIndexPlus()]
					.getJSONObject(
							jsonProcess.getDatesString().get(
									arg0 + jsonProcess.getIndexPlus()))
					.getString(FOOD.ALT_LUNCH.getValue());
			texts[3] = jsonObj[arg0 + jsonProcess.getIndexPlus()]
					.getJSONObject(
							jsonProcess.getDatesString().get(
									arg0 + jsonProcess.getIndexPlus()))
					.getString(FOOD.MAIN_DINNER.getValue());
			texts[4] = jsonObj[arg0 + jsonProcess.getIndexPlus()]
					.getJSONObject(
							jsonProcess.getDatesString().get(
									arg0 + jsonProcess.getIndexPlus()))
					.getString(FOOD.ALT_DINNER.getValue());
			texts = jsonProcess.clearString(texts);
			
			if (texts[2].contains(uselessString))
				texts[2] = texts[2].replace(uselessString, "");
			
			texts = jsonProcess.toUpperCaseWords(texts);
			
			

		} catch (JSONException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
		}

		return DinnerPage.newInstance(texts);
	}

}
