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
package com.towerlabs.yildizyemek.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.towerlabs.yildizyemek.R;
import com.towerlabs.yildizyemek.customviews.CustomTextView;

public class DinnerPage extends Fragment {

	public static String BUNDLE_KEY = "arg_key";

	private CustomTextView date_text;

	private CustomTextView main_lunch_tv;

	private CustomTextView alt_lunch_tv;

	private CustomTextView main_dinner_tv;

	private CustomTextView alt_dinner_tv;

	private String[] texts;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		texts = getArguments().getStringArray(BUNDLE_KEY);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.activity_dinner_page, container,
				false);

		initComponents(view);

		return view;

	}

	public static DinnerPage newInstance(String[] str) {
		DinnerPage dinnerPageFragment = new DinnerPage();

		Bundle args = new Bundle();
		args.putStringArray(BUNDLE_KEY, str);
		dinnerPageFragment.setArguments(args);

		return dinnerPageFragment;
	}

	private void initComponents(View view) {

		date_text = (CustomTextView) view.findViewById(R.id.date_textview);
		main_lunch_tv = (CustomTextView) view.findViewById(R.id.main_lunch);
		alt_lunch_tv = (CustomTextView) view.findViewById(R.id.alt_lunch);
		main_dinner_tv = (CustomTextView) view.findViewById(R.id.main_dinner);
		alt_dinner_tv = (CustomTextView) view.findViewById(R.id.alt_dinner);

		date_text.setText(texts[0]);
		main_lunch_tv.setText(texts[1]);
		alt_lunch_tv.setText(texts[2]);
		main_dinner_tv.setText(texts[3]);
		alt_dinner_tv.setText(texts[4]);
		

	}

	public String[] getTexts() {
		return texts;
	}

	public void setTexts(String[] texts) {
		this.texts = texts;
	}

}
