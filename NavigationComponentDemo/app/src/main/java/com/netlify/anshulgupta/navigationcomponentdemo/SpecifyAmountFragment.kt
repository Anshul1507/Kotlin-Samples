package com.netlify.anshulgupta.navigationcomponentdemo

import android.os.Bundle
import android.text.TextUtils
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_choose_recipient.*
import kotlinx.android.synthetic.main.fragment_specify_amount.*
import java.math.BigDecimal


class SpecifyAmountFragment : Fragment() ,View.OnClickListener{

    private lateinit var navController: NavController
    private lateinit var recipient: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.send_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)
        val msg = "Sending money to $recipient"
        view.findViewById<TextView>(R.id.recipient).text = msg
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = arguments!!.getString("recipient").toString()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.send_btn -> {
                debugger("Next Button in Choose Recipient Clicked.")
                if(!TextUtils.isEmpty(input_amount.text.toString())){
                    val amount = MoneyHandler(BigDecimal(input_amount.text.toString()))
                    val bundle = bundleOf(
                        "recipient" to recipient,
                        "amount" to amount
                    )

                    navController.navigate(R.id.action_specifyAmountFragment_to_confirmationFragment,bundle)
                }

            }
            R.id.cancel_btn -> {
                debugger("Cancel Button in Choose Recipient Clicked.")
                activity!!.onBackPressed()
            }
        }
    }

}
