package com.example.springgettingstarted;

import com.example.springgettingstarted.db.UserRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles({"test", "testdata"})
public class UserRepositoryTest {
    @Inject
    private UserRepository userRepository;

    @Test
    public void test() {
        assert (userRepository.findById(1) != null)
    }
}
