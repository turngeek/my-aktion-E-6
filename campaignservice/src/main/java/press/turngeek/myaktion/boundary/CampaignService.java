package press.turngeek.myaktion.boundary;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import press.turngeek.myaktion.data.CampaignListProducer;
import press.turngeek.myaktion.model.Campaign;

/**
 * CampaignService
 */
@Dependent
public class CampaignService {

    @Inject
    CampaignListProducer campaignListProducer;

    public List<Campaign> getCampaigns() {
        return campaignListProducer.getCampaigns();
    }

    public Campaign getCampaign(Long campaignId) {
        return campaignListProducer.findCampaign(campaignId);
    }

    public void deleteCampaign(Long campaignId) {
        campaignListProducer.getCampaigns().remove(campaignListProducer.findCampaign(campaignId));
    }

    public Campaign addCampaign(Campaign campaign) {
        campaignListProducer.getCampaigns().add(campaign);
        return campaignListProducer.findCampaign(campaign.getId());
    }

    public Campaign updateCampaign(Campaign campaign) {
        deleteCampaign(campaign.getId());
        addCampaign(campaign);
        return campaignListProducer.findCampaign(campaign.getId());
    }
}