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
package com.towerlabs.yildizyemek.customviews;

import com.towerlabs.yildizyemek.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class Fonts {

	public static Typeface setCustomTypedFaces(Context context, AttributeSet attrs) {
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.CustomFonts);

		String fontName = array.getString(R.styleable.CustomFonts_customFont);

		Typeface taypeFace = Typeface.createFromAsset(context.getAssets(),
				"fonts/" + fontName);

		array.recycle();
		
		return taypeFace;
	}

}
