import java.util.Scanner;
import java.util.regex.Pattern;


public class Assignment7
{
    public static void main(String args[])
    {
        Employee[] employees = new Employee[3];

        // Salaried employee
        employees[0] = new SalariedEmployee("Daniel", "Choi",
                new Address("11500 Rainbow Dr.", "Rockville", "MD", "20855"),
                new AssignmentDate(13, 1, 2008), employees, 75000.0);

        // Hourly Employee (under 40 hours)
        employees[1] = new HourlyEmployee("Steve", "Scissorhands",
                new Address("11300 Crystal Ave.", "Washington", "DC", "20002"),
                new AssignmentDate(13,4, 20012), employees, 15, 35);

        // Hourly Employee (over 40 hours)
        employees[2] = new HourlyEmployee("John", "Travolta",
                new Address("11124 Stone Ridge Circle.", "New York", "NY", "10001"),
                new AssignmentDate(2, 1, 2016), employees, 25, 55);

        // Displays all employee information
        for (Employee e: employees)
        {
            System.out.println("------- EMPLOYEE INFORMATION --------");
            System.out.println(e.displayEmployeeInformation());
            System.out.printf("%s\n", e.getPay());
            System.out.println("------- EMPLOYEE INFORMATION -------");
            System.out.println();
        }
    }
}

class Address
{
    private String street;   // stores number and street
    private String city;     // stores city
    private int state;       // stores state and corresponding index
    private String zip;      // stores zip code

    private static final int zip_digit_min = 48; // ASCII '0'
    private static final int zip_digit_max = 57; // ASCII '9'
    private static final int charnum_req = 5;

    public static final String[][] STATES = {
            {"AK", "Arkansas"},
            {"AL", "Alabama"},
            {"AZ", "Arizona"},
            {"CA", "California"},
            {"CO", "Colorado"},
            {"CT", "Connecticut"},
            {"DE", "Delaware"},
            {"FL", "Florida"},
            {"GA", "Georgia"},
            {"HI", "Hawaii"},
            {"ID", "Idaho"},
            {"IL", "Illinois"},
            {"IN", "Indiana"},
            {"IA", "Iowa"},
            {"KS", "Kansas"},
            {"KY", "Kentucky"},
            {"LA", "Louisiana"},
            {"ME", "Maine"},
            {"MD", "Maryland"},
            {"MA", "Massachusetts"},
            {"MI", "Michigan"},
            {"MN", "Minnesota"},
            {"MS", "Mississippi"},
            {"MO", "Missouri"},
            {"MT", "Montana"},
            {"NE", "Nebraska"},
            {"NV", "Nevada"},
            {"NH", "New Hampshire"},
            {"NJ", "New Jersey"},
            {"NM", "New Mexico"},
            {"NY", "New York"},
            {"NC", "North Carolina"},
            {"ND", "North Dakota"},
            {"OH", "Ohio"},
            {"OK", "Oklahoma"},
            {"OR", "Oregon"},
            {"PA", "Pennsylvania"},
            {"RI", "Rhode Island"},
            {"SC", "South Carolina"},
            {"SD", "South Dakota"},
            {"TN", "Tennessee"},
            {"TX", "Texas"},
            {"UT", "Utah"},
            {"VT", "Vermont"},
            {"VA", "Virginia"},
            {"WA", "Washington"},
            {"WV", "West Virginia"},
            {"WI", "Wisconsin"},
            {"WY", "Wyoming"},
            {"DC", "District of Colombia"},
            {"PR", "Puerto Rico"},
    };

    public Address(String streetStr,
                   String cityStr,
                   String stateStr,
                   String zipStr)
    {
        setStreetAddress(streetStr);
        setCity(cityStr);
        try {
            setState(isStateValid(stateStr));
        } catch (InvalidAddressEntryError err) {

        }
        setZipCode(zipStr);
    }

    public Address(String streetStr,
                   String cityStr,
                   int stateIndexInt,
                   String zipStr )
    {
        setStreetAddress(streetStr);
        setCity(cityStr);
        setState(stateIndexInt);
        setZipCode(zipStr);
    }

