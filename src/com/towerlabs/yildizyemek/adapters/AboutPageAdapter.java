package com.towerlabs.yildizyemek.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.towerlabs.yildizyemek.R;
import com.towerlabs.yildizyemek.customviews.CustomTextView;

public class AboutPageAdapter extends BaseAdapter {

	private Activity activity;
	private Integer[] icons;
	private Integer[] titles;
	private Integer[] subtitles;

	public AboutPageAdapter(Activity activity, Integer[] icons,
			Integer[] titles, Integer[] subtitles) {

		this.activity = activity;
		this.icons = icons;
		this.titles = titles;
		this.subtitles = subtitles;

	}

	private static class ViewHolder {

		public ImageView icon;
		public CustomTextView title, subtitle;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return titles.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return titles[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder;
		View rowView = convertView;

		if (rowView == null) {

			LayoutInflater inflater = activity.getLayoutInflater();

			rowView = inflater.inflate(R.layout.list_items, null);

			holder = new ViewHolder();
			holder.icon = (ImageView) rowView.findViewById(R.id.list_icon);
			holder.title = (CustomTextView) rowView.findViewById(R.id.list_title);
			holder.subtitle = (CustomTextView) rowView.findViewById(R.id.list_subtitle);

			holder.icon.setImageResource(icons[position]);
			holder.title.setText(titles[position]);
			holder.subtitle.setText(subtitles[position]);

			rowView.setTag(holder);
		} else {
			holder = (ViewHolder) rowView.getTag();
		}

		return rowView;
	}

}
