package prog3060.Zqi;

import java.util.HashSet;
import java.util.Set;

public class CensusYear {
	
	public int getCensusYearID() {
		return censusYearID;
	}
	public void setCensusYearID(int censusYearID) {
		this.censusYearID = censusYearID;
	}
	public int getCensusYear() {
		return censusYear;
	}
	public void setCensusYear(int censusYear) {
		this.censusYear = censusYear;
	}
	
	int censusYearID;
	int censusYear;

    private Set<Age> age = new HashSet<Age>();

	public Set<Age> getAge() {
		return age;
	}
	public void setAge(Set<Age> age) {
		this.age = age;
	}
}
