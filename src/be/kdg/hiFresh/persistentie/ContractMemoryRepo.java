package be.kdg.hiFresh.persistentie;

import be.kdg.hiFresh.domain.leverancier.Contract;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ContractMemoryRepo {
    private List<Contract> alleContracten;

    public void setContracts(List<Contract> contracts) {
        this.alleContracten = new LinkedList<Contract>(contracts);
    }

    public List<Contract> getAlleContracten() {
        return alleContracten;
    }
}
