---
layout: page
title: Dhruv's Project Portfolio Page
---

### Project: MyCRM

MyCRM is a desktop application for managing client contacts, repair job statuses, and product information that has been optimised for use via a Command Line Interface (CLI) while maintaining the benefits of a Graphical User Interface (GUI). If you type quickly, MyCRM can complete customer relationship management tasks faster than traditional GUI applications.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add repair jobs.
    * What it does: Allows the user to insert new repair jobs into MyCRM. This includes various information that would
      be useful to keep track for a repair job such as, description, fee, received date, expected completion date,
      and the job's completion status. The repair job also links with other entities such as contact and product to keep 
      track of the client information and product information.
    * Justification: Our application's target audience is repair shop technicians and as such the core purpose of our
      application is to help them keep track of information related to the repair job's they are handling.
    

* **New Feature**: Added the ability to edit jobs.
    * What it does: Allows the user to edit/modify information about existing repair jobs in MyCRM. The user can select which
      attributes of the repair job they want to edit.
    * Justification: This feature allows the user to easily amend information about existing jobs that might have changed or might
      have been keyed in wrongly, instead of deleting and keying the information again.


* **New Feature**: Allow sequential commands to create or edit a job.
    * What it does: Allows the user to key in all job related information first, before issuing subsequent commands 
      to either assign new or existing contact and/or product to job. Provides functionality to automatically link newly created contacts or products to jobs if the user desires.
    * Justification: This feature provides a user-friendly and natural way for users to assign contacts and/or products to jobs while being fast.
      This is in contrast to keying in all information about a job, client and product at once, which would be extremely error-prone. Or if they are assigning
      an existing contact and/or product they will first have to search for these entities first and remember their index before adding or editing a job.
      Instead, with sequential commands the user can choose to input job-specific information first. Then they are free to look through or filter the contact
      and product lists, to see if any existing entities can be reused. They can immediately select them without having to remember their index. Or if they need to
      create new contact or product entities, these will automatically be linked to the job current being added or edited, without additional hassle. 


* **New Feature**: Added the ability to mark jobs complete, and also to revert their completion status.
    * What it does: Allows the user to mark an existing job as complete once the product has been repaired. Jobs marked complete 
      will by default no longer be shown in the job list. Users can also revert the completion status of already completed jobs.
    * Justification: Allows the user to focus on the in-progress jobs they are currently handling. Reverting the completion status of a job
      is a fallback mechanism for the user in the case of mistakenly marking a job as complete or if there are issues with the repair and the job
      needs to be reopened.
    

* **New Feature**: Added the ability to filter job list based on completion status.
    * What it does: By default the job list will only show in-progress jobs. User can choose to also see only completed jobs
      or all jobs (completed and in-progress).
    * Justification: Most of the time users will be mainly concerned with in-progress jobs. However, from time to time, they may
      need to refer to prior completed jobs, to edit them or to re-look at some information. Filtering the job list by completion status
      gives them a flexible way to quickly narrow down the type of jobs they wish to see.


* **New Feature**: Added the ability to delete existing jobs
    * What it does: Deletes an existing job's information from MyCRM. 
    * Justification: Helps user's delete away repair jobs that were wrongly keyed in or repair job's that were cancelled.
    

* **New Feature**: Added the ability to filter products by name.
    * What it does: Allows the user to view only products whose name matches a certain keyword.
    * Justification: Product information is stored in MyCRM so that it can be reused for future repair jobs with the same product.
      Filtering the products by name allows users to quickly find out if a certain product's information is stored in MyCRM and can be reused.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=dhshah1&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=dhshah1&tabRepo=AY2122S1-CS2103-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements through tests**:
    * Wrote tests for job related features to increase coverage from 60.04% to 66.48% 
      (Pull request [#183](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/183)).


* **Documentation**:
    * User Guide:
        * Added documentation for new features `addJob`, `editJob`, `listJob`, `deleteJob`,
          `completeJob`, `undoCompleteJob`
          (Pull requests [#47](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/47),
          [#117](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/117),
          [#122](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/122),
          [#169](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/169).)
    * Developer Guide:
        * Added implementation documentation for new features and mechanisms such as `StateManager`, `Adding a Job`, `Editing a Job`
          (Pull Request [#202](https://github.com/AY2122S1-CS2103-T14-3/tp/pull/202))
        * Updated documentation of logic component
          

* **Community**:
    * Reported bugs and suggestions for other team (Pull Requests:
      [#149](https://github.com/AY2122S1-CS2103T-T12-3/tp/issues/149), [#148](https://github.com/AY2122S1-CS2103T-T12-3/tp/issues/148),
      [#147](https://github.com/AY2122S1-CS2103T-T12-3/tp/issues/147), [#146](https://github.com/AY2122S1-CS2103T-T12-3/tp/issues/146),
      [#145](https://github.com/AY2122S1-CS2103T-T12-3/tp/issues/145), [#144](https://github.com/AY2122S1-CS2103T-T12-3/tp/issues/144),
      [#143](https://github.com/AY2122S1-CS2103T-T12-3/tp/issues/143), [#142](https://github.com/AY2122S1-CS2103T-T12-3/tp/issues/142))
