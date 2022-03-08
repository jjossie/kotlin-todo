# Overview

This is a console-based productivity app involving both To-Do Lists
and Goal tracking. It allows users to create To-Dos, check them off, 
and filter the display between complete/incomplete items. It also 
allows for the creation of daily goals that the user can track their
progress toward. The most unique feature, however, is the ability to
set a goal for how many to-do list items the user wants to complete
on a given day.

The purpose of writing this software is to become familiar with the 
Kotlin language's syntax and style, utilizing both functional and 
object-oriented programming paradigms. For example, the goals and 
todos are represented by classes inheriting from a parent class Task,
while both are stored in encapsulated collections under subclasses of
an abstract class ProductivityList. Meanwhile, the console menus are
handled using Menu and MenuOperation classes, which accept and store
anonymous functions to associate code to be executed with each menu
item.

The latest feature developed is integration with Cloud Firestore as a
means of cloud-based data storage.
This (in theory) allows users to store updates to their todos and goals as edits are made.

[Demonstration Video](https://youtu.be/zW07IqWriBI)

# Cloud Database

While Cloud Firestore does have libraries made specifically for the Kotlin language, they are available only as Android libraries, included with Android Jetpack. As such, using them within a Kotlin-only project - i.e. no Android or Android Studio involved - proved to be impossible (or too difficult to be worth it). 
Instead, I used the libraries made for Java. This, however, proved to come with endless difficulties of its own. Kotlin models have to be converted to Java classes for proper storage and retrieval, which caused issues such as completely empty data sets being returned from the database. Because of this and other issues, at this time the app can currently only write to the database - it cannot read from, update, or delete any documents in the database.

That being said, the structure is as such: users are stored as documents within a *users* collection. Each user has an array of ToDo items and Goals attached, as well as necessary metadata. This type of nested document database proved to be very useful for this type of data storage, since no complex relational tables need to be made, and everything can be simply stored in hierarchical format.


# Development Environment

* Java 
* Kotlin
* IntelliJ IDEA 2021.3.2 (Ultimate Edition)
* Cloud Firestore (for Java)
* Firebase Admin SDK 8.1.0

# Useful Websites

* [Get started with Cloud Firestore](https://firebase.google.com/docs/firestore/quickstart) - Google's official Firebase Documentation
* [Kotlin Documentation](https://kotlinlang.org/docs/getting-started.html)
* [Stack Overflow (obviously)](https://stackoverflow.com/)

# Future Work

## Unimplemented Features

* Deleting/updating tasks/goals
* Updating the progress of count-based goals
* Acknowledgement of completing all todos or all goals
* Resetting daily goals
* Reading, deleting, and updating records in the database
* Completing the storage of all necessary data (e.g. Goals, GoalLists, etc.)

## Features to Add

* Weekly goals / enhanced scheduling 
* An Android app (!!)
