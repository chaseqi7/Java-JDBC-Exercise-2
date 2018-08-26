package prog3060.Zqi;

import java.util.HashSet;
import java.util.Set;
public class GeographicArea {
	
	public int getGeographicAreaID() {
		return geographicAreaID;
	}
	public void setGeographicAreaID(int geographicAreaID) {
		this.geographicAreaID = geographicAreaID;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAlternativeCode() {
		return alternativeCode;
	}
	public void setAlternativeCode(int alternativeCode) {
		this.alternativeCode = alternativeCode;
	}
	
	
	int geographicAreaID;
	int code;
	int level;
	String name;
	int alternativeCode;

    private Set<Age> age = new HashSet<Age>();

	public Set<Age> getAge() {
		return age;
	}
	public void setAge(Set<Age> age) {
		this.age = age;
	}
	
}
