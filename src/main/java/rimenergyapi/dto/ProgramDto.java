package rimenergyapi.dto;

public class ProgramDto {
	
	private int id;
	private String name;
	private String regex;
	private String from;
	private String to;

	public ProgramDto() { }

	public ProgramDto(int id, String name, String regex) {
		this.id = id;
		this.name = name;
		this.regex = regex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
}
