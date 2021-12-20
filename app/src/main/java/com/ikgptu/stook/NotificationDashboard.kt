package com.ikgptu.stook

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ikgptu.stook.databinding.ActivityNotificationDashboardBinding

class NotificationDashboard : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationDashboardBinding
    private lateinit var mAuth: FirebaseAuth

    //Global Array and Map and Del Array
    private var notificationArray: ArrayList<NotificationClass> = arrayListOf()
    private var mynotificationMap : MutableMap<String,NotificationClass> = mutableMapOf()
    private var delArray : ArrayList<String> = arrayListOf()
    //Views
    private lateinit var myRecylerView: RecyclerView
    //Adapter
    private lateinit var myRecyclerAdapter: MyRecyclerAdapter
    //Collection ID
    private lateinit var collID: String
    private lateinit var userEmail: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Getting Email
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        userEmail = currentUser?.email.toString()
        //End
        //Binding View
        myRecylerView = binding.notiRecylerView
        //Search and Find
        getcollectionID()
        //Specifying Drag Direction
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val deletedCourse: NotificationClass = notificationArray[viewHolder.adapterPosition]

                val position = viewHolder.adapterPosition

                val documentID = notificationArray[position].docID
                delArray.add(documentID.toString())
                notificationArray.removeAt(viewHolder.adapterPosition)
                myRecyclerAdapter.notifyItemRemoved(viewHolder.adapterPosition)

                Snackbar.make(myRecylerView,deletedCourse.title.toString(), Snackbar.LENGTH_SHORT)
                 .show()
                 saveWaste()
            }
        }).attachToRecyclerView(myRecylerView)

     }
    private fun getcollectionID(){
        //Getting ID
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(userEmail).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d("Found", "DocumentSnapshot data: ${document.getString("collectionID")}")
                        collID = document.getString("collectionID").toString()
                        loadWaste()
                        searchFireStore()
                    } else {
                        Log.d("Wrong", "No such document")
                    }
                }

    }
    private fun loadWaste(){
        //Getting UserData
        val db = FirebaseFirestore.getInstance()
        db.collection("usersnotification").document(userEmail).get()
                .addOnSuccessListener { document ->
                    Log.d("Found", "Fetched")
                    if(document.get("marked_as_read") != null ){
                        val tempArray  = document.get("marked_as_read")
                        delArray = arrayListOf()
                        delArray = tempArray as ArrayList<String>
                    }
                }
                .addOnFailureListener {
                    delArray = arrayListOf()
                }
    }
    private fun searchFireStore(){
        val db = FirebaseFirestore.getInstance()
        //Firestore Search
        db.collection(collID).get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            Log.d("Start", document.id + " => " + document.data)
                            val notification = NotificationClass(document.data["Title"] as String?, document.data["Notification"] as String?,true,document.id)
                            mynotificationMap.put(document.id,notification)
                            Log.d("Success", "Documents fetched successfully")
                        }
                        // convert in to a map with key as docid and Notification class as object
                        for (element in delArray){
                            mynotificationMap.remove(element)
                        }
                        notificationArray = arrayListOf()
                        //initalize notification array to size 0
                        for(key in mynotificationMap.keys){
                            val notification : NotificationClass? = mynotificationMap.get(key)
                            notificationArray.add(notification!!)
                        }
                        //iterate over map and put in notification aaray
                        if(notificationArray.size > 1){
                        myRecyclerAdapter = MyRecyclerAdapter(notificationArray)
                        myRecylerView.layoutManager = LinearLayoutManager(this)
                        myRecylerView.adapter = myRecyclerAdapter
                        myRecyclerAdapter.notifyDataSetChanged()
                            Toast.makeText(this,"Refreshed", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this,"No New Notifications", Toast.LENGTH_SHORT).show()
                        }
                        Log.d("FOR", "Success of FOR LOOP")
                    }
            }
        }
    private fun saveWaste(){
        val db = FirebaseFirestore.getInstance()
        val user:MutableMap<String , Any> = HashMap()
        user["marked_as_read"] = delArray
        db.collection("usersnotification").document(userEmail)
                .set(user)
    }
}