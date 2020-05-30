package com.netlify.anshulgupta.navigationcomponentdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation


class SpecifyAmountFragment : Fragment() ,View.OnClickListener{

    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.send_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.send_btn -> {
                debugger("Next Button in Choose Recipient Clicked.")
                navController.navigate(R.id.action_specifyAmountFragment_to_confirmationFragment)
            }
            R.id.cancel_btn -> {
                debugger("Cancel Button in Choose Recipient Clicked.")
                activity!!.onBackPressed()
            }
        }
    }

}
