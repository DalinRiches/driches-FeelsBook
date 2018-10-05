package driches.feelsbook.domain.interactors;

import driches.feelsbook.domain.interactors.base.BaseInteractor;
import driches.feelsbook.domain.repository.EmotionRepository;
import driches.feelsbook.domain.model.Emotion;

public class NewEmotion extends BaseInteractor {

    public interface Callback {
        // No callback functions
    }

    private Callback callback;
    private EmotionRepository mEmotionRepository;
    private Emotion newEmotion;

    public NewEmotion(Callback callback, EmotionRepository emotionRepository, Emotion newEmotion) {
        this.callback = callback;
        this.mEmotionRepository = emotionRepository;
        this.newEmotion =newEmotion;
    }

    @Override
    public void run() {
        this.mEmotionRepository.insert(newEmotion);
    }
}