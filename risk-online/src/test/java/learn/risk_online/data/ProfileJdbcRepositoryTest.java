package learn.risk_online.data;

import learn.risk_online.models.AppUser;
import learn.risk_online.models.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProfileJdbcRepositoryTest {

    @Autowired
    ProfileJdbcRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){ knownGoodState.set(); }



    @Test
    void shouldFindByProfileId(){
        Profile profile = repository.findByProfileId("69367d6d-3b3e-11ec-8708-0242ac110002");
        assertNotNull(profile);
        assertEquals(2, profile.getTotalGames());
        assertEquals(3, profile.getMicrotransactions().size());
    }

    @Test
    void shouldFindByUserId(){
        Profile profile = repository.findByUserId("4d980a71-3b3c-11ec-8708-0242ac110002");
        assertNotNull(profile);
        assertEquals(0, profile.getTotalGames());
        assertEquals(1, profile.getMicrotransactions().size());

    }

    @Test
    void shouldFindByUserId2(){
        Profile profile = repository.findByUserId("4d980627-3b3c-11ec-8708-0242ac110002");
        assertNotNull(profile);
        assertTrue( profile.getOngoingGames().size() >= 2);
    }

    @Test
    void shouldAdd(){

        Profile profile = repository.add("4d980a71-3b3c-11ec-8708-0242ac110055");

        assertNotNull(profile);
        assertEquals(0, profile.getTotalGames());
        assertEquals(0, profile.getGameTime());

    }

    @Test
    void shouldUpdate(){
        Profile profile = repository.findByProfileId("69367d6d-3b3e-11ec-8708-0242ac110002");
        profile.setPoints(10000);

        assertTrue(repository.updateByProfileId(profile));
        Profile updated = repository.findByProfileId("69367d6d-3b3e-11ec-8708-0242ac110002");
        assertEquals(10000, updated.getPoints());

    }

    @Test
    void shouldUpdate2(){
        Profile profile = repository.findByProfileId("69367d6d-3b3e-11ec-8708-0242ac110002");
        profile.setPoints(10000);

        assertTrue(repository.updateByUserId(profile));
        Profile updated = repository.findByProfileId("69367d6d-3b3e-11ec-8708-0242ac110002");
        assertEquals(10000, updated.getPoints());

    }

    @Test
    void shouldDelete(){
        assertTrue(repository.deleteByProfileId("69367ff8-3b3e-11ec-8708-0242ac110003"));
    }

    @Test
    void shouldNotDelete(){
        assertFalse(repository.deleteByProfileId("4d980627-3b3c-11ec-8708-0242ac110002"));
    }

    @Test
    void shouldDelete2(){
        assertTrue(repository.deleteByUserId("1d980a71-3b3c-11ec-8708-0242ac110002"));
    }



}