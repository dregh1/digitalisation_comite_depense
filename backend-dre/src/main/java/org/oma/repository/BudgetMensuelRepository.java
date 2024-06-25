package org.oma.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.oma.model.Brouillon;
import org.oma.model.BudgetMensuel;
@ApplicationScoped
public class BudgetMensuelRepository implements PanacheRepository<BudgetMensuel> {
}
