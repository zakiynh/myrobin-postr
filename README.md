# PostrServer
Services related to posr application

## Development

### Requirements

To develop this service, you need to have the following installed in your machine:

1. Java 17
2. Gradle 7.6
3. Docker 20
4. Docker Compose 1.29

### Running the Service Locally

We use Docker Compose to run the service locally. To run the service, follow these steps:

1. We need to build the service first. Run the command below to build the service:

    ```bash
    ./gradlew bootJar
    ```

2. Run the command below to start the Docker Compose:

    ```bash
    docker-compose up --build
    ```

   The service will be available at `http://localhost:8080`.

3. List of API:
   
   ```bash
   Create a posts
        @PostMapping(path = "/posts")
   ```

   ```bash
   Get a posts
        @GetMapping(path = "/posts")
   ```

   ```bash
   Get a limit users by newest post
        @GetMapping(path = "/posts/users")
   ```

   ```bash
   Create comment
        @PostMapping(path = "/posts/{postId}/comments")
   ```