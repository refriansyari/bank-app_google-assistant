package com.refridev.bankapp.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import com.refridev.bankapp.R
import com.google.assistant.appactions.widgets.AppActionsWidgetExtension
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BalanceWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            setTts("Berikut informasi saldo anda pada tanggal 19 January", appWidgetManager, appWidgetId)
            // Update the widget with the remote views defined in the XML layout
            appWidgetManager.updateAppWidget(appWidgetId, createRemoteViews(context))
        }
    }

    private fun createRemoteViews(context: Context): RemoteViews {
        // Create RemoteViews for the widget using the XML layout
        return RemoteViews(context.packageName, R.layout.balance_widget)
    }

    private fun setTts(
        text: String,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val appActionsWidgetExtension: AppActionsWidgetExtension =
            AppActionsWidgetExtension.newBuilder(appWidgetManager)
                .setResponseSpeech(text)  // TTS to be played back to the user
                .setResponseText(text)  // Response text to be displayed in Assistant
                .build()

        // Update widget with TTS
        appActionsWidgetExtension.updateWidget(appWidgetId)
    }
}