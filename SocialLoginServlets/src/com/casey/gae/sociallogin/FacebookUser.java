package com.casey.gae.sociallogin;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
/*
 * {"id":"520885766",
 * 	"name":"Karen Castillo",
 *  "first_name":"Karen",
 *  "last_name":"Castillo",
 *  "link":"http:\/\/www.facebook.com\/profile.php?id=520885766",
 *  "favorite_teams":[{"id":"104055132963391","name":"Chelsea F.C."},
 *  				  {"id":"56680618384","name":"Ferrari Formula 1"},
 *  				  {"id":"114991068515354","name":"Bar\u00e7a"}],
 *  "favorite_athletes":[{"id":"176063032413299","name":"Leo Messi"},
 *  					 {"id":"81221197163","name":"Cristiano Ronaldo"},
 *  					 {"id":"62975399193","name":"Michael Jordan"},
 *  					 {"id":"105545672813385","name":"Scottie Pippen"},
 *  					 {"id":"64760994940","name":"Roger Federer"}],
 *  "gender":"female",
 *  "email":"laprima\u0040hotmail.com",
 *  "locale":"en_US",
 *  "verified":true,
 *  "updated_time":"2011-10-06T19:41:00+0000"}
 */
public class FacebookUser {
	
	private String firstName;
	private String lastName;
	private String id;
	private String email;
	
	public void load(String jsonValues) throws JSONException{
		
		JSONObject jsonObject = new JSONObject(jsonValues);
		this.id = jsonObject.getString("id");
		this.firstName = jsonObject.getString("first_name");
		this.lastName = jsonObject.getString("last_name");
		this.email = jsonObject.getString("email");
		
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
