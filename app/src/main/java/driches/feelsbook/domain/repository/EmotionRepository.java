package driches.feelsbook.domain.repository;

import java.util.ArrayList;

import driches.feelsbook.domain.interactors.SuscribeToDatabase;
import driches.feelsbook.domain.model.Emotion;

public interface EmotionRepository {

    void suscribe(SuscribeToDatabase.Callback callback);
    ArrayList<Emotion> getAll();
    void insert(Emotion emotion);
    void update(Emotion oldEmotion, Emotion newEmotion);
    void delete(Emotion emotion);
}