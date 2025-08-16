# CCDSTRU
Discrete Structures

Implement a computer program following the specifications of the system given below.

Notes: 
- 3x3 Matrix for the game.
- Sets T M B refer to the position of Player 1
- Sets L C R refer to the position of Player 2
- variable next refers to the turn of the player
- Occ = occupied; occupied spaces on the matrix
- Free = free; free spaces on the matrix
- positive value of variable (turn) refers to Player 1's turn
  and vice versa.

Applicable Sets
    A : A is the set of positive integers x such that x is less than 4
    S : A × A
    V : {true, false}
    P : Numbers 1 to 9


    3x3 Matrix:
    T : {(1,1),(1,2),(1,3)} Top Side of the Matrix (1st Row)
    M : {(2,1),(2,2),(2,3)} Middle Side of the Matrix (2nd Row)
    B : {(3,1),(3,2),(3,3)} Bottom Side of the Matrix (3rd Row)

    L : {(1,1),(2,1),(3,1)} Left Side of the Matrix (1st Column)
    C : {(1,2),(2,2),(3,2)} Center of the Matrix (2nd Column)
    R : {(1,3),(2,3),(3,3)} Right Side of the Matrix (3rd Column)

    F : F is the set of all subsets x of set S, where the cardinality 
        (number of elements) of x is 3, except for the two subsets 
        {(1, 1), (2, 2), (3, 3)} and {(1, 3), (2, 2), (3, 1)} 
        which are excluded.

System Variables
    Occ, Free ⊆ S 
    One, Two, Three, Four, Five, Six ⊆ P
    Peg ⊆ P
    W ⊆ P(P) 
    ok ∈ V (Can be 1 or 0 (T or F))
    turn ∈ V (Can be 1 or 0 (T or F))
    over ∈ V (Can be 1 or 0 (T or F))
    next ∈ V (Can be 1 or 0 (T or F))

System Facts
    Free = S − Occ
    Peg = P − (One ∪ Two ∪ Three ∪ Four ∪ Five ∪ Six)
    W = {One} ∪ {Two} ∪ {Three} ∪ {Four} ∪ {Five} ∪ {Six}
    over ↔ (∃w(w ∈ W ∧ |w| = 3 ∧ ∑a∈w a < 15) ∨ ∀w (w ∈ W ∧ |w| = 3 ∧ ∑a∈w a = 15))

System Initialization

    Booleans:
    ok = false
    next = false
    turn = true

    Null sets:
    One = ∅
    Two = ∅
    Three = ∅
    Four = ∅
    Five = ∅
    Six = ∅
    Peg = P

System States and Behavior
    NextPlayerMove(peg ∈ P, pos ∈ S)
    - A function that takes in two points. A certain peg (peg) gains a certain 
      amount of points (P) when it is position in a position (pos) with coordinates (S). 

    peg ∈ Peg ∧ pos ∈ Free → ok = ¬ok
                            ∧ Occ = Occ ∪ {pos}
    - a Free (free) position (pos) turns occupied (Occ)
      and negates the value of variable ok (ok)

    ok ∧ pos ∈ T            → One = One ∪ {peg}
                            ∧ next = ¬next
    - variable (ok) is true and the position (pos) is located at the 
      top side of the matrix (set T). Peg (peg) gets added to the set (One) and
      variable (next) is negated.

    ok ∧ pos ∈ M            → Two = Two ∪ {peg}
                            ∧ next = ¬next
    - variable (ok) is true and the position (pos) is located at the 
      middle side of the matrix (set M). Peg (peg) gets added to the set (Two) and
      variable (next) is negated.

    ok ∧ pos ∈ B            → Three = Three ∪ {peg}
                            ∧ next = ¬next
    - variable (ok) is true and the position (pos) is located at the 
      bottom side of the matrix (set B). Peg (peg) gets added to the set (Three) and
      variable (next) is negated.

    ok ∧ next ∧ pos ∈ L     → Four = Four ∪ {peg}
                            ∧ next = ¬next
                            ∧ ok = ¬ok
    - variable (ok) is true and variable (next) is true and the position (pos) is
    located at the left side of the matrix (set L). Peg (peg) gets added to the 
    set (Four), variable (next) is negated, and variable (ok) gets negated.

    ok ∧ next ∧ pos ∈ C     → Five = Five ∪ {peg}
                            ∧ next = ¬next
                            ∧ ok = ¬ok
    - variable (ok) is true and variable (next) is true and the position (pos) is
    located at the center of the matrix (set C). Peg (peg) gets added to the 
    set (Five), variable (next) is negated, and variable (ok) gets negated.

    ok ∧ next ∧ pos ∈ R     → Six = Six ∪ {peg}
                            ∧ next = ¬next
                            ∧ ok = ¬ok
    - variable (ok) is true and variable (next) is true and the position (pos) is
    located at the right side of the matrix (set R). Peg (peg) gets added to the 
    set (Six), variable (next) is negated, and variable (ok) gets negated.

GameOver(over)
    result ∈ {B wins, A wins}
    - Returns a value if player A wins or player B wins (Hypothetically, 1 and 2 respectively).

    over ∧ turn ∧ ∃w (w ∈ W ∧ |w| = 3 ∧ ∑a < 15)    → result = B wins
                                        a∈w
    - Player B wins if the game is (over), if it's Player B's (turn), and 
      if the number of pegs (peg) are exactly three and that their total 
      sum is less than 15.
    
    over ∧ ¬turn ∧ ∃w (w ∈ W ∧ |w| = 3 ∧ ∑a < 15)   → result = A wins
                                        a∈w
    - Player A wins if the game is (over) and if the number of pegs (peg) are 
    exactly three and that their total sum is less than 15. Variable (turn) 
    being negative refers to not being Player B's turn.

    over ∧ turn ∧ ∀w, (w ∈ W ∧ |w| = 3 ∧ ∑a = 15)   → result = A wins
                                        a∈w
    - Player A wins if the game is (over), if it's Player A's (turn), and 
      if the number of pegs (peg) are exactly three and that their total 
      sum is exactly 15.

    over ∧ ¬turn ∧ ∀w, (w ∈ W ∧ |w| = 3 ∧ ∑a = 15)  → result = B wins
                                        a∈w
    - Player B wins if the game is (over), if it's Player B's (¬turn), and 
      if the number of pegs (peg) are exactly three and that their total 
      sum is exactly 15.
      
    ¬over                                            → turn = ¬turn
    - A loop that states that while the game is not over, variable (turn)
      gets negated,
