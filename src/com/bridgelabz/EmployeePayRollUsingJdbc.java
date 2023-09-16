package com.bridgelabz;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeePayRollUsingJdbc {
    static final String DB_URL = "jdbc:mysql://localhost:3306/Product_Database";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        //Queries
        String retrieveDataFromEmployee = "select * from employee";
        String retrieveDataFromDepartment = "select * from department";
        String retrieveDataFromSalary = "select * from salary";

        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //select * from employee result
            preparedStatement = conn.prepareStatement(retrieveDataFromEmployee);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int empId = rs.getInt("empid");
                String empName = rs.getString("empname");
                String empAddress = rs.getString("empaddress");

                System.out.println("id="+empId);
                System.out.println("Emp_Name="+empName);
                System.out.println("Emp_Address="+empAddress);
                System.out.println("-------------------------");
            }

            System.out.println();
            //select * from department
            preparedStatement = conn.prepareStatement(retrieveDataFromDepartment);
            ResultSet rs1 = preparedStatement.executeQuery();
            while(rs1.next()){
                int deptId = rs1.getInt("deptid");
                String deptName = rs1.getString("deptname");
                String empId = rs1.getString("empid");

                System.out.println("Dept_Id="+deptId);
                System.out.println("Dept_Name="+deptName);
                System.out.println("emp_Id="+empId);
                System.out.println("-------------------------");
            }


            System.out.println();
            //select * from salary
            preparedStatement = conn.prepareStatement(retrieveDataFromSalary);
            ResultSet rs2 = preparedStatement.executeQuery();
            while(rs2.next()){
                int id = rs2.getInt("id");
                String salary = rs2.getString("salary");
                String empId = rs2.getString("empid");

                System.out.println("id="+id);
                System.out.println("salary="+salary);
                System.out.println("empId="+empId);
                System.out.println("-------------------------");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
