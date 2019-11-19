package com.naufalprakoso.storybook.ui.auth.register

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.data.Const
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPreferences = getSharedPreferences(Const.SHARED_KEY, Context.MODE_PRIVATE)

        btn_back.setOnClickListener(this)
        btn_register.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> finish()
            R.id.btn_register -> {
                val username = edt_username.text.toString()
                val name = edt_name.text.toString()
                val email = edt_email.text.toString()
                val password = edt_password.text.toString()
                val phone = edt_phone.text.toString()

                when {
                    username.isEmpty() -> edt_username.error =
                        getString(R.string.validation_must_be_filled)
                    username.contains(" ") -> edt_username.error = "Space not allowed"
                    username.length < 3 -> edt_username.error =
                        "Username must be more than 3 characters"
                    name.isEmpty() -> edt_name.error = getString(R.string.validation_must_be_filled)
                    name.length < 3 -> edt_name.error = "Name must be more than 3 characters"
                    email.isEmpty() -> edt_email.error =
                        getString(R.string.validation_must_be_filled)
                    password.isEmpty() -> edt_password.error =
                        getString(R.string.validation_must_be_filled)
                    password.length < 6 -> edt_password.error =
                        "Password must be more than 6 characters"
                    phone.isEmpty() -> edt_phone.error =
                        getString(R.string.validation_must_be_filled)
                    else -> {
                        val store = FirebaseFirestore.getInstance()
                        // Check username is Exists
                        store.collection("users").whereEqualTo("username", username).limit(1)
                            .get()
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    val isEmpty = it.result?.isEmpty
                                    // If username doesn't exists
                                    if (isEmpty!!) {
                                        val auth = FirebaseAuth.getInstance()
                                        // Register auth
                                        auth.createUserWithEmailAndPassword(email, password)
                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    val user = hashMapOf<String, Any>(
                                                        "id" to auth.uid.toString(),
                                                        "username" to username,
                                                        "name" to name,
                                                        "email" to email,
                                                        "phone" to phone
                                                    )

                                                    // Store data to the Firestore
                                                    store
                                                        .collection("users")
                                                        .document(auth.uid.toString())
                                                        .set(user)
                                                        .addOnCompleteListener { storeTask ->
                                                            if (storeTask.isSuccessful) {
                                                                Toast.makeText(
                                                                    this,
                                                                    "Register successfully",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                                finish()
                                                            } else {
                                                                Toast.makeText(
                                                                    this, "Register failed",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                            }
                                                        }
                                                        .addOnFailureListener { exception ->
                                                            println("LogRegister: ${exception.message}")
                                                            Toast.makeText(
                                                                this, "Register failed",
                                                                Toast.LENGTH_SHORT
                                                            ).show()
                                                        }
                                                } else {
                                                    Toast.makeText(
                                                        this, "Register failed",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                            .addOnFailureListener { failure ->
                                                println("LogRegister: ${failure.message}")
                                                Toast.makeText(
                                                    this, "Register failed",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                    } else {
                                        val msg = "Username has been taken"
                                        edt_username.error = msg
                                        Toast.makeText(
                                            this, msg,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                            .addOnFailureListener { failure ->
                                println("LogRegister: ${failure.message}")
                            }
                    }
                }
            }
        }
    }
}
