package ni.edu.uam.navegacion.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ni.edu.uam.navegacion.components.CoverImage
import ni.edu.uam.navegacion.components.PostCard
import ni.edu.uam.navegacion.components.ProfileAvatar
import ni.edu.uam.navegacion.components.SocialBottomBar
import ni.edu.uam.navegacion.components.StatItem
import ni.edu.uam.navegacion.model.PostUiState
import ni.edu.uam.navegacion.model.ProfileUiState

@Composable
fun ProfileScreen(
    navController: NavController,
    profile: ProfileUiState,
    posts: SnapshotStateList<PostUiState>
) {
    Scaffold(
        bottomBar = {
            SocialBottomBar(
                navController = navController,
                selected = "profile"
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    CoverImage(
                        uri = profile.coverUri,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(175.dp)
                    )

                    ProfileAvatar(
                        uri = profile.avatarUri,
                        name = profile.name,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 22.dp, bottom = 8.dp)
                            .size(112.dp)
                    )

                    Surface(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 22.dp, bottom = 22.dp)
                            .clickable {
                                navController.navigate("editProfile")
                            },
                        shape = RoundedCornerShape(18.dp),
                        color = MaterialTheme.colorScheme.primary,
                        shadowElevation = 6.dp
                    ) {
                        Text(
                            text = "Editar perfil",
                            modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier.padding(horizontal = 22.dp)
                ) {
                    Text(
                        text = profile.name.ifBlank { "Usuario nuevo" },
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = profile.username.ifBlank { "@usuario" },
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.outline
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = profile.bio.ifBlank { "Aún no has agregado una biografía." },
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (profile.location.isBlank()) {
                            "Ubicación no agregada"
                        } else {
                            "Ubicación: ${profile.location}"
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 22.dp),
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        StatItem(number = posts.size.toString(), label = "posts")
                        StatItem(number = "0", label = "amigos")
                        StatItem(number = if (profile.avatarUri != null || profile.coverUri != null) "1+" else "0", label = "fotos")
                    }
                }
            }

            item {
                Text(
                    text = "Publicaciones",
                    modifier = Modifier.padding(horizontal = 22.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            if (posts.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 22.dp),
                        shape = RoundedCornerShape(26.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Sin publicaciones todavía",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Cuando publiques algo, aparecerá en tu perfil.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                }
            } else {
                items(
                    items = posts,
                    key = { post -> post.id }
                ) { post ->
                    Column(
                        modifier = Modifier.padding(horizontal = 22.dp)
                    ) {
                        PostCard(post = post)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(70.dp))
            }
        }
    }
}