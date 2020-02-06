package press.turngeek.myaktion.boundary;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.Valid;

import press.turngeek.myaktion.model.Campaign;

/**
 * CampaignServiceBean
 */
@ApplicationScoped
@Transactional
public class CampaignService {

    @Inject
    EntityManager em;

    public List<Campaign> getCampaigns() {
        TypedQuery<Campaign> query = em.createNamedQuery(Campaign.findAll, Campaign.class);
        List<Campaign> campaigns = query.getResultList();
        return campaigns;
    }

    public Campaign getCampaign(Long campaignId) {
        Campaign campaign = em.find(Campaign.class, campaignId);
        return campaign;
    }

    public void deleteCampaign(Long campaignId) {
        Campaign managedCampaign = em.find(Campaign.class, campaignId);
        em.remove(managedCampaign);
    }

    public Campaign addCampaign(@Valid Campaign campaign) {
        em.persist(campaign);
        return campaign;
    }

    public Campaign updateCampaign(@Valid Campaign campaign) {
        em.merge(campaign);
        return campaign;
    }

    
}