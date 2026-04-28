package ni.edu.uam.navegacion.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ni.edu.uam.navegacion.components.CoverImage
import ni.edu.uam.navegacion.components.ProfileAvatar
import ni.edu.uam.navegacion.model.ProfileUiState

@Composable
fun EditProfileScreen(
    navController: NavController,
    profile: ProfileUiState,
    isNewAccount: Boolean,
    onSaveProfile: (ProfileUiState) -> Unit
) {
    var name by remember { mutableStateOf(profile.name) }
    var username by remember { mutableStateOf(profile.username) }
    var bio by remember { mutableStateOf(profile.bio) }
    var location by remember { mutableStateOf(profile.location) }
    var avatarUri by remember { mutableStateOf(profile.avatarUri) }
    var coverUri by remember { mutableStateOf(profile.coverUri) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val avatarPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            avatarUri = uri.toString()
        }
    }

    val coverPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            coverUri = uri.toString()
        }
    }

    fun saveProfile() {
        val cleanName = name.trim()
        val cleanUsername = username.trim()

        if (cleanName.isBlank()) {
            errorMessage = "Ingresa tu nombre para continuar."
            return
        }

        if (cleanUsername.isBlank()) {
            errorMessage = "Ingresa un nombre de usuario para continuar."
            return
        }

        val finalUsername = if (cleanUsername.startsWith("@")) {
            cleanUsername
        } else {
            "@$cleanUsername"
        }

        onSaveProfile(
            ProfileUiState(
                name = cleanName,
                username = finalUsername,
                bio = bio.trim(),
                location = location.trim(),
                avatarUri = avatarUri,
                coverUri = coverUri
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(22.dp)
    ) {
        if (!isNewAccount) {
            Text(
                text = "← Volver al perfil",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                },
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        Text(
            text = if (isNewAccount) "Crear tu perfil" else "Editar perfil",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = if (isNewAccount) {
                "Agrega tus datos para empezar a usar tu cuenta."
            } else {
                "Actualiza tu información y tus fotos."
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline
        )

        Spacer(modifier = Modifier.height(22.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        ) {
            CoverImage(
                uri = coverUri,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clickable {
                        coverPicker.launch("image/*")
                    }
            )

            Surface(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .clickable {
                        coverPicker.launch("image/*")
                    },
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 6.dp
            ) {
                Text(
                    text = "Cambiar portada",
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            }

            ProfileAvatar(
                uri = avatarUri,
                name = name,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 18.dp, bottom = 6.dp)
                    .size(112.dp)
                    .clickable {
                        avatarPicker.launch("image/*")
                    }
            )

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 20.dp)
                    .clickable {
                        avatarPicker.launch("image/*")
                    },
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.primary,
                shadowElevation = 6.dp
            ) {
                Text(
                    text = "Cambiar foto",
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                errorMessage = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nombre") },
            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                errorMessage = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Usuario") },
            placeholder = { Text("@usuario") },
            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = bio,
            onValueChange = { bio = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Biografía") },
            shape = RoundedCornerShape(18.dp),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Ubicación") },
            placeholder = { Text("Managua, Nicaragua") },
            shape = RoundedCornerShape(18.dp)
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .clickable {
                    saveProfile()
                },
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.primary,
            shadowElevation = 8.dp
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isNewAccount) "Crear cuenta" else "Guardar cambios",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}