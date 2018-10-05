package driches.feelsbook.domain.interactors;

import driches.feelsbook.domain.interactors.base.BaseInteractor;
import driches.feelsbook.domain.repository.EmotionRepository;

public class SuscribeToDatabase extends BaseInteractor {

    public interface Callback {
        void onNotify();
    }

    Callback callback;
    EmotionRepository emotionRepository;

    public SuscribeToDatabase(Callback callback, EmotionRepository emotionRepository) {
        this.callback = callback;
        this.emotionRepository = emotionRepository;
    }

    @Override
    public void run() {
        this.emotionRepository.suscribe(this.callback);
    }
}
