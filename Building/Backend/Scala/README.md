# Scala Guide for Java Developers

Welcome! If you are a Java developer looking to learn Scala, you are in the right place. Scala is a powerful language that runs on the Java Virtual Machine (JVM), interoperates seamlessly with Java, and combines **Object-Oriented Programming (OOP)** with **Functional Programming (FP)**.

This guide is designed to translate your Java knowledge into Scala, highlighting the core differences, new paradigms, and how to write clean, idiomatic Scala code.

To follow along with hands-on code examples, see our companion project file: **[Main.scala](file:///c:/Users/ninad/antigravityworkspace/proejct1/src/scala/com/ninad/project1/Main.scala)**.

---

## 1. Quick Paradigm Comparison

| Feature | Java | Scala |
| :--- | :--- | :--- |
| **Philosophy** | Primarily imperative and Object-Oriented | Dual-paradigm (Full OOP + Full FP) |
| **Verbosity** | Verbose; requires boilerplate (getters, setters) | Extremely concise; syntax-light |
| **Immutability** | Opt-in (e.g., `final`, `unmodifiableList`) | Default and idiomatic (`val`, immutable collections) |
| **Variables** | `String s = "hello"` | `val s: String = "hello"` or `val s = "hello"` |
| **Functions** | Methods on classes; Lambdas via functional interfaces | First-class values (can be passed, returned, stored) |
| **Null Safety** | Optional container, but raw `null` is common | `Option[T]` (`Some`/`None`) is the standard; `null` is avoided |
| **Statics** | `static` keyword | Singleton `object` (Companion Objects) |

---

## 2. Core Concepts

### A. Val vs Var & Type Inference
In Java, you explicitly declare types. In Scala, the compiler can infer most types. You define values in two ways:
*   `val`: Immutable binding (like `final` in Java). Once assigned, it cannot be changed. **Always use this by default.**
*   `var`: Mutable binding. Can be reassigned. Use this sparingly.

```scala
// Scala
val message = "Hello, Scala!" // Inferred as String
var counter = 0
counter += 1 // Reassignment is allowed for var
```

```java
// Java Equivalent
final String message = "Hello, Scala!";
int counter = 0;
counter += 1;
```

### B. Everything is an Expression
In Java, there is a clear distinction between **statements** (which perform actions but return nothing, like `void` methods, `if-else` blocks, `for` loops) and **expressions** (which compute and return a value).

In Scala, **almost everything is an expression** that returns a value.
*   `if-else` returns a value (replacing the ternary operator `? :`).
*   Code blocks `{}` return the value of their last line.

```scala
// Scala
val status = if (age >= 18) "Adult" else "Minor"

val result = {
  val x = 10
  val y = 20
  x + y // The block returns 30
}
```

---

## 3. Object-Oriented Programming: Java vs. Scala

### A. Constructors and Fields
Java requires constructors, fields, getters, and setters, which leads to boilerplate code. Scala defines fields and the primary constructor directly in the class signature.

```scala
// Scala: One-liner class definition with immutable and mutable properties
class Person(val name: String, var age: Int) {
  def greet(): Unit = println(s"Hi, I'm $name")
}
```

```java
// Java Equivalent
public class Person {
    private final String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public void greet() { System.out.println("Hi, I'm " + name); }
}
```

### B. Companion Objects (No more `static`)
Scala does not have the `static` keyword. Instead, it uses **singleton objects** declared with the `object` keyword. 

When an `object` shares the same name and file as a `class`, it is called a **Companion Object**. The companion object and the class can access each other’s private members. Companion objects commonly implement an `apply` method, which acts as a factory constructor (allowing class instantiation without the `new` keyword).

```scala
class DatabaseConnection private(val url: String) // Private constructor

object DatabaseConnection {
  // Factory method
  def apply(host: String, port: Int): DatabaseConnection = {
    new DatabaseConnection(s"jdbc:postgresql://$host:$port/db")
  }
}

// Usage:
val conn = DatabaseConnection("localhost", 5432) // No 'new' needed!
```

### C. Traits vs. Interfaces
Scala `traits` are similar to Java 8+ interfaces with default methods, but they are much more powerful:
1.  They can hold concrete fields and state.
2.  Classes can inherit from multiple traits (called **Mixins**).

```scala
trait Logger {
  def log(msg: String): Unit = println(s"[INFO] $msg")
}

trait AuthHelper {
  val adminRole = "ADMIN"
}

class AdminService extends Logger with AuthHelper {
  def performAction(): Unit = {
    log(s"Performing action with role: $adminRole")
  }
}
```

---

## 4. Case Classes (The Lombok Killer)

In Java, representing pure immutable data structures requires writing `equals()`, `hashCode()`, `toString()`, and getters, or using Lombok annotations (`@Value`, `@Data`) or Java Records.

In Scala, you prepend `case` to a class definition. The compiler automatically generates:
*   Getter fields (defaulting to immutable `val`).
*   Friendly `toString()`.
*   Value-based `equals()` and `hashCode()`.
*   A `copy()` method for painless mutation by copying.
*   Companion object with `apply` and `unapply` (allowing pattern matching).

```scala
case class Book(title: String, author: String, pages: Int)

val scalaBook = Book("Programming in Scala", "Martin Odersky", 800)
// Value comparison
val copyBook = scalaBook.copy(pages = 850) // Easily make a copy with changes
println(scalaBook == copyBook) // Checks structural equality (false)
```

---

## 5. Functional Programming & Collections

### A. Functions are First-Class Citizens
In Java, functions must be wrapped in classes or Functional Interfaces (e.g., `Function<T, R>`). In Scala, functions are actual values that can be manipulated directly.

```scala
// Scala: Define a function value
val square: Int => Int = (x: Int) => x * x

// Pass it to another method
def runOperation(value: Int, operation: Int => Int): Int = operation(value)
println(runOperation(5, square)) // Prints 25
```

### B. Collections and Functional Pipelines
Java uses the Stream API (`list.stream().map(...).collect(...)`) which requires converting to a stream and back. Scala collections have functional operations built directly into them, and they are **immutable** by default.

```scala
val list = List(1, 2, 3, 4, 5)

// Scala pipeline
val doubledEvens = list
  .filter(_ % 2 == 0)
  .map(_ * 2) // List(4, 8)
```

```java
// Java Stream equivalent
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> doubledEvens = list.stream()
    .filter(x -> x % 2 == 0)
    .map(x -> x * 2)
    .collect(Collectors.toList());
```

---

## 6. Null Safety: Option vs. Optional

Java developers frequently struggle with `NullPointerException` (NPE). While Java introduced `Optional<T>`, it is not consistently used throughout the standard library.

Scala solves this with `Option[A]`, which is a container that has exactly two subclasses:
*   `Some(value)`: Represents the presence of a value.
*   `None`: Represents the absence of a value.

```scala
val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

// Option-based lookup
val capital: Option[String] = capitals.get("Germany") // Returns None

// Safely resolve the value with a fallback
val result = capital.getOrElse("Unknown Capital")
```

---

## 7. Pattern Matching: Switch on Steroids

While Java has recently introduced enhanced switch statements and pattern matching for records, Scala has had full pattern matching support from its inception. You can match against values, types, lists, and case classes, and write custom filters (guards).

```scala
abstract class Device
case class Phone(model: String) extends Device
case class Computer(brand: String, screenSized: Int) extends Device

def printDevice(device: Device): Unit = device match {
  case Phone("iPhone") => println("It is an Apple iPhone!")
  case Phone(model)    => println(s"It is a phone: $model")
  case Computer(brand, size) if size > 15 => println(s"Large $brand computer ($size inch screen)")
  case _               => println("Unknown device")
}
```

---

## 8. Implicits: Scala's Secret Weapon

One of the most unique features of Scala 2 is **Implicits**. It allows the compiler to fill in arguments or convert types behind the scenes:
1.  **Implicit Parameters**: Tells the compiler to look for an implicit value of the required type in the current scope and pass it automatically. Often used for configurations, database connections, or execution context.
2.  **Implicit Classes (Extension Methods)**: Allows you to add new methods to existing classes (even standard library classes like `String` or `Int`) without modifying their source code or inheriting from them.

```scala
// Extension Method Example
implicit class RichInt(val value: Int) {
  def isEven: Boolean = value % 2 == 0
}

// The compiler automatically wraps 10 in RichInt to call isEven
println(10.isEven) // Prints true
```

---

## 9. Next Steps to Run and Learn

To see all these paradigms in action, explore the comprehensive annotated playground in this project:

👉 **[src/scala/com/ninad/project1/Main.scala](file:///c:/Users/ninad/antigravityworkspace/proejct1/src/scala/com/ninad/project1/Main.scala)**

### How to Compile and Run
Ensure you are using Java 17 to compile and run the Scala compiler (due to compatibility constraints on newer JVM releases):

```powershell
# Set JDK 17 environment variables
$env:JAVA_HOME = "C:\apps\java17"
$env:Path = "C:\apps\java17\bin;" + $env:Path

# Compile the file
scalac src/scala/com/ninad/project1/Main.scala

# Run the program
scala com.ninad.project1.Main
```
