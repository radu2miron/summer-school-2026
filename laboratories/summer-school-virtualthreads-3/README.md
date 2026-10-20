# summer-school-virtualthreads

## Exercise 1

Test the applications from the course repository — Lecture 5.

## Exercise 2

Parallel processing of text files from a directory

#### Requirements

Implement a Java application that performs the following tasks:

1. Read two command-line arguments:

* The path to a directory containing text files (`.txt`);
* The number of threads, N.

2. Create an `ExecutorService` with N threads.

3. For each text file (`.txt`) in the directory, submit a `Callable<FileStats>` to the executor.

The `Callable<FileStats>` instance will perform the following operations:

* Read the file;
* Count the lines, words, and characters;
* Return a `FileStats` object with the properties `{ String fullPath; int lines; int words; int chars; }`.

4. Collect all objects from `Future<FileStats>` into a list.

5. All obtained results will be written to a single report file (`report.txt`).

#### Suggestions

* Use `File.listFiles()` to obtain the list of files from the directory. Filter text files by the `.txt` extension.

## Exercise 3

Parallel generation and verification of QR codes from a URL file using `CompletableFuture`

#### Requirements

Implement a Java application that reads multiple URLs from a file.

For each URL read from the file, the application generates the QR code in parallel through a `CompletableFuture` created with `supplyAsync()`, then initiates QR decoding and verification using `thenApply()`, finally reporting the status (`OK` or `ERROR`) and the processing times.

#### Suggestions

* The ZXing library is used for generating and decoding QR codes.
* QR code generation and reading are implemented in the `edu.tucn.ss.lab3.ex3.QrUtils` class.

## Exercise 4

HTTP checker with virtual threads (Java 21+)

#### Requirements

Write a “Java Virtual HTTP Checker” application that:

1. Receives two input arguments:

* `urls.txt`: a text file containing URLs, one URL per line;
* `timeoutSec`: the timeout, in seconds, for each HTTP request.

2. Creates an `ExecutorService` based on virtual threads.

3. For each URL read from the file:

* Submit a `Callable<UrlResult>` to the executor, where `UrlResult` contains `{ String url; int status; long timeMs; String error; }`.
* In `call()`:

  * Use `HttpRequest`/`HttpResponse` with `HttpClient` to perform the HTTP request;
  * Measure the response time;
  * Return the `UrlResult` object.

4. Collect all `Future<UrlResult>` objects into a list, wait for the results using `future.get()`, and then write them to the console.

## Exercise 5

Consider the list of free APIs, or another public API:

https://github.com/public-apis/public-apis?tab=readme-ov-file#index

#### Requirements

Implement a Java application similar to applications 3 and 4 from the Course 5 repository.

The application will perform several calls in parallel and display the results in a simple graphical user interface using Swing.
