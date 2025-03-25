package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import java.util.Calendar


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
    var isSplshScreenVisible by remember { mutableStateOf(true) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
        Scaffold(
            modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                if (!isSplshScreenVisible) {
                    TopBar(scrollBehavior = scrollBehavior)
                }
            }
        ) {
            paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )
            if (isSplshScreenVisible) {
                SplashScreen {
                    isSplshScreenVisible = false
                }
            } else{
                ScreenContent(modifier = Modifier.padding(paddingValues))
            }
    }
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
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        windowInsets = WindowInsets(top = 0.dp),
        title = {
            TextField(
                value = "",
                onValueChange = { /* update search query */},
                placeholder = @Composable {
                    Text(
                        text = "Search your notes",
                        color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                        fontSize = 17.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.Menu,
                contentDescription = "Open navigation menu",
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .size(27.dp)
//                    .clickable(
//                        interactionSource = remember { MustableInteractionSource() },
//                        indication = rememberRipple(bounded = false)
//                    ) {
//                        // Handle menu icon click
//                    }
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "View notifications",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(30.dp)
//                    .clickable(
//                        interactionSource = remember { MustableInteractionSource() },
//                        indication = rememberRipple(bounded = false)
//                    ) {
//                        // Handle notifications icon click
//                    }
            )

            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 4.dp, end = 16.dp)
                    .size(30.dp)
//                    .clickable(
//                        interactionSource = remember { MustableInteractionSource() },
//                        indication = rememberRipple(bounded = false)
//                    ) {
//                        // Handle profile icon click
//                    }
            )
        }
    )
}

@Composable
fun SplashScreen(onTimeOut: () -> Unit) {
    // Simulate loading with a delay
    LaunchedEffect(Unit){
        delay(3000)
        onTimeOut()
    }

    // Splash Screen UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
            .consumeWindowInsets(WindowInsets.systemBars)
            .padding(WindowInsets.systemBars
                .asPaddingValues()),
        contentAlignment = Alignment.Center
    ) {
        // Add your splash screen content here
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Splash Logo",
                    modifier = Modifier.size(200.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "year_grid") {
        composable("year_grid") { YearGridScreen(navController) }
        composable("month_view/{year}") { backStackEntry ->
            val year = backStackEntry.arguments?.getString("year")
            MonthViewScreen(year)
        }
    }
}

@Composable
fun YearGridScreen(navController: NavHostController) {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    var yearsBeofre = 25
    val yearsAfter = 26
    val years = (currentYear - yearsBeofre..currentYear + yearsAfter).toList()
    val lazyGridState = rememberLazyGridState()

//     Scroll to the current year when the screen is first displayed
    LaunchedEffect(Unit) {
        val currentYearIndex = years.indexOf(currentYear)
        if (currentYearIndex != -1) {
            val visibleItemsCount = 4 * 7
            val offset = (visibleItemsCount/2) - 1
            val targetIndex = maxOf(0, currentYearIndex - offset)
            lazyGridState.scrollToItem(targetIndex)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(4),
            state = lazyGridState,
            contentPadding = PaddingValues(16.dp)) {
            items(years.size)
            { index ->
                val year = years[index]
                val isPastYear = year <= currentYear
                YearItem(
                    year = year,
                    isPastYear = isPastYear,
                    onClick = {
                        navController.navigate("month_view/$year")
                    },
                )
            }
        }
    }
}

@Composable
fun YearItem(year: Int, isPastYear: Boolean, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .size(80.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isPastYear) Color.Black else Color.LightGray)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(
                text = "$year", fontSize = 16.sp,
                color = if (isPastYear) Color.White else Color.Black)
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