package com.example.funadvisor.ui.MagicBall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.funadvisor.R;

import java.util.Random;

public class BallFragment extends Fragment {

    Button btn;
    private BallViewModel notificationsViewModel;
    TextView answerText;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        notificationsViewModel = ViewModelProviders.of(this).get(BallViewModel.class);
        root = inflater.inflate(R.layout.fragment_ball, container, false);
        answerText = root.findViewById(R.id.textBallAnswer);

        btn = root.findViewById(R.id.button_play_ball);

        btn.setOnClickListener(view -> {
            int i = new Random().nextInt(Ball.answers.length);
            String answer = Ball.answers[i];
            answerText.setText(answer);
        });

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), s -> answerText.setText(s));
        return root;
    }
}