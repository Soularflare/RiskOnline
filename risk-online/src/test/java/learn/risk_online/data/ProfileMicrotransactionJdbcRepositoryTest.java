package learn.risk_online.data;

import learn.risk_online.models.Microtransaction;
import learn.risk_online.models.ProfileMicrotransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProfileMicrotransactionJdbcRepositoryTest {

    @Autowired
    ProfileMicrotransactionJdbcRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){ knownGoodState.set(); }


    @Test
    void shouldAdd(){
        Microtransaction mt = new Microtransaction();
        mt.setId(4);
        ProfileMicrotransaction profileMicrotransaction = new ProfileMicrotransaction();
        profileMicrotransaction.setMicrotransaction(mt);
        profileMicrotransaction.setProfileId("69367d6d-3b3e-11ec-8708-0242ac110002");
        profileMicrotransaction.setEquipped(false);

        assertTrue(repository.add(profileMicrotransaction));
    }

    @Test
    void shouldUpdate(){
        Microtransaction mt = new Microtransaction();
        mt.setId(3);
        ProfileMicrotransaction profileMicrotransaction = new ProfileMicrotransaction();
        profileMicrotransaction.setMicrotransaction(mt);
        profileMicrotransaction.setProfileId("69367d6d-3b3e-11ec-8708-0242ac110002");
        profileMicrotransaction.setEquipped(false);

        assertTrue(repository.update(profileMicrotransaction));
    }

    @Test
    void shouldDelete(){
        assertTrue(repository.deleteByKeys(2, "69367d6d-3b3e-11ec-8708-0242ac110002"));
    }

}