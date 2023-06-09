# Cucumber test Automation 

# What is it? 
A JAVA based Page Object Model test automation solution created using Selenium, Cucumber BDD, TestNG with the Maven build tool to automate web applications.

# What is the setup?
# Short Story: 
The solution itself is a maven project, all the dependencies are configured in pom.xml which interns download on the runtime. WebDrivers used are for Mac's OS, you might need to change the drivers and the path depending on your device OS and browser version. Since I dont have a windows PC I didn't add the code for handling it automatically. Don't want to publish something I didn't verify throughly. If you are mac user, you are good to go with current setup.

# Pre-Requisite:
1. JAVA and Maven installed and set in environment variables
2. IDE of your choice , with JAVA 11 or above 
3. I have used JAVA 11 and Maven 3.8.1 for development

# Execution via IDE: 
1. Clone the repository, load the project into IDE, let the dependencies gets downloaded
2. Right click on your project - Run as 'maven test' or 'maven install' (Menu might differ based on IDE you use)
3. The tags specified in DefaultRunner file will be picked / you can change the tag in run time from command line execution 

# Execution via Command Line: 
1. Clone the repository
2. cd to the cloned repository path from the command line / terminal
3. Navigate to pom folder path and Run the below command if you want the tag mentioned in the Default Runner to be picked       
 mvn test 
 clean test -P sit -Dbrowser=chrome  
# Run profiles
clean test -P cat -Dcucumber.options="--tags @LoginwithCredentials,@UpdateUserInfo" -Dbrowser=chrome
clean test -P sit -Dcucumber.options="--tags @LoginwithCredentials,@UpdateUserInfo" -Dbrowser=chrome  
clean test -P prod -Dcucumber.options="--tags not @smoke" -Dbrowser=chrome    

# Cucumber Execution Reports Path
1. You can see execution reports from the below folder after your run has completed
       target/cucumber-report-html/cucumber-html-reports/failures-overview.html

# Extent Execution Reports Path
Reports/Execution dd-mmm-yyyy/Reports/reports.html

Config file for extent report is present inside src/test/resources folder 'extent.properties' and the required dependencies on the pom.xml 

Mainly divided into 4 different source packages inside "src/test/java". Will provide a short note on each packages generic files
  # 1. com.test.framework 
           1.  AbstractPage - All the reusable methods
           2.  Init Driver -  Initiates web browser driver session
           3.  ScenarioContext - Handles the driver session, has pojo methods and utitilities 
   # 2. com.test.pageclass 
            - All the web element properties and actual business logic is handled here
   # 3. com.test.runner
            - The TestNG runner file, where we configure which tag to run, where is feature files and step defenition located, and the report path
   # 4. com.test.stepdefs
            - The step defintions / the implementations of the feature files are present here
   
What "src/test/resouces" got
  # 1. data
        - Test data in json format based on environment configured in pom file
  # 2. features
        - Cucumber Test Feature files in BDD Format
  # 3. driver 
        - chromedriver and firefox driver (geckodriver) has been preloaded in framework for mac OS


Added Features for Mobile
Pre-requisite:
  # 1. Start the appium session manually
  # 2. Setup and avd from android studio and configure the device details in InitDriver class

# That's it! Happy Testing


