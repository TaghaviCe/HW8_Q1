package com.example.hw8_q1

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hw8_q1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //var sharedFile="myInformation"
    lateinit var binding: ActivityMainBinding
    lateinit var sharePreference: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharePreference=getSharedPreferences("myInfo1", Context.MODE_PRIVATE)
       val usernameValue = sharePreference.getString("username", null)
       val codeMelliValue=sharePreference.getString("codeMelli", null)
       val city=sharePreference.getString("city", null)
       val codePosti=sharePreference.getString("codePosti", null)
       val address=sharePreference.getString("address", null)
       val gender=sharePreference.getString("gender",null)
       if (usernameValue!=null&&
               codeMelliValue!=null&&
               city!=null&&
               codePosti!=null&&
               address!=null&&
               gender!=null){
           var intent=Intent(this,MainActivity2::class.java)
           startActivity(intent)
        }else{
            setContentView(R.layout.activity_main)
            binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)
            saveInformation()
       }
    }

    private fun saveInformation() {
       sharePreference=getSharedPreferences("myInfo1", Context.MODE_PRIVATE)
        val username=binding.editTextPersonName.text
        val codeMelli=binding.codeMelli.text
        val city=binding.city.text
        val address=binding.address.text
        val codePosti=binding.codePostal.text



        binding.buttonSave.setOnClickListener {
            if (binding.editTextPersonName.text.isBlank() ||
                binding.codeMelli.text.isBlank() ||
                binding.city.text.isBlank() ||
                binding.codePostal.text.isBlank() ||
                binding.address.text.isBlank()
            ) {
                Toast.makeText(this, "The form is not filled in correctly", Toast.LENGTH_LONG)
                    .show()
            } else {

                    val share: SharedPreferences.Editor = sharePreference.edit()
                    share.putString("username", username.toString())
                    share.putString("codeMelli", codeMelli.toString())
                    share.putString("city", city.toString())
                    share.putString("address", address.toString())
                    share.putString("codePosti", codePosti.toString())

                    if (binding.radioWomen.isChecked == true) {
                        share.putString("gender", "زن")
                    } else if (binding.buttonMen.isChecked == true) {
                        share.putString("gender", "مرد")
                    }
                    share.apply()
                    share.commit()
                    Toast.makeText(
                        getApplicationContext(),
                        "Your information was saved!",
                        Toast.LENGTH_LONG
                    )
                    var intent = Intent(this, MainActivity2::class.java)
                    startForResult.launch(intent)
                }

        }
        }
    private fun changeInformation() {
        val sharedPreferences = getSharedPreferences("myInfo1", Context.MODE_PRIVATE)
        val username=binding.editTextPersonName.text
        val codeMelli=binding.codeMelli.text
        val city=binding.address.text
        val codePosti=binding.codePostal.text
        val address=binding.address.text
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
       // showInformation()
    }
    val startForResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: ActivityResult->
        if(result.resultCode== Activity.RESULT_OK){
            val intent=result.data
            changeInformation()

        }
    }


}