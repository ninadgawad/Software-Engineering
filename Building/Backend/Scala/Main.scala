package com.ninad.project1

import scala.annotation.tailrec
import scala.util.{Try, Success, Failure}

/**
 * ===================================================================================
 *                         SCALA LANGUAGE REVISION & TUTORIAL GUIDE
 * ===================================================================================
 * 
 * This file is a comprehensive revision guide for Scala (targeting Scala 2.12/2.13).
 * It covers basics, object-oriented features, functional programming, control structures,
 * collections, error handling, pattern matching, and implicit features.
 * 
 * To run this tutorial:
 * 1. Compile: scalac src/scala/com/ninad/project1/Main.scala
 * 2. Run:     scala -cp src/scala com.ninad.project1.Main
 */
object Main {

  def main(args: Array[String]): Unit = {
    printHeader("1. Variables, Constants, and Lazy Evaluation")
    demonstrateVariables()

    printHeader("2. Control Structures (If, For, While)")
    demonstrateControlStructures()

    printHeader("3. Functions and Methods (Higher-Order, Currying, Tailrec)")
    demonstrateFunctionsAndMethods()

    printHeader("4. Object-Oriented Programming (OOP) in Scala")
    demonstrateOOP()

    printHeader("5. Functional Collections & Monadic Operations")
    demonstrateCollections()

    printHeader("6. Pattern Matching & Extractor Objects")
    demonstratePatternMatching()

    printHeader("7. Error Handling (Try, Option, Either)")
    demonstrateErrorHandling()

    printHeader("8. Implicits (Parameters, Conversions, Extension Methods)")
    demonstrateImplicits()
  }

  // Helper method to print section headers
  def printHeader(title: String): Unit = {
    println("\n" + "=" * 80)
    println(s"  $title")
    println("=" * 80)
  }

  /**
   * ---------------------------------------------------------------------------------
   * 1. VARIABLES AND CONSTANTS
   * ---------------------------------------------------------------------------------
   * Scala supports three main ways to declare variables/bindings:
   * - `val`: Immutable reference (constant). Cannot be reassigned. Preferred by default.
   * - `var`: Mutable reference. Can be reassigned. Use sparingly.
   * - `lazy val`: Evaluated only once, when first accessed. Useful for lazy initialization.
   */
  def demonstrateVariables(): Unit = {
    // val is immutable
    val immutableValue: String = "Hello"
    // immutableValue = "World" // This would trigger a compilation error!
    println(s"val (immutable): $immutableValue")

    // Type inference: Scala can automatically infer the type of a variable
    val inferredInt = 42 // Scala infers this as Int
    println(s"Inferred Type (Int): $inferredInt")

    // var is mutable
    var mutableVariable = 10
    println(s"var (initial): $mutableVariable")
    mutableVariable += 5
    println(s"var (modified): $mutableVariable")

    // lazy val is only initialized when used
    var initializerCalled = false
    lazy val lazyValue = {
      initializerCalled = true
      "Computed!"
    }
    println(s"Before accessing lazyValue: initializerCalled = $initializerCalled")
    println(s"Accessing lazyValue: $lazyValue")
    println(s"After accessing lazyValue: initializerCalled = $initializerCalled")

    // Code Block expression: The last line of a block is its return value
    val blockResult = {
      val a = 5
      val b = 10
      a + b // return value
    }
    println(s"Code block expression result: $blockResult")
  }

  /**
   * ---------------------------------------------------------------------------------
   * 2. CONTROL STRUCTURES
   * ---------------------------------------------------------------------------------
   * - In Scala, `if-else` is an expression (returns a value).
   * - `for` loops can act as "for comprehensions" to filter and transform collections using `yield`.
   * - `while` loops are standard, returning `Unit` (equivalent to void in Java).
   */
  def demonstrateControlStructures(): Unit = {
    val x = 10

    // If-Else as an Expression (replaces ternary operator in other languages)
    val parity = if (x % 2 == 0) "Even" else "Odd"
    println(s"If-Else expression result: $parity")

    // Simple For Loop (iteration)
    print("Simple for loop: ")
    for (i <- 1 to 5) { // 1 to 5 is inclusive. Use 1 until 5 for exclusive upper bound
      print(s"$i ")
    }
    println()

    // For Comprehension with Filters (guards) and Generators
    // Generates a list of even numbers multiplied by 10
    val numbers = List(1, 2, 3, 4, 5, 6)
    val processedNumbers = for {
      num <- numbers        // Generator
      if num % 2 == 0       // Filter (guard)
    } yield num * 10        // Transformation yielding a new collection

    println(s"Original List: $numbers")
    println(s"For-yield result (even * 10): $processedNumbers")
  }

