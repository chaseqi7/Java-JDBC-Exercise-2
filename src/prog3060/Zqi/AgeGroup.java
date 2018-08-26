package prog3060.Zqi;

import java.util.HashSet;
import java.util.Set;
public class AgeGroup {
	
	public int getAgeGroupID() {
		return ageGroupID;
	}
	public void setAgeGroupID(int ageGroupID) {
		this.ageGroupID = ageGroupID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	int ageGroupID;
	String description;
	
    private Set<Age> age = new HashSet<Age>();

	public Set<Age> getAge() {
		return age;
	}
	public void setAge(Set<Age> age) {
		this.age = age;
	}
}
