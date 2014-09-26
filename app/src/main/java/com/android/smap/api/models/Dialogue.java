package com.android.smap.api.models;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Column.ForeignKeyAction;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.android.smap.models.TextMessage;

import java.util.List;

@Table(name = "surveys_contacts")
public class Dialogue extends Model {

    @Column(name = "distribution_id", onDelete = ForeignKeyAction.CASCADE)
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
	
	public Dialogue() {
		
	}

    public Dialogue(Distribution distribution, Contact contact) {
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

    public List<LogMessage> getMessages() {
        return getMany(LogMessage.class, "dialogue_id");
    }

    public void logMessage(TextMessage message) {
        ActiveAndroid.beginTransaction();
        try {
            LogMessage newMessage = new LogMessage(this, message.text, message.number);
            newMessage.save();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

	public static Dialogue findByDistributionAndContactIds(long distributionId, long contactId) {

		return new Select()
			.from(Dialogue.class)
			.where("distribution_id = ?", distributionId)
			.where("contact_id = ?", contactId)
			.executeSingle();
	}

}
