# API Automation Testing Using TestNg Demo

# Setup

- Install Java 8
- Install Maven Maven
- Get the latest Source Code
- Open Terminal or command line cd to the desired folder where the test automation source code needs to be checkout
- Run command git clone https://github.com/autumn-framework/AutumnAPITestNgDemo.git

This will download the latest template source code

To clean and compile the build

- mvn clean compile

To Run test cases 

1. Run Smoke Tests
    - mvn clean install -Dgroups=smoke

2. Run Sanity Tests
    - mvn clean install -Dgroups=sanity

3. Run Regression Test
   - mvn clean install -Dgroups=regression
