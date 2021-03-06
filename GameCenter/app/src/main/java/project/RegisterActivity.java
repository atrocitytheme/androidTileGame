package project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tests.slidingtiles.R;
import project.helper.ActivityHelper;
import project.model.component.User;
import project.controller.UserRouter;

/**
 * The register/sign up activity for the sliding puzzle tile game.
 */

public class RegisterActivity extends AppCompatActivity {

    /**
     * Error messages to be displayed as TextView for invalid login.
     */
    public static final String PASSWORD_REPEAT = "Passwords don't match!";
    public static final String INVALID_REGISTER = "This user  already exists!";
    public static final String INVALID_FORMAT = "Username/password should be at least 3 characters!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addLoginListener();
    }

    /**
     * Identify and verify if the login is valid, prompt error message to user otherwise.
     */
    private void addLoginListener() {
        Button loginButton = findViewById(R.id.register_btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.register_input_email);
                EditText password = findViewById(R.id.register_input_password);
                EditText confirmWord = findViewById(R.id.register_confirm_password);

                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                String confirmText = confirmWord.getText().toString();

                System.out.println(usernameText.length() + " is the length");
                System.out.println("here is the message");
                UserRouter router = new UserRouter(getContext());
                User user = new User(usernameText, passwordText);
                final TextView invalidView = findViewById(R.id.register_invalidWord);

                if (usernameText.length() < 3 || passwordText.length() < 3) {
                    ActivityHelper.disableButton(v, invalidView, INVALID_FORMAT);
                } else if (!passwordText.equals(confirmText)) {
                    ActivityHelper.disableButton(v, invalidView, PASSWORD_REPEAT);
                } else if (router.verifyUser(user, false)) {
                    ActivityHelper.disableButton(v, invalidView, INVALID_REGISTER);
                } else {
                    router.verifyUser(user, true);
                    finish();
                    switchToPanel();
                }
            }
        });
    }

    /**
     * Switch to Game Center Activity.
     */
    private void switchToPanel() {
        Intent tmp = new Intent(this, GameCenterActivity.class);
        startActivity(tmp);
    }

    /**
     * Get context of current game
     */
    private Context getContext() {
        return this.getApplicationContext();
    }

}
