package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarApp()
        }
    }
}

@Composable
fun CalendarApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "year_grid") {
        composable("year_grid") { YearGridScreen(navController) }
        composable("month_view/{year}") { backStackEntry ->
            val year = backStackEntry.arguments?.getString("year")
            MonthViewScreen(year)
        }
    }
}

@Composable
fun YearGridScreen(navController: NavHostController) {
    val startYear = 1900
    val endYear = 3000
    val years = (startYear..endYear).toList()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Select a Year", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(16.dp)) {
            items(years.size)
            { index ->
                YearItem(years[index])
                {
                    navController.navigate("month_view/${years[index]}")
                }
            }
        }
    }
}

@Composable
fun YearItem(year: Int, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .size(80.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "$year", fontSize = 16.sp)
        }
    }
}

@Composable
fun MonthViewScreen(year: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Month View for Year: $year", fontSize = 24.sp)
    }
}
