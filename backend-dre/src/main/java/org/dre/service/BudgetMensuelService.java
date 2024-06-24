package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.dre.model.BudgetMensuel;
import org.dre.model.Decision;
import org.dre.model.DetailDemande;
import org.dre.repository.AvisAchatRepository;
import org.dre.repository.BudgetMensuelRepository;

@ApplicationScoped
public class BudgetMensuelService {

    @Inject
    EntityManager entityManager;
    @Inject
    BudgetMensuelRepository budgetMensuelRepository;

    @Transactional
    public void create(BudgetMensuel budgetMensuel) {
        BudgetMensuel budgetMensuel1 = budgetMensuelRepository.getEntityManager().merge(budgetMensuel);
    }

    @Transactional
    public void update(BudgetMensuel budgetMensuel) {
        budgetMensuelRepository.getEntityManager().merge(budgetMensuel);
    }
    public Double getcurrentBudget() {
//        return entityManager.createQuery(
//                "SELECT montant FROM BUDGETMENSUEL p where p.annee = EXTRACT(YEAR FROM CURRENT_DATE) and p.numDuMois =EXTRACT(month FROM CURRENT_DATE)",
//                Long.class
//        ).getSingleResult();



//        return (double) query.getSingleResult();


        String sql =    "SELECT montant FROM BUDGETMENSUEL p where p.annee = EXTRACT(YEAR FROM CURRENT_DATE) and p.numDuMois =EXTRACT(month FROM CURRENT_DATE)";

        Query query = entityManager.createNativeQuery(sql, Double.class);

        Double response = (Double) query.getSingleResult();

        System.out.println("response ---------######------- = " + response);
        return response;

    }




}
