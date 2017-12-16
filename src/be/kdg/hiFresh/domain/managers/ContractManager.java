package be.kdg.hiFresh.domain.managers;

import be.kdg.hiFresh.domain.leverancier.Contract;
import be.kdg.hiFresh.domain.leverancier.ContractPeriode;
import be.kdg.hiFresh.domain.recept.Product;
import be.kdg.hiFresh.persistentie.ContractMemoryRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class ContractManager {
    private ContractMemoryRepo ContractRepo;

    public ContractManager(){
        ContractRepo = new ContractMemoryRepo();
    }

    private List<ContractPeriode> getContractPeriodesProduct(Product product){
        //1. alle contracten ophalen
        //2. voor elk contract contract periodes ophalen
        //we moeten over elk contract loopen.
        //3. Als het contractperiode het contractperiode voor ons product is geven we hem terug in resultatenlijst.

        List<ContractPeriode> periodesVoorProduct = new LinkedList<ContractPeriode>();

        for(Contract c : ContractRepo.getContracts()){
            for(ContractPeriode cp : c.getPeriodes()){
                if(cp.getProduct() == product){
                    periodesVoorProduct.add(cp);
                }
            }
        }
        return periodesVoorProduct;
    }

    private List<ContractPeriode> filterActievePeriodes(List<ContractPeriode> periodes, int week, int jaar){
        List<ContractPeriode> actieveContractPeriodes = new LinkedList<ContractPeriode>();
        for(ContractPeriode ca : periodes){
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int weekNumberVan = ca.getVan().get(weekFields.weekOfWeekBasedYear());
            int weekNumberTot = ca.getTot().get(weekFields.weekOfWeekBasedYear());

            if((ca.getVan().getYear() == jaar || weekNumberVan <= week) &&(weekNumberTot >= week)){
                actieveContractPeriodes.add(ca);
            }
        }
        return actieveContractPeriodes;
    }

    public double berekenGemiddelePrijsProduct(Product product, int week, int jaar){
        double totaal = 0;
        double gemiddelde = 0;
        for (ContractPeriode ca : filterActievePeriodes(getContractPeriodesProduct(product),week,jaar)){
            totaal += ca.getEenheidsPrijs();
        }

        gemiddelde = totaal / filterActievePeriodes(getContractPeriodesProduct(product),week,jaar).size();
        return gemiddelde;
    }

    public void fillRepo(List<Contract> contracten){
        ContractRepo.setContracts(contracten);
    }
}
