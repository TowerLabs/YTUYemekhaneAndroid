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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.towerlabs.yildizyemek.MainActivity;
import com.towerlabs.yildizyemek.R;
import com.towerlabs.yildizyemek.adapters.AboutPageAdapter;

public class About extends Fragment implements OnItemClickListener {

	public static boolean isWebPageOpen = false;

	private String designer_url = "https://twitter.com/mesutuntorunu";
	private String developer_url = "https://plus.google.com/app/basic/+cagdascaglak/posts";

	private Integer[] icons;
	private Integer[] titles;
	private Integer[] subtitles;

	private View view;
	private ListView list;
	private AboutPageAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.activity_about_page, container, false);
		initComponents(view);
		return view;
	}

	private void initComponents(View view) {

		icons = new Integer[] { R.drawable.dauut, R.drawable.caca };
		titles = new Integer[] { R.string.designer, R.string.developer };
		subtitles = new Integer[] { R.string.designer_twitter,
				R.string.developer_twitter };

		list = (ListView) view.findViewById(R.id.list_view);
		adapter = new AboutPageAdapter(getActivity(), icons, titles, subtitles);

		list.setAdapter(adapter);
		list.setOnItemClickListener(this);

	}

	public static About newInstance() {

		About about = new About();

		return about;

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		int itemID = position;

		Fragment fragment = null;

		if (itemID == 0) {

			fragment = AboutWeb.newInstance(designer_url);

			isWebPageOpen = true;

		} else if (itemID == 1) {

			fragment = AboutWeb.newInstance(developer_url);

			isWebPageOpen = true;
		}

		MainActivity.fragmentReplace(fragment);
	}

}
