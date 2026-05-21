package com.example.myapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.data.ProfileRepository
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = ProfileRepository(context)

    var name by remember { mutableStateOf(repository.getRestaurantName()) }
    var address by remember { mutableStateOf(repository.getAddress()) }
    var description by remember { mutableStateOf(repository.getDescription()) }
    var openHours by remember { mutableStateOf(repository.getOpenHours()) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        "EDIT BASE IDENTITY", 
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color(0xFFC5A059)
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali", tint = Color(0xFFC5A059))
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(32.dp)
        ) {
            Text(
                "Update Information",
                style = MaterialTheme.typography.headlineSmall.copy(fontFamily = FontFamily.Serif),
                color = Color(0xFF4E342E)
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            LuxuryTextField(
                value = name,
                onValueChange = { name = it },
                label = "Restaurant Name"
            )

            Spacer(modifier = Modifier.height(20.dp))

            LuxuryTextField(
                value = address,
                onValueChange = { address = it },
                label = "Location"
            )

            Spacer(modifier = Modifier.height(20.dp))

            LuxuryTextField(
                value = description,
                onValueChange = { description = it },
                label = "Heritage & Vision",
                singleLine = false
            )

            Spacer(modifier = Modifier.height(20.dp))

            LuxuryTextField(
                value = openHours,
                onValueChange = { openHours = it },
                label = "Operational Window"
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    repository.saveProfile(name, address, description, openHours)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth().height(60.dp),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC5A059))
            ) {
                Text("SAVE MODIFICATIONS", fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            TextButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("DISCARD CHANGES", color = Color.Gray, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun LuxuryTextField(value: String, onValueChange: (String) -> Unit, label: String, singleLine: Boolean = true) {
    Column {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFFC5A059),
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFFC5A059),
                unfocusedIndicatorColor = Color(0xFFC5A059).copy(alpha = 0.3f),
                cursorColor = Color(0xFFC5A059)
            ),
            singleLine = singleLine,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF5D4037))
        )
    }
}
