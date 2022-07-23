# Banking and Event Sourcing

A bank account application that implements the event sourcing pattern.

"We can query an application's state to find out the current state of the world, and this answers many questions.
However there are times when we don't just want to see where we are, we also want to know how we got
there." [[1]](#references)

"Event Sourcing ensures that all changes to application state are stored as a sequence of events. Not just can we query
these events, we can also use the event log to reconstruct past states, and as a foundation to automatically adjust the
state to cope with retroactive changes." [[1]](#references)

![Diagram of the projects architecture](./architecture.svg)

### Commands

Here are the possible commands for the application:

- open-bank-account
- deposit-funds
- withdraw-funds
- close-bank-account
- restore-read-db

### Queries

Here are the possible commands for the application:

- bank-account-lookup
- bank-account-lookup/by-id/{id}
- bank-account-lookup/by-holder/{holder}
- bank-account-lookup/with-balance/{equality}/{balance}

### Requests

Postman collection:

```
https://www.getpostman.com/collections/ed6b1ba9636e0b9b1424
```

### How to run

There is a docker-compose.yml in the project to bring up the databases and Kafka, so just clone the repo and execute the
command:

```
$ docker-compose up -d
```

And you're good to start the application!

I strongly recommend you to use the IntelliJ IDEA.

### Known bugs

- [ ] Sometimes the `restore-read-db` doesn't restore all the transactions of the account (e.g. wrong balance).

## References

Fowler, Martin (2005).
[Event Sourcing](https://martinfowler.com/eaaDev/EventSourcing.html).
Capture all changes to an application state as a sequence of events.
