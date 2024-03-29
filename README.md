﻿# Assignment_3
The assignment:

You must re-design and re-implement the chat system from Assignment 2, this time using RMI instead of Sockets.

You may completely redo the assignment, but the idea is that you take your assignment 2 and just delete the sockets part and insert RMI instead, leaving most of the assignment untouched.

We have talked about keeping the layers in your system separate, and loosely coupled. This assignment should show you, if anything outside of the networking layer is using anything from inside your networking layer, meaning you do not have a clear separation.

Some of you may not have kept this separation strict and may find it troublesome.

Requirements

· The application must use RMI for the clients connecting and sending/receiving messages (method calls, the sockets in Assignment 2 being replaced by RMI)

· You must follow the MVVM pattern and use the Observer pattern.

· It is required to make a class diagram for the final solution (in Astah). In the diagram, you must be able to identify the MVVM parts and the design of the RMI related parts. If other patterns are used, then these also have to be clearly marked with notes.
