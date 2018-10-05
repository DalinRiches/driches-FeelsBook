package driches.feelsbook.presentation.presenters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.ArrayList;

import driches.feelsbook.R;
import driches.feelsbook.data.repository.EmotionDataRepository;
import driches.feelsbook.domain.interactors.DeleteEmotion;
import driches.feelsbook.domain.interactors.GetEmotions;
import driches.feelsbook.domain.interactors.NewEmotion;
import driches.feelsbook.domain.interactors.SuscribeToDatabase;
import driches.feelsbook.domain.interactors.UpdateEmotion;
import driches.feelsbook.domain.model.Emotion;
import driches.feelsbook.domain.repository.EmotionRepository;
import driches.feelsbook.presentation.presenters.base.Presenter;
import driches.feelsbook.presentation.view.component.EmotionEdit;

public class EmotionLogPresenter implements Presenter, NewEmotion.Callback, UpdateEmotion.Callback,
                                            GetEmotions.Callback, DeleteEmotion.Callback,
                                            SuscribeToDatabase.Callback {

    private LogView logView;
    private EditView editView;

    public interface EditView {
        void onClickSave(Emotion oldEmotion, Emotion newEmotion);
        void showEditDialog(Emotion emotionToEdit);
    }

    public interface LogView {
        void onClickEdit(Emotion entry);
        void onClickDelete(Emotion entry);
        void updateEntryList(ArrayList<Emotion> listOfNewEmotions);
        void getList();
    }

    @Override
    public void onNotify() {
        this.getEntries();
    }

    @Override
    public void onRetrieved(ArrayList<Emotion> emotionList){
        this.logView.updateEntryList(emotionList);
    }

    public void setEditView(EditView editView) {
        this.editView = editView;
    }

    public void setLogView(LogView logView) {
        this.logView = logView;
    }

    public void suscribeToDatabase() {
        SuscribeToDatabase sus = new SuscribeToDatabase(this, EmotionDataRepository.getInstance());
        sus.run();
    }

    public void getEntries() {
        GetEmotions get = new GetEmotions(this, EmotionDataRepository.getInstance());
        get.run();
    }

    public void deleteEntry(Emotion entry) {
        DeleteEmotion del = new DeleteEmotion(this, EmotionDataRepository.getInstance(), entry);
        del.run();
    }

    public void addEntry(String type) {
        NewEmotion add = new NewEmotion(this, EmotionDataRepository.getInstance(), new Emotion(type));
        add.run();
    }

    public void replaceEntry(Emotion oldEntry, Emotion newEntry) {
        UpdateEmotion up = new UpdateEmotion(this, EmotionDataRepository.getInstance(), oldEntry, newEntry);
        up.run();
    }

    public void makeEditDialog(Context context, Emotion emotionToEdit) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        EmotionEdit view = new EmotionEdit(context);
        Dialog dialog;

        view.setView(this.editView);
        view.loadEmotion(emotionToEdit);

        builder.setView(view);
        dialog = builder.create();
        view.openDialog(dialog);
    }

    public void makeDeleteDialog(Context context, Emotion emotionToDelete) {
        final Emotion toDelete = emotionToDelete;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.delete_message);
        builder.setPositiveButton(R.string.delete_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteEntry(toDelete);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}