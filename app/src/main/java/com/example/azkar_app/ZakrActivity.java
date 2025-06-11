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

public class ZakrActivity extends AppCompatActivity {

    private ImageButton btnPrevious, btnHome, btnNext;
    private ListView listAzkar;
    private static final int ITEMS_PER_PAGE = 6; // Azkar per page
    private List<String> allAzkar; // All Evening Azkar List of virtues of Azkar
    private int currentIndex = 0; // Current displayed text index

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakr);

        // Initialize UI elements
        listAzkar = findViewById(R.id.listAzkar);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnHome = findViewById(R.id.btnHome);
        btnNext = findViewById(R.id.btnNext);

        // Initialize Zakr content
        initializeZakrTexts();

        // Load first text
        loadPage();

        // Button Click Listeners
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) {
                    currentIndex--;
                    loadPage();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentIndex + 1) * ITEMS_PER_PAGE < allAzkar.size()) {
                    currentIndex++;
                    loadPage();
                }
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZakrActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeZakrTexts() {
        allAzkar = new ArrayList<>();
        allAzkar.add("إن فضل قراءة أذكار الصباحوالمساء والمحافظة عليها هي إنشراح للصدر واستحضار حفظ الله ومعيته لنا ومن ذكر الله في حياته ذكره الله فى الملأ، فقد جاء في الحديث الشريف عن الرسول صلى الله عليه وسلم يقول الله عز وجل أنا عند ظن عبدي بي، وأنا معه حين يذكرني إن ذكرني في نفسه ذكرته في نفسي، وأن ذكرني في ملأ ذكرته في ملأ هم خير منهم \" هذا كله مدعاة للحفاظ على الأذكار اليومية وخاصة أذكار الصباحوالمساء.");
        allAzkar.add("من يرغب في الحفاظ على ترديد الأذكار اليومية وخاصة أذكار الصباح والمساء فإن لهما أوقات خاصة بهما ويجب استحضار القلب والعقل والشروع في هذه الأذكار لكي بقى بحفظ الله ورعايته، وينال بإذن الله كل الخير والفضل الذي يترتب على هذه الأذكار، فقد قال الله تعالى . أذكروني أذكركم \" فذكر الله عبادة وطاعة ويترتب عليها ذكر الله لنا وحفظنا ورعايتنا، ومما جاء في فضل قراءة اذكار الصباح والمساء ما يلي: کسب رضا الله عز وجل.");
        allAzkar.add("- حفظ المكان الذي ببيت فيه الإنسان. دوام الصلة بالله - عز وجل - والقرب منه والأنس به");
        allAzkar.add("ذكر الله عز وجل للعبد في الملأ الأعلى.");
        allAzkar.add("الحفظ من الحسد والعين.");
        allAzkar.add("إمداد الجسم بالقوة والطاقة.");
        allAzkar.add("مغفرة الذنوب والسيئات.");
        allAzkar.add("- زيادة الحسنات والثواب");
        allAzkar.add("ذكر الله يُرفع للعبد في الملأ الأعلى.");

    }

    private void loadPage() {
        int start = currentIndex * ITEMS_PER_PAGE;
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


        // Enable/Disable buttons based on the page
        btnPrevious.setEnabled(currentIndex > 0);
        btnNext.setEnabled(end < allAzkar.size());
    }

}
