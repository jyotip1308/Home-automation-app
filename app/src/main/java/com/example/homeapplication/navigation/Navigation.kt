package com.example.homeapplication.navigation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.homeapplication.screen.FanAcScreen
import com.example.homeapplication.screen.HomeScreen
import com.example.homeapplication.screen.LedBulbScreen
import com.example.homeapplication.viewModel.HomeAppViewModel


@Composable
fun NavigationController (navController: NavHostController, context: Context){

    val viewModel = viewModel<HomeAppViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeAppViewModel(
                    context = context
                ) as T
            }
        }
    )

    NavHost(navController = navController, startDestination = NavigationItem.HomeScreen.route){
        composable (NavigationItem.HomeScreen.route) {
            HomeScreen(viewModel)
        }
        composable (NavigationItem.LedBulbScreen.route){
            LedBulbScreen(context, viewModel)
        }
        composable(NavigationItem.FanAcScreen.route){
            FanAcScreen(context, viewModel)
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation (context: Context) {
    val navController = rememberNavController()
    val screenList = listOf(
        NavigationItem.HomeScreen,
        NavigationItem.LedBulbScreen,
        NavigationItem.FanAcScreen
    )
    Scaffold (

        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.background) {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route


                screenList.forEach {
                    BottomNavigationItem(selected = currentRoute == it.route,
                        label = {
                            Text(
                                text = it.label,
                                color = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )
                        },
                        icon = {
                            androidx.compose.material3.Icon(
                                painter = painterResource(it.icons),
                                contentDescription = "My Icon",
                                modifier = Modifier
                                    .size(25.dp),
                                tint = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )

                        },
                        onClick = {
                            if(currentRoute!=it.route){

                                navController.graph?.startDestinationRoute?.let {
                                    navController.popBackStack(it,true)
                                }

                                navController.navigate(it.route){
                                    launchSingleTop = true
                                }
                            }
                        })
                }
            }
        }) {
        NavigationController(navController ,context  )
    }
}
