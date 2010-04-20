
package pl.magot.vetch.ancal;


import java.text.DateFormatSymbols;
import java.util.Calendar;

import de.theprojects.ancal.MessageType;
import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.format.DateUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.app.AlertDialog;


public class Utils 
{
	private Context ctx = null;
	
	public static int ANIM_ALPHA_DURATION = 100;
	public static int ANIM_TRANSLATE_DURATION = 30;	
	
	//UTILS
	public Utils(Context context) {
		ctx = context;
	}
	
	public final String getShortWeekDay(Calendar date) {
		return getShortWeekDay(date.get(Calendar.DAY_OF_WEEK));
	}
	
	public String getShortWeekDay(int dayOfWeek) {
		return DateUtils.getDayOfWeekString(dayOfWeek, DateUtils.LENGTH_SHORT);
	}

	public String GetLongDate(Calendar date) {
		return DateUtils.formatDateTime(ctx, date.getTimeInMillis(), DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.LENGTH_MEDIUM);
	}
	
	//WARNING: String.format is VERY SLOW, not for paint draw !
	public String GetLongTime(Calendar date, boolean b24HourMode)
	{
		String s = "";
		if (b24HourMode)
		{
			s = String.format("%tk:%tM", date, date);
		} else {			
			s = String.format("%tk:%tM", date, date);
			if (date.get(Calendar.AM_PM) == 0) //AM						
				s = String.format("%tl:%tM am", date, date, date.get(Calendar.AM_PM));
			if (date.get(Calendar.AM_PM) == 1) //PM						
				s = String.format("%tl:%tM pm", date, date, date.get(Calendar.AM_PM));
		}
		return s; 
	}

	public String GetResStr(int id)
	{
		return ctx.getResources().getString(id);
	}

	public void alert(String msg)
	{
		int iconId = 0;
		AlertDialog.Builder dlg = new AlertDialog.Builder(ctx);		
		dlg.setMessage(msg);
		dlg.setPositiveButton(GetResStr(R.string.msgBoxButtonOk), null);		
		dlg.setTitle(ctx.getClass().getName().toString());		
		dlg.setIcon(iconId);		
		dlg.create();
		dlg.show();
	}
	
	public void alert(int i)
	{
		Integer ii = i;
		alert(ii.toString());
	}
	
	public void ShowMsgResStr(int i, MessageType msgType)
	{
		AlertDialog.Builder dlg = new AlertDialog.Builder(ctx);		
		dlg.setMessage(GetResStr(i));
		dlg.setPositiveButton(GetResStr(R.string.msgBoxButtonOk), null);		
		dlg.setTitle(msgType.getTitle());		
		dlg.setIcon(msgType.getIcon());		
		dlg.create();
		dlg.show();
	}
	
	public static int GetTimeAsSeconds(Calendar date)
	{
		return (date.get(Calendar.HOUR_OF_DAY) * 3600) +
			date.get(Calendar.MINUTE) * 60;
	}

	public static boolean YearDaysEqual(Calendar calDate, Calendar calDateTo)
	{
		if (calDate.get(Calendar.YEAR) == calDateTo.get(Calendar.YEAR))
			if (calDate.get(Calendar.MONTH) == calDateTo.get(Calendar.MONTH))
				if (calDate.get(Calendar.DAY_OF_MONTH) == calDateTo.get(Calendar.DAY_OF_MONTH))
					return true;
		return false;
	}

	public static boolean YearDaysGreater(Calendar calDate, Calendar calDateTo)
	{
		if (calDate.get(Calendar.YEAR) >= calDateTo.get(Calendar.YEAR))
			if (calDate.get(Calendar.MONTH) >= calDateTo.get(Calendar.MONTH))
				if (calDate.get(Calendar.DAY_OF_MONTH) >= calDateTo.get(Calendar.DAY_OF_MONTH))
					return true;
		return false;
	}
	
	public static String CapitalizeFirstLetter(String sText)
	{
		return sText.substring(0,1).toUpperCase() + sText.substring(1, sText.length()).toLowerCase();
	}

	public static String getAppVersionName(Context ctx)
	{
		try
		{
			PackageInfo pi = ctx.getPackageManager().getPackageInfo("pl.magot.vetch.ancal", 0);
			return pi.versionName;
		} catch (NameNotFoundException e) {
		}
		return "";
	}
	
  public static void startAlphaAnimIn(View view)
  {
		AlphaAnimation anim = new AlphaAnimation(0.5F, 1);
		anim.setDuration(ANIM_ALPHA_DURATION);
		anim.startNow();
		view.startAnimation(anim);
  }
  
  public static void startTranslateAnimIn(View view)
  {
		TranslateAnimation anim = new TranslateAnimation(0, 0, - view.getHeight(), 0);
		anim.setDuration(ANIM_TRANSLATE_DURATION);
		anim.startNow();
		view.startAnimation(anim);
  }  
	
}
