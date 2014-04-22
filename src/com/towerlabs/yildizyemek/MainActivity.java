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

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;

import com.towerlabs.yildizyemek.customviews.CustomButton;
import com.towerlabs.yildizyemek.fragments.About;
import com.towerlabs.yildizyemek.fragments.ViewPagerDinner;

public class MainActivity extends ActionBarActivity implements
		android.view.View.OnClickListener {

	public static int startCounter = 0;
	public static boolean askStatus = true;

	public static SimpleDateFormat dateFormatMonth;
	public static SimpleDateFormat dateFormat;
	public static SimpleDateFormat dateFormatFile;

	public static Activity activity;

	public static String fileName;
	public static String folderName = "/towerlabs/YildizYemek";

	private Drawable food, foodDark, team, teamDark;
	private CustomButton lunch_list_button, about_button;

	private Fragment fragment = null;
	public static FragmentManager fragmentManager;
	public static FragmentTransaction fragmentTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initComponents();

		if (savedInstanceState != null)
			startCounter = savedInstanceState.getInt("Counter");

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

//		if (askStatus == true) {
//			startCounter++;
//
//			if (startCounter == 5) {
//				scoreDialog();
//			}
//
//		} else {
//			startCounter = 0;
//		}

	}

//	@Override
//	protected void onRestoreInstanceState(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onRestoreInstanceState(savedInstanceState);
//
//		startCounter = savedInstanceState.getInt("Counter");
//		askStatus = savedInstanceState.getBoolean("Ask Status");
//
//	}
//
//	@Override
//	protected void onSaveInstanceState(Bundle outState) {
//		// TODO Auto-generated method stub
//		super.onSaveInstanceState(outState);
//
//		outState.putInt("Counter", startCounter);
//		outState.putBoolean("Ask Status", askStatus);
//
//	}

	private void initComponents() {

		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(R.layout.activity_actionbar);
		activity = MainActivity.this;

		food = getResources().getDrawable(R.drawable.food);
		foodDark = getResources().getDrawable(R.drawable.fooddark);
		team = getResources().getDrawable(R.drawable.team);
		teamDark = getResources().getDrawable(R.drawable.teamdark);

		fragmentManager = getSupportFragmentManager();

		fragment = ViewPagerDinner.newInstance();
		fragmentReplace(fragment);

		lunch_list_button = (CustomButton) findViewById(R.id.main_button_main);
		about_button = (CustomButton) findViewById(R.id.about_button_main);
		lunch_list_button.setOnClickListener(this);
		about_button.setOnClickListener(this);

		dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
		dateFormatMonth = new SimpleDateFormat("dd MMMM yyyy",
				Locale.getDefault());
		dateFormatFile = new SimpleDateFormat("MMyyyy", Locale.getDefault());

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		int itemID = v.getId();

		if (itemID == R.id.main_button_main) {

			if (!(fragment instanceof ViewPagerDinner)) {
				fragment = ViewPagerDinner.newInstance();
				fragmentReplace(fragment);

				lunch_list_button.setCompoundDrawablesWithIntrinsicBounds(null,
						foodDark, null, null);
				about_button.setCompoundDrawablesWithIntrinsicBounds(null,
						team, null, null);
			}

			About.isWebPageOpen = false;

		} else if (itemID == R.id.about_button_main) {
			if (!(fragment instanceof About)) {
				fragment = About.newInstance();
				fragmentReplace(fragment);

				about_button.setCompoundDrawablesWithIntrinsicBounds(null,
						teamDark, null, null);
				lunch_list_button.setCompoundDrawablesWithIntrinsicBounds(null,
						food, null, null);

			} else if (About.isWebPageOpen) {
				fragment = About.newInstance();
				fragmentReplace(fragment);

				about_button.setCompoundDrawablesWithIntrinsicBounds(null,
						teamDark, null, null);
				lunch_list_button.setCompoundDrawablesWithIntrinsicBounds(null,
						food, null, null);
			}

		}

	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			exitDialog();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	public static void fragmentReplace(Fragment fragment) {
		if (fragment != null) {
			fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			fragmentTransaction.replace(R.id.general_frame, fragment).commit();

		}
	}

	public static void errorDialog(String message) {

		AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.activity);

		builder.setTitle(R.string.error);
		builder.setMessage(message);

		builder.setPositiveButton(R.string.dialog_ok, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				MainActivity.activity.finish();
			}
		});

		builder.create().show();

	}

	public static void exitDialog() {

		AlertDialog.Builder dialog = new AlertDialog.Builder(
				MainActivity.activity);
		dialog.setTitle(R.string.exit_dialog_title);
		dialog.setMessage(R.string.exit_message);
		dialog.setPositiveButton(R.string.dialog_yes, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				MainActivity.activity.finish();
			}
		}).setNegativeButton(R.string.dialog_cancel, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		dialog.create().show();

	}

	public static void scoreDialog() {

		AlertDialog.Builder dialog = new AlertDialog.Builder(
				MainActivity.activity);
		dialog.setTitle(R.string.score_title);
		dialog.setMessage(R.string.score_text);
		dialog.setPositiveButton(R.string.dialog_ok, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				startCounter = 0;
				Intent webActivity = new Intent(Intent.ACTION_VIEW);
				 webActivity.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.towerlabs.yildizyemek"));
//				webActivity.setData(Uri
//						.parse("market://details?id=com.towerlabs.yildizyemek"));

				activity.startActivity(webActivity);
				dialog.dismiss();
			}
		}).setNeutralButton(R.string.dialog_after, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				askStatus = true;
				startCounter = 0;
				dialog.dismiss();
			}
		}).setNegativeButton(R.string.dialog_never_ask, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				askStatus = false;
				dialog.dismiss();
			}
		});
		dialog.create().show();

	}

}
