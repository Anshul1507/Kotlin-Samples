package com.netlify.anshulgupta.navigationcomponentdemo

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_choose_recipient.*
import kotlinx.android.synthetic.main.fragment_specify_amount.*
import java.util.*

class ChooseRecipientFragment : Fragment(),View.OnClickListener {

    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.next_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navController.popBackStack(R.id.mainFragment, false)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.next_btn -> {
                debugger("Next Button in Choose Recipient Clicked.")
                if( !TextUtils.isEmpty(input_recipient.text.toString())
                    && input_recipient.text.toString().toLowerCase(Locale.ROOT)[0] >= 'a'
                    && input_recipient.text.toString().toLowerCase(Locale.ROOT)[0] <= 'z'
                ){
                    val bundle = bundleOf("recipient" to input_recipient.text.toString())
                    navController.navigate(R.id.action_chooseRecipientFragment_to_specifyAmountFragment,bundle)
                }else{
                    Toast.makeText(activity,"Enter some name first",Toast.LENGTH_SHORT).show()
                }

            }
            R.id.cancel_btn -> {
                debugger("Cancel Button in Choose Recipient Clicked.")
                activity!!.onBackPressed()
            }
        }
    }

}
