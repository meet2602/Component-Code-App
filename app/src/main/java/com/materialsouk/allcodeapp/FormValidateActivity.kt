package com.materialsouk.allcodeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.util.Patterns
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import android.text.Editable

import android.text.TextWatcher



class FormValidateActivity : AppCompatActivity() {
    private var isValidPassword = false
    private var isValidConPassword = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_validate)
        val edName = findViewById<EditText>(R.id.edName)
        val edEmail = findViewById<EditText>(R.id.edEmail)
        val edPassword = findViewById<EditText>(R.id.edPassword)
        val edConPassword = findViewById<EditText>(R.id.edConPassword)
        val txtPasswordL = findViewById<TextInputLayout>(R.id.txtPasswordL)
        val txtConPasswordL = findViewById<TextInputLayout>(R.id.txtConPasswordL)

        edPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditTextPassword(edPassword, txtPasswordL, "password")
            }
        })
        edConPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditTextPassword(edConPassword, txtConPasswordL, "conPassword")
            }
        })

        findViewById<Button>(R.id.validateBtn).setOnClickListener {
            if (edName.text.trim().isEmpty()){
                edName.error = "Required"
            }else if (edEmail.text.trim().isEmpty()){
                edEmail.error = "Required"
            }else if (!validEmail(edEmail.text.trim().toString())){
                edEmail.error = "valid e-mail"
            } else {
                validateEditTextPassword(edPassword, txtPasswordL, "password")
                if (isValidPassword) {
                    validateEditTextPassword(edConPassword, txtConPasswordL, "conPassword")
                    if (isValidConPassword) {
                        if (edPassword.text.toString().trim() != edConPassword.text.toString().trim()) {
                            txtConPasswordL.error = "Password don't match!"
                        } else {
                            txtConPasswordL.error = null
                            Toast.makeText(this,"Success",Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
    private fun validEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun validateEditTextPassword(
        editText: EditText,
        textInputLayout: TextInputLayout,
        state: String
    ) {
        var truthState = false
        when {
            editText.text.toString().trim().isEmpty() -> {
                textInputLayout.error = "Required"
            }
            editText.text.toString().trim().length < 8 || editText.text.toString().trim().length > 10 -> {
                textInputLayout.error = "Password must be 8 to 10 character!"
            }
            else -> {
                textInputLayout.error = null
                truthState = true
            }
        }
        if (truthState) {
            if (state == "password") {
                isValidPassword = true
            } else if (state == "conPassword") {
                isValidConPassword = true
            }
        } else {
            if (state == "password") {
                isValidPassword = false
            } else if (state == "conPassword") {
                isValidConPassword = false
            }
        }
    }
}