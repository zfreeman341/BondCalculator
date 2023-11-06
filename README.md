# Bond Calculator GUI

This Java application allows users to calculate the price or yield of a bond with a user-friendly Swing GUI.

# Prerequisites:
  - Java (Java 8 or later)
  - Maven

# Step 1: Complile the Code

When using the program for the first time, or after making any changes to the program make sure to compile the code. From the root directory, use the following in the command line interface:

  mvn clean compile

This will compile all Java files.

# Step 2: Running the Application

Once compiled, the code can be run from the root directory by typing the following into the command line interface from the root directory:

  mvn exec:java

# OPTIONAL Step 3: Running Tests

To run the test suite, run:

  mvn test

# Directory Structure:
  - The primary code for this program can be found at the file path /src/main/java/com/zfreeman341/bondCalculator.
  - Test suites can be found at /src/test/java/com/zfreeman341/bondCalculator.
  - This program uses Maven as its build automation tool. The configuration for this project can be found at pom.xml in the root directory.


# Input Descriptions:
  - Coupon: The annual interest rate of the bond. This is expressed as a decimal. For example, 0.05 represents a 5% coupon rate.
  - Years: The number of years until the bond matures.
  - Face Value: The par or nominal value of the bond.
  - Rate: In 'Price Calculator' mode, this is the discount or interest rate, expressed as a decimal. For example, 0.05 represents a 5% interest rate.
  - Price: In 'Yield Calculator' mode, this represents the current market price of the bond.

# Usage Tips
  - Make sure to input valid numbers to avoid errors.
  - Use the "Switch to... Calculator" button to toggle between calculating price and bond yields.
.
# Contact Information
  - If you have any issues or questions about this calculator, feel free to reach out to me at zfreeman341@gmail.com.