    // Display Address in US Format
    public String toString()
    {
        return getStreetAddress() + "\n" +
                getCity() + ", " + getStateAbbrev() + " " + getZipCode();
    }


    public String getStreetAddress()
    {
        return street;
    }

    private void setStreetAddress(String a)
    {
        street = Address.capitalize(a);
    }

    private void setState(int stateindex)
    {
        state = stateindex;
    }


    public String getStateFull()
    {
        return Address.STATES[state][1];
    }

    public String getStateAbbrev()
    {
        return Address.STATES[state][0];
    }

    public String getCity()
    {
        return city;
    }

    private void setCity(String c)
    {
        city = Address.capitalize(c);
    }

    public String getZipCode()
    {
        return zip;
    }

    private void setZipCode(String z)
    {
        zip = z;
    }

    /// Constructor Method

    public static Address createAddressByConsoleInput(Scanner input)
    {
        boolean addressValid = false, cityValid = false, zipValid = false;

        String addr = new String();
        String city = new String();
        int state = -1;
        String zip = new String();

        // Prompts for Address Entry
        while(!addressValid)
        {
            try{
                System.out.print("Enter Address: ");
                addr = input.nextLine();
                addressValid = Address.isStreetAddressValid(addr);
            } catch (InvalidAddressEntryError e) {
                System.out.println(e.getMessage());
            }
        }

        while(!cityValid){
            try {
                System.out.print("Enter City: ");
                city = input.nextLine();
                cityValid = isCityValid(city);
            } catch (InvalidAddressEntryError e) {
                System.out.println(e.getMessage());
            }
        }

        while(state < 0)
        {
            try{
                System.out.print("Enter State (i.e. MI or Michigan): ");
                state = Address.isStateValid(input.nextLine());
            } catch (InvalidAddressEntryError e) {
                System.out.println(e.getMessage());
            }
        }

        while(!zipValid)
        {
            try {
                System.out.print("Enter 5-digit Zip Code : ");
                zip = input.nextLine();
                zipValid = Address.isZipValid(zip);
            } catch (InvalidAddressEntryError e){
                System.out.println(e.getMessage());
            }
        }

        input.reset();
        return new Address(addr, city, state, zip);
    }

    // Check Street Address Validity
    public static boolean isStreetAddressValid(String a)
            throws InvalidAddressEntryError
    {
        // Validates House Number or Address
        if (a.length() < 10)
        {
            String msg = "The address \"" + a + "\" is invalid. Please enter a new address.";
            throw new InvalidAddressEntryError(msg);
        }
        return true;
    }

    // Check City Validity
    public static boolean isCityValid(String c) throws InvalidAddressEntryError
    {
        if (c.length() < 10)
        {
            String msg = "The city \"" + c + "\" is invalid. Please enter a new city.";
            throw new InvalidAddressEntryError(msg);
        }
        return true;
    }

    // Check Zip Code Validity
    public static boolean isZipValid(String z) throws InvalidAddressEntryError
    {
        // Check Character Number
        if (z.length() != charnum_req)
        {
            String msg = "Zip code " + z + " requires " + charnum_req + " characters. " + z.length() + " characters were entered.";
            throw new InvalidAddressEntryError(msg);
        }

        for (char d: z.toCharArray())
        {

            if (d >= zip_digit_min && d <= zip_digit_max)
            {
                continue;
            } else {
                String msg = "Zip code " + z + " contains an illegal character. " + "Each character must be 0-9.";
                throw new InvalidAddressEntryError(msg);
            }
        }

        return true;
    }

    // Checks State Entry Validity
    public static int isStateValid(String state)
            throws InvalidAddressEntryError
    {

        String s = capitalize(state.toLowerCase());

        for (int i = 0; i < STATES.length; i++)
        {
            if (s.toUpperCase().equals(STATES[i][0]) || s.equals(STATES[i][1]))
            {

                return i;
            }
        }

        String msg = "State \"" + s + "\" not found.";
        throw new InvalidAddressEntryError(msg);
    }

