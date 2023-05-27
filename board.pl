:- use_module(library(dif)).
:- use_module(library(clpfd)).
:- use_module(library(clpb)).
:- use_module(library(chr)).
:- use_module(library(when)).
n_queens(Qs) :-
        length(Qs,8),
        Qs ins 1..8,
        safe_queens(Qs).

safe_queens([]).
safe_queens([Q|Qs]) :- safe_queens_(Qs, Q, 1), safe_queens(Qs).

safe_queens_([], _, _).
safe_queens_([Q|Qs], Q0, D0) :-
        Q0 #\= Q,
        abs(Q0 - Q) #\= D0,
        D1 #= D0 + 1,
        safe_queens_(Qs, Q0, D1).
solve:-
    X=
[_,3,1,_,_,_,_,_],
n_queens(X),labeling([ff],X),tell('result.txt'),write(X),told.