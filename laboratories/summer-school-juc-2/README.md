# summer-school-juc
Exercises for java.util.concurrent synchronization mechanisms

## Exercise 1

#### Description

Simulate a minimal banking application in which multiple threads perform credit and debit operations on account balances.

#### Requirements

Initialize a `ConcurrentHashMap<Integer, Double>` with 10 accounts, where the key is the client ID and the value is the initial balance of `1000.0`.

Start 5 threads that perform a total of 1,000 operations, randomly choosing between credit and debit operations with values between 1 and 100.

Each operation must update the balance atomically.

At the end, verify and display the total sum of all balances.

#### Make sure that:

Correctness is ensured: the total sum should be `10,000.0`, with differences only caused by debit operations.

`ConcurrentHashMap` and the `compute()` or `merge()` method are used.

The negative balance case is handled optionally.

## Exercise 2

#### Description

Simulate a game in which multiple threads pass a ball between two thread-safe containers.

#### Requirements

Use two `BlockingQueue<Ball>` instances, each with capacity 1, between players A and B.

Each thread A sends a “ball” — a simple object — to B and then waits to receive the ball back.

Repeat this 100 times and measure the execution time.

#### Make sure that:

The correct blocking behavior using `take()` and `put()` works properly.

Deadlock is avoided.

## Exercise 3

#### Description

Consider a parking lot with N spaces. Multiple cars arrive and leave simultaneously, but no more than N spaces can be occupied at the same time.

Manage this using a `Semaphore`.

#### Requirements

Define the `ParkingLot` class with a `Semaphore` attribute named `slots`, initialized with N permits, for example `N = 5`.

The `Car` class implements `Runnable`:

* At startup, it attempts to call `slots.acquire()`.
* If it obtains the permit, it displays “Car-ID X has parked” and remains parked for a random amount of time, between 2 and 4 seconds.
* Then it releases the permit using `slots.release()` and displays “Car-ID X has left”.

In `main`, start 10 `Car` threads, with IDs from 1 to 10, and wait for them to finish.

Use console messages to verify that no more than 5 cars are parked at the same time.

#### Extensions

Implement a waiting queue system: if all spaces are occupied, the car remains blocked, implicitly through `acquire()`, and then displays “Car-ID X is waiting”.

Add a timer: after 5 seconds of waiting, the car gives up and leaves.

## Exercise 4

Synchronization of work phases using `CyclicBarrier`

#### Description

Simulate a competition with 3 stages that a group of 4 competitors complete in parallel. Each stage involves some work, simulated by a random sleep between 500 and 1500 ms, after which all competitors must wait until everyone else has finished the respective stage before moving on to the next one.

#### Mandatory requirements

Create a `CyclicBarrier barrier = new CyclicBarrier(4, () -> System.out.println("Everyone has finished the current stage\n"));`

The `Competitor` class implements `Runnable`:

* For each of the 3 stages:
* Display “Competitor-ID X starts stage Y”.
* Simulate “working” using `Thread.sleep(...)`.
* Display “Competitor-ID X has finished stage Y”.
* Call `barrier.await()`.

In `main`, start 4 `Competitor` threads and wait for them to finish.

At the end, display “Competition completed!”.

#### Make sure that:

Synchronization correctness is ensured: no one moves on to the next stage before the others.

The `Runnable` mechanism and a thread executor, `ExecutorService`, are used.

Specific exceptions are handled: `BrokenBarrierException`, `InterruptedException`.

## Exercise 5

Parallel statistics aggregator from text files

#### Description

Create a Java application that reads multiple text files from a directory in parallel, calculates the number of occurrences of a set of keywords for each file, and then writes the results to a common report file. Access to the report file must be synchronized using a `ReentrantLock`.

The `input/` directory contains M text files, `.txt`, each with several hundred lines.

#### Requirements

There is a fixed set of K keywords, for example `["Java", "thread", "lock", "file"]`.

For each file, a separate thread will:

* Open the file and count, line by line, the occurrences of each keyword.
* Build a partial report in the following format:

`file1.txt: Java=12, thread=3, lock=5, file=8`

* Wait to acquire the lock before opening `new FileWriter("report.txt", true)` and appending the partial report to `report.txt` on a new line.
* Release the lock immediately after closing the writer.

At the end, the application will display in the console:

* The total number of processed files.
* The total execution time.
