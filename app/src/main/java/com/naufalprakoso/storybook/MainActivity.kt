package com.naufalprakoso.storybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        meow_bottom_nav.add(MeowBottomNavigation.Model(1, R.drawable.ic_explore_black))
        meow_bottom_nav.add(MeowBottomNavigation.Model(2, R.drawable.ic_book_black))
        meow_bottom_nav.add(MeowBottomNavigation.Model(3, R.drawable.ic_person_black))

        meow_bottom_nav.show(1, false)
    }
}
