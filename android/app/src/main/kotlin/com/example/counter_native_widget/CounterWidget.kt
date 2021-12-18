package com.example.counter_native_widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class CounterWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        val prefsSetting = context.getSharedPreferences("flutter_counter", Context.MODE_PRIVATE)

        val text = prefsSetting.getLong("count", 0).toString()
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {

            updateAppWidget(context, appWidgetManager, appWidgetId, text)

        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    text: String
) {
    val intent = Intent(context, CounterWidget::class.java)
    val pendingIntent = PendingIntent.getService(context, 0, intent, 0)

    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.counter_widget).also {
        it.setTextViewText(R.id.appwidget_text, text)
        it.setOnClickPendingIntent(R.id.addButton,pendingIntent)
        it.setOnClickPendingIntent(R.id.subtractButton,pendingIntent)
    }
    val addButton: Button = Button(context).findViewById(R.id.addButton)
    addButton.setOnClickListener{
    CounterApiImpl(context).counter++
}
    val subtractButton: Button = Button(context).findViewById(R.id.addButton)
    subtractButton.setOnClickListener{
        CounterApiImpl(context).counter--
    }
    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
