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

__CompletableFuture__
- Introduced in Java 8 for async task chaining
- Supports non-blocking computation
- Ideal for async workflows without full reactive overhead

__Spring Boot Async Support__
- @Async Annotation
- Makes methods run asynchronously in the background
- Backed by TaskExecutor
- Easy drop-in for simple parallelization in Spring apps


__Reactive Programming (Project Reactor)__
-WebFlux + Mono/Flux
- Built for non-blocking, event-driven systems
- Ideal for high-throughput, IO-bound applications
- Reactive Streams spec compatible
- Requires a reactive mindset and toolchain

**Virtual Threads (Java 21 - GA)**
- New lightweight threads in the JDK
- Thousands of threads with minimal memory overhead
- Simplifies concurrent code — same old imperative style
- Great for high-concurrency applications (e.g., web servers)
- Enabled via Thread.ofVirtual().start(...)

__Structured Concurrency (Java 21)__
- Better lifecycle management for concurrent tasks
- Tasks run in a scoped, coordinated way
- Cancellation and failure handled uniformly

Example: try (var scope = new StructuredTaskScope.ShutdownOnFailure())

__Spring Boot + Virtual Threads__
- Spring Boot 3.2+ offers early support
- Tomcat, Jetty, and Undertow can run with virtual threads
- Just set: server.tomcat.threads.virtual=true
Still maturing, but promising for simplifying thread management


| Use Case                     | Recommended Option            |
| ---------------------------- | ----------------------------- |
| Simple async method          | `@Async`, `CompletableFuture` |
| High-concurrency IO          | Virtual Threads (Java 21)     |
| Reactive APIs or Streams     | WebFlux (Reactive)            |
| Coordinated multi-task logic | Structured Concurrency        |
| Legacy systems               | Executors/Futures             |
