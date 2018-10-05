package driches.feelsbook.presentation.view.component;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import driches.feelsbook.R;

public class EmotionStat extends ConstraintLayout {

    private EmotionIcon icon;
    private TextView count;

    public EmotionStat(Context context){
        super(context);
        this.initViews(context);
    }

    public EmotionStat (Context context, AttributeSet attributes) {
        super(context, attributes);
        this.initViews(context);
    }

    public EmotionStat (Context context, AttributeSet attributes, int defStyleAttr) {
        super(context, attributes, defStyleAttr);
        this.initViews(context);
    }

    public void initViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.stat_bar, this);

        this.icon = findViewById(R.id.entry_icon);
        this.count = findViewById(R.id.count);
    }

    public void setCount(Integer count) {
        if (count == null) {
            count = 0;
        }

        this.count.setText(count.toString());
    }

    public void setType(String type) {
        this.icon.setType(type);
    }
}
