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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPush.setOnClickListener {
            pushNotification()
        }

        buttonRemove.setOnClickListener {
            removeNotification()
        }

        buttonRemoveAll.setOnClickListener {
            removeNotificationAll()
        }
    }

    // 通知送信用
    fun pushNotification() {
        val notification: Notification?
        // システムから通知マネージャー取得
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // アプリ名をチャンネルIDとして利用
        val chID = getString(R.string.app_name)

        // アンドロイドのバージョンで振り分け
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // APIが「26」以上の場合
            // 通知チャンネルIDを生成してインスタンス化
            // IMPORTANCEはポップアップ（正式名ヘッドアップ通知）するならHIGH以上にする
            val notificationChannel = NotificationChannel(chID, chID, NotificationManager.IMPORTANCE_HIGH)
            // 通知の説明のセット
            notificationChannel.description = chID
            // 通知チャンネルの作成
            notificationManager.createNotificationChannel(notificationChannel)
            // 通知の生成と設定とビルド
            notification = Notification.Builder(this, chID)
                .setAutoCancel(true)
                .setContentTitle(getString(R.string.app_name))     // 通知タイトル
                .setContentText("アプリ通知テスト Android API26以降") // 通知内容
                .setSmallIcon(android.R.drawable.ic_dialog_info)   // 通知用アイコン
                .build() // 通知のビルド
        } else { // APIが「25」以下の場合
            // 通知の生成と設定とビルド
            notification = Notification.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("アプリ通知テスト Android API25以前")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .build()
        }
        // 通知を送信
        notificationManager.notify(0, notification)
    }

    // 通知削除用
    fun removeNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(0) // 引数のidはnotificationManager.notifyで送ったidと揃える
    }

    // 通知全削除
    fun removeNotificationAll() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
    }
}

// 出典：https://android-java.hatenablog.jp/entry/2019/02/19/214301