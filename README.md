# Solution

## Architectural Components

### ViewModel

The ViewModel is used to manage all state of the application, and drives the UI using `LiveData`.

It includes:

- `matchItems: List<MatchItem>` - this stores all the match items that are given to the recycler
  view.
- `selectedItems: HashMap<String, MatchItem>` - this stores the currently checked match items. A
  hashmap is used for fast determination of if a match item has been checked.

> Note: An `id` field was added to the `MatchItem` for this purpose. I believe this to be a
> reasonable addition, as the match items are likely coming from a data source with a unique id.

- `targetValue: Float` - this is the amount remaining to be matched. Storing it in LiveData allows
  the activity to easily update it's UI as items are checked, and this value is changed.

The ViewModel also includes methods to manipulate these values, and unit tests to ensure that given
certain scenarios, the LiveData are updated correctly. The ViewModel's unit tests **only test that
the LiveData is correctly updated.** Any more complex logic and implementation details are placed
into *Repositories* with their own unit tests.

### Views

These are all the classes associated with the Android environment, such as Activities,
RecyclerViewAdapters and ViewHolders. These classes are very simple, and only include logic to
connect with UI elements and update them. All data comes from the ViewModel, and all interactions
with the UI are sent to the ViewModel for processing. The only way they can be tested is with UI
tests, which are cumbersome, and none are included in this project.

### Repositories

These components encapsulate business logic and data fetching. They have very explicit methods, each
with their own sets of unit tests. The repositories are injected into the ViewModel, so they can be
mocked in the ViewModel tests.

This project includes two repositories:

- `RecRepository` - this repository deals with getting the reconciliation items and the target match
  value. It does not include any unit tests at the moment given it's simplicity, however
  if I was to fetch the reconciliation items from a server for example, then it would need unit
  tests.
- `RecAutoMatcherRepository` - this repository deals with matching reconciliation items. Currently
  it
  includes one method to find a single transaction that matches the remaining total, if any (Goal
  #2).
  This repository is unit tested, and is where I would place the 'Thought Experiment' to match
  multiple items.

## Current project limitations

- Given the simple nature of this app, the ViewModel does not include any loading states. These
  would be added if getting the reconciliation items from a service, or matching the items takes
  time.
- Currently the `selectedItems` in the view model are updated in the checkboxes `onClickListener`,
  however the checkboxes are still calling the `toggle()` method themselves. Ideally, after
  the `selectedItems` is updated, the adapter would tell all the checkboxes if they should be
  checked. All of my attempts to implement this resulted in the checkboxes losing their animation,
  and so I chose not to do this.

## Adding 'Subset Matching'

In relation to the thought experiment, this would be very simple to implement. I would add a method
to the `RecAutoMatcherRepository` that performs this work, and returns a list of all items that
match.

- All logic related to how this subset matching works would be in the repository, and unit tested in
  the repository unit tests.
- The view model would just need to add a test that ensures that given a list of subset matching
  items, the `selectedItems` is correctly updated, and the `targetMatchValue` is set to 0.
- Adding this behaviour at the application start, after first trying but failing to match a single
  item, would simply require changing the `selectedItems` initial value
  from `this.autoMatchItem() ?: hashMapOf()`
  to `this.autoMatchItem() ?: this.autoMatchMultipleItems ?: hashMapOf()`.

---

# Task

---

## Hi

You are going through a technical interview with us at Xero, how exciting!

The purpose of this exercise is to get you building something that solves an actual customer
problem, lets you
think through it and start on it on your own, so that you can come in and walk us through your
solution.

## Overview

One of the core daily interactions that users have with the Xero mobile application is bank
reconciliation, where bank transactions are matched against the corresponding accounting records
(Invoices, Bills, etc.).

If an exact match is found, then the system will automatically suggest a match. There can also be
multiple accounting records which match to a single transaction, but if some combination of the
records sum to be an exact match, then the user has to find and select them themselves.
Surely there is a better way!

## Goals

If you run the application in this project you will see a single activity which displays a list of
accounting records and the total amount of a transaction at the top, there's not much else to it!

And that's where you come in:

You have two pieces of functionality to add. And we don't want you spending too much time on it, so
if you run out of time or get stuck then don't worry, we can talk through it together during the
interview.

1. Wire up the behaviour so that when an accounting record is selected, it is subtracted from the
   remaining total at the top.

2. When the activity opens initially, select a single transaction that matches remaining total
   automatically if any.

### Thought Experiment

We'd also like you to THINK ABOUT how we could achieve the following functionality:
When a subset of the accounting records sum to be an exact match of the remaining total, then
automatically select them. You don't need to write any code here. We will aim to explore your
thinking and design when you come in.

## Hints

As you go, mark down any questions or concerns which come up, we would love to hear them as you walk
us through the code during the upcoming interview.

You can change, delete or add classes, interfaces, layouts, tests and dependencies as you see fit.

