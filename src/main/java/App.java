import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class App {
    public static void main(String[] args) {
        get("/departments/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "department-form.hbs");
        },new HandlebarsTemplateEngine());
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("news", News.getAllGeneralNews());
            return new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());

        post("/departments", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String departmentName = request.queryParams("departmentName");
            String description = request.queryParams("description");
            int numberOfEmployees = Integer.parseInt(request.queryParams("numberOfEmployees"));
            Department newDepartment = new Department(departmentName, description, numberOfEmployees);
            newDepartment.save(newDepartment);
            model.put("departments",newDepartment);
            response.redirect("/departments");

            return new ModelAndView(model, "department.hbs");
        }, new HandlebarsTemplateEngine());
        get("/departments", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("departments", Department.getAll());
            return new ModelAndView(model, "department.hbs");
        },new HandlebarsTemplateEngine());


        get("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("departments", Department.getAll());
            model.put("users", User.getAll());
            return new ModelAndView(model, "users.hbs");
        },new HandlebarsTemplateEngine());


        post("/users/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String userName = request.queryParams("userName");
            int departmentId = Integer.parseInt(request.queryParams("departmentId"));
            String position = request.queryParams("position");
            String role = request.queryParams("role");
            User newUser = new User(userName, role,position,departmentId);
            newUser.save(newUser);
            response.redirect("/users");
            return new ModelAndView(model, "users.hbs");
        }, new HandlebarsTemplateEngine());
        get("/departments/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfDepartmentToFind = Integer.parseInt(req.params("id")); //new
            Department foundDepartment = Department.findById(idOfDepartmentToFind);
            model.put("Department", foundDepartment);
            List<User> allUsersByDepartment = Department.getAllUsersByDepartment(idOfDepartmentToFind);
            List<News> allNewsByDepartment = Department.getAllNewsByDepartment(idOfDepartmentToFind);
            model.put("users", allUsersByDepartment);
            model.put("news", allNewsByDepartment);

            model.put("departments", Department.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "department-detail.hbs"); //new

        }, new HandlebarsTemplateEngine());
        get("/news", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("departments", Department.getAll());
            model.put("news", News.getAllSpecificNews());
            return new ModelAndView(model, "news-form.hbs");
        },new HandlebarsTemplateEngine());

        post("/news", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String news = request.queryParams("news");
            int departmentId = Integer.parseInt(request.queryParams("departmentId"));
            News newNews = new News(news, departmentId);
            newNews.saveNews(newNews);
            model.put("news", News.getAllSpecificNews());
            response.redirect("/news");
            return new ModelAndView(model, "news-form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/general/news", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("news", News.getAllGeneralNews());
            return new ModelAndView(model, "generalNews.hbs");
        },new HandlebarsTemplateEngine());

        post("/general/news", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String news = request.queryParams("news");
            News newNews = new News(news);
            newNews.saveGeneralNews(newNews);
            model.put("news", News.getAllGeneralNews());
            response.redirect("/general/news");
            return new ModelAndView(model, "generalNews.hbs");
        }, new HandlebarsTemplateEngine());


        get("/users/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfUserToFind = Integer.parseInt(req.params("id")); //new
            User userFound = User.findById(idOfUserToFind);
            model.put("user", userFound);

            return new ModelAndView(model, "user-detail.hbs"); //new

        }, new HandlebarsTemplateEngine());

    }



}
