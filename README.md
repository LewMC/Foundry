![Foundry Banner](https://cdn.modrinth.com/data/cached_images/53f7d3d07af13bc8b841f1c7c6c5530fa4b5088b_0.webp)

[Maven Repository](https://repo.lewmc.net) - [Documentation](https://wiki.lewmc.net/foundry.html) - [JavaDocs](https://lewmc.github.io/foundry) - [Code Analysis](https://sonarcloud.io/project/overview?id=LewMC_Foundry)

# Contributing
We welcome contributions from the community. Please fork the repository, make your changes, and submit a pull request.

Please read [our contributor guide](CONTRIBUTING.md) before submitting any changes, thank you!

Please merge any changes into the `next-update` branch, not the `main` branch.
This helps us to ensure that our snapshot builds are labelled as snapshot so that it is clear to users download them that they are still in development, and that any changes being made will work with future versions of Foundry.

## Build Process

Install JDK 21 before continuing. Click [here](https://docs.oracle.com/en/java/javase/21/install/index.html) for documentation.

- You will also need Maven for the `mvn` command, which can be installed [here](https://maven.apache.org/download.cgi).
- Make sure that your version of JDK 21 includes JavaDoc.
    - For example, Eclipse Temurin JDK with Hotspot 21 includes this executable.

```sh
# Clone the repository and move into it.
git clone https://github.com/lewmc/foundry && cd foundry

# Perform a clean build (optional if you're rebuilding).
mvn clean package -Dmaven.test.skip=true

# Build the package with an explicit version target of 21.
mvn -B package --file pom.xml -Dmaven.compiler.source=21 -Dmaven.compiler.target=21
```

# Licensing

Foundry is licensed under the Apache License 2.0. See [LICENSE](LICENSE) for more information.
