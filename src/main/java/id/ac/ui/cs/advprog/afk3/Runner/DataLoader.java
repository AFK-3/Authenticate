package id.ac.ui.cs.advprog.afk3.Runner;
import id.ac.ui.cs.advprog.afk3.model.UserEntity;
import id.ac.ui.cs.advprog.afk3.repository.UserRepository;
import id.ac.ui.cs.advprog.afk3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder);
        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("pass"));
        user.setType("BUYERSELLER");
        user.setName("user");
        user.setAddress("userville");
        user.setPhonenumber("0812121");

        userService.create(user);
    }
}
