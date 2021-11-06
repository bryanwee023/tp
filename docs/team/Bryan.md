---
layout: page 
title: Bryan's Project Portfolio 
parent: The Team
---

### Project: BogoBogo

BogoBogo is a desktop inventory management application developed for small business owners. The user
interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Enhancement**: Extended the observer pattern between `model` and `ui`.
    * What it does: Allows BogoBogo to switch what is displayed on the main display panel upon the user's request (e.g. display transactions).
    * Justification: This feature allows commands to indirectly control `ui` without introducing any additional coupling.
    * Highlights: This enhancement required a major revamp to AB3's existing observer pattern. 
      In AB3, the observer (`DisplayListPanel`) is bound to the observed (`UniqueItemList`) at initialisation.
      To allow the switching of the observed list (e.g. from inventory items to order items) within `model`, there was a need for a separate class to handle the logic.
      Keeping future extension in mind, a `Displayable` interface has also been implemented. This allows future developers to display
      any new object in the panel without much change to the `ui` or `model` component. [More details here](https://ay2122s1-cs2103-f10-2.github.io/tp/DeveloperGuide.html#controlling-the-display-panel-in-ui)
    * Links to PRs: [\#96](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/96), [\#100](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/100)

* **Enhancement**: Implemented more robust responses by BogoBogo.
    * What it does: BogoBogo will respond to the user more appropriately under more niche scenarios.
                    For example, when there's ambiguity in the item specified by the user, or when the user has specified an overly large amount of item.
    * Justification: This feature makes BogoBogo much more user-friendly. With robust responses, even a careless user will have a pleasant experience with BogoBogo.
    * Highlights: This enhancement is a huge one as it is enforced across most commands. After my teammates have implemented the core components of a feature,
                  I add on by adding checks and appropriate response messages. Along the way, I have added utility classes such as `ItemDescriptor` to help me with this.
    * Links to PRs: [\#44](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/44), [\#53](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/53). [\#74](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/74)
    
* **New Feature**: Delete and Remove Commands.
    * What it does: Allows the user to either remove an item from the inventory or delete the item data entirely.
    * Justification: AB3 only supports deletion of an item. However, business owners might either want to remove an unsold item
      from the inventory (e.g. expired goods), or delete an incorrectly entered item. Spawning 2 distinct commands from AB3's 
      original `DeleteCommand` enables this.
    * Links to PRs: [\#44](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/44), [\#63](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/63)

* **Testing**: Added and maintain testing for features other than my own:
    * Tests for storage component [\#188](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/188)
    * Tests for order related commands [\#74](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/74)
    * Tests for ui components [\#194](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/194) <br>
_Creating tests for ui was particularly time-consuming since testing JavaFX components requires the use of additional frameworks (TestFX)._
    
* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=bryanwee023&tabRepo=AY2122S1-CS2103-F10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**:
    * User Guide:
      * Wrote the final version of the UG [\#159](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/159)
      * Integrated a new Jekyll theme [\#159](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/159)
    * Developer Guide:
      * Wrote the implementation details of `DisplayList` [\#179](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/76)
      * Consolidated and formatted use cases. [\#183](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/183)

* **Community**:
    * Generally assisted the team with advice on implementation of features and architectural patterns. 
      * e.g. Noted the mismatch of abstraction level between `BookKeepingStorage` and `TransactionStorage` in [\#95](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/95).
    * Team based tasks: 
      * Helped with managing and labelling of issues in the issue tracker.
      * Set up Github Actions and Jekyll theme in documentation.
      * Assisted team with Git issues (e.g. rebasing, forking, etc.)
    * PRs reviewed: [\#24](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/24)
      , [\#25](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/25)
      , [\#43](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/43)
      , [\#47](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/47)
      , [\#64](https://github.com/AY2122S1-CS2103-F10-2/tp/pull/64)
    * Reported bugs and suggestions for other teams during PE-D ([PE-D](https://github.com/bryanwee023/ped/issues))
