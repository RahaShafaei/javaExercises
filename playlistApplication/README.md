# Playlist Application
## Project description

The Playlist project is designed to store and play music, which includes the following features:
* It is possible to categorize and play music based on Genre and Artist.
* It is possible to apply music playback settings.
* It is possible to apply CRUD on music and playlists.

This project uses technologies related to the Spring framework, and the main components of the project are implemented based on a number of patterns, which are explained below.

## Project Technologies
* The database of this project is **H2**. A sample of how to set up this database could be found in [this](https://www.baeldung.com/spring-boot-h2-database) link. Data of this database produce through `data.sql` file which is placed in the [resource](https://github.com/RahaShafaei/javaExercises/tree/main/playlistApplication/src/main/resources) directory.
* The build tool of this project is **Maven**.
* Interactions with the database are done through **Spring JPA**.
* Building **RESTful** web services with **Spring Boot** to access the inner world of `Playlist Project`.
* Use **HATEOAS** to Link builder API to create links pointing to Spring MVC controller methods.

## Project Details
* Use **ResponseEntityExceptionHandler** to centralize Spring MVC exception handling.
* Use **DTO Design Pattern**, to prevent `StackOverflow Exception` happening when using `Entity Beans`.
* Use **Observer Pattern** to notify the playlist or song objects when there are changes or updates.
* Use **FactoryMethod Pattern** to create smart playlist based on genre, artist.
* Use **Singleton Pattern** to  have a central controller that handles the playlist operations.
* Use **Builder Pattern** to handle all `Controllers` constructor parameters.
* Trying to adhere to **SOLID** principles.