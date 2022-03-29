# User Retention App

![technology Gradle](https://img.shields.io/badge/technology-Gradle-blue.svg)

This is a Java Gradle application to analyze an application's user retention as we compute the number of users that use the application for a number of consecutive days.

## How to run the application

1. Install [Java 11](https://oracle.com/ar/java/technologies/javase/jdk11-archive-downloads.html)


2. Run in your terminal


   `java -jar user-retention-app-1.0.0.RELEASE.jar activity_data_2021_01_01.txt`


3. The console should show you the response as follow:

    ![](images/normal_response.png)


## Considerations

- It was considered that the input of the application is a file that always has information from the same month
- It was considered that the default range of days is from 1 to 14
- We have the possibility to specify a custom range of days, but there are some validations to have in mind:
  - The custom range of days must be between 01 and 31
  - The custom range of days must be started from 01
  - The custom range of days must have dd format
- If any of these validations are not met, we will take the default range of days (1-14)


### Valid cases:
  

    - Custom range of days: 01 to 05
  ![](images/custom_range_response.png)


    - Custom range of days: 01 to 10
  ![](images/custom_rangev2-response.png)



    
### Not valid cases:


    - Range of days as string:
  ![](images/day_range_as_string_response.png)




    - Invalid final day range:
  ![](images/invalid_final_day_range_response.png)




    - Range of days with no dd format:
  ![](images/range_days_with_no_dd_format.png)

## Test

The JaCoCo plugin provides code coverage metrics for Java code via integration with [JaCoCo](https://docs.gradle.org/current/userguide/jacoco_plugin.html)

This plugin was used in this project reaching 100% of coverage in the lines of code:

![](images/code_report.png)

    

## Questions

* [rnoblega@gmail.com](rnoblega@gmail.com)
