package com.cs364.fishquiz.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cs364.fishquiz.R

// An array of tab titles
private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3,
    R.string.tab_text_4


)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    // Returns a fragment for the given page
    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1)
    }
    // Returns the title of the tab at the given position
    override fun getPageTitle(position: Int): CharSequence? {
        // Returns the string resource with the given ID from the app's resources
        return context.resources.getString(TAB_TITLES[position])
    }

    // Returns the total number of pages to be displayed
    override fun getCount(): Int {
        // Show 4 total pages.
        return 4
    }
}