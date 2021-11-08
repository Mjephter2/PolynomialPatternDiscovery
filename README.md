# **Polynomial Pattern Discovery** (CSCI334 Queens College, Fall 2021)

Given a set of pairs *`(Xi, Yi)`*, we can find a polynomial `f` such that  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `f(Xi) = Yi`  
This repo exhibits an implementation of finding such polynomial. Derivation is done using the formula found on page 50 of [Information-Statistical Data Mining: Warehouse Integration with Examples of Oracle
Basics, Bon K. Sy and Arjun K. Gupta](https://books.google.com/books?id=Qwo2mFAn7AEC&dq=bon+sy+data+mining&hl=en&sa=X&ei=hrU-VeuDGZX-yQT2y4HQDQ&ved=0CDEQ6AEwAA)  
![](equation.png)  

Note:  
![image](https://user-images.githubusercontent.com/51377282/140623223-c3999134-69e2-4141-8062-f8843f835b8c.png)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;is represented by  
![image](https://user-images.githubusercontent.com/51377282/140623316-8057e09c-a89c-42c3-a473-fdb5ffc15c2f.png)

The equation equation essentially has 3 terms and we label them as `h1`, `h2` and `h3`. We calculate them seperately and sum them up to get `f`

