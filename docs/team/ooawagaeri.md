---
layout: page
title: Timothy Chua's Project Portfolio Page
---

### Project: MyCRM

MyCRM is a desktop application for managing client contacts, repair job statuses, and product information that has 
been optimised for use via a Command Line Interface (CLI) while maintaining the benefits of a Graphical User 
Interface (GUI). If you type quickly, MyCRM can complete customer relationship management tasks faster than 
traditional GUI applications.  

Given below are my contributions to the project.

* **New Feature**: Added the ability to add templates.
    * What it does: Allows the user to insert new email templates.
    * Justification: This feature improves convenience, allowing which users use to prepare and reuse future email 
      templates. Thus, increasing the speed of users can construct and send out emails to customers.
    * Highlights:
      * This enhancement affects the mail command by storing up drafts of future emails.
      * Templates added allow for `\n` characters, allowing for greater email template customization.

* **New Feature**: Added the ability to delete templates.
  * What it does: Allows the user to remove an existing email template.
  * Justification: This feature enables users to delete and remove any of their created templates, for example when 
    no longer relevant or when incorrect fields were inputted.
  
* **New Feature**: Added an edit template command
  * What it does: Allows the user to edit an existing email template.
  * Justification: This feature enables users to amend, correct or improve upon their already created templates. 
    Removes the need for users to delete and create a new template just to change certain fields.

* **New Feature**: Added the ability to see all templates.
  * What it does: Allows the user to view all email template in system.
  * Justification: Enables users at a glance, view all existing templates.

* **New Feature**: Added the filter and find templates based on subject header.
  * What it does: Allows the user to view only email template of interest containing specified word.
  * Justification: Enables users quickly find specific types of templates to employ in mailing, editing or deleting.

* **New Feature**: Added the ability to construct an email to send on the desktop's mailing application.
  * What it does: Allows the user to create a new email containing the details of an existing job and template.
  * Justification: This feature allows users to quickly generate emails for customers of jobs in a single line of 
    text rather than slowly opening the mail application, creating a new email, and keying in manually the required 
    of the client, and other information.
  * Highlights:
    * This enhancement has the ability to extract a job's client email, template subject header and body text.
  * Credit: [2ality](https://2ality.com/2010/12/simple-way-of-sending-emails-in-java.html) for reuse of urlEncode.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=ooawagaeri&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=zoom&tabAuthor=ooawagaeri&tabRepo=AY2122S1-CS2103-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zA=ooawagaeri&zR=AY2122S1-CS2103-T14-3%2Ftp%5Bmaster%5D&zACS=220.43386537126995&zS=2021-09-17&zFS=&zU=2021-11-04&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false&until=2021-11-04)

* **Project management**:
    * Managed group coordination and communication on Telegram.
    * Managed the timelines and issues of most GitHub milestones.
    * Managed the allocation, tagging and linkage of most GitHub issues.
    * Managed the allocation and update of GitHub [project board](https://github.com/AY2122S1-CS2103-T14-3/tp/projects/1).
    * Managed reviewing and merging of some PRs.
    * Manage quality control of code, documentation and diagrams.

* **Enhancements to existing features**:
    * Updated the JSON Storage component for Jobs, Products and Templates
      (Pull requests [#164](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/164)).
    * Updated the default generated JSON with MyCRM data
      (Pull requests [#87](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/87)).
    * Updated the `help` command with hyperlink / clickable URL.
      (Pull requests [#87](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/87)). 
    * Added more sample data to `SampleDataUtil`.
      (Pull requests [#197](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/197), [#87](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/87)). 
    * Wrote additional tests for existing features to increase coverage from 53.92% to 57.32%
      (Pull requests [#164](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/164)).

* **Documentation**:
    * User Guide:
        * Added documentation for the features `mail`, `addTemplate`, `deleteTemplate`, `editTemplate`, 
          `listTemplate`, `findTemplate`, `printReport` and `exportReport`:
          [#123](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/123),
          [#112](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/112),
          [#111](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/111),
          [#41](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/41).
        * Did cosmetic tweaks to existing documentation of features `exit`, `help` and `clear`:
          [#112](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/112).
    * Developer Guide:
        * Added documentation for the features `mail`, `addTemplate`, `deleteTemplate`, `editTemplate`,
          `listTemplate`, `findTemplate`, `help` and update "Launch and shutdown" and "Saving data".
        * Updated documentation of model components [#192](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/192), 
          [#80](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/80).

* **Community**:
    * Reported bugs and suggestions for other teams in the class (examples:
      [211](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/211), [193](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/193),
      [214](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/214), [203](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/203),
      [210](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/210), [199](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/199),
      [201](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/201), [216](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/216)).
