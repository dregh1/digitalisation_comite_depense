package org.oma.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.oma.model.Active;
import org.oma.model.DetailDemande;
import org.oma.repository.ActiveRepository;

import java.util.List;



@ApplicationScoped
public class ActiveService {
    @Inject
    ActiveRepository activeRepository;

    @Inject
    SessionCdService  sessionCdService;
    @Inject
    EntityManager entityManager;

    @Transactional
    public void create(Active active) {
        Active activeMerged = activeRepository.getEntityManager().merge(active);
//        ActiveRepository.persist(Active);
    }

    public List<Active> getAll() {
        return activeRepository.listAll();
    }



    public Active getActiveById(Long id) {
        return activeRepository.findById(id);
    }


    public double getSomme(Integer id){
        if(!this.sessionCdService.checkSession(id)){
            return 0;
        }

        String sql =    "select  coalesce ( montant , 0) from (SELECT coalesce(sum(montantht ) , 0 ) as montant, idsession  FROM active group by idsession ) as tab where idsession = "+ id;

        Query query = entityManager.createNativeQuery(sql, Double.class);

        Double response = (Double) query.getSingleResult();

        System.out.println("response ---------------- = " + response);
//        return (double) query.getSingleResult();

        return response;
    }

    public boolean estSoumis(Long id)
    {
        String sql =    "SELECT * FROM active " ;


            sql+="where id ="+id ;


        Query query = entityManager.createNativeQuery(sql, DetailDemande.class);

        List<Active> actives = query.getResultList();

        if( actives.size()!=0)return  true;

        return false;

    }

    @Transactional
    public void updateActive(Active active) {
        activeRepository.getEntityManager().merge(active);
    }

    @Transactional
    public boolean deleteActive(Long id) {
        Active active = activeRepository.findById(id);
        if (active != null) {
            activeRepository.delete(active);
            return true;
        }
        return false;
    }
    public List<Active> getActive(String idDirection , String idSession) {



        String sql =    "SELECT * FROM DetailDemande " ;

        if(!idDirection.isEmpty() || !idSession.isEmpty() )
        {
            sql+="where validationPrescripteur = true  ";
            if(!idDirection.isEmpty())
                sql+= " and idDirection ="+idDirection;
            if(!idSession.isEmpty())
                sql+= " and idSession ="+   idSession;

        }

        System.out.println(sql);

        Query query = entityManager.createNativeQuery(sql, DetailDemande.class);

        List<Active> actives = query.getResultList();

        return actives;

    }

    public List<Active> getActiveAvecTitre(String idDirection , String idSession) {


        String sql =    "SELECT * FROM active where titre!='sans titre' " ;

        if(!idDirection.isEmpty() || !idSession.isEmpty() )
        {
            sql+="and validationPrescripteur = true  ";
            if(!idDirection.isEmpty())
                sql+= " and idDirection ="+idDirection;
            if(!idSession.isEmpty())
                sql+= " and idSession ="+   idSession;

        }

        System.out.println(sql);

        Query query = entityManager.createNativeQuery(sql, DetailDemande.class);

        List<Active> actives = query.getResultList();

        return actives;

    }
    public List<Active> getActiveSansTitre(String idDirection , String idSession) {


        String sql =    "SELECT * FROM active  where titre='sans titre' " ;

        if(!idDirection.isEmpty() || !idSession.isEmpty() )
        {
            sql+="and validationPrescripteur = true ";
            if(!idDirection.isEmpty())
                sql+= " and idDirection ="+idDirection;
            if(!idSession.isEmpty())
                sql+= " and idSession ="+   idSession;

        }

        System.out.println(sql);

        Query query = entityManager.createNativeQuery(sql, DetailDemande.class);

        List<Active> actives = query.getResultList();

        return actives;

    }

    public List<Active> getActiveByTitre(int idTitre) {

        String sql =    "SELECT * FROM active  where idtitre =  "+ idTitre ;

        Query query = entityManager.createNativeQuery(sql, DetailDemande.class);

        List<Active> actives = query.getResultList();

        return actives;
    }
}
