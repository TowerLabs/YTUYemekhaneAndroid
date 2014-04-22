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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.towerlabs.yildizyemek.exceptions.LastDayException;

import android.util.Log;

public class JSonProcess {

	private String rawText;
	private JSONArray jsonArray;
	private JSONObject[] jsonObject;
	private ArrayList<Date> datesDate;
	private ArrayList<String> datesString;
	private int indexPlus;

	public JSonProcess(String rawText) {
		this.rawText = rawText;
	}

	public void init() throws JSONException, ParseException, LastDayException {

		jsonArray = new JSONArray(this.rawText);

		datesDate = new ArrayList<Date>();
		datesString = new ArrayList<String>();

		Log.d("available", String.valueOf(jsonArray.length()));

		jsonObject = new JSONObject[jsonArray.length()];

		int tempIndex = 0;

		for (int i = 0; i < jsonArray.length(); i++) {
			jsonObject[i] = jsonArray.getJSONObject(i);

			Iterator<?> it = jsonObject[i].keys();
			Date tempDate = null;
			while (it.hasNext()) {
				// Log.d("iterator", (String) it.next());
				String tempKey = (String) it.next();
				datesString.add(i, tempKey);

				tempDate = new SimpleDateFormat("dd.MM.yyyy",
						Locale.getDefault()).parse(tempKey);

				datesDate.add(i, tempDate);

			}

			if (MainActivity.dateFormat.format(tempDate).equalsIgnoreCase(
					MainActivity.dateFormat.format(new Date())))
				tempIndex = i;
			else if ((tempDate.compareTo(new Date()) < 0)) {
				tempIndex = i + 1;
			}

		}

		if (tempIndex > (jsonArray.length() - 1)) {
			setIndexPlus(jsonArray.length());
			throw new LastDayException();
		}

		else
			setIndexPlus(tempIndex);

		Log.d("index", String.valueOf(indexPlus));

	}

	public JSONObject[] getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject[] jsonObject) {
		this.jsonObject = jsonObject;
	}

	public ArrayList<Date> getDatesDate() {
		return datesDate;
	}

	public void getDatesDate(ArrayList<Date> datesDate) {
		this.datesDate = datesDate;
	}

	public ArrayList<String> getDatesString() {
		return datesString;
	}

	public void getDatesString(ArrayList<String> dates) {
		this.datesString = dates;
	}

	public int getIndexPlus() {
		return indexPlus;
	}

	public void setIndexPlus(int indexPlus) {
		this.indexPlus = indexPlus;
	}

	public String[] clearString(String[] rawString) {

		for (int i = 0; i < rawString.length; i++) {

			rawString[i] = rawString[i].replaceAll("\\&#\\d+", "");
			rawString[i] = rawString[i].replaceAll("\\;", "");
			rawString[i] = rawString[i].replaceAll("\\,", "");

		}

		return rawString;
	}

	public String[] toUpperCaseWords(String[] texts) {
		
		boolean isSpecialChar;
		char[] charsStr;
		
		for (int i = 0; i < texts.length; i++) {
			
			isSpecialChar = false;
			charsStr = texts[i].toLowerCase(Locale.getDefault()).toCharArray();
			
			for (int j = 0; j < charsStr.length; j++) {
				
				if (Character.isLetter(charsStr[j])) {
					if (!isSpecialChar) {
						charsStr[j] = Character.toUpperCase(charsStr[j]);
					}
					isSpecialChar = true;
				} else {
					isSpecialChar = false;
				}
			}
			
			texts[i] = new String(charsStr);
			
		}

		return texts;
	}

}
