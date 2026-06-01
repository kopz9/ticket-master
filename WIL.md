# What I Learned

Adminer -> web interface for database management
keycloak -> used for authentication and authorization in apps
handles login screens, user accounts, password managements, jwt token generations.
instead of creating auth yourself backend delegates to keycloak


page instead of list so you dont return all data in the database and instead a small part


@Lock(LockModeType.PESSIMISTIC_WRITE)
Optional<TicketType> findByIdWithLock(@Param("id") UUID id)

It's typically used to prevent race conditions in concurrent scenarios. For TicketType, a classic use case is ticket inventory management:
Transaction A reads: available_tickets = 1
Transaction B reads: available_tickets = 1
Transaction A sells 1 ticket → updates to 0, commits
Transaction B sells 1 ticket → updates to 0, commits  ← oversold!
With PESSIMISTIC_WRITE, Transaction B cannot even read the row while A holds the lock, preventing this double-sell.