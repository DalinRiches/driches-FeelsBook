package driches.feelsbook.presentation.presenters;

import java.util.HashMap;

import driches.feelsbook.data.repository.EmotionDataRepository;
import driches.feelsbook.domain.interactors.GetStats;
import driches.feelsbook.domain.interactors.SuscribeToDatabase;
import driches.feelsbook.presentation.presenters.base.Presenter;

public class EmotionStatPresenter implements Presenter, SuscribeToDatabase.Callback,
                                             GetStats.Callback{

    private StatsView statsView;

    public interface StatsView {
        void displayStats(HashMap<String, Integer> stats);
    }

    public void setStatsView(StatsView statsView) {
        this.statsView = statsView;
    }

    public void subscribeToDataBase() {
        SuscribeToDatabase sus = new SuscribeToDatabase(this, EmotionDataRepository.getInstance());
        sus.run();
    }

    public void getStats() {
        GetStats stats = new GetStats(this, EmotionDataRepository.getInstance());
        stats.run();
    }

    @Override
    public void onStatsRecieved(HashMap<String, Integer> stats) {
        this.statsView.displayStats(stats);
    }

    @Override
    public void onNotify() {
        this.getStats();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
