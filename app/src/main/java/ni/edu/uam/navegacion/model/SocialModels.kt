package ni.edu.uam.navegacion.model

data class ProfileUiState(
    val name: String = "",
    val username: String = "",
    val bio: String = "",
    val location: String = "",
    val avatarUri: String? = null,
    val coverUri: String? = null
)

data class PostUiState(
    val id: Long,
    val author: String,
    val username: String,
    val content: String,
    val avatarUri: String?,
    val likes: Int
)