package com.example.rehacer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var texto1: TextView
    private lateinit var texto2: TextView
    private lateinit var texto3: TextView
    private lateinit var mDatabase: DatabaseReference
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference
    private val firstDatabase = databaseReference.child("Humedad")
    private val secondDatabase = databaseReference.child("Temperatura")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        texto1 = findViewById(R.id.mms)
        texto2 = findViewById(R.id.mms2)
        texto3 = findViewById(R.id.info)
        mDatabase = FirebaseDatabase.getInstance().reference
        getUserInfo()
    }

    private fun getUserInfo() {
        mDatabase.child("Ejemplo_01").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val temperatura = snapshot.child("Temperatura").value.toString()
                    val humedad = snapshot.child("Humedad").value.toString()
                    val humedadSu = snapshot.child("Humedad Tierra").value.toString()
                    texto1.text = temperatura
                    texto2.text = humedad
                    texto3.text = humedadSu
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}
