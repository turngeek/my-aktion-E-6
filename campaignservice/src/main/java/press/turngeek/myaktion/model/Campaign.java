package press.turngeek.myaktion.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Campaign
 */
@NamedQueries({
    @NamedQuery(name = Campaign.findAll, query = "SELECT a FROM Campaign a ORDER BY a.name"),
    @NamedQuery(name = Campaign.findbyOrganizerName, query = "SELECT c FROM Campaign c WHERE c.organizerName = :organizerName ORDER BY c.name")})

@Entity
public class Campaign {
    @NotNull
    @Size(min = 3, max = 30)
    private String name;
    @NotNull
    @DecimalMin(value = "10.00")
    private Double targetAmount;
    @NotNull
    @DecimalMin(value = "1.00")
    private Double donationMinimum;
    @Transient
    private Double amountDonatedSoFar;
    public static final String findAll="Campaign.findAll";
    public static final String findbyOrganizerName="Campaign.findbyOrganizerName";
    @AttributeOverrides({@AttributeOverride(name="name", column=@Column(name="accountName"))})
    
    @Embedded
    @Valid
    private Account account;
    @GeneratedValue
    @Id
    private Long id;
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.REMOVE)
    private List<Donation> donations;

    @NotNull
    private String organizerName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Double getDonationMinimum() {
        return donationMinimum;
    }

    public void setDonationMinimum(Double donationMinimum) {
        this.donationMinimum = donationMinimum;
    }

    public Double getAmountDonatedSoFar() {
        return amountDonatedSoFar;
    }

    public void setAmountDonatedSoFar(Double amountDonatedSoFar) {
        this.amountDonatedSoFar = amountDonatedSoFar;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName=organizerName;
    }

    public String getOrganizerName() {
        return this.organizerName;
    }
}
