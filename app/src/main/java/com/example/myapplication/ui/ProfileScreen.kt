package com.example.myapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.data.ProfileRepository
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.R
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = ProfileRepository(context)

    val name = repository.getRestaurantName()
    val address = repository.getAddress()
    val description = repository.getDescription()
    val openHours = repository.getOpenHours()

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        "RESTAURANT PROFILE", 
                        fontWeight = FontWeight.Bold, 
                        letterSpacing = 2.sp, 
                        color = Color(0xFFC5A059),
                        fontFamily = FontFamily.Serif,
                        style = MaterialTheme.typography.titleSmall
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Color(0xFFC5A059))
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
                .verticalScroll(rememberScrollState())
        ) {
            // LUXURY PROFILE HEADER
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier.size(160.dp),
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 12.dp,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC5A059).copy(alpha = 0.5f))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.seafood),
                        contentDescription = "Logo",
                        modifier = Modifier.padding(30.dp),
                        contentScale = ContentScale.Fit
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontFamily = FontFamily.Serif,
                        color = Color(0xFF4E342E)
                    ),
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    "ESTABLISHED MCMXCV",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFC5A059),
                    letterSpacing = 2.sp
                )
            }

            // INFO SECTIONS
            Column(
                modifier = Modifier.padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                LuxuryInfoItem("Heritage & Vision", description)
                LuxuryInfoItem("Location", address)
                LuxuryInfoItem("Service Hours", openHours)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate("edit_profile") },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC5A059))
                ) {
                    Text("UPDATE INFORMATION", fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                }
                
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun LuxuryInfoItem(title: String, content: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFFC5A059),
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 2.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF5D4037),
            lineHeight = 26.sp,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = Color(0xFFC5A059).copy(alpha = 0.2f))
    }
}
