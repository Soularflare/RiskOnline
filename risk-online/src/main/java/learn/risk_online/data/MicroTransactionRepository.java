package learn.risk_online.data;


import learn.risk_online.models.Microtransaction;

import java.util.List;

public interface MicroTransactionRepository {
    List<Microtransaction> findAll();

    Microtransaction findById(int id);

    Microtransaction add(Microtransaction microt);

    boolean update(Microtransaction microt);

    boolean deleteById(int id);
}
