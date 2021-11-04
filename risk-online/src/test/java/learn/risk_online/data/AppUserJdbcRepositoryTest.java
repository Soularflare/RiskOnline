package learn.risk_online.data;

import learn.risk_online.models.AppUser;
import learn.risk_online.models.Microtransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AppUserJdbcRepositoryTest {

    @Autowired
    AppUserRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        //Microtransaction microt = makeMicrot(1);
        assertEquals(repository.findAll().size(),2);
    }

    @Test
    void shouldFindById() {
        AppUser user = new AppUser("4d980627-3b3c-11ec-8708-0242ac110002", "user_name", "password_hash",false);
        AppUser actual = repository.findById("4d980627-3b3c-11ec-8708-0242ac110002");
        assertEquals(actual.getPassword(),user.getPassword());
    }

    @Test
    void shouldAdd() {
        AppUser user = new AppUser("hi","userfake","pass",false);
        assertTrue(repository.add(user));
    }

    @Test
    void shouldUpdate() {
        AppUser user = new AppUser("hi","userfakethesecond","pass",false);
        assertTrue(repository.update(user));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById("4d980627-3b3c-11ec-8708-0242ac110002"));
    }

}