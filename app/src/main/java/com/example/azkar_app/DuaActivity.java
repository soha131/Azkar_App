package com.example.azkar_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class DuaActivity extends AppCompatActivity {

    private ImageButton btnPrevious, btnHome, btnNext;
    private ListView listDua;

    private List<String> allDuas; // All Supplications
    private static final int ITEMS_PER_PAGE = 6; // Duas per page
    private int currentPage = 0; // Current page index

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dua);

        // Initialize UI elements
        listDua = findViewById(R.id.listDua);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnHome = findViewById(R.id.btnHome);
        btnNext = findViewById(R.id.btnNext);

        // Initialize all Duas
        initializeDuas();

        // Load first page
        loadPage();

        // Button Click Listeners
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage > 0) {
                    currentPage--;
                    loadPage();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentPage + 1) * ITEMS_PER_PAGE < allDuas.size()) {
                    currentPage++;
                    loadPage();
                }
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DuaActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeDuas() {
        allDuas = new ArrayList<>();
        allDuas.add("رَبِّ اشْرَحْ لِي صَدْرِي وَيَسِّرْ لِي أَمْرِي وَاحْلُلْ عُقْدَةً مِنْ لِسَانِي يَفْقَهُوا قَوْلِي");
        allDuas.add("رَبِّ إِنِّي مَسَّنِيَ الضُّرُّ وَأَنتَ أَرْحَمُ الرَّاحِمِينَ");
        allDuas.add("لَا إِلَهَ إِلَّا أَنتَ سُبْحَانَكَ إِنِّي كُنتُ مِنَ الظَّالِمِينَ");
        allDuas.add("أَنتَ وَلِيِّي فِي الدُّنْيَا وَالآخِرَةِ تَوَفَّنِي مُسْلِمًا وَأَلْحِقْنِي بِالصَّالِحِينَ");
        allDuas.add("اللَّهُمَّ اجْعَلْنِي مِنْ التَّوَّابِينَ وَاجْعَلْنِي مِنَ المُتَطَهِّرِينَ");
        allDuas.add("اللَّهُمَّ إِنِّي أَسْأَلُكَ العَفْوَ وَالعَافِيَةَ فِي الدُّنْيَا وَالآخِرَةِ");
        allDuas.add("اللَّهُمَّ ارْزُقْنِي حُبَّكَ وَحُبَّ مَنْ يُحِبُّكَ وَحُبَّ كُلِّ عَمَلٍ يُقَرِّبُنِي إِلَى حُبِّكَ");
        allDuas.add("اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ العَجْزِ وَالكَسَلِ وَالجُبْنِ وَالبُخْلِ وَالهَرَمِ وَعَذَابِ القَبْرِ");
    }

    private void loadPage() {
        int start = currentPage * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, allDuas.size());

        List<String> pageDuas = allDuas.subList(start, end);

        // استخدام ArrayAdapter مع تخصيص TextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pageDuas) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);

                // الترقيم المستمر عبر الأذكار
                int overallPosition = start + position + 1;  // الإجمالي بدون إعادة الترقيم
                String numberedText = overallPosition + "/ " + pageDuas.get(position);
                textView.setText(numberedText);

                // جعل النص بالخط العريض
                textView.setTypeface(null, Typeface.BOLD);

                // تعيين اللون الأسود للنص
                textView.setTextColor(Color.BLACK);

                // إضافة مسافة بين كل TextView
                int paddingTop = 20;  // المسافة بين النصوص من الأعلى
                int paddingBottom = 50;  // المسافة بين النصوص من الأسفل
                textView.setPadding(10, paddingTop, 10, paddingBottom);  // إضافة المسافة

                return view;
            }
        };

        listDua.setAdapter(adapter);

        // Enable/Disable buttons based on the page
        btnPrevious.setEnabled(currentPage > 0);
        btnNext.setEnabled(end < allDuas.size());
    }
}
