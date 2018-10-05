package driches.feelsbook.presentation.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import driches.feelsbook.R;
import driches.feelsbook.domain.model.Emotion;
import driches.feelsbook.presentation.presenters.EmotionLogPresenter;
import driches.feelsbook.presentation.view.adapter.EmotionAdapter;
import driches.feelsbook.presentation.view.component.EmotionBar;

public class EmotionLogFragment extends Fragment implements EmotionLogPresenter.LogView, EmotionLogPresenter.EditView, EmotionBar.Callback{

    private RecyclerView recyclerView;
    private EmotionAdapter adapter;
    private EmotionLogPresenter presenter;


    public EmotionLogFragment() {
        // Required empty public constructor

    }

    @Override
    public void getList() {
        this.presenter.getEntries();
    }

    @Override
    public void onBarClicked(String type) {
        this.presenter.addEntry(type);
    }

    @Override
    public void onClickSave(Emotion oldEmotion, Emotion newEmotion) {
        this.presenter.replaceEntry(oldEmotion, newEmotion);
    }

    @Override
    public void showEditDialog(Emotion emotionToEdit) {
        presenter.makeEditDialog(this.getContext(), emotionToEdit);
    }

    @Override
    public void onClickEdit(Emotion emotion) {
        this.showEditDialog(emotion);
    }

    @Override
    public void onClickDelete(Emotion emotion) {
        this.presenter.makeDeleteDialog(this.getContext(), emotion);
    }

    @Override
    public void updateEntryList(ArrayList<Emotion> emotionArrayList) {
        if (this.adapter != null) {
            this.adapter.setEmotionArrayList(emotionArrayList);
        }
        else {
            this.finishInit(emotionArrayList);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        init((RecyclerView) view.findViewById(R.id.entries_view));
        return view;
    }

    private void init(RecyclerView rView){
         this.recyclerView = rView;

        this.presenter = new EmotionLogPresenter();
        this.presenter.setEditView(this);
        this.presenter.setLogView(this);
        this.presenter.suscribeToDatabase();
        this.getList();
    }

    public void finishInit(ArrayList<Emotion> emotions) {

        this.adapter = new EmotionAdapter(this.getContext(), this, emotions);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.adapter.notifyDataSetChanged();
    }
}
