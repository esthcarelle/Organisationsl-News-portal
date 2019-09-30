import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class News {
    private String news;
    private String type;
    private int id;
    private int departmentId;
    public News(String news){
        this.news=news;
        this.type="general department news";

    }
    public News(String news, int departmentId) {
        this.news= news;
        this.departmentId = departmentId;
        this.type = "department news";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getNews() {
        return news;
    }


    public void setNews(String news) {
        this.news = news;
    }
    public void saveNews(News news) {
        String sql = "INSERT INTO news (news,departmentId,type) VALUES (:news,:departmentId ,:type);";
        try(Connection con = DB.sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(news)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
    }
    public void saveGeneralNews(News news) {
        String sql = "INSERT INTO news (news,type) VALUES (:news,:type);";
        try(Connection con = DB.sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(news)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
    }


    public static List<News> getAllGeneralNews() {
        String sql = "SELECT news FROM news WHERE type='general department news'";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(News.class);
        }

    }
    public static List<News> getAllSpecificNews() {
        String sql = "SELECT * FROM news WHERE type='department news'";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(News.class);
        }

    }
}
