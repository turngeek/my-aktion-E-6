package press.turngeek.myaktion.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import press.turngeek.myaktion.boundary.DonationService;
import press.turngeek.myaktion.model.Donation;

/**
 * DonationResource
 */
@Path("/donation")
public class DonationResource {

    @Inject
    DonationService donationService;

    @GET
    @Path("/list/{campaignId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Donation> getDonations(@PathParam(value = "campaignId") Long campaignId) {
        List<Donation> donations = donationService.getDonations(campaignId);
        donations.forEach(donation -> donation.setCampaign(null));
        return donations;
    }

    @POST
    @Path("/{campaignId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addDonation(@PathParam(value = "campaignId")Long campaignId, Donation donation) {
        donationService.addDonation(campaignId, donation);
    }  
}