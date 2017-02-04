/*
score: 8.5 + 1(extra credit)
Your coding is good, just be careful with corner case
*/
package assignment2;

/**
 * Created by Rose on 1/21/17.
 * Assignment for your lecture 2. Please finish all the questions under
 * 'Assignment'. Assignment 2 includes 3 interview prepare questions. They
 * are similar to what you will meet during your technical interviews.Write some tests as practice.
 * Please try to think the extra credit question. The deadline of this assignment is 01/26/2017 23:59 PST.
 * Please feel free to contact me for any questions.
 */

class Employee {
    String name;
    int age;
    Gender gender;
    double salary;// salary per month


    public Employee(String name, int age, Gender gender, double salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void raiseSalary(double byPercent) {
        this.salary *= (1+byPercent);
    }
}

enum Gender {
    MALE,
    FEMALE;
}


public class Assignment2 {
    // Assignment

    /**
     * Write a method to calculate the Social Security Tax of an employee and print it.
     * If the salary is less than or equal to 8900, the Social Security Tax is 6.2% of the salary.
     * If the salary is more than 8900, the Social Security Tax is 6.2% of 106,800.
     */
    public double socialSecurityTax(Employee employee) {                               //correct
        if(employee == null) return -1.0;
        return (employee.salary <= 8900 ? employee.salary : 106800) / 100 * 6.2;
    }

    /**
     * Write a method to calculate an employee's contribution for insurance coverage and print it.
     * Amount of deduction is computed as follows:
     * If the employee is under 35, rate is 3% of salary; if the employee is between 35 and 50(inclusive), rate is 4% of salary;
     * If the employee is between 50 and 60(exclusive), rate is 5% of salary; If the employee is above 60, rate is 6% of salary.
     */
    public double insuranceCoverage(Employee employee) {                          //correct
        if(employee == null) return -1.0;
        int age = employee.age;
        double rate = 0;
        if(age < 35) {
            rate = 0.03;
        } else if(age >= 35 && age <= 50 ) {
            rate = 0.04;
        } else if(age > 50 && age < 60){
            rate = 0.05;
        } else {
            rate = 0.06;
        }
        return rate*employee.salary;
    }

    /**
     * Write a method to sort three employees' salary from low to high, and then print their name in order.
     * For example, Alice's salary is 1000, John's salary is 500, Jenny's salary is 1200, you should print:
     * John Alice Jenny
     */
    public void sortSalary(Employee e1, Employee e2, Employee e3) {                 //excellent!
        if(e1 == null || e2 == null || e3 == null) return;
        Double max = 0.0;
        Employee[] es = {e1, e2, e3}; 
        int len = es.length;
        for(int i = 1; i < len; i++) {
            for(int j = i; j > 0 && es[j].salary < es[j-1].salary; j--) {
                Employee temp = es[j];
                es[j] = es[j-1];
                es[j-1] = temp;
            }
        }
        for(int i = 0; i < len; i++) System.out.print(es[i].name + " ");
    }

    /**
     * Write a method to raise an employee's salary to three times of his/her original salary.
     * Eg: original salary was 1000/month. After using this method, the salary is 3000/month.
     * Do not change the input of this method.
     * Try to add a new method in Employee class: public void raiseSalary(double byPercent)
     */
    public void tripleSalary(Employee employee) {                     //correct
        if(employee != null) employee.salary *= 3;     
    }
    


    //Interview prepare questions

    /**
     * Write a method to determine whether a number is prime
     */
    public boolean isPrime(int n) {                                    //excellent!
        if(n <= 1) return false;
        if(n <= 3) return true;
        int i = 2;
        while(i <= n/2 && n % i != 0) {i++;} 
        return i > n/2;
    }

    /**
     * Given a non-negative integer n, repeatedly add all its digits until the
     * result has only one digit. For example: Given n = 38, the process is
     * like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
     */
    public int addDigits(int n) {                     //wrong: if n is 19, expected answer is 1, your output is 10.
        if(n < 0) return -1;
        int sum = 0;
        while(n > 0) {
            sum += (n % 10);
            n /= 10;
        }
        return sum;
    }

    /**
     * Write a program to check whether a given number is an ugly number. Ugly
     * numbers are positive numbers whose prime factors only include 2, 3, 5.
     * For example, 6, 8 are ugly, while 14 is not ugly since it includes
     * another prime factor 7. Note that 1 is typically treated as an ugly
     * number.
     */
    public boolean isUgly(int n) {                   //miss one corner case: if n is 1, expected return is true, your input is false
        if(n <= 1) return false;
        while(n > 1 && n % 2 == 0) {n /= 2;}
        while(n > 1 && n % 3 == 0) {n /= 3;}
        while(n > 1 && n % 5 == 0) {n /= 5;}
        return n == 1;
    }

    //Extra credit

    /**
     * I have written some code below. What I want is to swap two Employee objects.
     * One is Jenny and one is John. But after running it, I got the result below:
     * Before: a=Jenny
     * Before: b=John
     * After: a=Jenny
     * After: b=John
     * There is no change after swap()! Do you know the reason why my swap failed?
     * Write your understanding of the reason and explain it.
     */
    /*
     write your understanding here.
        It is because Java is call-by-value instead of call-by-reference. The parameters are not addresses while they are just some copy of references.
    */
    public static void main(String[] args) {                                                                       //correct
        Employee a = new Employee("Jenny", 20, Gender.FEMALE, 2000);
        Employee b = new Employee("John", 30, Gender.MALE, 2500);
        System.out.println("Before: a=" + a.getName());
        System.out.println("Before: b=" + b.getName());
        swap(a, b);
        System.out.println("After: a=" + a.getName());
        System.out.println("After: b=" + b.getName());
        
        
        //my tests
        Employee c = new Employee("Dohn", 30, Gender.MALE, 4400);
        Assignment2 a2 = new Assignment2();
        a2.sortSalary(c, a, b);       
        System.out.println("\n"+a2.addDigits(123123123));
        System.out.println(a2.isUgly(105));
    }

    public static void swap(Employee x, Employee y) {
        Employee temp = x;
        x = y;
        y = temp;
    }
}
