package pl.magot.vetch.ancal.activities;

import pl.magot.vetch.ancal.AnCalDateUtils;
import pl.magot.vetch.ancal.CommonActivity;
import pl.magot.vetch.ancal.MessageType;
import pl.magot.vetch.ancal.R;
import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;

//New Note activity
public class ActivityOptions extends CommonActivity {
    // views
    private CheckBox chk24HourMode = null;
    private ImageButton btnSave = null;

    // views
    private Spinner spinFirstDayOfWeek = null;
    private ArrayAdapter<CharSequence> adpFirstDayOfWeek = null;

    // views
    private Spinner spinSnoozeMinutes = null;
    private Spinner spinSnoozeTimes = null;

    private Integer[] vecIntSnoozeMinutes = { 3, 5, 10, 15, 20, 30, 45 };
    private Integer[] vecIntSnoozeTimes = { 1, 3, 5, 7 };

    // methods
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.options);

        // initialize views
        InitViews();
    }

    @Override
    public void onStart() {
        super.onStart();
        InitState();
    }

    private void InitViews() {
        chk24HourMode = (CheckBox) findViewById(R.id.chkOptions24HourMode);

        adpFirstDayOfWeek = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, AnCalDateUtils
                .getWeekDays());
        adpFirstDayOfWeek.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinFirstDayOfWeek = (Spinner) findViewById(R.id.spinFirstDayOfWeek);
        spinFirstDayOfWeek.setAdapter(adpFirstDayOfWeek);

        ArrayAdapter<Integer> adpSnoozeMinutes = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
                vecIntSnoozeMinutes);
        adpSnoozeMinutes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSnoozeMinutes = (Spinner) findViewById(R.id.spinSnoozeMinutes);
        spinSnoozeMinutes.setAdapter(adpSnoozeMinutes);

        ArrayAdapter<Integer> adpSnoozeTimes = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
                vecIntSnoozeTimes);
        adpSnoozeTimes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSnoozeTimes = (Spinner) findViewById(R.id.spinSnoozeTimes);
        spinSnoozeTimes.setAdapter(adpSnoozeTimes);

        btnSave = (ImageButton) findViewById(R.id.btnOptionsSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SaveData();
            }
        });
    }

    private void InitState() {
        // title
        String sSubTitle = utils.GetResStr(R.string.titleDefaultOptions);

        SetActivityTitle(sSubTitle);

        chk24HourMode.setChecked(prefs.b24HourMode);

        spinFirstDayOfWeek.setSelection(prefs.iFirstDayOfWeek);
        spinSnoozeMinutes.setSelection(GetPositionByValue(vecIntSnoozeMinutes, prefs.iSnoozeMinutesOverdue), false);
        spinSnoozeTimes.setSelection(GetPositionByValue(vecIntSnoozeTimes, prefs.iSnoozeCount), false);

        // set focus
        spinFirstDayOfWeek.requestFocus();
    }

    public int GetPositionByValue(Integer[] vec, Integer iValue) {
        for (int i = 0; i < vec.length; i++)
            if (vec[i] == iValue)
                return i;
        return 0;
    }

    public void SaveData() {
        prefs.b24HourMode = chk24HourMode.isChecked();
        prefs.iFirstDayOfWeek = spinFirstDayOfWeek.getSelectedItemPosition();

        prefs.iSnoozeMinutesOverdue = (Integer) spinSnoozeMinutes.getSelectedItem();
        prefs.iSnoozeCount = (Integer) spinSnoozeTimes.getSelectedItem();

        if (prefs.Save()) {
            CloseActivity(CommonActivity.bundleOptionsUpdated, "");
        } else {
            utils.ShowMsgResStr(R.string.errCantSaveOptions, MessageType.ERROR);
        }
    }

    @Override
    protected void restoreStateFromFreeze() {

    }

}
