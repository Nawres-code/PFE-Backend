package rimenergyapi.entity.Type;

public enum GroupsType {

    GENERAL("General"), SOUS_GENERAL("Sous_general"), OTHERS("Others"),INDEPENDANT("Independant");

    private String value ;

    private GroupsType(String state){
        this.value = state;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public static GroupsType fromValue(String x){
        if(x == null){
            return null;
        }
        switch (x){
            case "General":
                return GENERAL;
            case "Sous_general" :
                return SOUS_GENERAL;
            case "Others" :
                return OTHERS;
            case "Independant" :
                return INDEPENDANT;

        }
        return null;
    }
}
