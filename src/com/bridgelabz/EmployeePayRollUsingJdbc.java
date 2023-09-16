package com.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeePayRollUsingJdbc {
    static final String DB_URL = "jdbc:mysql://localhost:3306/payroll_service_db";
    static final String USER = "root";
    static final String PASS = "vinitkulkarni";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        //Queries
        String retrieveDataFromEmployee = "select * from employee";
        String retrieveDataFromDepartment = "select * from department";
        String retrieveDataFromSalary = "select * from salary";
        String updateSalary = "update salary set salary = ? where empid = ?";
        String query1 = "select sum(salary.salary), avg(salary.salary), max(salary.salary), min(salary.salary) from employee inner join salary on employee.empid = salary.empid";

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

            //update salary
            preparedStatement = conn.prepareStatement(updateSalary);
            preparedStatement.setFloat(1, 65000);
            preparedStatement.setInt(2, 1);
            preparedStatement.executeUpdate();

            //find sum,avg,max,min
            preparedStatement = conn.prepareStatement(query1);
            ResultSet rs3 = preparedStatement.executeQuery();
            while(rs3.next()){
                int sum = rs3.getInt("sum(salary.salary)");
                float avgSalary = rs3.getFloat("avg(salary.salary)");
                float maxSalary = rs3.getFloat("max(salary.salary)");
                float minSalary = rs3.getFloat("min(salary.salary)");

                System.out.println("sum="+sum);
                System.out.println("avg="+avgSalary);
                System.out.println("max="+maxSalary);
                System.out.println("min="+minSalary);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}