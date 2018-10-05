package driches.feelsbook.presentation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.ArrayList;

import driches.feelsbook.domain.model.Emotion;
import driches.feelsbook.R;
import driches.feelsbook.presentation.presenters.EmotionLogPresenter;
import driches.feelsbook.presentation.view.component.EmotionIcon;
import driches.feelsbook.presentation.view.listeners.RecyclerViewClickListener;

public class EmotionAdapter extends RecyclerView.Adapter<EmotionAdapter.EmotionViewHolder> implements RecyclerViewClickListener {

    private LayoutInflater layoutInflater;
    private ArrayList<Emotion> emotions;
    private Context context;

    private final EmotionLogPresenter.LogView view;


    public EmotionAdapter(Context context, EmotionLogPresenter.LogView view, ArrayList<Emotion> emotions) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.view = view;
        this.notifyDataSetChanged();
        this.emotions = emotions;

    }

    @Override
    public int getItemCount() {
        return emotions.size();
    }

    @NonNull
    @Override
    public EmotionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.entry_listitem, viewGroup, false);
        EmotionViewHolder holder = new EmotionViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmotionViewHolder EmotionViewHolder, int i) {
        EmotionViewHolder.setType(emotions.get(i).getType());
        EmotionViewHolder.setTime(emotions.get(i).getTimeStamp());
        EmotionViewHolder.setComment(emotions.get(i).getComment());
    }

    public void setEmotionArrayList(ArrayList<Emotion> emotionArrayList) {
        this.emotions = emotionArrayList;
        this.notifyDataSetChanged();
    }

    @Override
    public void onClickCard(int position) {
        this.view.onClickEdit(this.emotions.get(position));
    }

    @Override
    public void onClickDelete(int position) {
        this.view.onClickDelete(this.emotions.get(position));
    }

    public class EmotionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener listener;

        private ImageView delete;
        private EmotionIcon icon;
        private TextView placeholderText;
        private TextView comment;
        private TextView time;
        private RelativeLayout parentLayout;

        public EmotionViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            this.listener = listener;
            delete = itemView.findViewById(R.id.delete_button);
            icon = itemView.findViewById(R.id.entry_icon);
            placeholderText = itemView.findViewById(R.id.comment_tooltip);
            comment = itemView.findViewById(R.id.comment);
            time = itemView.findViewById(R.id.time);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            icon.setOnClickListener(this);
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (this.listener == null) return;

            if (v.equals((View) delete)) {
                this.listener.onClickDelete(getAdapterPosition());
                return;
            }

            this.listener.onClickCard(getAdapterPosition());
        }

        public void setComment(String comment) {
            if (comment == null) {
                this.comment.setVisibility(View.GONE);
                this.placeholderText.setVisibility(View.VISIBLE);

                this.comment.setText(comment);
                return;
            }

            this.comment.setVisibility(View.VISIBLE);
            this.placeholderText.setVisibility(View.GONE);
            this.comment.setText(comment);
        }

        public void setType(String type) {
            icon.setType(type);
            icon.invalidate();
        }

        public void setTime(Date time) {
            this.time.setText(time.toString());
        }
    }
}
