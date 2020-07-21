package com.example.notificationsample


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat


//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.notification_icon)
//                .setContentTitle("My notification")
//                .setContentText("Much longer text that cannot fit one line...")
//                .setStyle(NotificationCompat.BigTextStyle()
//                        .bigText("Much longer text that cannot fit one line..."))
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//    }
//
//    private fun createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = getString(R.string.channel_name)
//            val descriptionText = getString(R.string.channel_description)
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//            // Register the channel with the system
//            val notificationManager: NotificationManager =
//                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//}

class MainActivity : AppCompatActivity() {
    //通知オブジェクトの用意と初期化
    var notification: Notification? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //レイアウトファイルをコンテントビューとしてセット
        setContentView(R.layout.activity_main)
        //システムから通知マネージャー取得
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //アプリ名をチャンネルIDとして利用
        val chID = getString(R.string.app_name)

        //アンドロイドのバージョンで振り分け
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {     //APIが「26」以上の場合

            //通知チャンネルIDを生成してインスタンス化
            val notificationChannel = NotificationChannel(chID, chID, NotificationManager.IMPORTANCE_DEFAULT)
            //通知の説明のセット
            notificationChannel.description = chID
            //通知チャンネルの作成
            notificationManager.createNotificationChannel(notificationChannel)
            //通知の生成と設定とビルド
            notification = Notification.Builder(this, chID)
                    .setContentTitle(getString(R.string.app_name)) //通知タイトル
                    .setContentText("アプリ通知テスト26以上") //通知内容
                    .setSmallIcon(R.drawable.ic_launcher_foreground) //通知用アイコン
                    .build() //通知のビルド
        } else {
            //APIが「25」以下の場合
            //通知の生成と設定とビルド
            notification = Notification.Builder(this)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText("アプリ通知テスト25まで")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .build()
        }
        //通知の発行
        notificationManager.notify(1, notification)
    }
}