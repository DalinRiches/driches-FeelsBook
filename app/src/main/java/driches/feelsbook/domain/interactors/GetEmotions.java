package driches.feelsbook.domain.interactors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import driches.feelsbook.domain.interactors.base.BaseInteractor;
import driches.feelsbook.domain.repository.EmotionRepository;
import driches.feelsbook.domain.model.Emotion;

public class GetEmotions extends BaseInteractor {

    public interface Callback {
        void onRetrieved(ArrayList<Emotion> emotionList);
    }

    private Comparator<Emotion> emotionComparator = new Comparator<Emotion>() {
        @Override
        public int compare(Emotion o1, Emotion o2) {

            if (o1.getTimeStamp().before(o2.getTimeStamp())) return 1;
            if (o1.getTimeStamp().after(o2.getTimeStamp())) return  -1;

            return 0;
        }
    };

    private Callback callback;
    private EmotionRepository emotionRepository;

    public GetEmotions(Callback callback, EmotionRepository emotionRepository) {
        this.callback = callback;
        this.emotionRepository = emotionRepository;
    }

    @Override
    public void run() {
        final ArrayList<Emotion> emotionList = this.emotionRepository.getAll();

        Collections.sort(emotionList, emotionComparator);

        callback.onRetrieved(emotionList);
    }
}