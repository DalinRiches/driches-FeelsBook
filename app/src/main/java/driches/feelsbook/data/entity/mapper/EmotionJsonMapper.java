package driches.feelsbook.data.entity.mapper;

import driches.feelsbook.domain.model.Emotion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class EmotionJsonMapper {
    private final Gson gson;
    private final Type typeToken;

    public EmotionJsonMapper() {
        GsonBuilder builder = new GsonBuilder();
        this.gson = builder.create();
        this.typeToken = new TypeToken<ArrayList<Emotion>>() {}.getType();
    }

    public ArrayList<Emotion> toCollection(String emotionEntityArrayListJsonString)  throws JsonSyntaxException {
        return this.gson.fromJson(emotionEntityArrayListJsonString, this.typeToken);
    }

    public String toJsonString(ArrayList<Emotion> emotionEntityArrayList) {
        return this.gson.toJson(emotionEntityArrayList,this.typeToken);
    }
}