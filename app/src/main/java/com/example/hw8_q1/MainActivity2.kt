package com.example.hw8_q1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hw8_q1.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    lateinit var binding1:ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        binding1= ActivityMain2Binding.inflate(layoutInflater)
        val view= binding1.root
        setContentView(view)

        showInformation()

        binding1.buttonChange.setOnClickListener {
            //changeInformation()
            var result=Intent()
            setResult(RESULT_OK,result)
            finish()
        }
    }

    private fun changeInformation() {
        val sharedPreferences = getSharedPreferences("myInfo1", Context.MODE_PRIVATE)
        val username=binding1.editTextPersonName.text
        val codeMelli=binding1.codeMelli.text
        val city=binding1.address.text
        val codePosti=binding1.codePostal.text
        val address=binding1.address.text
        val share: SharedPreferences.Editor=sharedPreferences.edit()
        share.clear()
        share.putString("username", username.toString())
        share.putString("codeMelli", codeMelli.toString())
        share.putString("city",city.toString())
        share.putString("codePosti",codePosti.toString())
        share.putString("address",address.toString())
        share.apply()
        share.commit()
        Toast.makeText(this,"Your information was changed!", Toast.LENGTH_LONG).show()
       showInformation()
    }

    private fun showInformation() {
        binding1.editTextPersonName.setText("")
        binding1.codeMelli.setText("")
        binding1.city.setText("")
        binding1.address.setText("")
        binding1.codePostal.setText("")
        binding1.gender.setText("")
        val sharedPreferences = getSharedPreferences("myInfo1", MODE_PRIVATE)
        val usernameValue = sharedPreferences.getString("username", "")
        val codeMelliValue=sharedPreferences.getString("codeMelli", "")
        val city=sharedPreferences.getString("city", "")
        val codePosti=sharedPreferences.getString("codePosti", "")
        val address=sharedPreferences.getString("address", "")
        val gender=sharedPreferences.getString("gender","")
        binding1.editTextPersonName.setText("نام و نام خانوادگی:  "+usernameValue)
        binding1.codeMelli.setText("کد ملی:  "+codeMelliValue)
        binding1.city.setText("  شهر:  "+city)
        binding1.address.setText("آدرس:  "+address)
        binding1.codePostal.setText("کد پستی:  "+codePosti)
        binding1.gender.setText("جنسیت:  "+gender)

    }
}