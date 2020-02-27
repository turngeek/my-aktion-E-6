package press.turngeek.myaktion.boundary;

import java.security.Principal;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.ForbiddenException;

import press.turngeek.myaktion.model.Campaign;

/**
 * CampaignServiceBean
 */
@ApplicationScoped
@Transactional
public class CampaignService {

    @Inject
    EntityManager em;

    @Inject
    Principal principal;

    public List<Campaign> getCampaigns() {
        TypedQuery<Campaign> query = em.createNamedQuery(Campaign.findbyOrganizerName, Campaign.class);
        query.setParameter("organizerName", principal.getName());
        List<Campaign> campaigns = query.getResultList();
        return campaigns;
    }

    public Campaign getCampaign(Long campaignId) {
        Campaign campaign = em.find(Campaign.class, campaignId);
        if (!campaign.getOrganizerName().equals(principal.getName()))
            throw new ForbiddenException("Not organizer of campaign with id "+campaignId);
        return campaign;
    }

    public void deleteCampaign(Long campaignId) {
        Campaign managedCampaign = em.find(Campaign.class, campaignId);
        if (!managedCampaign.getOrganizerName().equals(principal.getName()))
            throw new ForbiddenException("Not organizer of campaign with id "+campaignId);
        em.remove(managedCampaign);
    }

    public Campaign addCampaign(@Valid Campaign campaign) {
        campaign.setOrganizerName(principal.getName());
        em.persist(campaign);
        return campaign;
    }

    public Campaign updateCampaign(@Valid Campaign campaign) {
        if (!campaign.getOrganizerName().equals(principal.getName()))
            throw new ForbiddenException("Not organizer of campaign with id "+campaign.getId());
        em.merge(campaign);
        return campaign;
    }

    
}
