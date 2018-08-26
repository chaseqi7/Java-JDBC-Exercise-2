package prog3060.Zqi;

public class Age {
	
	public int getAgeID() {
		return ageID;
	}
	public void setAgeID(int ageID) {
		this.ageID = ageID;
	}
	public GeographicArea getGeographicArea() {
		return geographicArea;
	}
	public void setGeographicArea(GeographicArea geographicArea) {
		this.geographicArea = geographicArea;
	}
	public AgeGroup getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(AgeGroup ageGroup) {
		this.ageGroup = ageGroup;
	}
	public CensusYear getCensusYear() {
		return censusYear;
	}
	public void setCensusYear(CensusYear censusYear) {
		this.censusYear = censusYear;
	}
	public int getCombined() {
		return combined;
	}
	public void setCombined(int combined) {
		this.combined = combined;
	}
	public int getMale() {
		return male;
	}
	public void setMale(int male) {
		this.male = male;
	}
	public int getFemale() {
		return female;
	}
	public void setFemale(int female) {
		this.female = female;
	}
	

	int ageID;
	private GeographicArea geographicArea;
	private AgeGroup ageGroup;
	private CensusYear censusYear;
	int combined;
	int male;
	int female;
	

}
