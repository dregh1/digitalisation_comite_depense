package org.cd.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.cd.model.AvisAchat;
import org.cd.repository.AvisAchatRepository;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class AvisAchatService {

    @Inject
    AvisAchatRepository avis_achatRepository;

    @Transactional
    public void create(AvisAchat avis_achat) {
        AvisAchat avis_achatMerged = avis_achatRepository.getEntityManager().merge(avis_achat);
//        AvisAchatRepository.persist(AvisAchat);
    }

    public List<AvisAchat> getAll() {
        return avis_achatRepository.listAll();
    }



    public AvisAchat getAvisAchatById(Long id) {
        return avis_achatRepository.findById(id);
    }

    @Transactional
    public void updateAvisAchat(AvisAchat avis_achat) {
        avis_achatRepository.getEntityManager().merge(avis_achat);
    }

    @Transactional
    public boolean deleteAvisAchat(Long id) {
        AvisAchat avis_achat = avis_achatRepository.findById(id);
        if (avis_achat != null) {
            avis_achatRepository.delete(avis_achat);
            return true;
        }
        return false;
    }

    public AvisAchat getAvisAchatByIdDemande(Long idDemande)
    {
        List<AvisAchat> avisAchat = this.getAll();


        for (AvisAchat d : avisAchat )
        {
            if(d.getIdDemande()== idDemande)
            {

                return d;
            }
        }
        return null;
    }

    public boolean checkAvisAchatByIdDemande(Long idDemande) {
        List <AvisAchat> avisCds = this.getAll();
        for(AvisAchat avis : avisCds)
        {
            if(Objects.equals(avis.getIdDemande(), idDemande))
            {
                return true;
            }
        }
        return false;
    }
}