  /**
   * ---------------------------------------------------------------------------------
   * 3. FUNCTIONS AND METHODS
   * ---------------------------------------------------------------------------------
   * Scala integrates Object-Oriented and Functional Programming.
   * - Methods are defined with `def`.
   * - Functions are first-class values (can be stored, passed as arguments, or returned).
   * - Supports currying, tail recursion, named/default parameters.
   */
  def demonstrateFunctionsAndMethods(): Unit = {
    
    // Method with default parameter values
    def greet(name: String, prefix: String = "Mr/Ms"): String = s"Hello, $prefix $name"
    println(s"Default parameter: ${greet("Ninad")}")
    println(s"Overridden parameter: ${greet("Ninad", "Dr.")}")
    println(s"Named arguments: ${greet(prefix = "Prof.", name = "Ninad")}")

    // Higher-Order Function (takes a function as parameter)
    // formatInt takes an Int and a converter function (Int => String)
    def formatInt(n: Int, formatter: Int => String): String = {
      formatter(n)
    }

    val binaryFormatter: Int => String = (i: Int) => i.toBinaryString
    val hexFormatter: Int => String = (i: Int) => s"0x${i.toHexString}"

    println(s"Higher-order function (binary): ${formatInt(255, binaryFormatter)}")
    println(s"Higher-order function (hex): ${formatInt(255, hexFormatter)}")
    // Anonymous inline lambda function
    println(s"Higher-order function (inline lambda): ${formatInt(255, x => s"Value is $x")}")

    // Currying (methods with multiple parameter lists)
    // Currying facilitates partial application and type inference
    def multiply(x: Int)(y: Int): Int = x * y
    val doubleValue = multiply(2) _ // Partially applied function (returns Int => Int)
    println(s"Curried function doubleValue(5): ${doubleValue(5)}")
    println(s"Direct curried call multiply(3)(4): ${multiply(3)(4)}")

    // Tail Recursion: Prevents stack overflow by reusing stack frames
    // The compiler enforces this optimization when using `@tailrec`
    @tailrec
    def factorial(n: Int, accumulator: BigInt = 1): BigInt = {
      if (n <= 1) accumulator
      else factorial(n - 1, accumulator * n) // recursive call is in tail position
    }
    println(s"Tail recursive factorial(5): ${factorial(5)}")
  }

  /**
   * ---------------------------------------------------------------------------------
   * 4. OBJECT-ORIENTED PROGRAMMING (OOP)
   * ---------------------------------------------------------------------------------
   * - Traits: Interface-like structures that can contain abstract and concrete methods.
   *   Classes can extend multiple traits (Mixins).
   * - Companion Objects: Objects with the same name as a class in the same file.
   *   Used for static-like methods, factory constructors (`apply`), and shared state.
   * - Case Classes: Immutable data containers with automatically generated boilerplate 
   *   (equals, hashCode, toString, copy, apply companion method).
   */
  def demonstrateOOP(): Unit = {
    // Create class instance
    val person = new RegularPerson("Alice", 30)
    person.greet()
    println(s"Inherited trait property: ${person.category}")

    // Companion Object Demo
    // Notice we do NOT use 'new' here because we call the Companion's apply method
    val bob = PersonData("Bob", 25)
    println(s"Companion-created Case Class representation: $bob")
    
    // Copy constructor (easy mutation by copying with overrides)
    val olderBob = bob.copy(age = 26)
    println(s"Copied Case Class: $olderBob")
    println(s"Case classes equal check: ${bob == olderBob}") // returns false

    // Demonstration of mixin traits
    val developer = new Developer("Carol")
    developer.greet()
    developer.writeCode()
  }

  // Trait defining a contract and default implementation
  trait Greeter {
    def name: String
    def greet(): Unit = println(s"Hi, I am $name.")
    val category: String = "Human"
  }

