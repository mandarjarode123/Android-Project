package com.example.flashcardquizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion, tvScore;
    private EditText etUserAnswer;
    private int currentIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion = findViewById(R.id.tv_question);
        tvScore = findViewById(R.id.tv_score);
        etUserAnswer = findViewById(R.id.et_user_answer);

        loadNextQuestion();

        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userAnswer = etUserAnswer.getText().toString().trim();

                if (userAnswer.isEmpty()) {
                    Toast.makeText(QuizActivity.this, "Please enter your answer", Toast.LENGTH_SHORT).show();
                } else {
                    checkAnswer(userAnswer);
                }
            }
        });
    }

    private void loadNextQuestion() {
        if (currentIndex < AddFlashcardActivity.flashcardList.size()) {
            tvQuestion.setText(AddFlashcardActivity.flashcardList.get(currentIndex).getQuestion());
        } else {
            Toast.makeText(this, "Quiz completed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkAnswer(String userAnswer) {
        Flashcard currentFlashcard = AddFlashcardActivity.flashcardList.get(currentIndex);

        if (userAnswer.equalsIgnoreCase(currentFlashcard.getAnswer())) {
            score++;
            tvScore.setText("Score: " + score);
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong! Correct answer: " + currentFlashcard.getAnswer(), Toast.LENGTH_SHORT).show();
        }

        currentIndex++;
        etUserAnswer.setText("");

        if (currentIndex < AddFlashcardActivity.flashcardList.size()) {
            loadNextQuestion();
        } else {
            Toast.makeText(this, "Quiz completed! Final Score: " + score, Toast.LENGTH_LONG).show();
        }
    }
}
