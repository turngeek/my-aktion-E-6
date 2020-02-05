package press.turngeek.myaktion.boundary;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import press.turngeek.myaktion.data.CampaignListProducer;
import press.turngeek.myaktion.model.Campaign;
import press.turngeek.myaktion.model.Donation;

/**
 * DonationService
 */
@Dependent
public class DonationService {

    private CampaignListProducer campaignListProducer;

    @Inject
    public DonationService(CampaignListProducer campaignListProducer) {
        this.campaignListProducer = campaignListProducer;
    }

    public List<Donation> getDonationList(Long campaignId) {
        return campaignListProducer.findCampaign(campaignId).getDonations();
    }

    public void addDonation(Long campaignId, Donation donation) {
        Campaign campaign = campaignListProducer.findCampaign(campaignId);
        campaign.getDonations().add(donation);
    } 
}