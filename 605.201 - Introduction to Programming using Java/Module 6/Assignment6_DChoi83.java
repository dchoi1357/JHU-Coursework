/**
 * Module 6 Assignment
 *
 * Employee information database that includes:
 * Employee number, Name, Address, and Hire Date
 * At Program exit, all employee information is displayed
 *
 * @author: Daniel Choi
 * Date created: 13 October 2018
 **/
import java.util.Scanner;

public class Module6 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int empCount;

        System.out.println("Please enter the number of employees: ");
        empCount = input.nextInt();
        input.nextLine();

        Employee[] employeeList = new Employee[empCount];

        for (int i = 0; i < empCount; i++) {
            Employee n1 = new Employee();
            Name name = new Name();
            Date hireDate = new Date();
            Address address = new Address();

            System.out.println("Enter first name of the employee: ");
            name.setFirstName(input.nextLine());
            System.out.println("Enter last name of the employee: ");
            name.setLastName(input.nextLine());
            n1.setName(name);

            System.out.println("Enter the address information - Street, City, State, Zip Code ");
            System.out.println("1) Enter the Street Number and Address: ");
            address.setStreet(input.nextLine());
            System.out.println("2) Enter the City: ");
            address.setCity(input.nextLine());
            System.out.println("3) Enter the State in 2-letter format: ");
            address.setState(input.nextLine());
            System.out.println("4) Enter the 5-digit zipcode: ");
            address.setZipCode(input.nextLine());
            n1.setAddress(address);

            System.out.println("Enter the Hiring date information in DD/MM/YYYY format below: ");
            System.out.println("Enter employee's hire date: ");
            hireDate.setDay(Integer.parseInt(input.nextLine()));
            System.out.println("Enter employee's hire month: ");
            hireDate.setMonth(Integer.parseInt(input.nextLine()));
            System.out.println("Enter employee's hire year: ");
            hireDate.setYear(Integer.parseInt(input.nextLine()));
            n1.setHireDate(hireDate);

            employeeList[i] = n1;
        }

        for (Employee n : employeeList) {
            System.out.println(n.getName());
            System.out.println(n.getAddress());
            System.out.println(n.getHireDate());
            System.out.println("---------------------------------------------");
        }
        input.close();
    }
}

class Employee
{
    private Name name;
    private Address address;
    private Date dateHire;

    public Name getName()
    {
        return name;
    }
    public void setName(Name name)
    {
        this.name = name;
    }
    public Address getAddress()
    {
        return address;
    }
    public void setAddress(Address address)
    {
        this.address = address;
    }
    public Date getHireDate()
    {
        return dateHire;
    }
    public void setHireDate(Date dateHire)
    {
        this.dateHire = dateHire;
    }
}

class Name
{
    private String FirstName;
    private String LastName;
    public Name()
    {
    FirstName = "";
    LastName = "";
    }
    public void setFirstName(String firstname)
    {
        FirstName=firstname;
    }
    public void setLastName(String lastname)
    {
        LastName=lastname;
    }
    public String getFirstName()
    {
        return FirstName;
    }
    public String getLastName()
    {
        return LastName;
    }
    public String toString(){
        return FirstName + " " + LastName;
    }
}

class Address
{
    private String Street;
    private String City;
    private String State;
    private String ZipCode;
    public Address()
    {
        Street = "";
        City = "";
        State="";
        ZipCode="";
    }
    public void setStreet(String streetName)
    {
        Street = streetName;
    }
    public void setCity(String cityName)
    {
        City = cityName;
    }
    public void setState(String stateName)
    {
        State = stateName;
    }
    public void setZipCode(String zipcodeName)
    {
        ZipCode = zipcodeName;
    }
    public String getStreet()
    {
        return Street;
    }
    public String getCity()
    {
        return City;
    }
    public String getState()
    {
        return State;
    }
    public String getZipCode()
    {
        return ZipCode;
    }
    public String toString(){
        return Street + " ," + City + " ," + State + ", " + ZipCode;
    }
}

class Date
{
    private int month;
    private int day;
    private int year;
    public Date()
    {
        month=0;
        year=0;
        day=0;
    }
    public void setDay(int dayOfMonth)
    {
        day = dayOfMonth;
    }
    public void setMonth (int monthOfYear)
    {
        month = monthOfYear;
    }
    public void setYear (int exactYear)
    {
        year = exactYear;
    }
    public int getDay()
    {
        return day;
    }
    public int getMonth()
    {
        return month;
    }
    public int getYear()
    {
        return year;
    }
    public String toString()
    {
        return String.format("%d-%d-%d", year,day, month);
    }
}



