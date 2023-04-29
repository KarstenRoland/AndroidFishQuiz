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
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.cs364.fishquiz.R
import com.cs364.fishquiz.databinding.FragmentMainBinding

class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
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
                1 -> { CatalogScreen(vmData) }
                2 -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.image2),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .height(160.dp),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
                3 -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.image3),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .height(160.dp),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
                else -> {
                    Column(modifier = Modifier.fillMaxSize()) {
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
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ComposeView(root.context).apply {
                setContent {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Hello World!", style = MaterialTheme.typography.h1)
                    }
                }
            }
        }
    }


    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

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
