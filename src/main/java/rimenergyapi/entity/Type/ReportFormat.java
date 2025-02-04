package rimenergyapi.entity.Type;


public enum ReportFormat {
	
	WORD("word"), PDF("pdf"), EXCEL("excel");
	
	private String value;

	private ReportFormat(String state) {
		this.value = state;
	}
	
	
	public static ReportFormat fromValue(String x) {
		if (x == null) {
			return null;
		}
		switch (x) {
		case "word":
			return WORD;
		case "pdf":
			return PDF;
		case "excel":
			return EXCEL;
		}
		return null;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}
	
	
}
