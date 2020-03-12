package com.example.calendar_no1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // CalendarViewの生成
        val calendarView = CalendarView(this)
        calendarView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        // 親レイアウトに、CalendarViewを追加
        val linearLayout = findViewById<LinearLayout>(R.id.container)
        linearLayout.addView(calendarView)

        // Button切り替え用のフラグ
        var flag = false

        // リスナーをボタンに登録
//        button.setOnClickListener {
//
//            if (flag) {
//                // flagがtrueの時
//
//                flag = false
//            } else {
//                // flagがfalseの時
//                textview.setText(getString(R.string.world))
//                flag = true
//            }
//        }
    }
}
