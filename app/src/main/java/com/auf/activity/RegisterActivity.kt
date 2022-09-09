package com.auf.activity

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.auf.activity.databinding.ActivityConfirmBinding
import com.auf.activity.databinding.ActivityRegisterBinding
import org.w3c.dom.Text

class RegisterActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private lateinit var binding: ActivityRegisterBinding
    private var genderIndex:Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rdgrp.setOnCheckedChangeListener(this)
        binding.reg.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        if(binding.username.text.isEmpty()){
            binding.username.error = "Required"
            return
        }
        if(binding.lastname.text.isEmpty()){
            binding.lastname.error = "Required"
            return
        }
        if(binding.password.text.isEmpty()){
            binding.password.error = "Required"
            return
        }
        if(binding.firstname.text.isEmpty()){
            binding.firstname.error = "Required"
            return
        }
        if(binding.age.text.isEmpty()){
            binding.age.error = "Required"
            return
        }
        if(binding.password.text.toString() != binding.password2.text.toString()) {
            binding.password.error = "Password does not match"
            binding.password2.error = "Password does not match"
            return
        }
        if (genderIndex == -1){
            Toast.makeText(this,"Specify your sex",Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, ConfirmActivity::class.java)
        intent.putExtra("Username",binding.username.text.toString())
        intent.putExtra("Password",binding.password.text.toString())
        intent.putExtra("FirstName",binding.firstname.text.toString())
        intent.putExtra("LastName",binding.lastname.text.toString())
        intent.putExtra("age",binding.age.text.toString())
        intent.putExtra("Gender", genderIndex)

        startActivity(intent)

    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        val rb = binding.rdgrp.findViewById<View>(p1)
        genderIndex = binding.rdgrp.indexOfChild(rb)
    }
}

