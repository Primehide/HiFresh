package be.kdg.hiFresh.persistentie;

import be.kdg.hiFresh.domain.leverancier.Contract;

import java.util.ArrayList;
import java.util.List;

public class ContractRepo {
    private List<Contract> contracts;

    public ContractRepo(){
        this.contracts = new ArrayList<>();
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
