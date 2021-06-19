# **Requirements**

- JDK needs to be installed
- IDE (preferably IntellIJ)

# **Details**
`Framework:` https://cucumber.io/ -- Test scenarios have been written in BDD (Behavior-Driven Development) fashion.

`Build Tool:` https://gradle.org/


# **Execution**
`Execution via UI`
Scenario can be executed from IntellIJ by using `Run Scenario` option

`Execution via CLI` Scenario can also be executed via command line.

- Update cucumber tag inside build.gradle
- Run command `./gradlew cucumber` to execute

# **Results**
Cucumber report gets generated post execution, which captures details on pass and fail count, along with failure details.
Screenshots of both UI and API sample results are attached in the repo.

- UI Tests Report: https://github.com/arpitjain1/realworld/blob/master/cucumber-report-ui.png
- API Tests Report: https://github.com/arpitjain1/realworld/blob/master/cucumber-report-api.png 
