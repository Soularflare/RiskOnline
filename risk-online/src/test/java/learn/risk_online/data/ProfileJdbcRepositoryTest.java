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
        assertEquals(3, profile.getOngoingGames().size());
    }

    @Test
    void shouldAdd(){
        AppUser user = new AppUser();
        user.setUserId("4d980a71-3b3c-11ec-8708-0242ac110055");

        Profile profile = repository.add(user);

        assertNotNull(profile);
        assertEquals(0, profile.getTotalGames());
        assertEquals(0, profile.getGameTime());

    }

    @Test
    void shouldUpdate(){
        Profile profile = repository.findByProfileId("69367d6d-3b3e-11ec-8708-0242ac110002");
        profile.setPoints(10000);

        assertTrue(repository.update(profile));
        Profile updated = repository.findByProfileId("69367d6d-3b3e-11ec-8708-0242ac110002");
        assertEquals(10000, updated.getPoints());

    }

    @Test
    void shouldDelete(){

    }



}