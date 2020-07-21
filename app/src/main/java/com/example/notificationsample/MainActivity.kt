package com.example.notificationsample


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // 通知オブジェクトの用意と初期化
    var notification: Notification? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // システムから通知マネージャー取得
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // アプリ名をチャンネルIDとして利用
        val chID = getString(R.string.app_name)

        // アンドロイドのバージョンで振り分け
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // PIが「26」以上の場合

            // 通知チャンネルIDを生成してインスタンス化
            val notificationChannel = NotificationChannel(chID, chID, NotificationManager.IMPORTANCE_DEFAULT)
            // 通知の説明のセット
            notificationChannel.description = chID
            // 通知チャンネルの作成
            notificationManager.createNotificationChannel(notificationChannel)
            // 通知の生成と設定とビルド
            notification = Notification.Builder(this, chID)
                    .setContentTitle(getString(R.string.app_name)) // 通知タイトル
                    .setContentText("アプリ通知テスト Android API26以降") // 通知内容
                    .setSmallIcon(R.drawable.ic_launcher_foreground) // 通知用アイコン
                    .build() // 通知のビルド
        } else {
            // APIが「25」以下の場合
            // 通知の生成と設定とビルド
            notification = Notification.Builder(this)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText("アプリ通知テスト Android API25以前")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .build()
        }

        var notificationId = 0
        // ボタン押下で通知を出す
        button.setOnClickListener {
            // 通知の発行
            notificationManager.notify(notificationId, notification)
            notificationId++
        }
    }
}

// 出典：https://android-java.hatenablog.jp/entry/2019/02/19/214301