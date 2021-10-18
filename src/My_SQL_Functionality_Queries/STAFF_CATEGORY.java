package My_SQL_Functionality_Queries;

public enum STAFF_CATEGORY 
{
	STANDING_ADMINISTRATIVE("standing_administrative"), 
    CONTRACT_ADMINISTRATIVE("contract_administrative"), 
    PERMANENT_TEACHING("permanent_teaching"), 
    CONTRACT_TEACHING("contract_teaching");
 
    private String category_name;
 
    STAFF_CATEGORY(String category_name) {
        this.category_name = category_name;
    }
 
    public String getCategory() {
        return category_name;
    }
    

} 