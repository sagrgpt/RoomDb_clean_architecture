# Crick Squad

A simple attempt at exploring clean architecture with Room Db.

* Fetches a json from remote, stores it into the db and then returns back the new data to the view, all done asynchronously.

## - Architectural Layers
- **Network** Responsible for remote server access
- **Database** Responsible to locally store data in device.
- **Repository** Exposes the api for CRUD operations related to logical data. Wraps Network/database layers
- **ViewModel** Responsible for handling the logic behind how the data is displayed in the view
- **View** The UI layer coupled with Android Framework


## - Built With
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Rx-Java](https://github.com/ReactiveX/RxJava) - For composing asynchronous and event-based programs by using observable sequences.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
- [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room) - A persistence library that provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [OkHttp](http://square.github.io/okhttp/) - HTTP client that's efficient by default: HTTP/2 support allows all requests to the same host to share a socket
- [Mockito](http://site.mockito.org/) - Most popular mocking framework for Java/kotlin.

This application also leverages the benefits of TDD to achieve 92% code coverage for Business Logic.