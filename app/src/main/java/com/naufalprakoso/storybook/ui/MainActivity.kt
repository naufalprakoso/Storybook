package com.naufalprakoso.storybook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.data.Mock
import com.naufalprakoso.storybook.ui.explore.ExploreFragment
import com.naufalprakoso.storybook.ui.profile.ProfileFragment
import com.naufalprakoso.storybook.ui.story.AddStoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentFragment = ExploreFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Mock.insertToMyStory1()
//        Mock.insertToMyStory2()
//        Mock.insertToMyStory3()

        meow_bottom_nav.add(
            MeowBottomNavigation.Model(
                1,
                R.drawable.ic_explore_black
            )
        )
        meow_bottom_nav.add(
            MeowBottomNavigation.Model(
                2,
                R.drawable.ic_book_black
            )
        )
        meow_bottom_nav.add(
            MeowBottomNavigation.Model(
                3,
                R.drawable.ic_person_black
            )
        )

        meow_bottom_nav.show(1, false)
        openFragment(currentFragment)

        meow_bottom_nav.setOnClickMenuListener {
            when (it.id) {
                1 -> {
                    currentFragment = ExploreFragment.newInstance()
                    openFragment(currentFragment)
                }
                2 -> {
                    currentFragment = AddStoryFragment.newInstance()
                    openFragment(currentFragment)
                }
                3 -> {
                    currentFragment = ProfileFragment.newInstance()
                    openFragment(currentFragment)
                }
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_container, fragment)
            commit()
        }
    }
}
