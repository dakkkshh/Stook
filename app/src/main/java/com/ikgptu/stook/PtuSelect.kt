package com.ikgptu.stook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.ikgptu.stook.databinding.ActivityPtuSelectBinding

lateinit var topic : String
lateinit var collid : String
class PtuSelect : AppCompatActivity() {
    private lateinit var binding: ActivityPtuSelectBinding

    //Object for E-mail and name
    private lateinit var useremail :String
    private lateinit var username : String
    //Firebase Obj
    private lateinit var mAuth: FirebaseAuth
    //Object for department and year
    private lateinit var department:String
    private lateinit var year : String

    /**Starting of Back Action*/
    override fun onBackPressed() {
        finishAffinity()
        finish()
    }
    /**End of back action*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPtuSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Saving spinner result to objects
        binding.deptDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long) {
                department = parent?.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.yearDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long) {
                year = parent?.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //Google Things
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        useremail = currentUser?.email.toString()
        username = currentUser?.displayName.toString()

        //End of google things
        binding.proceedPtu.setOnClickListener{
            receiveNotification()
            saveFireStore()
            val intent = Intent(this,MasterView::class.java)
            startActivity(intent)
        }
    }
    private fun receiveNotification(){
        //Conditions
        if(department == "Computer Science" && year == "2020"){
            topic = "/topics/cse_2020"
            collid = "cse_2020"

        }
        if(department == "Computer Science" && year == "2019"){
            topic = "/topics/cse_2019"
            collid = "cse_2019"

        }
        if(department == "Computer Science" && year == "2018"){
            topic = "/topics/cse_2018"
            collid = "cse_2018"

        }
        if(department == "Computer Science" && year == "2017"){
            topic = "/topics/cse_2017"
            collid = "cse_2017"
        }
        if(department == "Civil" && year == "2020"){
            topic = "/topics/ce_2020"
            collid = "ce_2020"
        }
        if(department == "Civil" && year == "2019"){
            topic = "/topics/ce_2019"
            collid = "ce_2019"

        }
        if(department == "Civil" && year == "2018"){
            topic = "/topics/ce_2018"
            collid = "ce_2018"
        }
        if(department == "Civil" && year == "2017"){
            topic = "/topics/ce_2017"
            collid = "ce_2017"

        }
        if(department == "Electrical" && year == "2020"){
            topic = "/topics/ee_2020"
            collid = "ee_2020"

        }
        if(department == "Electrical" && year == "2019"){
            topic = "/topics/ee_2019"
            collid = "ee_2019"

        }
        if(department == "Electrical" && year == "2018"){
            topic = "/topics/ee_2018"
            collid = "ee_2018"

        }
        if(department == "Electrical" && year == "2017"){
            topic = "/topics/ee_2017"
            collid = "ee_2017"

        }
        if(department == "Mechanical" && year == "2020"){
            topic = "/topics/me_2020"
            collid = "me_2020"

        }
        if(department == "Mechanical" && year == "2019"){
            topic = "/topics/me_2019"
            collid = "me_2019"

        }
        if(department == "Mechanical" && year == "2018"){
            topic = "/topics/me_2018"
            collid = "me_2018"

        }
        if(department == "Mechanical" && year == "2017"){
            topic = "/topics/me_2017"
            collid = "me_2017"

        }
        //End

        FirebaseMessaging.getInstance().subscribeToTopic(topic)
            .addOnSuccessListener {
            Log.d("Success in TOPIC","Go For IT")
        }

    }
    private fun saveFireStore(){
        val db = FirebaseFirestore.getInstance()
        val user:MutableMap<String , Any> = HashMap()
        user["User Name"] = username
        user["User Email"] = useremail
        user["Department"] = department
        user["Year"] = year
        user["collectionID"] = collid
        db.collection("users").document(useremail)
                .set(user)

    }
}