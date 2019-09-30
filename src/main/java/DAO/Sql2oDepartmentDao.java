package DAO;

//import Department;
//import News;
//import User;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentDao {
//
//    @Override
//    public void add(Department department) {
//        String sql = "INSERT INTO departments (departmentname, description, numberofemployees) VALUES (:departmentName, :description, :numberOfEmployees);";
//        try(Connection con = sql2o.open()){
//            int id = (int) con.createQuery(sql, true)
//                    .bind(department)
//                    .executeUpdate()
//                    .getKey();
//            department.setId(id);
//        } catch(Sql2oException ex){
//            System.out.println(ex);
//        }
//    }
//
//    @Override
//    public List<Department> getAll() {
//        return null;
//    }
//
//    @Override
//    public Department findById(int id) {
//        return null;
//    }
//
//    @Override
//    public List<User> getUsers(int departmentId) {
//        return null;
//    }
//
//    @Override
//    public List<News> getNews(int departmentId) {
//        return null;
//    }
//
//    @Override
//    public void update(int id, String departmentName, String description, int numberOfEmployees) {
//
//    }
//
//    @Override
//    public void deleteById(int id) {
//
//    }
//
//    @Override
//    public void clearAll() {
//
//    }
}
