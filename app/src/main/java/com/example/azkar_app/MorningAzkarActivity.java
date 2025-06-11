package com.example.azkar_app;

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

public class MorningAzkarActivity extends AppCompatActivity {

    private ImageButton btnPrevious, btnHome, btnNext;
    private ListView listAzkar;

    private List<String> allAzkar; // All Azkar
    private static final int ITEMS_PER_PAGE = 6; // Azkar per page
    private int currentPage = 0; // Current page index

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning_azkar);

        // Initialize UI elements
        listAzkar = findViewById(R.id.listAzkar);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnHome = findViewById(R.id.btnHome);
        btnNext = findViewById(R.id.btnNext);

        initializeAzkar();

        loadPage();

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
                if ((currentPage + 1) * ITEMS_PER_PAGE < allAzkar.size()) {
                    currentPage++;
                    loadPage();
                }
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MorningAzkarActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeAzkar() {
        allAzkar = new ArrayList<>();
        allAzkar.add("بسم الله الذي لا يضر مع اسمه شيء في الأرض ولا في السماء وهو السميع العليم (3 مرات)");
        allAzkar.add("سبحان الله وبحمده عدد خلقه ورضى نفسه وزنة عرشه ومداد كلماته (3 مرات)");
        allAzkar.add("أصبحنا وأصبح الملك لله والحمد لله ولا إله إلا الله وحده لا شريك له...");
        allAzkar.add("أستغفر الله العظيم الذي لا إله إلا هو وأتوب إليه.");
        allAzkar.add("قراءة سورة الإخلاص والمعوذات (3 مرات).");
        allAzkar.add("أصبحنا على فطرة الإسلام وعلى كلمة الإخلاص...");
        allAzkar.add("اللهم أنت ربي لا إله إلا أنت خلقتني وأنا عبدك...");
        allAzkar.add("اللهم عافني في بدني، اللهم عافني في سمعي...");
        allAzkar.add("اللهم أنت السلام ومنك السلام تباركت يا ذا الجلال والإكرام.");
    }
    private void loadPage() {
        int start = currentPage * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, allAzkar.size());

        List<String> pageAzkar = allAzkar.subList(start, end);

        // استخدام ArrayAdapter مع تخصيص TextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pageAzkar) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);

                // الترقيم المستمر عبر الأذكار
                int overallPosition = start + position + 1;  // الإجمالي بدون إعادة الترقيم
                String numberedText = overallPosition + "/ " + pageAzkar.get(position);
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

        listAzkar.setAdapter(adapter);

        // تمكين أو تعطيل الأزرار بناءً على الصفحة الحالية
        btnPrevious.setEnabled(currentPage > 0);
        btnNext.setEnabled(end < allAzkar.size());
    }

}
