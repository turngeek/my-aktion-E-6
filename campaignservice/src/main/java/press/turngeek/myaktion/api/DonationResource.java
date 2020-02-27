package press.turngeek.myaktion.api;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.ForbiddenException;

import press.turngeek.myaktion.boundary.DonationService;
import press.turngeek.myaktion.model.Donation;

/**
 * DonationResource
 */
@Path("/donation")
public class DonationResource {

    @Inject
    DonationService donationService;

    @RolesAllowed("Organizer")
    @GET
    @Path("/list/{campaignId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDonations(@PathParam(value = "campaignId") Long campaignId) {
        List<Donation> donations;
        try {
            donations = donationService.getDonations(campaignId);
        } catch (ForbiddenException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
        donations.forEach(donation -> donation.setCampaign(null));
        return Response.ok(donations).build();
    }
    @PermitAll
    @POST
    @Path("/{campaignId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addDonation(@PathParam(value = "campaignId")Long campaignId, Donation donation) {
        donationService.addDonation(campaignId, donation);
    }  
}