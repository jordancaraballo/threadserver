# threadserver
Java Server and Client sharing Sockets - Multithreaded Server

## Purpose

This project is intended to cover fork() and threading concepts towards the operating system behaviour. This project is part of the Operating System Class at the University of Puerto Rico at Humacao and for recreational purposes only.

## DateServer

DateServer script listen at a socket, receives a positive integer from a client (or many clients) and process it by running a function to find the number of prime numbers within the given integer. The Pi function was intentionally created as most inneficient as possible to have enough time to see the created threads. The server will stay online receiving and creating a new thread for each new client. The server prints out the ip address and the received integer from the clients.

```
javac DateServer.java   # compiling
java DateServer         # running the server
```

## DateClient

DateClient uses the ip address and port from the server, and sends an integer number to it. It will wait for the server to calculate the Pi function and close the socket ones it is done. You can start as many clients as you want in order to see enough threads and enough distributed CPU workload.

```
javac DateClient.java             # compiling
java DateClient 127.0.0.1 100000  # first argument ip address, second argument integer value
```

## References

Silberschatz, A., Galvin P.B., Gagne G., Operating Systems Concepts (9th Ed.)
