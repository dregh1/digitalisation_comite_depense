package org.dre.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.dre.model.Brouillon;
import org.dre.model.BudgetMensuel;
@ApplicationScoped
public class BudgetMensuelRepository implements PanacheRepository<BudgetMensuel> {
}
