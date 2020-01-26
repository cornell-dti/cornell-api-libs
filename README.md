# Cornell API Client Library

[![Build Status](https://github.com/cornell-dti/cornell-api-libs/workflows/CI/badge.svg)](https://github.com/cornell-dti/cornell-api-libs/actions)

Kotlin-friendly Cornell API Client Library.

We would thank [Cornell Open Data Initiative](https://github.com/cornell-data)'s
documentation for APIs.

## Example Usage

### Library Usage

```kotlin
fun main() {
    runBlocking { ClassesApiClient.getRosters().forEach { println(it) } }
    runBlocking { ClassesApiClient.getAllCourses() }?.let { println(it) }
}
```

For more example, you can see some code snippets in tests.

### CLI Usage

```bash
# Print all courses to a json file for later usage.
java -jar cornell-api-libs.jar print-all-courses > courses.json
# It will print progress messages along the way.
```

```bash
# Print all courses in FA19 to a json file for later usage.
java -jar cornell-api-libs.jar print-all-courses-in FA19 > courses.json
# It will print progress messages along the way.
```
