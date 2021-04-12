package kotlinclass.leminh.kotlin_week2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(var fullname: String = "", var email: String = "", var password: String= "",var phonenumber: String = "") : Parcelable