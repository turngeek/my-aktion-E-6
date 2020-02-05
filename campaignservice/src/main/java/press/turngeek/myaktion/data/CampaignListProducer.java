package press.turngeek.myaktion.data;

import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.Dependent;

import press.turngeek.myaktion.model.Account;
import press.turngeek.myaktion.model.Campaign;
import press.turngeek.myaktion.model.Donation;
import press.turngeek.myaktion.model.Donation.Status;

/**
 * CampaignListProducer
 * TODO Optimize scopes of beans
 */
@Dependent
public class CampaignListProducer {

    private List<Campaign> campaigns;

    public CampaignListProducer() {
        campaigns = createMockCampaigns();
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public Campaign findCampaign(Long campaignId) {
        for (Campaign campaign : campaigns) {
            if (campaign.getId().equals(campaignId)) {
                return campaign;
            }
        }
        //TODO Exception handling overkill at this stage of development.
        return null; 
    }

    public List<Campaign> createMockCampaigns() {
        Donation donation1 = new Donation();
        donation1.setDonorName("Heinz Schmidt");
        donation1.setAmount(20d);
        donation1.setReceiptRequested(true);
        donation1.setStatus(Status.TRANSFERRED);
        donation1.setAccount(new Account(donation1.getDonorName(), "XXX Bank",
                "DE44876543210000123456"));
        Donation donation2 = new Donation();
        donation2.setDonorName("Karl Meier");
        donation2.setAmount(30d);
        donation2.setReceiptRequested(false);
        donation2.setStatus(Status.IN_PROCESS);
        donation2.setAccount(new Account(donation2.getDonorName(), "YYY Bank",
                "DE44864275310000654321"));
        List<Donation> donations = new LinkedList<>();
        donations.add(donation1);
        donations.add(donation2);

        Campaign campaign1 = new Campaign();
        campaign1.setName("Trikots für A-Jugend");
        campaign1.setTargetAmount(1000d);
        campaign1.setAmountDonatedSoFar(258d);
        campaign1.setDonationMinimum(20d);
        campaign1.setId(1L);
        campaign1.setAccount(new Account("Max Mustermann", "ABC Bank",
                "DE44123456780100200300"));
        campaign1.setDonations(donations);

        Campaign campaign2 = new Campaign();
        campaign2.setName("Rollstuhl für Maria");
        campaign2.setTargetAmount(2500d);
        campaign2.setAmountDonatedSoFar(742d);
        campaign2.setDonationMinimum(25d);
        campaign2.setId(2L);
        campaign2.setAccount(campaign1.getAccount());
        campaign2.setDonations(donations);
        List<Campaign> ret = new LinkedList<>();
        ret.add(campaign1);
        ret.add(campaign2);
        return ret;
    }   
}