package com.example.myapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.myapplication.R
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Brush.verticalGradient(listOf(Color(0xFFFFFFFF), Color(0xFFFDFBF0))))
    ) {
        // LUXURY MINIMALIST HEADER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
        ) {
            // Soft background image with extreme fade
            Image(
                painter = painterResource(id = R.drawable.seafood),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.15f
            )
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Gold Rimmed Logo
                Surface(
                    modifier = Modifier.size(150.dp),
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 20.dp,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC5A059).copy(alpha = 0.5f))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.seafood),
                        contentDescription = "Logo",
                        modifier = Modifier.padding(25.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    "Ocean Delight",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Light,
                        letterSpacing = 4.sp,
                        color = Color(0xFFC5A059) // Gold
                    )
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Divider(
                    modifier = Modifier.width(40.dp),
                    thickness = 2.dp,
                    color = Color(0xFFC5A059).copy(alpha = 0.3f)
                )
                
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "FINE SEAFOOD GASTRONOMY",
                    style = MaterialTheme.typography.labelLarge.copy(
                        letterSpacing = 4.sp,
                        color = Color(0xFF5D4037).copy(alpha = 0.6f)
                    ),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // LUXURY HORIZONTAL SECTION
        Column(modifier = Modifier.padding(vertical = 24.dp)) {
            Text(
                "Seasonal Selections",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = FontFamily.Serif,
                    letterSpacing = 1.sp
                ),
                modifier = Modifier.padding(horizontal = 30.dp),
                color = Color(0xFF4E342E)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 30.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                val promos = listOf(
                    Triple("Lobster Mornay", R.drawable.seafood, "1"),
                    Triple("Caviar Selection", R.drawable.seafood, "7"),
                    Triple("King Crab", R.drawable.seafood, "5")
                )
                items(promos) { promo ->
                    Column(
                        modifier = Modifier
                            .width(180.dp)
                            .clickable { navController.navigate("detail/${promo.third}") }
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp),
                            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Image(
                                painter = painterResource(id = promo.second),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            promo.first,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4E342E),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        // FLOATING ACTION BUTTONS (Luxury Pill Style)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LuxuryButton(
                text = "Discover Menu",
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate("menu") }
            )
            LuxuryButton(
                text = "Our Story",
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate("profile") },
                isOutlined = true
            )
        }
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun LuxuryButton(text: String, modifier: Modifier, onClick: () -> Unit, isOutlined: Boolean = false) {
    if (isOutlined) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier.height(56.dp),
            shape = RoundedCornerShape(30.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC5A059))
        ) {
            Text(text.uppercase(), color = Color(0xFFC5A059), fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        }
    } else {
        Button(
            onClick = onClick,
            modifier = modifier.height(56.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC5A059))
        ) {
            Text(text.uppercase(), color = Color.White, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        }
    }
}
