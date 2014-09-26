package com.android.smap.api.models;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Table(name = "distributions")
public class Distribution extends Model {

    @Column
    private String name;

    @Column(name = "survey_id", onDelete = Column.ForeignKeyAction.CASCADE)
    private Survey survey;

    public Distribution() {

    }

    public Distribution(Survey survey, String name) {
        this.survey = survey;
        this.name = name;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public List<Dialogue> getSurveyContacts() {
        List<Dialogue> contacts = getMany(Dialogue.class, "distribution_id");
        Collections.sort(contacts,new Comparator<Dialogue>() {
            @Override
            public int compare(Dialogue dialogue, Dialogue dialogue2) {
                return dialogue.contact.getName().compareTo(dialogue2.contact.getName());
            }
        });
        return contacts;
    }

    public void addContact(Contact contact) {
        ActiveAndroid.beginTransaction();
        try {
            new Dialogue(this, contact).save();

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    public void addContacts(List<Contact> contacts) {
        ActiveAndroid.beginTransaction();
        try {

            for (Contact contact : contacts) {
                new Dialogue(this, contact).save();
            }

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
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

    public static Distribution findById(Long id) {
        return Model.load(Distribution.class, id);
    }

    public static List<Distribution> findAll() {
        return new Select().from(Distribution.class).execute();
    }

    public static List<Distribution> findBySurvey(Survey survey) {
        return survey.getDistributions();
    }
}
