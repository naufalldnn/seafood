package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R
import coil.compose.AsyncImage
import androidx.navigation.NavController
import com.example.myapplication.data.MenuData
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMenuScreen(navController: NavController, menuId: String) {
    val menu = MenuData.getMenuById(menuId) ?: return

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Color(0xFFC5A059))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // LUXURY IMAGE HEADER
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(24.dp)
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(24.dp),
                    shadowElevation = 16.dp
                ) {
                    AsyncImage(
                        model = menu.imageUrl.toIntOrNull() ?: menu.imageUrl,
                        contentDescription = menu.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        error = painterResource(id = R.drawable.seafood)
                    )
                }
            }

            // CONTENT
            Column(modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)) {
                Text(
                    text = menu.name,
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontFamily = FontFamily.Serif,
                        color = Color(0xFF4E342E)
                    ),
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "IDR ${menu.price}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFFC5A059),
                    fontWeight = FontWeight.Light
                )

                Spacer(modifier = Modifier.height(32.dp))

                HorizontalDivider(color = Color(0xFFC5A059).copy(alpha = 0.3f), thickness = 1.dp)

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "THE EXPERIENCE",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(0xFFC5A059),
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = menu.description,
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = 30.sp,
                    color = Color(0xFF5D4037),
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = RoundedCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC5A059))
                ) {
                    Text("RESERVE THIS DISH", fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                }
                
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}
