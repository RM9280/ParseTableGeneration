This project implements an LL(1) predictive parser in Java.

**It demonstrates how to:**

Define a grammar using production rules,
Compute parsing behavior using FIRST and FOLLOW sets,
Construct an LL(1) parse table,
Parse an input token stream using a stack-based predictive parsing algorithm

**Grammer is:**

P  → E

E  → T E'

E' → + T E' | ε

T  → F T'

T' → * F T' | ε

F  → ( E ) | int
