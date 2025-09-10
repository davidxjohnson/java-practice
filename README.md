# Java Practice Mono-Repo

This repository contains a multi-module Java project managed with Maven.

## Structure
- `module-a` — Example Java module
- `module-b` — Example Java module

## Build All Modules
```bash
mvn clean install
```

## Run Example App
```bash
mvn -pl module-a exec:java -Dexec.mainClass="com.example.App"
mvn -pl module-b exec:java -Dexec.mainClass="com.example.App"
```

## Requirements
- JDK 17 or newer
- Maven
- VS Code (recommended) with Java extensions