    public static String capitalize(String s)
    {

        char tempS = s.toUpperCase().toCharArray()[0];
        char[] tempLowerS = s.toLowerCase().toCharArray();
        tempLowerS[0] = tempS;

        String output = new String();
        for (char c : tempLowerS )
        {
            output+=c;
        }

        return output;
    }

}

class InvalidAddressEntryError extends Exception
{
    private static final long serialVersionUID = -2993851664632662067L;

    public InvalidAddressEntryError(String msg)
    {
        super(msg);
    }
}

class AssignmentDate {
    private int month, day, year;
    private static final int minyear = 1900;
    private static final int maxyear = 2020; // [TODO] set based on system clock

    // Store # of Days per Month
    public static final int[] DaysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // Store Month and Month Abbreviations
    public static final String[][] Months = {
            {"JAN", "January"}, {"FEB", "February"}, {"MAR", "March"}, {"APR", "April"},
            {"MAY", "May"}, {"JUN", "June"}, {"JUL", "July"}, {"AUG", "August"},
            {"SEP", "September"}, {"OCT", "October"}, {"NOV", "November"}, {"DEC", "December"}
    };

    AssignmentDate(int d, int m, int y)
    {
        day = d;
        month = m;
        year = y;
    }

    // Output HireDate as DD-MMM-YYYY format
    public String toString()
    {
        return day + "-" + Months[month][0] + "-" + year;
    }

    // Return Days in Month depending on Year
    public static int getDaysInMonth( int m, int y)
    {
        if (isLeapYear(y) && m == 1)
        {
            return 29;
        } else {
            return DaysInMonth[m];
        }
    }
    // Checks Valid Date
    public static AssignmentDate enterAndValidateDate(Scanner input)
    {
        boolean yearValid = false;
        boolean monthValid = false;
        boolean dayOfMonthValid = false;

        int year = 0;
        int month = -1;
        int day = 0;

        while(!yearValid)
        {
            try {
                System.out.print("Enter year hired: ");
                year = input.nextInt();
                yearValid = isYearValid(year);

            } catch (InvalidAssignmentDateError e) {
                System.out.println(e.getMessage());
            }
        }

        // Only Valid Month Dates
        while(!monthValid)
        {
            try {
                System.out.print("Enter month hired (1-12): ");
                month = input.nextInt() - 1;
                monthValid = isMonthValid(month);

            } catch (InvalidAssignmentDateError e) {
                System.out.println(e.getMessage());
            }
        }

        // Only Valid Days of the Month
        while(!dayOfMonthValid)
        {
            try {
                System.out.print("Enter day (1-" + getDaysInMonth(month, year) + "): ");
                day = input.nextInt();
                dayOfMonthValid = isDayOfMonthValid(day, month, year);
            } catch (InvalidAssignmentDateError e) {
                System.out.println(e.getMessage());
            }
        }

        input.nextLine();
        return new AssignmentDate(day, month, year);
    }

    // Leap Year determination
    public static boolean isLeapYear(int y)
    {
        return ((y %4 == 0) && (y % 100 !=0) || (y % 400 == 0));
    }

    // Valid Year Check
    public static boolean isYearValid(int y) throws InvalidAssignmentDateError
    {
        if (y >= minyear && y <= maxyear)
        {
            return true;
        } else {
            String msg = "Invalid year: " + y + ". Year must be between " + minyear + " and " + maxyear;
            throw new InvalidAssignmentDateError(msg);
        }
    }

    // Valid Month Check
    public static boolean isMonthValid(int m) throws InvalidAssignmentDateError
    {
        if (m >= 0 && m <= 11)
        {
            return true;
        } else {
            String msg = "Invalid month: " + (m) + ". Month must be between 1 and 12.";
            throw new InvalidAssignmentDateError(msg);
        }
    }

