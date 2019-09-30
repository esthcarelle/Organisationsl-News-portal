import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class Department {

    private int id;
    private String name;
    private String description;
    private int  number_of_employees ;

    public Department(String name, String description, int  number_of_employees ) {
        this.name = name;
        this.description = description;
        this.number_of_employees  =  number_of_employees ;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumber_of_employees () {
        return  number_of_employees ;
    }

    public void setNumber_of_employees (int  number_of_employees ) {
        this. number_of_employees =  number_of_employees ;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void save(Department department) {
        String sql = "INSERT INTO departments (name, description, number_of_employees) VALUES (:name, :description, :number_of_employees );";
        try(Connection con = DB.sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    public static List<Department> getAll() {
        String sql = "SELECT * FROM departments";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Department.class);
        }

        }
    public  static Department findById(int id) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM departments WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }

    }
    public static List<User> getAllUsersByDepartment(int departmentId) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM users WHERE departmentId = :departmentId")
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(User.class);
        }
    }
    public static List<News> getAllNewsByDepartment(int departmentId) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM news WHERE departmentId = :departmentId")
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(News.class);
        }
    }

}
