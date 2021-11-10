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
        assertEquals(repository.findAll().size(),6);
    }

    @Test
    void shouldFindById() {
        AppUser user = new AppUser("4d980a71-3b3c-11ec-8708-0242ac110055", "user_name", "$2a$10$L4c8Ky5D5O7WsVm89lTiPO3/pPtME0itveW5GuSI1.vVWSBS1bCJ2",false);
        AppUser actual = repository.findById("4d980a71-3b3c-11ec-8708-0242ac110055");
        assertEquals(actual.getPassword(),user.getPassword());
    }

    @Test
    void shouldFindByUserName(){
        AppUser user = new AppUser("4d980a71-3b3c-11ec-8708-0242ac110055", "userName1", "password_hash",false);
        AppUser actual = repository.findByUserName("userName1");
        assertEquals(actual.getUserName(),user.getUserName());
    }

    @Test
    void shouldAdd() {
        AppUser user = new AppUser(null,"userfake","pass",false);
        assertNotNull(repository.add(user));
    }

    @Test
    void shouldUpdate() {
        AppUser user = new AppUser("4d980a71-3b3c-11ec-8708-0242ac110002","userfakethesecond","pass",false);
        assertTrue(repository.update(user));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById("4d980627-3b3c-11ec-8708-0242ac110002"));
    }

}