    public static boolean isDayOfMonthValid(int d, int m, int y)
            throws InvalidAssignmentDateError
    {
        // Match Month and Date match validity
        if (d >= 1 && d <= getDaysInMonth(m,y))
        {
            return true;
        } else {
            String msg = "Day out-of-range: " + d + ". " + AssignmentDate.Months[m][1] + " " + y + " has " + getDaysInMonth(m,y);
            throw new InvalidAssignmentDateError(msg);
        }
    }

}

class InvalidAssignmentDateError extends Exception
{
    private static final long serialVersionUID = 1L;

    public InvalidAssignmentDateError(String msg)
    {
        super(msg);
    }
}

class Employee
{

    private int employeeId;
    private AssignmentDate hireDate;
    private Address address;
    private String firstName;
    private String lastName;
    public static final int employeeid_start = 100000;

    public Employee(String first,
                    String last,
                    Address addr,
                    AssignmentDate date,
                    Employee[] employees)
    {
        setFirstName(first);
        setLastName(last);
        address = addr;
        hireDate = date;
        setEmployeeId(generateEmployeeID(employees));
    }

    public Employee(Scanner input, Employee[] employees)
    {
        firstName = "";
        lastName = "";
        boolean firstNameValid = false;
        boolean lastNameValid = false;

        // Obtain First Name
        while(!firstNameValid)
        {
            try{
                System.out.print("Enter employee's first name: ");
                firstName = input.nextLine();
                firstNameValid = isNameValid(firstName);
            } catch (InvalidEmployeeError e) {
                System.out.println(e.getMessage());
            }
        }

        // Obtain Last Name
        while (!lastNameValid)
        {
            try{
                System.out.print("Enter employee's last name: ");
                lastName = input.nextLine();
                lastNameValid = isNameValid(lastName);
            } catch (InvalidEmployeeError e) {
                System.out.println(e.getMessage());
            }
        }

        input.reset();

        setFirstName(firstName);
        setLastName(lastName);
        setAddress(input);
        setHireDate(input);
        setEmployeeId(generateEmployeeID(employees));
    }

    // Display Employee Information
    public String displayEmployeeInformation()
    {
        return displayFullName() + " (ID: " + getEmployeeId() + ")\n" + "Hire date: " + hireDate.toString() + "\n" + address.toString();
    }
    //Display Full Name including First and Last Names
    public String displayFullName()
    {
        return getFirstName() + " " + getLastName();
    }

    public void setFirstName(String s)
    {
        firstName = s;
    }

    public String getFirstName()
    {
        return firstName;
    }
    public void setLastName(String s)
    {
        lastName = s;
    }

    // Pay Method
    public String getPay()
    {
        return "";
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setHireDate(Scanner input)
    {
        hireDate = AssignmentDate.enterAndValidateDate(input);
    }

    public void setAddress(Scanner input)
    {
        address = Address.createAddressByConsoleInput(input);
    }

    public void setEmployeeId(int i)
    {
        employeeId = i;
    }
    public int getEmployeeId()
    {
        return employeeId;
    }


    // Check Name Validity
    public static boolean isNameValid(String name) throws InvalidEmployeeError
    {

        if(Pattern.matches(".*\\W+.*", name))
        {
            String msg = "Invalid characters detected in name.";
            throw new InvalidEmployeeError(msg);
        }
        return true;
    }

    // Generates Unique IDs for sequential employee information

    public static int generateEmployeeID(Employee[] employees)
    {
        int id = employeeid_start;

        for (Employee e: employees)
        {
            if (e == null)
            {
                return id;
            } else {
                if (e.getEmployeeId() >= id)
                {
                    id++;
                }
            }
        }
        return id;
    }
}

class InvalidEmployeeError extends Exception
{
    private static final long serialVersionUID = -2L;

    public InvalidEmployeeError(String msg)
    {
        super(msg);
    }
}

class HourlyEmployee extends Employee
{
    private double hourlyRate = 0.0;
    private double hoursWorked = -1.0;
    private static final double hours_min_worked = 0.0;
    private static final double max_reg_hours_week = 40.0;
    private static final double max_possible_hrs = 100.0;
    private static final double rate_overtime = 1.5;
    private static final double min_wage = 15.0;

