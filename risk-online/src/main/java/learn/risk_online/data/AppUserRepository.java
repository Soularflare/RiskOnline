package learn.risk_online.data;



import learn.risk_online.models.AppUser;

import java.util.List;

public interface AppUserRepository {
    List<AppUser> findAll();

    AppUser findById(String id);

    AppUser findByUserName(String userName);

   AppUser add(AppUser user);

    boolean update(AppUser user);

    boolean deleteById(String id);
}
