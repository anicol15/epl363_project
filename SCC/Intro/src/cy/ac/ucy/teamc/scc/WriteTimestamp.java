package cy.ac.ucy.teamc.scc;

import java.util.Calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class WriteTimestamp {

	public static void writeTime(Context c, String u) {
		SharedPreferences s_pref = PreferenceManager
				.getDefaultSharedPreferences(c);
		Editor edit = s_pref.edit();
		edit.putString("udate", u);
		edit.commit();
	}

	public static void writeCurrentTime(Context c) {
		SharedPreferences s_pref = PreferenceManager
				.getDefaultSharedPreferences(c);
		Editor edit = s_pref.edit();
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp timeStamp = new java.sql.Timestamp(now.getTime());
		edit.putString("udate", timeStamp.toString());
		edit.commit();
	}

	public static String readTime(Context c) {
		SharedPreferences s_pref = PreferenceManager
				.getDefaultSharedPreferences(c);

		String udate = s_pref.getString("udate", "");
		return udate;
	}
}
