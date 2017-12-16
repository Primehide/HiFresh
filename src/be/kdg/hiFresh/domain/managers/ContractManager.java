package be.kdg.hiFresh.domain.managers;

import be.kdg.hiFresh.domain.leverancier.Contract;
import be.kdg.hiFresh.domain.leverancier.ContractPeriode;
import be.kdg.hiFresh.domain.recept.Product;
import be.kdg.hiFresh.persistentie.ContractMemoryRepo;

import java.util.ArrayList;
import java.util.List;

public class ContractManager {
    private ContractMemoryRepo repo;

    public ContractManager(){
        repo = new ContractMemoryRepo();
    }

    public List<ContractPeriode> getContractPeriodes(Product product){
        List<ContractPeriode> res = new ArrayList<>();
        List<Contract> contracts = this.repo.getContracts();
        for (Contract c : contracts){
            List<ContractPeriode> periodes = c.getPeriodes();
            for (ContractPeriode p : periodes){
                if (p.getProduct().equals(product)){
                    res.add(p);
                }
            }
        }
        return res;
    }

    public void fillRepo(List<Contract> contracten){
        repo.setContracts(contracten);
    }
}
