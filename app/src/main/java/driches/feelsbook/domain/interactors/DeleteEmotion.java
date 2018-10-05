package driches.feelsbook.domain.interactors;

import driches.feelsbook.domain.interactors.base.BaseInteractor;
import driches.feelsbook.domain.repository.EmotionRepository;
import driches.feelsbook.domain.model.Emotion;

public class DeleteEmotion extends BaseInteractor {

    public interface Callback {
        // Has no callback
    }

    private DeleteEmotion.Callback callback;
    private EmotionRepository mEmotionRepository;
    private Emotion emotionToDelete;

    public DeleteEmotion(Callback callback, EmotionRepository emotionRepository, Emotion emotionToDelete) {
        this.callback = callback;
        this.mEmotionRepository = emotionRepository;
        this.emotionToDelete =emotionToDelete;
    }

    @Override
    public void run() {
        this.mEmotionRepository.delete(emotionToDelete);
    }
}