package com.netlify.anshulgupta.gdg_finder.searchGDG

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.netlify.anshulgupta.gdg_finder.R

/**
 * A simple [Fragment] subclass.
 */
class GdgListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gdg_list, container, false)
    }

}
