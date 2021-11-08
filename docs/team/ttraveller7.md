---
layout: page
title: ttraveller7's Project Portfolio Page
---

### Project: MyCRM

MyCRM is a desktop application for managing client contacts, repair job statuses, and product information that has been optimised for use via a Command Line Interface (CLI) while maintaining the benefits of a Graphical User Interface (GUI). If you type quickly, MyCRM can complete customer relationship management tasks faster than traditional GUI applications.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add products.
    * What it does: Allows the user to insert new products.
    * Justification: This feature increases usefulness of MyCrm, allowing users to refer to the details a product and 
      reuse its information in future jobs.


* **New Feature**: Added the ability to delete products.
    * What it does: Allows the user to remove an existing product.
    * Justification: This feature enables users to delete and remove any of their created products, for example when
      a product is less popular in repair jobs.


* **New Feature**: Added an edit product command
    * What it does: Allows the user to edit an existing product.
    * Justification: This feature allows users to change certain fields of a product without removing it.
    * Highlights: All product fields except *product name* are optional. This allows the user to store product information 
      in multiple short commands instead of one long command. <br>For example, the user can key in an `addProduct` command to 
      create a product with *product name*, and then issue several `editProduct` commands to fill in *other fields*.


* **New Feature**: Added a list product command.
    * What it does: Allows the user to view all products in MyCrm.
    * Justification: Enables users view all existing products.


* **New Feature**: Created a new appearance theme and added the ability to change theme.
    * What it does: Allows the user to change the theme of appearance of MyCrm.
    * Justification: Allows the user to switch to his/her preferred theme.
    * Highlights: 
      * A new theme, [light](https://github.com/AY2122S1-CS2103-T14-3/tp/blob/master/src/main/resources/view/LightTheme.css), 
      is created as a stylesheet with new colorscheme and font family. 
      * To change the current theme, the user can either use `themeCommand` or choose from the theme menu
      * When the user exits MyCrm, the most recent theme will be stored. The same theme will be loaded to GUI next time 
        the user opens the app.
    

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabAuthor=TTraveller7&tabRepo=AY2122S1-CS2103-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&tabType=authorship)


* **Enhancements to existing features**:
    * Modified GUI layout: split the list pane to two columns (Pull requests
      [#82](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/82)).
    * Adjusted attributes of Ui components; make GUI response with different commands reasonably (Pull requests 
      [#65](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/65))
    * Wrote tests for products commands, product components and theme command.


* **Documentation**:
    * Updated README of team repository [#37](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/37) 
    * User Guide: Added documentation for the features `addProduct`, `deleteProduct`, `listProduct`, `editProduct`,
          `findProduct`, and `theme`.
    * Developer Guide:
        * Added use cases and test instructions for the features `addProduct`, `deleteProduct`, `listProduct`, `editProduct`,
          `findProduct`, and `theme`.
        * Added implementation details for the features `addProduct` and `editProduct`.
        * Updated documentation of UI components [#116](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/116).


* **Community**:
    * Reported bugs and suggestions for other teams. e.g.
      [125](https://github.com/AY2122S1-CS2103T-T17-4/tp/issues/125), [128](https://github.com/AY2122S1-CS2103T-T17-4/tp/issues/128),
      [129](https://github.com/AY2122S1-CS2103T-T17-4/tp/issues/129), [123](https://github.com/AY2122S1-CS2103T-T17-4/tp/issues/123),
      [124](https://github.com/AY2122S1-CS2103T-T17-4/tp/issues/124), [127](https://github.com/AY2122S1-CS2103T-T17-4/tp/issues/127).