  // Another trait to showcase multiple inheritance (mixins)
  trait Coder {
    def writeCode(): Unit = println("I write Scala code!")
  }

  // Standard class extending a trait
  class RegularPerson(val name: String, var age: Int) extends Greeter

  // Class mixing in multiple traits
  class Developer(val name: String) extends Greeter with Coder

  // Case Class: designed for immutable data storage
  case class PersonData(name: String, age: Int)

  // Companion Object for PersonData (if we wanted to add custom factory logic)
  object PersonData {
    // Factory method 'apply' lets us instantiate without 'new'
    def apply(name: String): PersonData = new PersonData(name, 0)
  }

  /**
   * ---------------------------------------------------------------------------------
   * 5. COLLECTIONS AND FUNCTIONAL OPERATIONS
   * ---------------------------------------------------------------------------------
   * Scala offers immutable collections by default. They can be manipulated 
   * using rich monadic/combinator methods:
   * - `map`: Transforms each element of the collection.
   * - `filter`: Retains elements satisfying a boolean condition.
   * - `flatMap`: Transforms elements into collections, then flattens the result.
   * - `foldLeft` / `reduce`: Aggregates elements using a binary operator.
   */
  def demonstrateCollections(): Unit = {
    val numbers = List(1, 2, 3, 4, 5)

    // Map operation
    val squares = numbers.map(x => x * x)
    println(s"Original list: $numbers")
    println(s"Mapped list (squares): $squares")

    // Filter operation
    val odds = numbers.filter(_ % 2 != 0) // '_' represents the current element placeholder
    println(s"Filtered list (odds): $odds")

    // FlatMap operation: maps each element to a List, then flattens all of them into a single List
    val duplicated = numbers.flatMap(x => List(x, x * 10))
    println(s"FlatMapped list (duplicated & scaled): $duplicated")

    // foldLeft: fold from left to right with an initial accumulator value
    // Signature: foldLeft(initial)( (accumulator, element) => result )
    val sum = numbers.foldLeft(0)((acc, elem) => acc + elem)
    println(s"FoldLeft sum: $sum")

    // reduce: aggregates without an initial accumulator (crashes if list is empty)
    val product = numbers.reduce((acc, elem) => acc * elem)
    println(s"Reduced product: $product")

    // Maps (Key-Value pairs)
    val userAges = Map("Alice" -> 30, "Bob" -> 25)
    println(s"Map content: $userAges")
    // Safe lookup: returns Option (Some/None)
    println(s"Lookup safe (Alice): ${userAges.get("Alice")}")
    println(s"Lookup safe (Charlie): ${userAges.get("Charlie")}")
  }

  /**
   * ---------------------------------------------------------------------------------
   * 6. PATTERN MATCHING & EXTRACTOR OBJECTS
   * ---------------------------------------------------------------------------------
   * Pattern matching is Scala's powerful switch-on-steroids mechanism. It can match
   * values, types, case classes, and support guards (if statements).
   * Extractor objects implement `unapply` to decompose items.
   */
  def demonstratePatternMatching(): Unit = {
    // 1. Matching Values
    def matchValue(x: Any): String = x match {
      case 1          => "The integer one"
      case "Scala"    => "A programming language"
      case true       => "Boolean True"
      case _          => "Something else (default wildcard)"
    }
    println(s"Value match (1): ${matchValue(1)}")
    println(s"Value match ('Scala'): ${matchValue("Scala")}")

    // 2. Matching Case Classes & Type Checks with Guards
    abstract class Notification
    case class Email(sender: String, title: String, body: String) extends Notification
    case class SMS(caller: String, message: String) extends Notification
    case class VoiceCall(caller: String) extends Notification

    def processNotification(notification: Notification): String = notification match {
      case Email(sender, title, _) if sender.endsWith("@company.com") => 
        s"Corporate Email received from $sender: '$title'"
      case Email(sender, title, _) => 
        s"Personal Email received from $sender: '$title'"
      case SMS(caller, message) => 
        s"SMS from $caller: '$message'"
      case VoiceCall(caller) => 
        s"Voice call request from $caller"
    }

    val corpEmail = Email("boss@company.com", "Important Meeting", "Please attend")
    val spamEmail = Email("spammer@ads.com", "Buy products", "Cheap items")
    val sms = SMS("123-456", "Hey! Where are you?")

    println(processNotification(corpEmail))
    println(processNotification(spamEmail))
    println(processNotification(sms))

    // 3. Extractor Objects (unapply method)
    // Allows decomposing instances of classes that aren't case classes
    class CustomUser(val name: String, val domain: String)
    
    object CustomUser {
      // Extractor returns Option of fields to match against
      def unapply(user: CustomUser): Option[(String, String)] = {
        Some((user.name, user.domain))
      }
    }

    val userObj = new CustomUser("admin", "github.com")
    val extractionResult = userObj match {
      case CustomUser("admin", dom) => s"Admin user matched on domain: $dom"
      case CustomUser(name, dom)   => s"Regular user $name at $dom"
    }
    println(s"Extractor (unapply) match result: $extractionResult")
  }