    public HourlyEmployee(String first,
                          String last,
                          Address addr,
                          AssignmentDate date,
                          Employee[] employees,
                          double hourlyr,
                          double hourswkd)
    {
        super(first, last, addr, date, employees);
        try {
            setHourlyRate(hourlyr);
            setHoursWorked(hourswkd);
        } catch (InvalidEmployeeError err) {

        }
    }

    public HourlyEmployee(Scanner input, Employee[] employee)
    {
        super(input, employee);

        while(getHourlyRate() < min_wage)
        {
            try {
                System.out.print("Enter employee's hourly rate: ");
                setHourlyRate(input.nextDouble());
            } catch (InvalidEmployeeError err) {
                System.out.println(err.getMessage());
            }
        }

        while(!(hoursWorked >= hours_min_worked && hoursWorked <= max_possible_hrs))
        {
            try {
                System.out.print("Enter number of hours worked: ");
                setHoursWorked(input.nextDouble());
            } catch (InvalidEmployeeError err) {
                System.out.println(err.getMessage());
            }
        }

        input.reset();
    }

    public double getHourlyRate()
    {
        return hourlyRate;
    }
    // Employee Pay based on Amount of Time Worked
    public String getPay()
    {
        if (hoursWorked <= hours_min_worked)
        {
            return "Employee did not work this pay period.";
        }

        double pay = Math.min(max_reg_hours_week, hoursWorked) * hourlyRate + Math.max(0,hoursWorked - max_reg_hours_week ) * rate_overtime * hourlyRate;

        return String.format("Employee worked %.1f hours and earned $%.2f",
                hoursWorked, pay);
    }

    // Check Hourly Rate within Federal Minimum Wage
    public void setHourlyRate(double hr) throws InvalidEmployeeError
    {
        if (hr < min_wage)
        {
            String msg = "Hourly rate is below federal minimum wage!";
            throw new InvalidEmployeeError(msg);
        } else {
            hourlyRate = hr;
        }
    }

    // Get Hours Worked during the Week
    public double getHoursWorked()
    {
        return hoursWorked;
    }

    // Set Hours Worked during the Pay Period
    public void setHoursWorked(double hours) throws InvalidEmployeeError
    {
        if (hours < hours_min_worked || hours > max_possible_hrs)
        {
            String msg = hours + " is an invalid number of hours worked";
            throw new InvalidEmployeeError(msg);
        } else {
            hoursWorked = hours;
        }
    }
}

class SalariedEmployee extends Employee
{
    private double annualSalary;

    public SalariedEmployee(String first,
                            String last,
                            Address addr,
                            AssignmentDate date,
                            Employee[] employees,
                            double salary)
    {
        super(first, last, addr, date, employees);
        try {
            setAnnualSalary(salary);
        } catch (InvalidEmployeeError err) {

        }
    }

    public SalariedEmployee(Scanner input, Employee[] employees)
    {
        super(input, employees);
        while(annualSalary <= 0)
        {
            try {
                System.out.print("Enter annual salary: ");
                setAnnualSalary(input.nextDouble());
                input.reset();
            } catch (InvalidEmployeeError err) {
                System.out.println(err.getMessage());
            }
        }
    }

    // Display Employee Annual Salary
    public double getAnnualSalary()
    {
        return annualSalary;
    }

    public String getPay()
    {
        return String.format("Employee is salaried and earns $%.2f a month",
                annualSalary / 12.0);
    }

    public void setAnnualSalary(double salary) throws InvalidEmployeeError
    {
        if (salary <= 0 )
        {
            String msg = "Invalid salary " + salary + " entered.";
            throw new InvalidEmployeeError(msg);
        } else {
            annualSalary = salary;
        }
    }

    public void printPay()
    {
        System.out.printf("Employee's monthly pay is : $%.2f\n",
                getAnnualSalary() / 12);
    }
}