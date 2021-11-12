package application;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Entry {
	
	String name;
	String date;
	String duration;
	String distance;
	String journal;
	
	
	public Entry() {
		name = null;
		date = null;
		duration = null;
		distance = null;
		journal = null;
	}
	
	
	public Entry(String name, String date, String duration, String distance, String journal) {
		this.name = name;
		this.date = date;
		this.duration = duration;
		this.distance = distance;
		this.journal = journal;
	}
	
	public String getName() {return name;}
	public String getDate() {return date;}
	public String getDuration() {return duration;}
	public String getDistance() {return distance;}
	public String getJournal() {return journal;}
	
	@XmlAttribute
	public void setName(String name) { this.name = name;}
	@XmlAttribute
	public void setDate(String date) { this.date = date;}
	@XmlAttribute
	public void setDuration(String duration) { this.duration = duration;}
	@XmlAttribute
	public void setDistance(String distance) { this.distance = distance;}
	@XmlAttribute
	public void setJournal(String journal) { this.journal = journal;}
	
	public String toString() {
		
		return String.format("%s\t%s\t%s\t%s\t%s\n", name, date, duration, distance, journal);
		
		
	}
	
	
	
}
