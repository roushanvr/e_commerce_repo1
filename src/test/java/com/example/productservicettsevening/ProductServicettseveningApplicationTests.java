package com.example.productservicettsevening;

import com.example.productservicettsevening.inheritanceexamples.joinedTable.JTMentorRepository;
import com.example.productservicettsevening.inheritanceexamples.joinedTable.Mentor;
import com.example.productservicettsevening.inheritanceexamples.joinedTable.User;
import com.example.productservicettsevening.inheritanceexamples.joinedTable.JTUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServicettseveningApplicationTests {
    @Autowired
    private JTUserRepository userRepository;
    @Autowired
    private JTMentorRepository mentorRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testDifferentInheritances(){
        User user= new User();
        user.setEmail("roushan@scaler.com");
        user.setPassword("password");
        userRepository.save(user);

        Mentor mentor=new Mentor();
        mentor.setEmail("naman@scaler.com");
        mentor.setPassword("12345678");
        mentor.setNumberOfSessions(50);
        mentor.setNumberOfMentees(4);
        mentorRepository.save(mentor);

    }



}
