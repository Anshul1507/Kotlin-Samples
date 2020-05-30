package com.netlify.anshulgupta.navigationcomponentdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainFragment : Fragment(), View.OnClickListener{

    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Reference to nav graph a view
        navController = Navigation.findNavController(view)
        //Click Listeners
        view.findViewById<Button>(R.id.view_transactions_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.send_money_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.view_balance_btn).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.view_transactions_btn -> {
                debugger("View Transaction Button Clicked.")
                navController.navigate(R.id.action_mainFragment_to_viewTransactionFragment)
            }
            R.id.send_money_btn -> {
                debugger("Send Money Button Clicked.")
                navController.navigate(R.id.action_mainFragment_to_chooseRecipientFragment)
            }
            R.id.view_balance_btn -> {
                debugger("View Balance Button Clicked.")
                navController.navigate(R.id.action_mainFragment_to_viewBalanceFragment)
            }
        }
    }


}
