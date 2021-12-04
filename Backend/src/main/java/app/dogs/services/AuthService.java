package app.dogs.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import app.dogs.Database;
import app.dogs.models.Login;
import app.dogs.models.User;
import spark.Request;
import spark.Response;

public class AuthService extends BaseService {
    private Database db;

    public AuthService() {
        try {
            db = new Database();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object login(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        try {
            Login login = gson.fromJson(request.body(), Login.class);
            User user = db.getUserByName(login.name);

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (!encoder.matches(login.passwordHash, user.passwordHash)) {
                throw new Exception("Inválid username or password!");
            }

            response.status(200);
            return "{ \"status\": \"success\" }";
        } catch (Exception e) {
            response.status(500);
            return "{ \"error\": \"" + e + "\" }";
        }
    }
}
