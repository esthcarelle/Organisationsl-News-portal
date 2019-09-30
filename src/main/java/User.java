import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String userName;
    private int departmentId;
    private String role;
    private String position;

    public User(String userName, String role,String position, int departmentId) {
        this.userName = userName;
        this.departmentId = departmentId;
        this.role = role;
        this.position=position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return departmentId == user.departmentId &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, departmentId, role);
    }

    public static User findById(int id) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM users WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(User.class); //fetch an individual item
        }
    }
    public void save(User user) {
        String sql = "INSERT INTO users (userName,role,position,departmentId) VALUES (:userName,:role, :position, :departmentId );";
        try(Connection con = DB.sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    public static List<User> getAll() {
        String sql = "SELECT * FROM users";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(User.class);
        }

    }




}
