package com.rachen0101.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

/*Android Studio 4.1 須手動增加的擴充外掛：
於 Gradle Scripts > build.gradle (Module:app) 的最上方的 plugins區塊，
增加 id 'kotlin-kapt' 及 id 'kotlin-android-extensions' 並同步*/

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()  //產生SecretNumber物件

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  //App一開始所使用的 Layout

        Log.d("MainActivity", "secret: " + secretNumber.secret)  //顯示秘密數字
    }

    //需先於 activity_main.xml 中 Button 身上的 onClick屬性 設定 check方法名稱
    fun check(view : View) {  //並於括號內導入 View (android.view)物件
        //下列是因為之前增加的'kotlin-android-extensions'擴充外掛，所以可快速存取到畫面當中的 number元件
        val n = number.text.toString().toInt()  //取得輸入方塊上的文字，並轉換為Int
        println("number: $n")  //Android會將訊息顯示在下方的Logcat視窗

        //使用 Log類別的方法：d (Debug除錯)、e (Error錯誤)、i (Info資訊)、v (Verbose詳細)、w (Warn警告)
        Log.d("MainActivity", "number: " + n)

        val diff = secretNumber.validate(n)

        /*if(diff < 0) {
            //Toast浮動訊息.makeText(出現的畫面, 出現的訊息, 訊息長短).show()
            Toast.makeText(this, "Bigger", Toast.LENGTH_LONG).show()  //this代表MainActivity本身的這個物件
        } else if (diff > 0) {
            Toast.makeText(this, "Smaller", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Yes, you got it", Toast.LENGTH_LONG).show()
        }*/

        var message = "Yes, you got it"
        if(diff < 0) {
            message = "Bigger"
        } else if (diff > 0) {
            message = "Smaller"
        }
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        //新版 AlertDialog (androidx.appcompat.app) 訊息對話框
        AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
    }
}