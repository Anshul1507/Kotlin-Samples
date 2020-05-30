package com.netlify.anshulgupta.navigationcomponentdemo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

//Data Class for holding a Big Decimal Value as a Money
@Parcelize
data class MoneyHandler(val money:BigDecimal): Parcelable