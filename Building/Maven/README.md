## Maven
It is a build automation and project management tool primarily used for Java projects. 
It simplifies the build process, dependency management, and project configuration by using a standardized XML configuration file (pom.xml), ensuring consistency and facilitating easy integration with various development tools and environments.

### Why pom.xml ? 
The pom.xml (Project Object Model) file is a fundamental component of Maven, which is a widely-used build automation and project management tool for Java-based projects. 

Maven was introduced by Jason van Zyl and others at the Apache Software Foundation in 2002 to address these limitations. 
Maven aimed to provide:
1. Convention over Configuration: Standardized project structure and lifecycle, reducing the need for extensive configuration.
2. Simplified Build Process: A unified and consistent way to manage builds across projects.
3. Dependency Management: An integrated dependency management system, leveraging repositories to resolve and manage project dependencies.

### Handy Maven Commands

- **mvn dependency:tree** - Prints the dependency tree for your project. This is particularly useful for identifying dependency conflicts and understanding the transitive dependencies brought in by your direct dependencies.
- **mvn dependency:resolve** - Displays the resolved dependencies of your project, including versions and scopes. This can help you see the exact versions of dependencies Maven is using.
- **mvn dependency:analyze** - analyzes the dependencies of your project and reports on unused declared dependencies and used undeclared dependencies. This helps to clean up your pom.xml.
- **mvn dependency:analyze-duplicate** - This goal checks for duplicate dependencies in your project. This can help identify where you might have declared the same dependency multiple times with different versions.


### Sample Project Structure
```
maven-based-app
|-- pom.xml
`-- src
    |-- main
    |   `-- java
    |       `-- com
    |           `-- ninad
    |               `-- app
    |                   `-- MavenApp.java
    `-- test
        `-- java
            `-- com
                `-- ninad
                    `-- app
                        `-- MavenApp.java

```




## Links:
[Apache Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
