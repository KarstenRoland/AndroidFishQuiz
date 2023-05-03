package com.cs364.fishquiz.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.cs364.fishquiz.R
import com.cs364.fishquiz.databinding.FragmentMainBinding

/**
 * PlaceholderFragment is a Fragment that displays different content based on the section number
 * passed to it as an argument.
 */
class PlaceholderFragment : Fragment() {

    /**
     * Reference to the ViewModel for this Fragment
     */
    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    /**
     * Inflates the Fragment's layout and returns the root View.
     * Uses a ComposeView to display content.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the Fragment
     * @param container If non-null, this is the parent view that the Fragment's UI should be attached to
     * @param savedInstanceState If non-null, this Fragment is being re-constructed from a previous saved state as given here
     * @return The View for the Fragment's UI, or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                // call your Composable function here
                onCreateViewWithLayoutInflator()
            }
        }
    }

    /**
     * Composable function that inflates the Fragment's layout and sets up the UI based on the section number.
     * Uses Jetpack Compose for UI rendering.
     */
    @Composable
    fun onCreateViewWithLayoutInflator() {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        val root = binding.root

        var myContext = LocalContext.current
        var vmData: FishDBViewModel by remember { mutableStateOf(FishDBViewModel(myContext)) }

        val imageView: ImageView = binding.backgroundImage

        // Set the background image to be used for all tabs
        imageView.setImageResource(R.drawable.default_image)

        // Check if this is the default tab (section number is null)
        if (arguments?.getInt(ARG_SECTION_NUMBER) == null) {
            createComposeView(root)
        } else {
            val fishInfoText: TextView = binding.fishInfoText

            // Set the text to display on the textView based on the tab number
            when(arguments?.getInt(ARG_SECTION_NUMBER)) {
                1 -> {
                    CatalogScreen(vmData = vmData, R.string.tab_text_1, R.drawable.image1)
                }
                2 -> {
                    CatalogScreen(vmData = vmData, R.string.tab_text_2, R.drawable.image2)
                }
                3 -> {
                    CatalogScreen(vmData = vmData, R.string.tab_text_3, R.drawable.image3)
                }
                else -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        // ComposeView that displays default image
                        Image(
                            painter = painterResource(id = R.drawable.default_image),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .height(160.dp),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun createComposeView(root: View){
        // Create a Box composable that fills the entire size of its parent view and centers its child elements
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Create a ComposeView and set its content
            ComposeView(root.context).apply {
                setContent {
                }
            }
        }
    }


    companion object {
        // The argument key used to store the section number in the arguments bundle
        private const val ARG_SECTION_NUMBER = "section_number"
        /**
         * Create a new instance of PlaceholderFragment with the given section number as an argument
         * @param sectionNumber the section number to be associated with this instance
         * @return a new instance of PlaceholderFragment
         */
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    /**
     * Called when the view associated with this fragment is destroyed, sets the binding to null
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
