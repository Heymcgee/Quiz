package com.example.android.quiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * My quiz app about Pop Art for Udacity Android Basics
 */
public class MainActivity extends AppCompatActivity {

    int score = 0;
    Button reset_button;
    ImageButton myImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This method turns my soup can image into a button that creates an intent to open a Pop Art website
        myImageButton = findViewById(R.id.go_to_web_image);
        myImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.theartstory.org/movement-pop-art.htm"));
                startActivity(intentLoadNewActivity);
            }
        });

//        This makes the keyboard only pop up when the EditText field is selected.
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

//   FLAG_ACTIVITY_CLEAR_TOP -RESET BUTTON RESTARTS ACTIVITY
//        If the Intent resolves to an Activity in the current
//        task the Activities above it on the stack are destroyed
//        so that it is at the top of the stack, and it is re-used.

        reset_button = findViewById(R.id.reset_button);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent restartIntent = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                assert restartIntent != null;
                restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(restartIntent);
            }
        });
    }

    /**
     * Method for Question 1: If the answer is correct, add 1 to score.
     */
    public void q1Answer() {
        CheckBox q1Answer4CheckBox = findViewById(R.id.Q1_answer4);
        boolean q1Answer4CheckBoxChecked = q1Answer4CheckBox.isChecked();
        if (q1Answer4CheckBoxChecked) {
            score += 1;
        }
    }

    /**
     * Method for Question 2: If the answers (2 must be selected) is correct, add 2 to score.
     */
    public void q2Answer() {
        CheckBox q2Answer4CheckBox = findViewById(R.id.Q2_answer4);
        boolean q2Answer4CheckBoxChecked = q2Answer4CheckBox.isChecked();

        CheckBox q2Answer6CheckBox = findViewById(R.id.Q2_answer6);
        boolean q2Answer6CheckBoxChecked = q2Answer6CheckBox.isChecked();
        if (q2Answer4CheckBoxChecked && q2Answer6CheckBoxChecked) {
            score += 2;
        }
    }

    /**
     * Method for Question 3: If the input answer is correct (contains Warhol), add 1 to score.
     */
    public void q3Answer() {
        EditText answerField = findViewById(R.id.artist_answer);
        String correctAnswer = answerField.getText().toString().replace(" ", "");
        if (correctAnswer.equals("Warhol")) {
            score += 1;
        }
    }

    /**
     * Method for Question 4: If the answer is correct, add 1 to score.
     */
    public void q4Answer() {
        RadioButton q4TrueButton = findViewById(R.id.true_button);
        boolean q4TrueButtonChecked = q4TrueButton.isChecked();
        if (q4TrueButtonChecked) {
            score += 1;
        }
    }

    /**
     * Method for Question 5: If the answer is correct, add 1 to score.
     */
    public void q5Answer() {
        RadioButton q5Answer3Button = findViewById(R.id.Q5_answer3);
        boolean q5Answer3ButtonChecked = q5Answer3Button.isChecked();
        if (q5Answer3ButtonChecked) {
            score += 1;
        }
    }

    /**
     * Method for Question 6: If the answer is correct, add 1 to score.
     */
    public void q6Answer() {
        RadioButton q6FalseButton = findViewById(R.id.false_button_two);
        boolean q6FalseButtonChecked = q6FalseButton.isChecked();
        if (q6FalseButtonChecked) {
            score += 1;
        }
    }

    /**
     * This is the method called for when the scores are added together.
     */
    public void calculateScore() {

        q1Answer();
        q2Answer();
        q3Answer();
        q4Answer();
        q5Answer();
        q6Answer();
    }

    /**
     * This is the method called when the GET SCORE button is clicked.
     * Toast message corresponds to given score.
     */
    public void getScore(View view) {

        calculateScore();

        if (score == 7) {
            String scoreMessage6 = "You scored " + score + " out of 7! " + "You are a Pop Art genius!";
            Toast.makeText(this, scoreMessage6, Toast.LENGTH_LONG).show();
        } else if (score == 6) {
            String scoreMessage5 = "You scored " + score + " out of 7! " + "You know a good deal about Pop Art!";
            Toast.makeText(this, scoreMessage5, Toast.LENGTH_LONG).show();
        } else if (score == 5) {
            String scoreMessage4 = "You scored " + score + " out of 7! " + "Pretty good!";
            Toast.makeText(this, scoreMessage4, Toast.LENGTH_LONG).show();
        } else if (score == 4) {
            String scoreMessage3 = "You scored " + score + " out of 7! " + "You can do better!";
            Toast.makeText(this, scoreMessage3, Toast.LENGTH_LONG).show();
        } else if (score == 3) {
            String scoreMessage2 = "You scored " + score + " out of 7! " + "Not good, keep studying!";
            Toast.makeText(this, scoreMessage2, Toast.LENGTH_LONG).show();
        } else if (score == 2) {
            String scoreMessage1 = "You scored " + score + " out of 7! " + "You don't know much about art, do you?";
            Toast.makeText(this, scoreMessage1, Toast.LENGTH_LONG).show();
        } else if (score == 1) {
            String scoreMessage = "You scored " + score + " out of 7! " + "You don't know much about art, do you?.";
            Toast.makeText(this, scoreMessage, Toast.LENGTH_LONG).show();
        } else {
            String scoreMessage0 = "You scored " + score + " out of 7! " + "Click on the soup can to learn about Pop Art.";
            Toast.makeText(this, scoreMessage0, Toast.LENGTH_LONG).show();
        }

        final Button score_button = findViewById(R.id.score_button);
        score_button.setClickable(false);
        score_button.setVisibility(View.GONE);
    }

    /**
     * This is the method called when the RESET button is clicked.
     */
    public void resetQuiz(View view) {
        score = 0;
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }
}


