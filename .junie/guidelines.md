# M2Utility Project Guidelines

## Project Overview

M2Utility is a utility library designed to be used across a wide variety of projects. It provides flexible, extensible utilities that support a broad range of use cases. The project is structured as a multi-module Kotlin library with cross-platform (KMP) support.

## Code Style

### Kotlin Style Guide

We follow the [official Kotlin style guide](https://kotlinlang.org/docs/coding-conventions.html) with the following additional requirements:

- Use 4 spaces for indentation
- Maximum line length is 120 characters
- Use trailing commas in multi-line lists/arrays/parameters
- Prefer named parameters for improved readability for functions with multiple parameters, especially when parameters share the same type
- Always prefer explicit null handling; avoid `!!` (not null asserts) unless absolutely unavoidable
- Use `require`, `requireNotNull`, `check`, `checkNotNull`, and `error` functions to enforce invariants early, and document these checks in KDoc comments

### Documentation

#### KDoc Comments

- All public APIs must have KDoc comments
- Use proper markdown formatting in KDoc
- Include code examples where appropriate using markdown code blocks
- Document all parameters, return values, and exceptions

#### KDoc Tags

- `@param` - Document all parameters with their purpose and constraints
- `@property` - Document properties in classes, especially in data classes
- `@return` - Document return values (not needed for functions returning `Unit`)
- `@throws` - Document exceptions that the function explicitly throws
- `@constructor` - Document class constructors when they have specific behavior
- `@receiver` - Document extension function receivers to explain context
- `@sample` - Use to reference sample code when available
- `@deprecated` - Not supported by KDoc, use the `@Deprecated` annotation instead

#### Markdown Style

- Markdown headers should have a blank line above and below them
- For Markdown headers use proper Title Casing (APA style)
- For strong emphasis (bold) use `__text__`
- For weak emphasis (italic) use `*text*`
- Lists, code blocks, and quotes should have a blank line above and below them (except when they are part of a list)
- Images should have a proper alt text and where possible a light and dark version as supported by GitHub
- The Markdown dialect for md files is the GitHub flavor and for KDoc its the base Markdown dialect as supported by KDoc
- Code blocks should always have a language specified where possible
- For code references in Markdown either use a code block, inline code formatting (backticks), or for KDoc a KDoc style reference (example: `uses [SomeClass] to do things`)

#### Deprecation

For deprecated elements:

- Add `@Deprecated` annotation with appropriate deprecation level and message
- Include `replaceWith` parameter when possible to guide users to the new API
- Document the reason for deprecation and migration path in KDoc

Example:

```kotlin
/**
 * This method performs an operation that is now deprecated.
 */
@Deprecated(
    message = "Use newMethod() instead which provides better performance",
    replaceWith = ReplaceWith("newMethod()"),
    level = DeprecationLevel.WARNING
)
fun oldMethod() {
    // Implementation
    newMethod() // Delegate to new method when possible
}
```

### Changelog

- Follow the [Keep a Changelog](https://keepachangelog.com/) format
- Group changes under: Added, Changed, Deprecated, Removed, Fixed, Security, or Dependencies
- Version numbers are a single number incremented by 1 for each release
- Older releases are not maintained for this internal project
- For new releases the current date should be included in the header
- Change entries should be prefix with the correct module like `- __test:__ Added some function`
- For breaking changes a `(Breaking)` should be suffixed to the change module prefix like `- __test (Breaking): Changed parameters of some function`

### Module Documentation

- Each module should have a comprehensive README.md
- README files should be properly formatted for Dokka to generate documentation HTML for GitHub Pages
- Include module purpose, main components, usage examples, and integration instructions for larger systems/functionality only

## Code Quality

### Static Analysis

All code must pass:

- Android lint checks
- Detekt analysis (configuration in detekt-config.yml)
- SonarQube analysis

### Testing Requirements

- All new code should have appropriate unit tests
- Follow Behavior-Driven Testing principles with Given-When-Then structure
- Test coverage should be maintained or improved with new code
- Testing shared code should be prioritized over platform-specific tests where possible
- Where relevant use a @ParameterizedTest
- Bug fixes should have tests for them where possible

### Testing Stack

- JUnit 5 for test framework
- MockK for mocking
- EasyRandom for test data generation
- Turbine for testing Flow
- Custom utilities from test and test-android modules

## Design Principles

- **Flexibility**: Code should be adaptable to different use cases
- **Extensibility**: Design for extension through inheritance or composition
- **Reusability**: Components should be reusable across different projects
- **Maintainability**: Code should be easy to understand and maintain
- **Performance**: Consider performance implications, especially for utility functions

## API Design and Maintenance

### Public API Guidelines

- For any public-facing API (including internal expect/actual in KMP), changes should:
    - Avoid breaking changes unless justified and properly documented
    - Consider using deprecation cycles (WARNING → ERROR → removal over releases)
- For expect/actual declarations make sure to document platform specific implementations clearly. Also avoid leaking platform-specific concepts into common code
- When APIs are only available on some targets explicitly mention it in KDoc

### Coroutines

- Use structured concurrency. Avoid GlobalScope (as per coroutine best practices)
- Document coroutine context switches or expectations clearly in the KDocs
- Prefer suspend functions and flows over callbacks

## Versioning and Maintenance

- Version numbers are incremented by 1 for each release
- Only the latest version is maintained
- Breaking changes should be clearly documented in the changelog

## Dependencies Management

### Dependency Guidelines

- New dependencies should only be added when needed and only exposed using API in very rare exceptions
- Use Kotlin stdlib and kotlinx libraries over alternatives where feasible
- Dependencies that impact public API require extra scrutiny

### Gradle Version Catalogue

- New dependencies should be added using the Gradle version catalogue system
- Use the standard `gradle/libs.versions.toml` file for version declarations
- Follow the established pattern for declaring library versions and dependencies
