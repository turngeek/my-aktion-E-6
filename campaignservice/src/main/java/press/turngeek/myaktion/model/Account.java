package press.turngeek.myaktion.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Account
 */
@Embeddable
public class Account {

    @NotNull
    @Size(min=3, max=60)
    private String name;
    @NotNull
    @Size(min=3, max=40)
    private String nameOfBank;
    @NotNull
    @Pattern(regexp="[A-Z]{2}[0-9]{2}[A-Z0-9]{12,30}")
    private String iban;

	public Account() {
        this(null,null,null);
    }
    
    public Account(String name, String iban, String nameOfBank) {
        this.name=name;
        this.nameOfBank=nameOfBank;
        this.iban=iban;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfBank() {
        return nameOfBank;
    }

    public void setNameOfBank(String nameOfBank) {
        this.nameOfBank = nameOfBank;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}