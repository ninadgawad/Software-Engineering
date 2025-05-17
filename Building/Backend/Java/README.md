## Java & Spring Boot

### Latest concurrency Options JDK 21+ 
- Java Executors and Futures
- Completable Futures
- Structured concurrency
- @Async annotation
- Reactive Programming
- Virtual Threads

### Legacy Foundations
__Java Executors & Futures__
- Traditional thread pool management via ExecutorService
- Blocking, limited scalability
- Still useful for controlled thread management

```java
ExecutorService executor = Executors.newFixedThreadPool(2);
Future<String> future = executor.submit(() -> "Hello from thread!");
String result = future.get(); // Blocks
executor.shutdown();
```

__CompletableFuture__
- Introduced in Java 8 for async task chaining
- Supports non-blocking computation
- Ideal for async workflows without full reactive overhead


```java
CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "Hello async!");
cf.thenAccept(System.out::println); // Non-blocking
```

### Example Code: 
```java
import java.util.concurrent.CompletableFuture;

public class StarTrekAsync {

    public static void main(String[] args) {

        CompletableFuture<String> logFuture = CompletableFuture.supplyAsync(() ->
                "Captain's Log: Stardate 41153.7");

        CompletableFuture<String> missionFuture = CompletableFuture.supplyAsync(() ->
                "Starfleet: Mission is classified");

        CompletableFuture<String> spockFuture = CompletableFuture.supplyAsync(() ->
                "Spock: Highly illogical, but probable");

        // Combine all responses
        CompletableFuture<Void> allDone = CompletableFuture.allOf(logFuture, missionFuture, spockFuture);

        allDone.thenRun(() -> {
            try {
                System.out.println(logFuture.get());
                System.out.println(missionFuture.get());
                System.out.println(spockFuture.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Optional: wait for all to complete for demo purposes
        allDone.join();
    }
}

```
### Output:
```
Captain's Log: Stardate 41153.7
Starfleet: Mission is classified
Spock: Highly illogical, but probable
```

__Spring Boot Async Support__
- @Async Annotation
- Makes methods run asynchronously in the background
- Backed by TaskExecutor
- Easy drop-in for simple parallelization in Spring apps


```java
// Service class
@Async
public CompletableFuture<String> fetchData() {
    return CompletableFuture.completedFuture("Async response");
}

// Enable async in Spring Boot
@Configuration
@EnableAsync
class AsyncConfig {}


```
__Reactive Programming (Project Reactor)__
-WebFlux + Mono/Flux
- Built for non-blocking, event-driven systems
- Ideal for high-throughput, IO-bound applications
- Reactive Streams spec compatible
- Requires a reactive mindset and toolchain


```java

@GetMapping("/reactive")
public Mono<String> getReactive() {
    return Mono.just("Hello Reactive");
}

```

**Virtual Threads (Java 21 - GA)**
- New lightweight threads in the JDK
- Thousands of threads with minimal memory overhead
- Simplifies concurrent code — same old imperative style
- Great for high-concurrency applications (e.g., web servers)
- Enabled via Thread.ofVirtual().start(...)

```java
ExecutorService vExecutor = Executors.newVirtualThreadPerTaskExecutor();
vExecutor.submit(() -> System.out.println("Virtual thread task"));
vExecutor.shutdown();


```

__Structured Concurrency (Java 21)__
- Better lifecycle management for concurrent tasks
- Tasks run in a scoped, coordinated way
- Cancellation and failure handled uniformly

Example: try (var scope = new StructuredTaskScope.ShutdownOnFailure())


```java
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    Future<String> task1 = scope.fork(() -> fetchDataFromServiceA());
    Future<String> task2 = scope.fork(() -> fetchDataFromServiceB());

    scope.join();           // Wait for all
    scope.throwIfFailed();  // Propagate exceptions

    String combined = task1.resultNow() + task2.resultNow();
    System.out.println(combined);
}


```

__Spring Boot + Virtual Threads__
- Spring Boot 3 offers Virtual threads
- Tomcat, Jetty, and Undertow can run with virtual threads
- Just set: server.tomcat.threads.virtual=true
Still maturing, but promising for simplifying thread management


```
# application.yml 
server.tomcat.threads.virtual: true


```


| Use Case                     | Recommended Option            |
| ---------------------------- | ----------------------------- |
| Simple async method          | `@Async`, `CompletableFuture` |
| High-concurrency IO          | Virtual Threads (Java 21)     |
| Reactive APIs or Streams     | WebFlux (Reactive)            |
| Coordinated multi-task logic | Structured Concurrency        |
| Legacy systems               | Executors/Futures             |
