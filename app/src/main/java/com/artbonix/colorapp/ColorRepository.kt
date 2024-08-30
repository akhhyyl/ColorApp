package com.artbonix.colorapp

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ColorRepository(private val colorDao: ColorDao) {

    val allColors: LiveData<List<ColorEntity>> = colorDao.getAllColors()

    // Insert a color entity into the database
    suspend fun insert(color: ColorEntity) {
        try {
            colorDao.insertColor(color)
        } catch (e: Exception) {
            // Handle error
            Log.e("ColorRepository", "Error inserting color", e)
        }
    }

    // Synchronize with a remote server (e.g., Firebase)
    suspend fun syncWithServer() {
        try {
            // Example code for Firebase sync
            val firebaseDatabase = FirebaseDatabase.getInstance()
            val colorRef = firebaseDatabase.getReference("colors")

            // Fetch data from Firebase and update local database
            colorRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val colorList = mutableListOf<ColorEntity>()
                    for (snapshot in dataSnapshot.children) {
                        val color = snapshot.getValue(ColorEntity::class.java)
                        if (color != null) {
                            colorList.add(color)
                        }
                    }
                    // Update the local database with new data
                    CoroutineScope(Dispatchers.IO).launch {
                        colorDao.insertColors(colorList)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                    Log.e("ColorRepository", "Firebase sync failed", databaseError.toException())
                }
            })
        } catch (e: Exception) {
            // Handle error
            Log.e("ColorRepository", "Error syncing with server", e)
        }
    }
}
