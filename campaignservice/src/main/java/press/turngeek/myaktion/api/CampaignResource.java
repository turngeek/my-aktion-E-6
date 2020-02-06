package press.turngeek.myaktion.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import press.turngeek.myaktion.boundary.CampaignService;
import press.turngeek.myaktion.model.Campaign;

/**
 * CampaignResource
 * 
 */
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
    public void deleteCampaign(@PathParam(value = "campaignId") Long campaignId) {
        campaignService.deleteCampaign(campaignId);
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
    public Campaign updateCampaign(@PathParam(value = "campaignId") Long campaignId,
                                   Campaign newCampaign) {
        
        newCampaign = campaignService.updateCampaign(newCampaign);
        
        return newCampaign;
    }
    
}