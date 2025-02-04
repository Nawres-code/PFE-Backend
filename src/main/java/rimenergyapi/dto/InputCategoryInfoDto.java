package rimenergyapi.dto;

public class InputCategoryInfoDto {
		private int id;
	    private String name;
	    private String color;
	    private String icon;
	    private String type;
	    
		public InputCategoryInfoDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		public InputCategoryInfoDto(int id, String name, String color, String icon) {
			super();
			this.id = id;
			this.name = name;
			this.color = color;
			this.icon = icon;
		}
		
		public InputCategoryInfoDto(int id, String name, String color, String icon, String type) {
			super();
			this.id = id;
			this.name = name;
			this.color = color;
			this.icon = icon;
			this.type = type;
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
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
}
