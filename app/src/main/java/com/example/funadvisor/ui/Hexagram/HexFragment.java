package com.example.funadvisor.ui.Hexagram;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.funadvisor.R;

import java.util.Random;

import static com.example.funadvisor.R.drawable.ic_3;


public class HexFragment extends Fragment {

    private HexViewModel hexViewModel;
    ImageView[] imageView = new ImageView[6];
    Button btn;
    View root;
    int[] digits = {0, 0, 0, 0, 0, 0};
    private int pressed_count = 0;
    TextView russianResult, chineseResult, digitResult;
    TextView hexDescription;
//    DocumentView hexDescription;
    ScrollView hexScroll;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hexViewModel =
                ViewModelProviders.of(this).get(HexViewModel.class);
        root = inflater.inflate(R.layout.fragment_hex, container, false);

        final TextView textView = root.findViewById(R.id.text_dashboard);
        btn = root.findViewById(R.id.button_play);
        russianResult = root.findViewById(R.id.russianResult);
        chineseResult = root.findViewById(R.id.chineseResult);
        digitResult = root.findViewById(R.id.digitResult);
        hexDescription = root.findViewById(R.id.hex_description);
        hexScroll = root.findViewById(R.id.hex_scroll);

        ResetImages();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btnText;
                if (pressed_count < 6) {
                    btnText = String.format("Еще нажатий: %s", 5 - pressed_count);
                    int id;
                    if (new Random().nextBoolean()) {
                        id = R.drawable.ic_1;
                        digits[pressed_count] = 0;
                    } else {
                        digits[pressed_count] = 1;
                        id = R.drawable.ic_2;
                    }

                    imageView[pressed_count].setImageResource(id);
                    if (pressed_count == 5) {
                        btnText = getString(R.string.hex_button_start);
                        int sum = 0;
                        String a = "";
                        for (int i = 0; i < 6; i++) {
                            int d = i;
                            sum += (Math.pow(2, d) * digits[d]);
                            a = a + "" + (digits[d]);
                        }
                        russianResult.setText(HexValues.values[sum]);
                        chineseResult.setText(HexValues.chineseNames[sum]);
//                        if (sum < 16) {
                            hexDescription.setText("\t\t\t" + HexValues.descriptions[sum] + "\n\n\n");
//                        }
                    }
                    btn.setText(btnText);
                    pressed_count++;
                } else {
                    pressed_count = 0;
                    ResetImages();
                }
            }
//
        });
        hexViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

    }

    private void ResetImages() {
        for (int i = 0; i < 6; i++) {
            String imgId = "line" + (i + 1);
            int resID = getResources().getIdentifier(imgId, "id", getActivity().getPackageName());
            imageView[i] = root.findViewById(resID);
            imageView[i].setImageResource(ic_3);
            russianResult.setText("");
            chineseResult.setText("");
            digitResult.setText("");
            hexDescription.setText("\n\n\n\n\n\n");
        }

    }
}