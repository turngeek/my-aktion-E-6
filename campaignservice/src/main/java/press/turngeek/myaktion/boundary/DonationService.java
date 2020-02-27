package press.turngeek.myaktion.boundary;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.ForbiddenException;

import press.turngeek.myaktion.model.Campaign;
import press.turngeek.myaktion.model.Donation;

/**
 * DonationServiceBean
 */
@Transactional
@ApplicationScoped
public class DonationService {

    @Inject
    EntityManager em;

    @Inject
    Principal principal;

    public List<Donation> getDonations(Long campaignId) {
        Campaign managedCampaign = em.find(Campaign.class, campaignId);
        if (!managedCampaign.getOrganizerName().equals(principal.getName()))
            throw new ForbiddenException("Not organizer of campaign with id "+campaignId);
        List<Donation> donations = new ArrayList<>(managedCampaign.getDonations());
        return donations;
    }
    
    public void addDonation(Long campaignId, @Valid Donation donation) {
        Campaign managedCampaign = em.find(Campaign.class, campaignId);
        donation.setCampaign(managedCampaign);
        em.persist(donation);
    }
}