package com.cs364.fishquiz.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cs364.fishquiz.R
import com.cs364.fishquiz.databinding.FragmentMainBinding
import org.w3c.dom.Text


class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

        val imageView: ImageView = binding.backgroundImage

        // Set the background image to be used for all tabs
        imageView.setImageResource(R.drawable.default_image)

        // Check if this is the default tab (section number is null)
        if (arguments?.getInt(ARG_SECTION_NUMBER) == null) {
            root.addView(createComposeView())
        } else {
            val fishInfoText: Text = binding.fishInfoText

            // Set the text to display on the textView based on the tab number
            when(arguments?.getInt(ARG_SECTION_NUMBER)) {
                1 -> {
                    fishInfoText.text = "Fish Info for Tab 1"
                }
                2 -> {
                    fishInfoText.text = "Fish Info for Tab 2"
                    fishInfoText.setTextColor(resources.getColor(R.color.black))
                }
                3 -> {
                    fishInfoText.text = "Fish Info for Tab 3"
                }
                else -> {
                    fishInfoText.text = ""
                }
            }
        }

        return root
    }

    @Composable
    private fun createComposeView() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Hello World!", style = MaterialTheme.typography.h1)
        }
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
