1.Design and develop a simple web application to enter and store following information with applied rules in data base
  and provide a option to list all details with pagination
  Use any front end ,database .write service and web application with properly handling concurrancy.

I want to enter follwoing information in one screen and store in database.
I

ITEMMAS
----------------------------------------------------
Itemid
Itemname
ItemQty
SALEDETAILS [Table] with below listed columns
----------------------------------------------------
shopdate 
mobileno
Custname
Itemid
Address
State  
Date of birth
Minor
Quantity
price
PAYAMOUNT
emailid

Basic validations for all information
************************************
1.All dates should be with proper date format
2validate mobile number
3.validate emailids
4.validate Item selected must be in Item master.
5.Validate Item selected must be available for sale.

Methods 
1.To create new item in item master
2.To enter sale details and save in sales table.
  -Item need to be selected from item master
  -states need to be selected with fixed list of values 
  -If he is 18 years then mark as Minor 
  -If Minor then she can shop for only RS.1000
  -If he is from maharashtra then then 20% discount on payamount 
3.cancel sale entry
4.Update sale entry in case need to change the entered information
5.Display functionality of sale details 
  -Search be Itemname
  -Search with Customer name
  -Search with Mobile number
  -With amount
  if more than 20 records come in search criretia then apply pagination
6.Create for Displaying 
  Itemwise,Customer wise Total payment amount collected upt last month and current monthend
  i.e Page should display records with following details
  Item NAme,Customer name ,Month end date,Total Payment Collected upto last month ,
  Current month payment,Total payment collected upto current month

7. Top 5 customer based on Payment collected.
8. Top 10 Customers based on number of times they did shoping. 




Details:
1.Front end : Thymleaf
2.API :SpringBoot,Spring MVC, Spring JPA, MySQL
3.How many validations done validations [1-5] =All
4.How many points covered from [1-8]: All
