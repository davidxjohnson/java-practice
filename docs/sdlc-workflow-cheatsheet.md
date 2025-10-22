# SDLC Workflow Cheat Sheet

A comprehensive guide to Software Development Life Cycle (SDLC) workflows with Maven, Git, and GitHub CLI commands for Java projects.

## Table of Contents
1. [Development Workflow Overview](#development-workflow-overview)
2. [Project Setup & Initialization](#project-setup--initialization)
3. [Feature Development Workflow](#feature-development-workflow)
4. [Build & Test Commands](#build--test-commands)
5. [Code Quality & Analysis](#code-quality--analysis)
6. [Release Management](#release-management)
7. [Collaboration & Code Review](#collaboration--code-review)
8. [Troubleshooting & Maintenance](#troubleshooting--maintenance)

## Development Workflow Overview

### Typical SDLC Phases
1. **Planning** → Issue creation, sprint planning
2. **Development** → Feature branch creation, coding, testing
3. **Integration** → Code review, merge to main branch
4. **Testing** → Automated testing, manual QA
5. **Deployment** → Release preparation, deployment
6. **Monitoring** → Post-deployment monitoring, bug fixes

## Project Setup & Initialization

### Initial Project Setup
```bash
# Clone repository
git clone https://github.com/davidxjohnson/java-practice.git
cd java-practice

# Verify Java and Maven versions
java --version
mvn --version

# Install dependencies
mvn clean install
```

### Multi-Module Maven Project Setup
```bash
# Create new module
mvn archetype:generate -DgroupId=com.example \
    -DartifactId=new-module \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DinteractiveMode=false

# Add module to parent pom.xml
# <modules>
#   <module>new-module</module>
# </modules>
```

## Feature Development Workflow

### 1. Start New Feature
```bash
# Update local main branch
git checkout main
git pull origin main

# Create and switch to feature branch
git checkout -b feature/your-feature-name

# Or use GitHub CLI to create issue-linked branch
gh issue create --title "Feature: Your Feature" --body "Description"
gh issue develop ISSUE_NUMBER --checkout
```

### 2. Development Cycle
```bash
# Check current status
git status
git branch -v

# Stage and commit changes
git add .
git commit -m "feat: implement feature functionality"

# Or commit specific files
git add src/main/java/com/example/NewClass.java
git commit -m "feat: add NewClass with core logic"

# Push feature branch
git push -u origin feature/your-feature-name
```

### 3. Continuous Integration During Development
```bash
# Build and test frequently
mvn clean compile
mvn test
mvn clean install

# Run specific module
mvn -pl module-a clean test
mvn -pl module-b clean install

# Quick compilation check
mvn compile exec:java -Dexec.mainClass="com.example.App"
```

## Build & Test Commands

### Maven Build Lifecycle
```bash
# Clean previous builds
mvn clean

# Compile source code
mvn compile

# Run unit tests
mvn test

# Run tests for specific module
mvn -pl module-a test
mvn -pl maxConcurant test

# Package (create JAR/WAR)
mvn package

# Install to local repository
mvn install

# Full clean build
mvn clean install

# Skip tests (use sparingly)
mvn clean install -DskipTests

# Run with specific profiles
mvn clean install -Pdev
mvn clean install -Pprod
```

### Running Applications
```bash
# Run main class directly
mvn -pl module-a exec:java -Dexec.mainClass="com.example.App"
mvn -pl module-b exec:java -Dexec.mainClass="com.example.App"

# Run with arguments
mvn exec:java -Dexec.mainClass="com.example.App" -Dexec.args="arg1 arg2"

# Run JAR file
java -jar target/your-app-1.0-SNAPSHOT.jar
```

## Code Quality & Analysis

### Static Analysis & Code Quality
```bash
# Generate site documentation
mvn site

# Run SpotBugs (if configured)
mvn spotbugs:check

# Run Checkstyle (if configured)
mvn checkstyle:check

# Run PMD (if configured)
mvn pmd:check

# Generate dependency tree
mvn dependency:tree

# Check for dependency updates
mvn versions:display-dependency-updates
mvn versions:display-plugin-updates
```

### Testing Strategies
```bash
# Run only unit tests
mvn test

# Run integration tests (if configured)
mvn integration-test

# Run tests with coverage (if JaCoCo configured)
mvn clean test jacoco:report

# Run performance tests
mvn test -Dtest=PerformanceTest

# Debug tests
mvn test -Dmaven.surefire.debug
```

## Release Management

### Version Management
```bash
# Check current version
mvn help:evaluate -Dexpression=project.version -q -DforceStdout

# Update version
mvn versions:set -DnewVersion=1.1.0-SNAPSHOT
mvn versions:commit

# Prepare release
mvn release:prepare
mvn release:perform

# Create release tag
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

### GitHub Releases
```bash
# Create release with GitHub CLI
gh release create v1.0.0 \
    --title "Release v1.0.0" \
    --notes "Release notes here" \
    --target main

# Upload artifacts to release
gh release upload v1.0.0 target/*.jar

# List releases
gh release list

# View specific release
gh release view v1.0.0
```

## Collaboration & Code Review

### Pull Request Workflow
```bash
# Create pull request
gh pr create \
    --title "feat: implement new feature" \
    --body "Description of changes" \
    --base main \
    --head feature/your-feature-name

# List pull requests
gh pr list
gh pr list --state=open --assignee=@me

# Review pull request
gh pr checkout 123
gh pr review 123 --approve
gh pr review 123 --request-changes --body "Please fix XYZ"

# Merge pull request
gh pr merge 123 --squash
gh pr merge 123 --rebase
gh pr merge 123 --merge
```

### Code Review Commands
```bash
# View differences
git diff HEAD~1
git diff main..feature/your-feature-name

# Interactive rebase for cleanup
git rebase -i main

# Squash commits before merge
git reset --soft HEAD~3
git commit -m "feat: consolidated feature implementation"

# Stash changes temporarily
git stash
git stash pop
git stash list
git stash apply stash@{0}
```

## Troubleshooting & Maintenance

### Git Troubleshooting
```bash
# Reset to last commit (careful!)
git reset --hard HEAD

# Undo last commit (keep changes)
git reset --soft HEAD~1

# Fix merge conflicts
git status
# Edit conflicted files
git add .
git commit

# Clean untracked files
git clean -fd

# Recover deleted branch
git reflog
git checkout -b recovered-branch COMMIT_HASH
```

### Maven Troubleshooting
```bash
# Clean up Maven cache
mvn dependency:purge-local-repository

# Force update dependencies
mvn clean install -U

# Debug Maven execution
mvn -X clean install

# Skip problematic tests temporarily
mvn test -Dtest=!ProblematicTest

# Offline mode
mvn clean install -o
```

### Common Issues & Solutions

#### Maven Build Failures
```bash
# Clear target directories
find . -name target -type d -exec rm -rf {} +
mvn clean install

# Update Maven wrapper
mvn wrapper:wrapper -Dmaven=3.9.0
```

#### Git Workflow Issues
```bash
# Sync fork with upstream
git remote add upstream https://github.com/original/repo.git
git fetch upstream
git checkout main
git merge upstream/main

# Change commit author
git commit --amend --author="New Author <email@example.com>"

# Cherry-pick specific commit
git cherry-pick COMMIT_HASH
```

## Best Practices Summary

### Commit Message Conventions
```
feat: add new feature
fix: bug fix
docs: documentation changes
style: formatting changes
refactor: code refactoring
test: adding tests
chore: maintenance tasks
```

### Branch Naming Conventions
```
feature/feature-name
bugfix/bug-description
hotfix/critical-fix
release/version-number
```

### Maven Best Practices
- Always run `mvn clean install` before commits
- Use dependency management in parent POM
- Keep module dependencies minimal
- Run tests in specific modules: `mvn -pl module-name test`
- Use profiles for environment-specific configurations

### Git Best Practices
- Commit frequently with meaningful messages
- Keep feature branches small and focused
- Rebase before merging to maintain clean history
- Use pull requests for all changes to main branch
- Tag releases consistently

---

*Last Updated: October 2025*
*Project: java-practice multi-module Maven project*