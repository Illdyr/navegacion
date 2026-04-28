# SocialApp - Navegación en Jetpack Compose

## Descripción

SocialApp es una aplicación móvil básica desarrollada en Android Studio utilizando Kotlin y Jetpack Compose.

El objetivo del proyecto es implementar un sistema de navegación entre pantallas, simulando una interfaz sencilla tipo red social. La aplicación permite crear un perfil desde cero, editar datos personales, agregar foto de perfil y portada desde la galería, publicar contenido y navegar entre el inicio y el perfil del usuario.

## Objetivo de la práctica

Implementar un sistema de navegación entre pantallas en una aplicación móvil utilizando Jetpack Compose, comprendiendo el flujo de navegación, la estructura de pantallas y el manejo de componentes interactivos básicos.

## Funcionalidades principales

- Creación de perfil desde cero.
- Edición de nombre, usuario, biografía y ubicación.
- Selección de foto de perfil desde la galería.
- Selección de foto de portada desde la galería.
- Creación de publicaciones.
- Visualización de publicaciones en el inicio.
- Visualización de publicaciones en el perfil.
- Navegación funcional entre pantallas.
- Interfaz con colores pasteles, tonos mates y sombras suaves.

## Pantallas de la aplicación

La aplicación contiene tres pantallas principales:

1. Crear o editar perfil  
   En esta pantalla el usuario puede ingresar sus datos personales, cambiar su foto de perfil y seleccionar una portada.

2. Inicio  
   En esta pantalla el usuario puede escribir y publicar contenido, además de ver sus publicaciones.

3. Perfil  
   En esta pantalla se muestra la información del usuario, sus estadísticas básicas y sus publicaciones.

## Tecnologías utilizadas

- Kotlin
- Android Studio
- Jetpack Compose
- Material Design 3
- Navigation Compose
- Coil Compose

## Dependencias principales

Para que el proyecto funcione correctamente, se utilizaron las siguientes dependencias:

```kotlin
implementation("androidx.navigation:navigation-compose:2.8.9")
implementation("io.coil-kt:coil-compose:2.7.0")
