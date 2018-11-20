package com.example;

import com.example.models.Permission;
import com.example.models.Role;
import com.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;

/**
 * @author kawasima
 * @author tada
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PersistenceContext
    EntityManager em;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Add Data of Permission, Role, and User
     */
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Permission readIssue =  new Permission("readIssue");
        Permission writeIssue = new Permission("writeIssue");
        Permission manageUser = new Permission("manageUser");

        em.persist(readIssue);
        em.persist(writeIssue);
        em.persist(manageUser);

        Role adminRole = new Role("admin");
        adminRole.setPermissions(new HashSet<>() {{
            add(readIssue);
            add(writeIssue);
            add(manageUser);
        }});
        em.persist(adminRole);

        Role developerRole = new Role("developer");
        developerRole.setPermissions(new HashSet<>() {{
            add(readIssue);
            add(writeIssue);
        }});
        em.persist(developerRole);

        Role guestRole = new Role("guest");
        guestRole.setPermissions(new HashSet<>() {{
            add(readIssue);
        }});
        em.persist(guestRole);

        User guestUser = new User("guest", "guest", "guest", passwordEncoder.encode("guest"));
        guestUser.setRoles(new HashSet<>(){{
            add(guestRole);
        }});
        em.persist(guestUser);

        User developerUser = new User("developer", "developer", "developer", passwordEncoder.encode("developer"));
        developerUser.setRoles(new HashSet<>(){{
            add(developerRole);
        }});
        em.persist(developerUser);

        User adminUser = new User("admin", "admin", "admin", passwordEncoder.encode("admin"));
        adminUser.setRoles(new HashSet<>(){{
            add(adminRole);
        }});
        em.persist(adminUser);
    }

}
