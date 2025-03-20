package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()
            CalendarApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarApp() {

//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
//        state = rememberTopAppBarState()
//    )

    Scaffold /*(
//        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//
//        topBar = {
//            TopBar(scrollBehavior = scrollBehavior)
//
////            CenterAlignedTopAppBar(
////                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
////                    containerColor = MaterialTheme.colorScheme.primaryContainer,
////                    titleContentColor = MaterialTheme.colorScheme.primary,
////                ),
////                title = {
////                    Text(
////                        "Centered Top App Bar",
////                        maxLines = 1,
////                        overflow = TextOverflow.Ellipsis
////                    )
////                },
////                navigationIcon = {
////                    IconButton(onClick = { /* do something */ }) {
////                        Icon(
////                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
////                            contentDescription = "Localized description"
////                        )
////                    }
////                },
////                actions = {
////                    IconButton(onClick = { /* do something */ }) {
////                        Icon(
////                            imageVector = Icons.Filled.Menu,
////                            contentDescription = "Localized description"
////                        )
////                    }
////                },
////                scrollBehavior = scrollBehavior,
////            )
//        },
        bottomBar = {

        },
        floatingActionButton = {

        }
        ) */ {
        paddingValues ->
        Screen(
            modifier = Modifier.padding(paddingValues)
        )
    }

//    val navController = rememberNavController()
//
////     code to show year grid and month view
//    NavHost(navController, startDestination = "year_grid") {
//        composable("year_grid") { YearGridScreen(navController) }
//        composable("month_view/{year}") { backStackEntry ->
//            val year = backStackEntry.arguments?.getString("year")
//            MonthViewScreen(year)
//        }
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(100.dp)),
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.6f)
            containerColor = MaterialTheme.colorScheme.primaryContainer,
//            titleContentColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.6f),
        ),
        windowInsets = WindowInsets(top = 0.dp),
        title = {
            Text(
                text = "Sreach your notes",
                color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                fontSize = 17.sp
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.Menu,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .size(27.dp)
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(30.dp)
            )

            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 4.dp, end = 16.dp)
                    .size(30.dp)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(scrollBehavior = scrollBehavior)
        }
    ) {
        paddingValues ->
        ScreenContent(
            paddingValues = paddingValues
        )
    }
}

@Composable
fun ScreenContent(paddingValues: PaddingValues){

    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(top = 0.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        contentPadding = PaddingValues(
//            top = paddingValues.calculateTopPadding() + 10.dp
//        )
    ){
        NavHost(navController, startDestination = "year_grid") {
            composable("year_grid") { YearGridScreen(navController) }
            composable("month_view/{year}") { backStackEntry ->
                val year = backStackEntry.arguments?.getString("year")
                MonthViewScreen(year)
            }
        }
    }

//    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        contentPadding = PaddingValues(
//            top = paddingValues.calculateTopPadding() + 10.dp
//        )
//    ) {
//        items(1100){
//        }
//    }

//    //     code to show year grid and month view
//    val navController = rememberNavController()
//    NavHost(navController, startDestination = "year_grid") {
//        composable("year_grid") { YearGridScreen(navController) }
//        composable("month_view/{year}") { backStackEntry ->
//            val year = backStackEntry.arguments?.getString("year")
//            MonthViewScreen(year)
//        }
//    }
}

@Composable
fun ScrollContent(innerPadding: PaddingValues) {

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
//        Text("Select a Year", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
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
