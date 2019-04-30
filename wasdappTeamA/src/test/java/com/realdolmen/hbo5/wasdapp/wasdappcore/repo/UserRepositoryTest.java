package com.realdolmen.hbo5.wasdapp.wasdappcore.repo;

import com.google.common.truth.Truth;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void user() {
        System.out.println(userRepository.findById(1L).get().getName());
        List<UserWassdapp> test = new ArrayList<>();
        test = userRepository.findAll();

        for(UserWassdapp e : test){
            System.out.println(e.getName());
        }
        Truth.assertThat(userRepository.findById(1L).isPresent());
    }
}
