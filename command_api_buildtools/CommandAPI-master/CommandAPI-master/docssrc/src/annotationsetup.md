# Using the annotation system

The annotation system is a separate part of the CommandAPI, and as a result it needs to be included as an additional dependency to your project. 

The annotation system effectively needs to be added twice: Once for compilation and again to invoke the annotation processor itself.

-----

## Using Maven

- If you haven't already done so, add the maven repository to your `pom.xml` file:

  ```xml
  <repositories>
      <repository>
          <id>jitpack.io</id>
          <url>https://jitpack.io</url>
      </repository>
  </repositories>
  ```

- Add the annotation dependency to your `pom.xml`:

  ```xml
  <dependencies>
      <dependency>
          <groupId>dev.jorel.CommandAPI</groupId>
          <artifactId>commandapi-annotations</artifactId>
          <version>6.5.3</version>
          <scope>provided</scope>
      </dependency>
  </dependencies>
  ```

- Add the annotation processor as an annotation process to the compile task in the `pom.xml`:

  ```xml
  <build>
      <plugins>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-compiler-plugin</artifactId>
              <version>3.8.1</version>
              <configuration>
                  <annotationProcessorPaths>
                      <path>
                          <groupId>dev.jorel.CommandAPI</groupId>
                          <artifactId>commandapi-annotations</artifactId>
                          <version>6.5.3</version>
                      </path>
                  </annotationProcessorPaths>
              </configuration>
        </plugin>
      </plugins>
  </build>
  ```

-----

## Using Gradle

- If you haven't already done so, add the maven repository to your `build.gradle` file:

  ```gradle
  repositories {
      maven { url = "https://jitpack.io" }
      maven { url = "https://repo.codemc.org/repository/maven-public/" }
  }
  ```

- Add the dependency and annotation processor to your list of dependencies in your `build.gradle` file:

  ```gradle
  dependencies {
      compileOnly "dev.jorel.CommandAPI:commandapi-annotations:6.5.3"
      annotationProcessor "dev.jorel.CommandAPI:commandapi-annotations:6.5.3"
  }
  ```

  