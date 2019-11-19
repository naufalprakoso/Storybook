package com.naufalprakoso.storybook.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.ui.MainActivity
import com.naufalprakoso.storybook.ui.auth.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        txt_create.setOnClickListener(this)
        btn_login.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            moveToMain()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txt_create -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_login -> {
                val email = edt_email.text.toString()
                val password = edt_password.text.toString()
                when {
                    email.isEmpty() -> edt_email.error =
                        getString(R.string.validation_must_be_filled)
                    password.isEmpty() -> edt_password.error =
                        getString(R.string.validation_must_be_filled)
                    else -> {
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(
                                        this, "Login successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    moveToMain()
                                } else {
                                    Toast.makeText(
                                        this, "Invalid email or password",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    this, "Login failed",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                }
            }
        }
    }

    private fun moveToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
