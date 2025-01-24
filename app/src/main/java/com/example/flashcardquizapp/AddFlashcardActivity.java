package com.example.flashcardquizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class AddFlashcardActivity extends AppCompatActivity {

    private EditText etQuestion, etAnswer;
    public static ArrayList<Flashcard> flashcardList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);

        etQuestion = findViewById(R.id.et_question);
        etAnswer = findViewById(R.id.et_answer);

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = etQuestion.getText().toString().trim();
                String answer = etAnswer.getText().toString().trim();

                if (question.isEmpty() || answer.isEmpty()) {
                    Toast.makeText(AddFlashcardActivity.this, "Please fill both fields", Toast.LENGTH_SHORT).show();
                } else {
                    flashcardList.add(new Flashcard(question, answer));
                    Toast.makeText(AddFlashcardActivity.this, "Flashcard added!", Toast.LENGTH_SHORT).show();
                    etQuestion.setText("");
                    etAnswer.setText("");
                }
            }
        });
    }
}
