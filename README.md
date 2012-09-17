You can tell that cellular phones are at work in rural communities, from
the giant microwave towers you sometimes see sprouting out of corn
fields and cow pastures. Let’s consider a very simplified model of a
cellular phone network in a sparsely populated area.
We are given the locations of n base stations, specified as points
bl ..... bn in the plane. We are also given the locations of n cellular phones,
specified as points Pl ..... Pn in the plane. Finally, we are given’a range
parameter A > 0. We call the set of cell phones fully connected if it is
possible to assign each phone to a base station in such a way that

* Each phone is assigned to a different base station, and
* If a phone at pi is assigned to a base station at bj, then the straight-line
  distance between the points Pi and b1 is at most Δ.

Suppose that the owner of the cell phone at point Pl decides to go
for a drive, traveling continuously for a total of z units of distance due
east. As this cell phone moves, we may have to update the assignment of
phones to base stations (possibly several times) in order to keep the set
of phones fully connected.
Give a polynomial-time algorithm to decide whether it is possible to
keep the set of phones fully connected at all times during the travel of
this one cell phone. (You should assume that all other phones remain sta-
tionary during this travel.) If it is possible, you should report a sequence
of assignments of phones to base stations that will be sufficient in order
to maintain full connectivity; ff it is not possible, you should report a
point on the traveling phone’s path at which full connectivity cannot be
maintained.
You should try to mak~ your algorithm run in O(n3) time if possible.

Example.

Suppose we have phones at Pl = (0, 0) and P2 = (2, 1); we have
base stations at bl = (1, 1) and b2 ----- (3, 1); and A = 2. Now consider the case
in which the phone at pl moves due east a distance of 4 units, ending at
(4, 0). Then it is possible to keep the phones fully connected during this
Exercises
motion: We begin by assigning Pl to b1 and P2 to b2, and we reassign pl to
b2 and P2 to b1 during the motion (for example, when p1 passes the point
(2, 0)).

Algorithm Design by Jon Kleinberg and Éva Tardos

