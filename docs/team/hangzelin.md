---
layout: page
title: HangZelin's Project Portfolio Page
---

### Project: MyCRM

MyCRM is a desktop application for managing client contacts, repair job statuses, and product information that has been optimised for use via a Command Line Interface (CLI) while maintaining the benefits of a Graphical User Interface (GUI). If you type quickly, MyCRM can complete customer relationship management tasks faster than traditional GUI applications.

Given below are my contributions to the project.

  * **Enhancements to existing features**: Modified old contacts in **AddressBook** to `addContact`, `editContact`, `findContact`, `deleteContact` and `listContact` commands.
      * What it does: Allows users to make basic operations in **contact** part of **MyCRM**.
      * Justification: These add, edit, find etc. commands are now viewed as only a part of commands in **MyCRM** system,
      and served as commands specific towards **contact** part in **MyCRM**. Besides, a contact is no longer viewed as normal
      friends in addressBook, but a cooperation partner, so information given should be working official.  
      * Credits: These modifications follow the design pattern in **AddressBook**.
  * **Enhancements to existing features**: Enhanced `addContact` command to support **optional** fields.
      * What it does: Allows users to enter partial information of a client.
      * Justification: A client may consider his own privacy issue, thus **MyCRM** should allow a client to conceal 
      part of his/her information.
  * **Enhancements to existing features**: Enhanced `listContact` command to be able to list **unhidden** or **all** contacts in the list.
      * Justification: Since there are hidden clients in the list, normal `listContact` only support viewing unhidden
      contacts in the list. Therefore, a different **listContact** command is needed if a user requires to see all contacts
      in the list.
  * **Enhancements to existing features**:  Enhanced `deleteContact` command to disable deletion of a contact if it is already linked to a job.
      * Justification: It is not reasonable if a user requires to delete a client in **MyCRM** when the job
      linked to this client has not been completed yet.
  <br><br>
  * **New Features**: Added `hideContact` and `undoHideContact` command to Contact part of **MyCRM**:
      * What it does: Allows users to hide unused contacts in MyCRM. Users can type `undoHideContact` to set this client back 
      to **not hidden** state. A hidden contact will have a new tag **hidden**, indicating that this client is currently unused,
      and not visible in the normal contact list.
      * Justification: This feature improves the MyCRM's efficiency when accessing clients 
      because a user can prevent himself/herself getting distracted by unused clients.
      They can also set hidden clients back to visible easily with these two commands.
      * Highlights: This enhancement affect the visibility of contact list. It requires further changes in
      other contact commands if users want to view this contact or make operations upon it.
  * **New Features**: Added `findJob` command to Job part.
      * What it does: Allows users to find certain jobs via a job's description, client name, 
      product name linked to it or completion status.
      * Justification: This feature improves the MyCRM's efficiency when accessing jobs. Users can easily locate
      jobs they hope to seek in the list.
      * Credits: This new feature follows the design pattern of **AddressBook**'s `find` command, and more keyword types
      are allowed in `findJob` instead of only the name of a job.

  * **Enhancements in Tests**:
    * Wrote more test cases for existing features: `addContactCommandParser`, `deleteContactCommand`,
    `ListContactCommand`, `listContactCommandParser`, and increases code coverage from 54% to 59% 
    (pull requests [\#168](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/168) [\#167](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/167) [\#166](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/166))



* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=Hang%20Zelin&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=zoom&tabAuthor=HangZelin&tabRepo=AY2122S1-CS2103-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&zFR=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zA=HangZelin&zR=AY2122S1-CS2103-T14-3%2Ftp%5Bmaster%5D&zACS=212.8258064516129&zS=2021-09-17&zFS=Hang%20Ze%3Bin&zU=2021-11-06&zMG=false&zFTF=commit&zFGS=groupByRepos)


* **Project management**:
  * Set up the GitHub team org/repo for T14-3.
  * Set up CodeCov report for team repo.
  * General code enhancement:
    * Rename product from `addressBook` to `MyCRM`.
    * Change product icon to `MyCRM`.
  * Managed reviewing and merging of most PRs.
  * Managed code quality review.
  * Managed releases `v1.2.1` (1 release) on GitHub
  

* **Documentation**:
  * Set up [MyCRM Product Website](https://ay2122s1-cs2103-t14-3.github.io/tp/).
  * Update **README** to link to **MyCRM** project 
  * User Guide:
    * Added documentation for [contacts features](https://ay2122s1-cs2103-t14-3.github.io/tp/UserGuide.html#adding-a-contact-addcontact):
      * `addContact`: adds a new client's contact into MyCRM.
      * `editContact`: edits a current client's contact info.
      * `deleteContact`: deletes a client's contact.
      * `findContact`: finds all contacts matches keyword given.
      * `listContact`: lists unhidden contacts or all contacts.
      * `hideContact`: hides a currently not being used contact info.
      * `undoHideContact`: undoes hiding a contact operation.
    * Did cosmetic tweaks to [Quick Start](https://ay2122s1-cs2103-t14-3.github.io/tp/UserGuide.html#quick-start) part command examples.
  * Developer Guide:
    * Added [implementation details](https://ay2122s1-cs2103-t14-3.github.io/tp/DeveloperGuide.html#adding-a-contact) for all 
    **contact** command features：`addContact`, `editContact`, `deleteContact`, `findContact`, `hideContact`, `undoHideContact` and `listContact`.
    * Added [use cases](https://ay2122s1-cs2103-t14-3.github.io/tp/DeveloperGuide.html#use-cases) for all **contact** command features:`addContact`,
    `editContact`, `deleteContact`, `findContact`, `hideContact`, `undoHideContact` and `listContact`.   
      (Please scroll down to see contact command features part.)
    * Added [Instructions for manual testing](https://github.com/AY2122S1-CS2103-T14-3/tp/blob/master/docs/DeveloperGuide.md#appendix-instructions-for-manual-testing) for all       **contact** command features: `addContact`, `editContact`, `deleteContact`, `findContact`, `hideContact`, `undoHideContact` and `listContact`.   

* **Community**:
    * Helped changed **completion status of a job** into a new component in Job model.
      (pull requests: [\#77](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/77))
    
    * Reported bugs and suggestions for other teams in the class (examples:
      [120](https://github.com/AY2122S1-CS2103-F09-4/tp/issues/120), [121](https://github.com/AY2122S1-CS2103-F09-4/tp/issues/121),
      [122](https://github.com/AY2122S1-CS2103-F09-4/tp/issues/122), [123](https://github.com/AY2122S1-CS2103-F09-4/tp/issues/123),
      [124](https://github.com/AY2122S1-CS2103-F09-4/tp/issues/124), [125](https://github.com/AY2122S1-CS2103-F09-4/tp/issues/125),
      [126](https://github.com/AY2122S1-CS2103-F09-4/tp/issues/126), [127](https://github.com/AY2122S1-CS2103-F09-4/tp/issues/127),
      [128](https://github.com/AY2122S1-CS2103-F09-4/tp/issues/128))
