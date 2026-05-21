package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        "DISCOVER", 
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 8.sp,
                        color = Color(0xFFC5A059)
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Color(0xFFC5A059))
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White.copy(alpha = 0.9f))
            )
        }
    ) { paddingValues ->
        // NEW RADICAL LAYOUT: "The Asymmetric Stack"
        // Replacing the grid with a dynamic, high-fashion vertical flow
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 40.dp)
        ) {
            item {
                Column(modifier = Modifier.padding(32.dp)) {
                    Text(
                        "Ocean Treasures",
                        style = MaterialTheme.typography.displaySmall.copy(fontFamily = FontFamily.Serif),
                        color = Color(0xFF4E342E)
                    )
                    HorizontalDivider(
                        modifier = Modifier.width(60.dp).padding(vertical = 12.dp),
                        thickness = 1.dp,
                        color = Color(0xFFC5A059)
                    )
                }
            }

            itemsIndexed(MenuData.menuList) { index, menu ->
                AsymmetricMenuRow(
                    menu = menu, 
                    isEven = index % 2 == 0,
                    onClick = { navController.navigate("detail/${menu.id}") }
                )
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

@Composable
fun AsymmetricMenuRow(menu: com.example.myapplication.data.MenuItem, isEven: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .clickable { onClick() }
            .padding(horizontal = 24.dp)
    ) {
        // The Background Decorative "Frame"
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.8f)
                .align(if (isEven) Alignment.BottomStart else Alignment.BottomEnd),
            color = Color(0xFFF3E5AB).copy(alpha = 0.3f),
            shape = RoundedCornerShape(20.dp)
        ) {}

        // The Image Card (Floating and Offset)
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .fillMaxHeight(0.85f)
                .align(if (isEven) Alignment.TopEnd else Alignment.TopStart),
            shape = RoundedCornerShape(12.dp),
            shadowElevation = 12.dp
        ) {
            AsyncImage(
                model = menu.imageUrl.toIntOrNull() ?: menu.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.seafood)
            )
        }

        // The Floating Info Text (Opposite of Image)
        Column(
            modifier = Modifier
                .fillMaxWidth(0.45f)
                .align(if (isEven) Alignment.CenterStart else Alignment.CenterEnd)
                .padding(bottom = 20.dp),
            horizontalAlignment = if (isEven) Alignment.Start else Alignment.End
        ) {
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(8.dp),
                shadowElevation = 6.dp,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = menu.name.uppercase(),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF4E342E),
                        textAlign = if (isEven) TextAlign.Start else TextAlign.End,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "IDR ${menu.price / 1000}K",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color(0xFFC5A059),
                        fontWeight = FontWeight.Bold,
                        textAlign = if (isEven) TextAlign.Start else TextAlign.End
                    )
                }
            }
            
            // Decorative Gold Line
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(1.dp)
                    .background(Color(0xFFC5A059))
            )
        }
    }
}
