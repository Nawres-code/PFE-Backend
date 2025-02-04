package rimenergyapi.dto;

public class OutputDto {

	private int id;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public OutputDto(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public OutputDto() {}
	
	@Override
	public String toString() {
		return "OutputDto [id=" + id + ", name=" + name + "]";
	}
	
	
}
