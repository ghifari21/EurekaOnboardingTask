package com.gosty.loginandshowdata

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudentModel(
    val name: String,
    val email: String,
    val jurusan: String,
    val semester: Int
): Parcelable
