package driches.feelsbook.presentation.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import driches.feelsbook.R;
import driches.feelsbook.presentation.presenters.EmotionStatPresenter;
import driches.feelsbook.presentation.view.component.EmotionStat;

public class EmotionStatFragment extends Fragment implements EmotionStatPresenter.StatsView {

    private EmotionStat angerStat;
    private EmotionStat fearStat;
    private EmotionStat joyStat;
    private EmotionStat loveStat;
    private EmotionStat sadStat;
    private EmotionStat supriseStat;

    private EmotionStatPresenter presenter;

    public EmotionStatFragment() {
        /* Required empty public constructor */
    }

    @Override
    public void displayStats(HashMap<String, Integer> stats) {
        if (stats == null) return;

        this.angerStat.setCount(stats.get("anger"));
        this.fearStat.setCount(stats.get("fear"));
        this.joyStat.setCount(stats.get("joy"));
        this.loveStat.setCount(stats.get("love"));
        this.sadStat.setCount(stats.get("sad"));
        this.supriseStat.setCount(stats.get("suprise"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        this.angerStat = view.findViewById(R.id.anger_stat);
        this.fearStat = view.findViewById(R.id.fear_stat);
        this.joyStat = view.findViewById(R.id.joy_stat);
        this.loveStat = view.findViewById(R.id.love_stat);
        this.sadStat = view.findViewById(R.id.sad_stat);
        this.supriseStat = view.findViewById(R.id.suprise_stat);

        init();

        return view;
    }

    private void init(){
        this.presenter = new EmotionStatPresenter();
        this.presenter.setStatsView(this);
        this.presenter.subscribeToDataBase();

        this.angerStat.setType("anger");
        this.fearStat.setType("fear");
        this.joyStat.setType("joy");
        this.loveStat.setType("love");
        this.sadStat.setType("sad");
        this.supriseStat.setType("suprise");

        this.presenter.getStats();
    }
}
