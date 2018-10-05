package driches.feelsbook.presentation.view.component;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import driches.feelsbook.R;

public class EmotionBar extends ConstraintLayout {

    public interface Callback {
        void onBarClicked(String type);
    }

    private EmotionBar.Callback callback;

    private EmotionIcon anger;
    private EmotionIcon fear;
    private EmotionIcon joy;
    private EmotionIcon love;
    private EmotionIcon sad;
    private EmotionIcon suprise;

    private int textColor;

    public EmotionBar(Context context){
        super(context);
        this.initViews(context);
    }

    public EmotionBar (Context context, AttributeSet attributes) {
        super(context, attributes);
        this.initViews(context);
    }

    public EmotionBar (Context context, AttributeSet attributes, int defStyleAttr) {
        super(context, attributes, defStyleAttr);
        this.initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.emotion_bar, this);

        this.anger = (EmotionIcon) findViewById(R.id.add_anger);
        this.fear = (EmotionIcon) findViewById(R.id.add_fear);
        this.joy = (EmotionIcon) findViewById(R.id.add_joy);
        this.love = (EmotionIcon) findViewById(R.id.add_love);
        this.sad = (EmotionIcon) findViewById(R.id.add_sad);
        this.suprise = (EmotionIcon) findViewById(R.id.add_suprise);

        this.anger.setType("anger");
        this.fear.setType("fear");
        this.joy.setType("joy");
        this.love.setType("love");
        this.sad.setType("sad");
        this.suprise.setType("suprise");

        this.anger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton("anger");
            }
        });

        this.fear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton("fear");
            }
        });

        this.joy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton("joy");
            }
        });

        this.love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton("love");
            }
        });

        this.sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton("sad");
            }
        });

        this.suprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton("suprise");
            }
        });
    }

    public void setCallback(EmotionBar.Callback callback) {
        this.callback = callback;
    }

    private void onClickButton(String type) {
        if (this.callback == null) return;

        this.callback.onBarClicked(type);
    }
}
