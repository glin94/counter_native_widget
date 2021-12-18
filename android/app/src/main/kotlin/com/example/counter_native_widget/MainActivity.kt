package com.example.counter_native_widget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.SharedPreferences
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.Counter
import io.flutter.plugins.Counter.*


import android.widget.RemoteViews


class MainActivity : FlutterActivity() {

    val preferences: SharedPreferences? = null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        CounterApi.setup(flutterEngine.dartExecutor, CounterApiImpl(context))
    }

}

public class CounterApiImpl(var context: Context) : Counter.CounterApi {
    private val prefsSetting =
        context.getSharedPreferences("flutter_counter", Context.MODE_PRIVATE)

    override fun setCounter(counterValue: Long?) {
        prefsSetting.edit().putLong("count", counterValue!!).apply();

        val remoteViews = RemoteViews(context.packageName, R.layout.counter_widget).also {
            it.setTextViewText(R.id.appwidget_text, counterValue.toString())
        }
        val thisWidget = ComponentName(context, CounterWidget::class.java)
        AppWidgetManager.getInstance(context).updateAppWidget(thisWidget, remoteViews)
    }

    override fun getCounter(): Long {
        return prefsSetting.getLong("count", 0);
    }
}
