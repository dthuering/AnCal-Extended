package pl.magot.vetch.ancal;

import java.util.Calendar;

import android.content.Context;
import android.text.format.DateUtils;

public abstract class AnCalDateUtils {
	private static final int LONG_DATE = DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.LENGTH_MEDIUM;
	
	public static String formatLongDate(Context context, Calendar date) {
	    return DateUtils.formatDateTime(context, date.getTimeInMillis(), LONG_DATE);		
	}

    public static String formatMediumDate(Context context, Calendar date) {
        return getAbbrevDayOfWeekString(date.get(Calendar.DAY_OF_WEEK)) + ", " + DateUtils.formatDateTime(context, date.getTimeInMillis(), DateUtils.FORMAT_NUMERIC_DATE);      
    }
    
    public static String formatTime(Context context, Calendar date) {
        return DateUtils.formatDateTime(context, date.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
    }
	
	public static String getAbbrevDayOfWeekString(Calendar date) {
	    return getAbbrevDayOfWeekString(date.get(Calendar.DAY_OF_WEEK));
	}
	
	public static String getAbbrevDayOfWeekString(int day) {
	    return DateUtils.getDayOfWeekString(day, DateUtils.LENGTH_MEDIUM);
	}

	private AnCalDateUtils() { /* NOOP */ }
}
