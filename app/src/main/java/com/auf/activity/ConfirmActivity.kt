package com.auf.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.auf.activity.databinding.ActivityConfirmBinding
import com.auf.activity.databinding.ActivityRegisterBinding

class ConfirmActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityConfirmBinding
    private lateinit var sharedPreferences: SharedPreferences

    private var genderIndex:Int = -1
    private var age: String = ""
    private var FirstName: String = ""
    private var LastName: String = ""
    private var UserName: String = ""
    private var password: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnconf.setOnClickListener(this)
        binding.redo.setOnClickListener(this)

        sharedPreferences = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE)

        if (intent != null) {
            UserName = intent.getStringExtra("Username")!!
            FirstName = intent.getStringExtra("FirstName")!!
            LastName = intent.getStringExtra("LastName")!!
            genderIndex = intent.getIntExtra("Gender",2)!!
            age = intent.getStringExtra("age")!!
            password = intent.getStringExtra("Password")!!
        }

        when(genderIndex){
            (0) -> {
                binding.gender.text = "Male"
            }
            (1) -> {
                binding.gender.text = "Female"
            }
            (2) -> {
                binding.gender.text = "Prefer not to say"
            }
        }

        binding.usernames.text = UserName
        binding.lnames.text = String.format("%s %s",FirstName,LastName)
        binding.ages.text = age
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btnconf) -> {
                val editor = sharedPreferences.edit()
                editor.putString(USER_NAME,UserName)
                editor.putString(USER_PASSWORD,password)
                editor.putString(USER_FIRST_NAME,FirstName)
                editor.putString(USER_LAST_NAME,LastName)
                editor.putString(USER_AGE,age)
                editor.putInt(USER_GENDER,genderIndex)
                editor.apply()



                val  intent = Intent(this,MainScreen::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            (R.id.redo)-> {
                finish()
            }
        }
    }
}