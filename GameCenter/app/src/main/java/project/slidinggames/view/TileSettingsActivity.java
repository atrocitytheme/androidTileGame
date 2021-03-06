package project.slidinggames.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;

import project.controller.system.GameCacheSystem;
import project.slidinggames.controller.BoardManager;
import tests.slidingtiles.R;
import project.slidinggames.model.component.BitmapCollection;
import project.helper.ActivityHelper;
import project.helper.structure.InputFilterMinMax;
import project.controller.system.UserPanel;

/**
 * Excluded from tests because it's a view class.
 * The Tiles Settings Activity that is the initial activity when the user clicks on the tiles game,
 * for which user can set the game configuration (complexity, max undo step, and their own image as
 * tiles background).
 */
public class TileSettingsActivity extends AppCompatActivity {

    /**
     * The main radioGroup of 3 RadioButton for each board complexity.
     */
    RadioGroup radioGroup;
    final static int RESULT_LOAD_IMAGE = 1;

    /**
     * The 3 RadioButton for each corresponding board complexity.
     */
    RadioButton radioButton;

    /**
     * the Bitmap collection of uploaded image
     */
    private Bitmap chosenImage;

    /**
     * The maximum number of undo steps per move (inputted by user).
     */
    private static int undo;

    /**
     * The hashMap to store the String info got from user and the corresponding numeric complexity
     */
    private HashMap<String, Integer> hook = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_settings);
        setConstant();

        final Button buttonApply = findViewById(R.id.button_apply);

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //onClick button for apply button
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID); //use radioButton.getID() to see which board is selected
                String s = radioButton.getResources().getResourceEntryName(radioID); // s is now "three", "four", "or "five".

                EditText mEdit = findViewById(R.id.text_undo);
                String setting = mEdit.getText().toString();
                undo = (setting.equals("")) ? 0 : Integer.valueOf(setting);

                if (undo >= 3) {
                    GameActivity.maxUndoSteps = undo;
                    int i = hook.get(s);
                    if (chosenImage != null && !BitmapCollection.getInstance().isLocked()) {
                        BitmapCollection collection = BitmapCollection.getInstance();
                        collection.loadImage(ActivityHelper.splitBitmap(chosenImage, i, i));
                    } else {
                        BitmapCollection.getInstance().latch(true);
                    }

                    BoardManager boardManager = new BoardManager(i, i);
                    GameCacheSystem sys = GameCacheSystem.getInstance();
                    sys.update(UserPanel.getInstance().getName(), boardManager);
                    switchToGame();
                } else {
                    final TextView invalidView = findViewById(R.id.text_tile_warnning);
                    ActivityHelper.disableButton(v, invalidView, "Minimum number of undo steps should be at least 3");
                }
            }
        });

        addUploadImage();
    }

    /**
     * To set those constant value used in the configuration process
     */
    private void setConstant() {
        hook.put("three", 3);
        hook.put("four", 4);
        hook.put("five", 5);

        radioGroup = findViewById(R.id.radioGroup);
        EditText et = findViewById(R.id.text_undo);
        et.setFilters(new InputFilter[]{new InputFilterMinMax("3", "100000")});
        et.setText("3");

    }

    /**
     * to add imaged uploaded if there is one.
     */
    private void addUploadImage() {
        Button imageLoader = findViewById(R.id.image_loader);
        imageLoader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selection = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(selection, RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedTarget = data.getData();
            try {
                chosenImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedTarget);
                BitmapCollection.getInstance().latch(false);
            } catch (IOException e) {
                System.out.println("no file specified");
            }
        }
    }

    /**
     * switch to Game activity
     */
    private void switchToGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
