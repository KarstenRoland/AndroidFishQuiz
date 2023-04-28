package com.cs364.fishquiz.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cs364.fishquiz.R
import com.cs364.fishquiz.databinding.FragmentMainBinding
import com.cs364.fishquiz.ui.ViewModelFactory

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    /**
     * used to declare binding with fragment xml
     */
    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    /**
     * Adds images to the background and cycles between them depending on which tab is currently active
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

        val textView: TextView = binding.sectionLabel
        val imageView: ImageView = binding.backgroundImage
        pageViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        when(arguments?.getInt(ARG_SECTION_NUMBER)) {
            1 -> imageView.setImageResource(R.drawable.image1)
            2 -> imageView.setImageResource(R.drawable.image2)
            3 -> imageView.setImageResource(R.drawable.image3)
            else -> imageView.setImageResource(R.drawable.default_image)
        }
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }


    /**
     * Destroys view once done using
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}