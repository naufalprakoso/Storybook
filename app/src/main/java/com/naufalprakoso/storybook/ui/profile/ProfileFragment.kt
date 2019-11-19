package com.naufalprakoso.storybook.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.model.User
import com.naufalprakoso.storybook.ui.auth.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    companion object {
        fun newInstance(): Fragment {
            return ProfileFragment();
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()

        FirebaseFirestore.getInstance().collection("users")
            .whereEqualTo("id", auth.uid.toString()).limit(1).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = it.result?.documents?.get(0)?.toObject(User::class.java)
                    view.txt_name.text = user?.name
                    view.txt_username.text = user?.username
                }
            }

        view.tab_layout.setupWithViewPager(view.view_pager)
        view.tab_layout.tabGravity = TabLayout.GRAVITY_FILL

        view.tab_layout.addTab(view.tab_layout.newTab().setText(getString(R.string.title_stories)))
        view.tab_layout.addTab(view.tab_layout.newTab().setText(getString(R.string.title_circle)))

        activity?.supportFragmentManager?.let {
            TabsProfileManager(it, 0)
        }

        val tabsAdapter = activity?.supportFragmentManager?.let {
            TabsProfileManager(it, view.tab_layout.tabCount)
        }
        view.view_pager.adapter = tabsAdapter
        view.view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(view.tab_layout))

        view.tab_layout.setTabTextColors(
            ContextCompat.getColor(view.context, R.color.colorPrimary),
            ContextCompat.getColor(view.context, R.color.colorPrimaryDark)
        )

        view.tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                view.view_pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        view.btn_logout.setOnClickListener(this)
        view.btn_edit_profile.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_logout -> {
                MaterialAlertDialogBuilder(context)
                    .setTitle("Wanna logout?")
                    .setMessage("Are you sure to logout?")
                    .setPositiveButton("Sure") { _, _ ->
                        auth.signOut()

                        Toast.makeText(
                            context, "Good bye~",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(context, LoginActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
            R.id.btn_edit_profile -> {

            }
        }
    }

}
