package learn.risk_online.data;

import learn.risk_online.models.Microtransaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MicroTransactionJdbcRepositoryTest {

    @Autowired
    MicroTransactionJdbcRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        //Microtransaction microt = makeMicrot(1);
        assertEquals(repository.findAll().size(),4);
    }

    @Test
    void shouldFindById() {
        Microtransaction microt = makeMicrot(2);
        assertEquals(repository.findById(2),microt);
    }

    @Test
    void shouldAdd() {
        Microtransaction microt = makeMicrot(1);
        Microtransaction actual = repository.add(microt);
        assertEquals(microt,actual);
    }

    @Test
    void shouldUpdate() {
        Microtransaction microt = makeMicrot(1);
        assertTrue(repository.update(microt));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(4));
    }

    Microtransaction makeMicrot(int i) {
        Microtransaction microt = new Microtransaction();
        switch (i){
            case 1:
                microt.setId(5);
                microt.setProduct("Gummy Bear Avatar 2");
                microt.setPrice(200);
                break;
            case 2:
                microt.setId(2);
                microt.setProduct("Black Bear Bow Tie Avatar");
                microt.setPrice(1);
                break;
            case 3:
                microt.setId(3);
                microt.setProduct("Polar Bear Top Hat Avatar Changed");
                microt.setPrice(2);
                break;
        }

        return microt;
    }



}