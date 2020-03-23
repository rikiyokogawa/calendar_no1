package com.example.calendar_no1

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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

        // ボタンを設定
        val button: View = findViewById<Button>(R.id.fab)
        button.setOnClickListener {
            val editText = EditText(this)
            editText.hint = "ここに入力"
            //ダイアログだす
            AlertDialog.Builder(this)
                .setTitle("タイトル")
                .setView(editText)
                .setPositiveButton("登録",
                    DialogInterface.OnClickListener { dialog, which ->
                        Log.d("AlertDialog", "test")
                    })
                .show()
        }



        //csvの読み取り
        readCsv("baseballGames.csv")

        //現在日付が取れるはず。。。だけどとれてないかな？
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = "$year/$month/$dayOfMonth"
            val textView: TextView = findViewById(R.id.textView1)
            textView.text = date
            //Toast.makeText(this, date, Toast.LENGTH_SHORT).show()

            val textView1: TextView = findViewById(R.id.textView2)
            textView.text = game[2]

            calendarView.findViewById<CalendarView>(R.id.calendar)
            calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                val date = "$year/$month/$dayOfMonth"
                val textView: TextView = findViewById(R.id.textView1)
                textView.text = date
                //Toast.makeText(this, date, Toast.LENGTH_SHORT).show()
            }
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

    //TODO: カスタムダイアログを作ろうとしている
//    override fun onCreateDialog(id: Int): Dialog? {
//        val dialog: Dialog
//        dialog = Dialog(this)
//        dialog.setContentView(R.layout.dialog)
//        dialog.setPositiveButton("もう一度",
//            DialogInterface.OnClickListener { dialogInterface, i -> recreate() })
//    }
}
