# HotelsApp

Es una aplicación en la que podrás visualizar un listado de hoteles junto a los servicios que ofrece, ubicación, precio, reviews, rating, etc.
Además contarás con una sección "Vistos" que funciona como una especie de historial de los hoteles en los que ingresaste a su detalle correspondiente

Por si fuera poco también tendrás la posibilidad de  ver en un mapa interactivo la ubicación de todos los hoteles que se almacenaron en tu historial, pudiendo de esta manera, si asi lo quisieras, abrir Google Maps y buscar más información como rutas, tráfico, etc.

# Detalles técnicos

Esta aplicación fue desarrollada 100% en Kotlin aplicando muchos de los patrones y arquitectura sugeridas por Google en su última I/O como por ejemplo LiveData, Room, ViewModel, Navigation components, etc.
- Kotlin
  - Coroutines
  - Lambda expressions
  - Extension functions
  - Null-safety
  - Smart casts
  - String templates
  - Android extensions
  - Companion objects
  - Data classes
- Clean-Code
- MVVM
- SOLID
- Kodein para dependency injection
- Retrofit
- ConstraintLayout
- CardView
- Android architecture components
  - LiveData
  - Room
  - ViewModel
- Navigation components
- AndroidX
- Glide
- Google Maps SDK
- Lottie (para animaciones)
- Calligraphy
- Font, FontVariable: clases creadas para el manejo de fuentes, con la ayuda de extension functions y calligraphy.
- GSON para serialization y deserialization de JSON
- DTO pattern
- Singleton pattern
- Zoomage para zoom de imágenes
- AutoViewPager para slide automático de ViewPager
- ShowMoreText para mostrar textos largos en pocas lineas y darle al usuario la posibilidad de ver más o ver menos del texto total, según su preferencia.
- Material Design
- Anko
- LifeCycle

# Decisiones

Soy una persona que está constantemente intentando crecer y aprender las nuevas tendencias, herramientas, tecnologías y metodologías que van surgiendo con respecto al desarrollo.

Por eso es que desde hace un tiempo opté por inclinarme hacia el uso de Kotlin como lenguaje principal para mis desarrollos, si bien llevo poco tiempo con este lenguaje, me he podido adaptar rápidamente y estoy mas que contento con ello.

Como trato de mantenerme siempre al día también decidí probar Android architecture components y navigation components. En este proyecto traté de plasmar un poco todo esto de usar las últimas tecnologías siempre con la mirada en el producto y su impacto en el usuario.

Tal es así que la sección en donde muestro los hoteles "vistos", la usé como excusa perfecta para poder hacer uso de Room, ya que como ViewModel, LiveData, Coroutines y todo lo demás me pareció muy oportuno mostrando.

La aplicación está hecha siguiendo el patrón de arquitectura MVVM que junto a MVP son mis favoritos. 

Para el manejo de networking opté por utilizar Retrofit y por primera vez integrarlo con coroutines. El resultado fue genial, me encantó haber probado eso aunque se que hay muchas cosas todavía por mejorar.













