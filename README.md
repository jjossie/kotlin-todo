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

[Demonstration Video](https://youtu.be/zW07IqWriBI)

# Development Environment

* Java 
* Kotlin
* IntelliJ IDEA 2021.3.2 (Ultimate Edition)

# Useful Websites

* [Kotlin Documentation](https://kotlinlang.org/docs/getting-started.html)
* [Stack Overflow (obviously)](https://stackoverflow.com/)

# Future Work

## Unimplemented Features

* Deleting/updating tasks/goals
* Updating the progress of count-based goals
* Acknowledgement of completing all todos or all goals
* Resetting daily goals
* Data storage

## Features to Add

* Weekly goals / enhanced scheduling 
* Cloud database storage (stay tuned!)
* An Android app (!!)
