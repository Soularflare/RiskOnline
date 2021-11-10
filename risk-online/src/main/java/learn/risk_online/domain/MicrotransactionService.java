package learn.risk_online.domain;

import learn.risk_online.data.MicroTransactionRepository;
import learn.risk_online.models.Microtransaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicrotransactionService {

    private final MicroTransactionRepository microtransactionRepository;

    public MicrotransactionService(MicroTransactionRepository microtransactionRepository) {
        this.microtransactionRepository = microtransactionRepository;
    }

    public List<Microtransaction> findAllMicrotransactions(){
        return microtransactionRepository.findAll();
    }

    public Microtransaction findById(int microId){
        return microtransactionRepository.findById(microId);
    }


}
