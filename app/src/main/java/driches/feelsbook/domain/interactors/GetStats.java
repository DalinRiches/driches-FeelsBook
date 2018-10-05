package driches.feelsbook.domain.interactors;

import java.util.ArrayList;
import java.util.HashMap;

import driches.feelsbook.domain.interactors.base.BaseInteractor;
import driches.feelsbook.domain.model.Emotion;
import driches.feelsbook.domain.repository.EmotionRepository;

public class GetStats extends BaseInteractor {

    public interface Callback {
        void onStatsRecieved(HashMap<String, Integer> stats);
    }

    private EmotionRepository mEmotionRepository;
    private Callback callback;

    public GetStats(Callback callback, EmotionRepository emotionRepository) {
        this.callback = callback;
        this.mEmotionRepository = emotionRepository;
    }

    @Override
    public void run() {
        HashMap<String, Integer> emotionCounts = new HashMap<>();
        ArrayList<Emotion> emotionList = this.mEmotionRepository.getAll();

        for (Emotion emotion: emotionList) {
            if (!emotionCounts.containsKey(emotion.getType())) {
                emotionCounts.put(emotion.getType(), 1);
                continue;
            }

            emotionCounts.replace(
                    emotion.getType(),
                    emotionCounts.get(emotion.getType()) + 1);
        }

        this.callback.onStatsRecieved(emotionCounts);
    }
}

