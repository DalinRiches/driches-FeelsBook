package driches.feelsbook.presentation.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;

import driches.feelsbook.R;


public class EmotionIcon extends RelativeLayout {

     private ImageView iconImage;
     private TextView iconText;

     public EmotionIcon(Context context) {
         super(context);
         this.initViews(context);
     }

     public EmotionIcon(Context context, AttributeSet attributes) {
         super(context, attributes);
         this.initViews(context);
     }

     public EmotionIcon(Context context, AttributeSet attributes, int defStyleAttr) {
         super(context, attributes, defStyleAttr);
         this.initViews(context);
     }

     private void initViews(Context context) {
         LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         inflater.inflate(R.layout.emotion_icon, (ViewGroup) this.getRootView());

         this.iconImage = (ImageView) this.findViewById(R.id.icon_image);
         this.iconText = (TextView) this.findViewById(R.id.icon_text);
     }

     public void setType(String type) {
         if (type == "anger") {
             this.iconImage.setImageResource(R.drawable.anger);
             this.iconText.setText(R.string.angryButtDesc);
         }
         else if (type == "fear") {
             this.iconImage.setImageResource(R.drawable.fear);
             this.iconText.setText(R.string.fearButtDesc);
         }
         else if (type == "joy") {
             this.iconImage.setImageResource(R.drawable.joy);
             this.iconText.setText(R.string.joyButtDesc);
         }
         else if (type == "love") {
             this.iconImage.setImageResource(R.drawable.love);
             this.iconText.setText(R.string.loveButtDesc);
         }
         else if (type == "sad") {
             this.iconImage.setImageResource(R.drawable.sad);
             this.iconText.setText(R.string.sadButtDesc);
         }
         else if (type == "suprise") {
             this.iconImage.setImageResource(R.drawable.suprise);
             this.iconText.setText(R.string.supriseButtDesc);
         }
     }
}