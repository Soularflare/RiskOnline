package learn.risk_online.data;

import learn.risk_online.models.Microtransaction;
import learn.risk_online.models.MicrotransactionProfile;
import learn.risk_online.models.ProfileMicrotransaction;

import java.util.List;

public interface ProfileMicrotransactionRepository {

    boolean add(ProfileMicrotransaction profileMicrotransaction);

    boolean update(ProfileMicrotransaction profileMicrotransaction);

    boolean deleteByKeys(int microtransactionId, String profileId);

//    boolean microtransactionInUse(int microtransactionId);

}
