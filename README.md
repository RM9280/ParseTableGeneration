This project implements an LL(1) predictive parser in Java.

**It demonstrates how to:**

Define a grammar using production rules,
compute parsing behavior using FIRST and FOLLOW sets,
construct an LL(1) parse table,
parse an input token stream using a stack-based predictive parsing algorithm

**Grammer is:**

P  → E

E  → T E'

E' → + T E' | ε

T  → F T'

T' → * F T' | ε

F  → ( E ) | int
