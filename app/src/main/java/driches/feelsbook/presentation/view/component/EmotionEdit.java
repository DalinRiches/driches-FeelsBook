package driches.feelsbook.presentation.view.component;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import driches.feelsbook.R;
import driches.feelsbook.domain.model.Emotion;
import driches.feelsbook.presentation.presenters.EmotionLogPresenter;

public class EmotionEdit extends ScrollView implements EmotionBar.Callback{

    private EmotionLogPresenter.EditView view;
    private Dialog dialog;
    private EditText commentBox;
    private EmotionBar typeBar;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button saveButton;
    private Emotion oldEmotion = null;
    private String selectedType = null;

    public EmotionEdit(Context context){
        super(context);
        this.initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.edit_emotion_dialog, this);

        this.typeBar = (EmotionBar) findViewById(R.id.edit_type_bar);
        this.commentBox = (EditText) findViewById(R.id.edit_text_box);
        this.datePicker = (DatePicker) findViewById(R.id.date_picker);
        this.timePicker = (TimePicker) findViewById(R.id.time_picker);
        this.saveButton = (Button) findViewById(R.id.save_button);

        this.typeBar.setCallback(this);

        this.saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, datePicker.getYear());
                calendar.set(Calendar.MONTH, datePicker.getMonth());
                calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                calendar.set(Calendar.HOUR, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());

                Date date = calendar.getTime();

                view.onClickSave(oldEmotion, new Emotion(
                        selectedType,
                        date,
                        commentBox.getText().toString()
                ));

                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBarClicked(String type) {
        this.selectedType = type;
    }

    public void loadEmotion(Emotion emotionToEdit) {
        this.oldEmotion = emotionToEdit;
        this.commentBox.setText(this.oldEmotion.getComment());
        this.selectedType = emotionToEdit.getType();

        LocalDate localDate = emotionToEdit.getTimeStamp().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        this.datePicker.updateDate(
                localDate.getYear(),
                localDate.getMonthValue() - 1,
                localDate.getDayOfMonth()
        );

        this.timePicker.setHour(emotionToEdit.getTimeStamp().getHours());
        this.timePicker.setMinute(emotionToEdit.getTimeStamp().getMinutes());
    }

    public void setView(EmotionLogPresenter.EditView view) {
        this.view = view;
    }

    public void openDialog(Dialog dialog) {
        if (dialog == null) return;

        this.dialog = dialog;
        this.dialog.show();
    }
}
