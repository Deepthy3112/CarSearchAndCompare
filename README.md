This project is designed to:
read the input text file and pick the car regestration numbers using pattern matching -> save them to List of car regestraion numbers in given order -> search each of them on webuyanycare.com website -> compares them to the output file in given order

## getting started
Copy the repo into your local machine and have TestNG plugin installed in IntelliJ or eclipse and have latest Java packages intalled.

## running test
BuyYourCarValuationTest.java is where the required test is defined.
you can use following command to run tests from terminal 
mvn clean test

## report after execution
I used extent reports for report generation and for each test run a new Html is generated with unique timestamp.
Once a test us run, you can find it should generate a new .html under /testExecutionReports folder with latest timestamp.
You can navigate between the sections of the test report if you open it using a browser(for better experience).
