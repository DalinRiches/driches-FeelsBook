package driches.feelsbook.domain.interactors;

import driches.feelsbook.domain.interactors.base.BaseInteractor;
import driches.feelsbook.domain.repository.EmotionRepository;
import driches.feelsbook.domain.model.Emotion;

public class UpdateEmotion extends BaseInteractor {

    public interface Callback {
        // No callback functions
    }

    private Callback callback;
    private EmotionRepository mEmotionRepository;
    private Emotion oldEmotion;
    private Emotion newEmotion;


    public UpdateEmotion(Callback callback, EmotionRepository emotionRepository, Emotion oldEmotion, Emotion newEmotion) {
        this.callback = callback;
        this.mEmotionRepository = emotionRepository;
        this.oldEmotion = oldEmotion;
        this.newEmotion = newEmotion;
    }

    @Override
    public void run() {
        this.mEmotionRepository.update(this.oldEmotion, this.newEmotion);
    }
}