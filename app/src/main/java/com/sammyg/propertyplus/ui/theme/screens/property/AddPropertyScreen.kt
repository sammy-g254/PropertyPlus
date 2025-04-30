package com.sammyg.propertyplus.ui.theme.screens.property

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sammyg.propertyplus.data.PropertyViewModel
import com.sammyg.propertyplus.navigation.ROUT_VIEW_PROPERTY


@Composable
fun AddPropertyScreen(navController: NavHostController) {
    val context = LocalContext.current
    val propertyViewModel = remember { PropertyViewModel(navController, context) }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add New Property",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
            fontSize = 20.sp
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty() && price.isNotEmpty() && location.isNotEmpty()) {
                    propertyViewModel.uploadProperty(title, description, price, location)
                    title = ""
                    description = ""
                    price = ""
                    location = ""
                } else {
                    Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp,end=20.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Upload Property")
        }

        Spacer(modifier = Modifier.height(24.dp))


        TextButton(
            onClick = { navController.navigate(ROUT_VIEW_PROPERTY) }
        ) {
            Text(text = "View Properties")
        }


    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AddPropertyScreenPreview() {
    AddPropertyScreen(navController = rememberNavController())
}