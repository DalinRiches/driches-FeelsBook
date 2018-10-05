package driches.feelsbook.data.repository;

import java.io.File;
import java.util.ArrayList;

import driches.feelsbook.data.disk.FileManager;
import driches.feelsbook.domain.interactors.SuscribeToDatabase;
import driches.feelsbook.domain.model.Emotion;
import driches.feelsbook.domain.repository.EmotionRepository;
import driches.feelsbook.data.entity.mapper.EmotionJsonMapper;


public class EmotionDataRepository implements EmotionRepository {
    private static EmotionDataRepository emotionDataRepository = null;

    public static EmotionDataRepository getInstance() {
        return emotionDataRepository;
    }

    public static void initRepository(File file) {
        if (emotionDataRepository == null) {
            emotionDataRepository = new EmotionDataRepository(file);
        }
    }

    private File emotionFile;
    private EmotionJsonMapper emotionJsonMapper;
    private FileManager fileManager;
    private ArrayList<Emotion> emotionList;
    private ArrayList<SuscribeToDatabase.Callback> suscribers;

    private EmotionDataRepository(File file) {
        this.fileManager = FileManager.getFileManager();
        this.emotionFile = file;
        this.emotionJsonMapper = new EmotionJsonMapper();

        this.emotionList = new ArrayList<>();
        this.suscribers = new ArrayList<>();

        // Load previously saved emotions
        String jsonString = this.fileManager.loadFile(this.emotionFile);

        ArrayList<Emotion> loadedEmotions = this.emotionJsonMapper.toCollection(jsonString);

        if (loadedEmotions != null) {
            this.emotionList = loadedEmotions;
        }
    }

    private void notifySuscribers() {
        for (SuscribeToDatabase.Callback suscriber: this.suscribers) {
            suscriber.onNotify();
        }
    }

    @Override
    public void suscribe(SuscribeToDatabase.Callback callback) {
        if (this.suscribers.contains(callback)) return;

        suscribers.add(callback);
    }

    @Override
    public ArrayList<Emotion> getAll() {
        return this.emotionList;
    }

    @Override
    public void insert(Emotion emotion) {
        if (this.emotionList.contains(emotion)) return;

        this.emotionList.add(emotion);
        this.notifySuscribers();

        this.fileManager.saveFile(
                this.emotionFile,
                this.emotionJsonMapper.toJsonString(this.emotionList)
                );
    }

    @Override
    public void update(Emotion oldEntry, Emotion newEntry) {

        if (!this.emotionList.contains(oldEntry)) {
            //TODO: Handle this
            return;
        }

        int index = this.emotionList.indexOf(oldEntry);
        this.emotionList.set(index, newEntry);

        this.notifySuscribers();

        this.fileManager.saveFile(
                this.emotionFile,
                this.emotionJsonMapper.toJsonString(this.emotionList)
        );
    }

    @Override 
    public void delete(Emotion emotion) {
        this.emotionList.remove(emotion);
        this.notifySuscribers();

        this.fileManager.saveFile(
                this.emotionFile,
                this.emotionJsonMapper.toJsonString(this.emotionList)
                );
    }
}