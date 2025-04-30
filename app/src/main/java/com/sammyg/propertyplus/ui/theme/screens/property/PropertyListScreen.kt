package com.sammyg.propertyplus.ui.theme.screens.property

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import com.sammyg.propertyplus.data.PropertyViewModel
import com.sammyg.propertyplus.models.Property
import com.sammyg.propertyplus.navigation.ROUT_ADD_PROPERTY
import com.sammyg.propertyplus.navigation.ROUT_VIEW_PROPERTY
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PropertyListScreen(navController: NavHostController) {
    val context = LocalContext.current
    val propertyViewModel = remember { PropertyViewModel(navController, context) }

    val selectedProperty = remember { mutableStateOf(Property("", "", "", "", "", "")) }
    val propertyList = remember { mutableStateListOf<Property>() }

    val currentUser = FirebaseAuth.getInstance().currentUser
    val currentUserId = currentUser?.uid

    val properties = propertyViewModel.allProperties(selectedProperty, propertyList)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        TextButton(
            onClick = { navController.navigate(ROUT_ADD_PROPERTY) }
        ) {
            Text(text = "Add New Property")
        }


        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Property Listings",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(properties) { property ->
                if (property.userId == currentUserId) {
                    PropertyItem(
                        property = property,
                        onUpdate = {
                            navController.navigate("edit_property_screen/${property.id}")
                        },
                        onDelete = { propertyViewModel.deleteProperty(property.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun PropertyItem(
    property: Property,
    onUpdate: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Title: ${property.title}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "Description: ${property.description}", fontSize = 14.sp)
            Text(text = "Price: ${property.price}", fontSize = 14.sp)
            Text(text = "Location: ${property.location}", fontSize = 14.sp)

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(onClick = onUpdate) {
                    Icon(Icons.Default.Edit, contentDescription = "Update")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Update")
                }
                Button(onClick = onDelete, colors = ButtonDefaults.buttonColors(Color.DarkGray)) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Delete", color = Color.White)
                }
            }
        }
    }
}