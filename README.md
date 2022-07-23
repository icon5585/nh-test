### Project for [Notable Health](https://www.noteablehealth.com) take home assignment
Hank DeDona
7/23/2022

Project:
This project is built using the basics of spring boot v2.7.2 and is designed to run on a tomcat container on port 8080.
To start, you can simply run the com.notable.health.nhtest.NhTestApplication.java class

Problem statement:
We need a way to automatically integrate doctor’s calendars from their existing
systems of records with the Notable platform. The task is to build a web back-end that supports
the following functionality via HTTP requests:
  ● Get a list of all doctors
  ● Get a list of all appointments for a particular doctor and particular day
  ● Delete an existing appointment from a doctor's calendar
  ● Add a new appointment to a doctor's calendar
    ○ New appointments can only start at 15 minute intervals (ie, 8:15AM is a valid time
but 8:20AM is not)
    ○ A doctor can have multiple appointments with the same time, but no more than 3
appointments can be added with the same time for a given doctor


Thoughts/Notes:
There is so much missing, full test suites, better documentation, authentication/authorization, CSRF protection, etc.
