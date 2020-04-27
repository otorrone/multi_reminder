package com.example.multi_reminder

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
//import kotlinx.android.synthetic.main.activity_cleaning.*
//import kotlinx.android.synthetic.main.activity_drink_daily.*
//import kotlinx.android.synthetic.main.activity_drink_daily.et_message
//import kotlinx.android.synthetic.main.activity_drink_daily.floatingSettings
//import kotlinx.android.synthetic.main.activity_drink_daily.timePicker
//import kotlinx.android.synthetic.main.activity_drink_daily.time_create
import java.util.*

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_eat_daily.*
import kotlinx.android.synthetic.main.activity_eat_daily.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast

class Eat_daily : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eat_daily)








///Aleksin alapuolella



        floatingSettings.setOnClickListener{
            val layoutInflater : LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val view: View = layoutInflater.inflate(R.layout.popup_settings, null)

            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val focusable = true

            val popupWindow = PopupWindow(view,
                width,
                height,
                focusable
            )
            val buttonDR = view.findViewById<Button>(R.id.buttonDelete)

            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

            buttonDR.setOnClickListener{
                popupWindow.dismiss()
                val layoutInflater3 : LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view3: View = layoutInflater3.inflate(R.layout.popup_deletereminders, null)
                val width = LinearLayout.LayoutParams.WRAP_CONTENT
                val height = LinearLayout.LayoutParams.WRAP_CONTENT
                val focusable = true
                val popupWindow3 = PopupWindow(view3,
                    width,
                    height,
                    focusable)
                popupWindow3.showAtLocation(view, Gravity.CENTER, 0, 0)

                val buttonYes = view3.findViewById<Button>(R.id.buttonChooseYes)
                val buttonNo = view3.findViewById<Button>(R.id.buttonChooseNo)
                buttonNo.setOnClickListener{popupWindow3.dismiss()}
            }



        }
    }

    private fun setAlarm(time: Long, message: String) {

        val intent = Intent(this, ReminderReceiver::class.java)
        intent.putExtra("message", message)

        val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_ONE_SHOT)

        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        manager.setExact(AlarmManager.RTC, time, pendingIntent)

        runOnUiThread{toast("Reminder is created")}


    }


}
