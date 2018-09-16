# salesdiscountcalculator
Example project to calculate the different discount of retail sales based on customer type.
This is a Spring Boot 2.0 project.

# Requirements
JDK 1.8+
MAVEN

# Run the test cases
mvn -Dtest=SalesApplicationTests test

# Class diagram 
Refer to 'ClassDiagram.png'


# Key Configuration and Discount calculation approach
Spring Boot will initialize the properties value starting with "config.****" to "PropertiesResource.java" object.
DiscountCalculationService.calculateDiscount() will iterate through the SaleItems. Calculate ActualTotalBill and TotalBillBasedOnCustomerType (Refer to CustomerTypesEnum.java).
Discount % values will be applied based on configuration mentioned in "application.properties".

"SalesApplicationTests" has different methods to test various customer type and Grocery/Non-Grocery types.


