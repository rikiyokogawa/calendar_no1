package com.example.calendar_no1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.*
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    //試合情報配列
    var game: Array<String> = emptyArray<String>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //カレンダービューの生成
        val calendarView = CalendarView(this)
        calendarView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        //csvの読み取り
        readCsv("baseballGames.csv")

        //現在日付が取れるはず。。。だけどとれてないかな？
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = "$year/$month/$dayOfMonth"
            val textView: TextView = findViewById(R.id.textView1)
            textView.text = date


            //Toast.makeText(this, date, Toast.LENGTH_SHORT).show()
        }
        val textView: TextView = findViewById(R.id.textView2)
        textView.text = game[2]

        calendarView.findViewById<CalendarView>(R.id.calendar)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = "$year/$month/$dayOfMonth"
            val textView: TextView = findViewById(R.id.textView1)
            textView.text = date
            //Toast.makeText(this, date, Toast.LENGTH_SHORT).show()
        }
    }

    fun readCsv(filename: String) {

        val file = resources.assets.open(filename)
        val fileReader = BufferedReader(InputStreamReader(file))
        var i: Int = 0
        fileReader.forEachLine {
            if (it.isNotBlank()) {
                game = it.split(",").toTypedArray()
            }
            i++;
        }


    }
}
