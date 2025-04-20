# Java Microbenchmarks

The motivation and structure of this repository is almost identical to my Node.js equivalent. Read the [README of Node.js Microbenchmarks](https://gitlab.com/SethFalco/nodejs-microbenchmarks) for context.

## Running Benchmarks

To see a list of microbenchmarks available, either:

* Look in `src/jmh/fun/falco/microbenchmarks/`.
* Or execute `./gradlew clean jmh` on its own, which will list them for you.


Run the relevant benchmark with Gradle:

```sh
./gradlew clean jmh -P benchmark=CssToJavaColor
```
