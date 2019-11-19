package com.naufalprakoso.storybook.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.naufalprakoso.storybook.ui.profile.circle.CircleFragment
import com.naufalprakoso.storybook.ui.profile.post.PostFragment

class TabsProfileManager(
    fm: FragmentManager,
    private var mNumOfTabs: Int
) : FragmentStatePagerAdapter(fm) {

    private val tabTitles = arrayOf("Posts", "Circle")

    override fun getCount(): Int {
        return mNumOfTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PostFragment.newInstance()
            else -> CircleFragment.newInstance()
        }
    }
}