  /**
   * ---------------------------------------------------------------------------------
   * 7. ERROR HANDLING
   * ---------------------------------------------------------------------------------
   * Scala avoids throwing raw exceptions in FP code by using container types:
   * - `Option[A]`: Either `Some(value)` or `None`. Replaces null checks.
   * - `Either[A, B]`: Either `Left(errorValue)` or `Right(successValue)`.
   * - `Try[A]`: Either `Success(value)` or `Failure(exception)`. Evaluates expressions.
   */
  def demonstrateErrorHandling(): Unit = {
    // 1. Option (Some / None)
    def divide(a: Int, b: Int): Option[Double] = {
      if (b == 0) None else Some(a.toDouble / b)
    }

    println(s"Safe divide 10/2: ${divide(10, 2)}")
    println(s"Safe divide 10/0: ${divide(10, 0)}")

    // Retrieve values safely using getOrElse or map/flatMap
    val result = divide(10, 0).getOrElse(-1.0)
    println(s"getOrElse fallback for 10/0: $result")

    // 2. Either (Left / Right) - Right is correct/success by convention, Left is error/failure
    def safeParseInt(str: String): Either[String, Int] = {
      try {
        Right(str.toInt)
      } catch {
        case _: NumberFormatException => Left(s"'$str' could not be parsed to an Int")
      }
    }

    println(s"Either parse '123': ${safeParseInt("123")}")
    println(s"Either parse 'abc': ${safeParseInt("abc")}")

    // Processing Either using pattern matching
    safeParseInt("abc") match {
      case Right(value) => println(s"Success! Parsed value: $value")
      case Left(error)  => println(s"Error occurred: $error")
    }

    // 3. Try (Success / Failure) - captures exception object
    val divisionTry: Try[Int] = Try(10 / 0)
    divisionTry match {
      case Success(value)     => println(s"Try Succeeded: $value")
      case Failure(exception) => println(s"Try Failed with Exception: ${exception.getMessage}")
    }
  }

  /**
   * ---------------------------------------------------------------------------------
   * 8. IMPLICITS
   * ---------------------------------------------------------------------------------
   * Implicits allow the compiler to inject values, arguments, or class wraps:
   * - Implicit Parameters: Automatically injected if an implicit value of the
   *   matching type exists in the scope.
   * - Implicit Classes: Wrap an existing class to extend its behavior with new methods
   *   (often called "extension methods" in other languages).
   */
  def demonstrateImplicits(): Unit = {
    // 1. Implicit Parameter
    // We define a configuration or execution context as implicit
    implicit val defaultGreetingPrefix: String = "Dear"

    // Method that accepts an implicit parameter list
    def greetWithImplicit(name: String)(implicit prefix: String): Unit = {
      println(s"Greeting with implicit prefix: $prefix $name")
    }

    // Call without providing the second parameter list. 
    // The compiler automatically injects `defaultGreetingPrefix` from scope.
    greetWithImplicit("Ninad")

    // We can also pass it explicitly, overriding the implicit behavior
    greetWithImplicit("Ninad")("Hello")

    // 2. Implicit Class (Extension Method)
    // Adds a custom method `shout` to the existing library class String.
    // The implicit class must be defined inside a wrapping object/class (which Main is).
    implicit class StringShouter(val s: String) {
      def shout: String = s.toUpperCase + "!!!"
    }

    // Using the extension method: "hello" is automatically wrapped in StringShouter by the compiler
    val baseString = "hello scala world"
    println(s"Implicit class conversion: ${baseString.shout}")
  }
}
