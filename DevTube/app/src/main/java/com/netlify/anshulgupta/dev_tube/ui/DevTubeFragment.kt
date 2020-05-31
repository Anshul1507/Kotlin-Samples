package com.netlify.anshulgupta.dev_tube.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netlify.anshulgupta.dev_tube.R
import com.netlify.anshulgupta.dev_tube.databinding.FragmentDevTubeBinding

class DevTubeFragment : Fragment() {
    private val viewModel: DevTubeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, DevTubeViewModel.Factory(activity.application))
            .get(DevTubeViewModel::class.java)
    }

    /**
     * RecyclerView Adapter for converting a list of Video to cards.
     */
    private var viewModelAdapter: DevTubeAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.playlist.observe(viewLifecycleOwner,Observer<List<Video>>{
            it?.apply {
                viewModelAdapter?.videos = it
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentDevTubeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dev_tube,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel
        viewModelAdapter = DevTubeAdapter(VideoClick {
            // When a video is clicked this block or lambda will be called by DevByteAdapter
            // context is not around, we can safely discard this click since the Fragment is no
            // longer on the screen
            val packageManager = context?.packageManager ?: return@VideoClick

            //Try to generate a direct intent to the Youtube app
            var intent = Intent(Intent.ACTION_VIEW,it.launchUri)
            if (intent.resolveActivity(packageManager) == null){
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            }
            startActivity(intent)
        })
        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        return binding.root
    }

    /**
     * Helper method to generate YouTube app links
     */
    private val Video.launchUri: Uri
        get() {
            val httpUri = Uri.parse(url)
            return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
        }

}
