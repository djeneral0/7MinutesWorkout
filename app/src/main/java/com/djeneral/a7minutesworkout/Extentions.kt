package com.djeneral.a7minutesworkout

import android.content.Context
import android.widget.Toast

fun Context.showToat(mess: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, mess, duration).show()
}