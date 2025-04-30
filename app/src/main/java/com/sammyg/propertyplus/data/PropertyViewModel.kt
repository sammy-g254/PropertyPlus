package com.sammyg.propertyplus.data

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.sammyg.propertyplus.models.Property
import com.sammyg.propertyplus.navigation.ROUT_LOGIN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PropertyViewModel(var navController: NavHostController, var context: Context) {
    var authViewModel: AuthViewModel
    private val databaseReference = FirebaseDatabase.getInstance().getReference("Properties")

    init {
        authViewModel = AuthViewModel(navController, context)
        if (!authViewModel.isLoggedIn()) {
            navController.navigate(ROUT_LOGIN)
        }
    }


    // Upload property
    fun uploadProperty(title: String, description: String, price: String, location: String) {

        val propertyId = System.currentTimeMillis().toString()

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        val property = Property(
            title = title,
            description = description,
            price = price,
            location = location,
            id = propertyId,
            userId = userId ?: ""
        )

        val databaseRef = databaseReference.child(propertyId)

        databaseRef.setValue(property).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this.context, "Property uploaded successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this.context, "Error uploading property", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Deleting details from database
    fun deleteProperty(propertyId: String) {
        val ref = databaseReference.child(propertyId)
        ref.removeValue()
        Toast.makeText(context, "Property deleted successfully", Toast.LENGTH_SHORT).show()
    }



    // Fetching details from the database
    fun allProperties(
        property: MutableState<Property>,
        properties: SnapshotStateList<Property>
    ): SnapshotStateList<Property> {

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                properties.clear()
                for (snap in snapshot.children) {
                    val retrievedProperty = snap.getValue(Property::class.java)
                    if (retrievedProperty != null) {
                        property.value = retrievedProperty
                        properties.add(retrievedProperty)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Database error", Toast.LENGTH_SHORT).show()
            }
        })
        return properties
    }

    // Update Property method

    fun getPropertyById(propertyId: String, callback: (Property?) -> Unit) {
        databaseReference.child(propertyId).get().addOnSuccessListener { snapshot ->
            val property = snapshot.getValue(Property::class.java)
            callback(property)
        }.addOnFailureListener {
            callback(null)
        }
    }

    fun updateProperty(
        propertyId: String,
        title: String,
        description: String,
        price: String,
        location: String
    ) {
        val updatedData = mapOf(
            "title" to title,
            "description" to description,
            "price" to price,
            "location" to location
        )

        databaseReference.child(propertyId).updateChildren(updatedData).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Property updated successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to update property", Toast.LENGTH_SHORT).show()
            }
        }
    }
}