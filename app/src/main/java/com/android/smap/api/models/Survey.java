package com.android.smap.api.models;

import java.util.List;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "surveys")
public class Survey extends Model {
	
	@Column
	private String name;
	
	/**
	 * Raw XML string with JavaRosa form definition
	 */
	@Column
	private String formContent;
	
	public Survey() {
		
	}
	
	public Survey(String name) {
		this.setName(name);
	}

	public Survey(String name, String formContent) {
		this.name = name;
		this.formContent = formContent;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFormContent() {
		return formContent;
	}

	public void setFormContent(String content) {
		this.formContent = content;
	}

	public int getMembersCount() {
        // TODO delegate this to the distributions
		return 0;
	}
	
	public int getCompletedCount() {
        // TODO delegate this to the distributions
		return 0;
	}
	
	public int getPartialCount() {
        // TODO delegate this to the distributions
		return 0;
	}
	
	public float getCompletionPercentage() {
        // TODO delegate this to the distributions
		return ((float) getPartialCount() / getCompletedCount()) * 100f;
	}
	
	public static Survey findById(Long id) {
		return Model.load(Survey.class, id);
	}
	
	public static List<Survey> findAll() {
		return new Select().from(Survey.class).execute();
	}

    // relations
    public Distribution createDistribution(String name) {
        ActiveAndroid.beginTransaction();
        Distribution distribution = null;
        try {
            distribution = new Distribution(this, name);
            distribution.save();

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

        return distribution;
    }

    public List<Distribution> getDistributions() {
        return getMany(Distribution.class, "survey_id");
    }
}
