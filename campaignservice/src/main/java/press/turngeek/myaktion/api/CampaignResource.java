package press.turngeek.myaktion.api;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import press.turngeek.myaktion.boundary.CampaignService;
import press.turngeek.myaktion.model.Campaign;

/**
 * CampaignResource
 * 
 */
@RolesAllowed("Organizer")
@Path("/campaign")
public class CampaignResource {

    @Inject
    CampaignService campaignService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Campaign> getCampaigns() {
        List<Campaign> allCampaigns = campaignService.getCampaigns();
        allCampaigns.forEach(campaign -> campaign.setDonations(null));
        return allCampaigns;
    }

    @DELETE
    @Path("/{campaignId}")
    public Response deleteCampaign(@PathParam(value = "campaignId") Long campaignId) {
        try {
            campaignService.deleteCampaign(campaignId);
            return Response.ok().build();
        } catch(ForbiddenException e) {
            return Response.status(Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Campaign addCampaign(Campaign campaign) {
        return campaignService.addCampaign(campaign);
    }

    @PUT
    @Path("/{campaignId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCampaign(@PathParam(value = "campaignId") Long campaignId,
                                   Campaign newCampaign) {
        try {
            newCampaign = campaignService.updateCampaign(newCampaign);
            return Response.ok(newCampaign).build();
        } catch(ForbiddenException e) {
            return Response.status(Status.FORBIDDEN).entity(e.getMessage()).build();
        }  
    }
    
}