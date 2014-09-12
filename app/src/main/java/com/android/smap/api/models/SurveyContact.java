package com.android.smap.api.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Column.ForeignKeyAction;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "surveys_contacts")
public class SurveyContact extends Model {

    @Column(name = "distribution_id")
	private Distribution distribution;
	
	@Column(name = "contact_id", onDelete = ForeignKeyAction.CASCADE)
	public Contact contact;
	
	/**
	 * Raw XML String to store JavaRosa response  
	 */
	@Column
	private String rawInstance;
	
	// dummy fields for the moment
	public int		answers;
	public int		total;
	public String	updatedAt;
	
	public SurveyContact() {
		
	}

    public SurveyContact(Distribution distribution, Contact contact) {
        this.distribution = distribution;
        this.contact = contact;
    }

	public String getRawInstance() {
		return rawInstance;
	}

	public void setRawInstance(String rawInstance) {
		this.rawInstance = rawInstance;
	}

    public Distribution getDistribution() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

	public static SurveyContact findByDistributionAndContactIds(long distributionId, long contactId) {

		return new Select()
			.from(SurveyContact.class)
			.where("distribution_id = ?", distributionId)
			.where("contact_id = ?", contactId)
			.executeSingle();
	}

}
