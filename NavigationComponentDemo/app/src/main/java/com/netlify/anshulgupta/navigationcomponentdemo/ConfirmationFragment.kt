package com.netlify.anshulgupta.navigationcomponentdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ConfirmationFragment : Fragment() {

    lateinit var recipient: String
    private lateinit var amountMoney : MoneyHandler

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = arguments!!.getString("recipient").toString()
        amountMoney = arguments!!.getParcelable("amount")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val amount = amountMoney.money
        val confirmationMessage = "You have sent $ $amount to $recipient"
        view.findViewById<TextView>(R.id.confirmation_message).text = confirmationMessage
    }

}
