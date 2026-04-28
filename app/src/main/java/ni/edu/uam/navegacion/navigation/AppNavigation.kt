package ni.edu.uam.navegacion.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ni.edu.uam.navegacion.model.PostUiState
import ni.edu.uam.navegacion.model.ProfileUiState
import ni.edu.uam.navegacion.screens.EditProfileScreen
import ni.edu.uam.navegacion.screens.FeedScreen
import ni.edu.uam.navegacion.screens.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    var profile by remember {
        mutableStateOf(ProfileUiState())
    }

    val posts = remember {
        mutableStateListOf<PostUiState>()
    }

    NavHost(
        navController = navController,
        startDestination = "editProfile"
    ) {
        composable("feed") {
            FeedScreen(
                navController = navController,
                profile = profile,
                posts = posts,
                onAddPost = { text ->
                    if (text.isNotBlank()) {
                        val authorName = profile.name.ifBlank { "Usuario nuevo" }
                        val authorUser = profile.username.ifBlank { "@usuario" }

                        posts.add(
                            0,
                            PostUiState(
                                id = System.currentTimeMillis(),
                                author = authorName,
                                username = authorUser,
                                content = text.trim(),
                                avatarUri = profile.avatarUri,
                                likes = 0
                            )
                        )
                    }
                }
            )
        }

        composable("profile") {
            ProfileScreen(
                navController = navController,
                profile = profile,
                posts = posts
            )
        }

        composable("editProfile") {
            val isNewAccount = profile.name.isBlank() && profile.username.isBlank()

            EditProfileScreen(
                navController = navController,
                profile = profile,
                isNewAccount = isNewAccount,
                onSaveProfile = { updatedProfile ->
                    profile = updatedProfile

                    if (isNewAccount) {
                        navController.navigate("feed") {
                            popUpTo("editProfile") {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}