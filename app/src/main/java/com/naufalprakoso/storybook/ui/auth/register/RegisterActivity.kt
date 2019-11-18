package com.naufalprakoso.storybook.ui.auth.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.naufalprakoso.storybook.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_back.setOnClickListener(this)
        btn_register.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> finish()
            R.id.btn_register -> {
                val name = edt_name.text.toString()
                val email = edt_email.text.toString()
                val password = edt_password.text.toString()
                val phone = edt_phone.text.toString()

                when {
                    name.isEmpty() -> edt_name.error = getString(R.string.validation_must_be_filled)
                    name.length < 3 -> edt_name.error = "Name must be more than 3 characters"
                    email.isEmpty() -> edt_email.error = getString(R.string.validation_must_be_filled)
                    password.isEmpty() -> edt_password.error = getString(R.string.validation_must_be_filled)
                    password.length < 6 -> edt_password.error = "Password must be more than 6 characters"
                    phone.isEmpty() -> edt_phone.error = getString(R.string.validation_must_be_filled)
                    else -> {
                        val auth = FirebaseAuth.getInstance()
                    }
                }
            }
        }
    }
}
