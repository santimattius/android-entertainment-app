# Android Movie App Template

This is a template to build an Android app applying good practices and using a clean architecture, you will see that the code is super decoupled with external frameworks and even with the same Android framework, this will help you to model your domain purely in Kotlin without generating external dependencies.

<p align="center">

  <img src="https://github.com/santimattius/android-movie-app-template/blob/master/screenshoot/screenshot_app_light.png?raw=true" alt="App Capture"/>
  <img src="https://github.com/santimattius/android-movie-app-template/blob/master/screenshoot/screenshot_app_dark.png?raw=true" alt="App Capture"/>

</p>

## TheMovieDB API

Check this documentation: https://www.themoviedb.org/documentation/api

### Setup TheMovieDb apikey on project
Using local properties for define api key:
- Example: https://gist.github.com/ramonaharrison/7a2061bf5ee920dfff53b37ed83520ec

## Dependencies

Below you will find the libraries used to build the template and according to my criteria the most used in android development so far.

- **Koin** - dependencie provider: 
  - https://insert-koin.io/
- **Retrofit** - networking: 
  - https://square.github.io/retrofit/
- **Moshi** - json parser:
  - https://github.com/square/moshi
- **Glide** with image loader:
  - https://github.com/bumptech/glide
- **Kotlin coroutines**
  - https://kotlinlang.org/docs/reference/coroutines-overview.html
- **Mockk**, testing library
  - https://mockk.io/  

## Referencias

 - [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
 - [Clean Code](https://blog.cleancoder.com/)
 - [Jetpack](https://developer.android.com/jetpack?gclid=CjwKCAjw7diEBhB-EiwAskVi13xJGdb6SCxqntF3pNt6JQ4ulvEQsB9JelBK2OIG5P0cePTCcsOksBoCk1sQAvD_BwE&gclsrc=aw.ds)
 - [Android developers](https://developer.android.com/)
