package Utils;

public class Constant {
	public static final String FILE_FULL_PATH="/home/smart/workspace/new_Project/src/main/resources/TestingApp.xlsx";
	public static final String FILE_PATH = "src/main/resources/";
	public static final String FILE_NAME = "TestingApp.xlsx";
	public static final String SHEET_NAME = "Sheet1";
	public static final String URL = "http://139.59.29.185/admin";
	public static final String MOBILE_NO="919408580801";
	public static final String PASSWORD="smart123";
	
	//page title check
    public static final String LOGIN_TITLE="Log in | Django site admin";
    public static final String SELECT_FEED_TITLE="Site administration | Django site admin";
    public static final String NEWS_FEED_TITLE="Select news feed to change | Django site admin";
    public static final String ADD_NEWSFEED_TITLE="Add news feed | Django site admin";

    //error check
    public static final String MANDATORY_ERROR="This field is required.";
    public static final String TITLE_ERROR_CHECK="News title should be of 10 to 40 characters";
    public static final String DESCRIPTION_ERROR="News description should be of 100 to 2000 characters";
   
    
    //event 
    public static final String EVENT_TITLE="Select event to change | Django site admin";
    public static final String ADD_EVENT_TITLE="Add event | Django site admin";
    public static final String DATE_ERROR_CHECK="Event start date should be lesser than event end date";


    //project
    public static final String SELECT_PROJECT_TITLE="Select project to change | Django site admin";
    public static final String ADD_PROJECT_TITLE="Add project | Django site admin";
    public static final String PROJECT_TITLE_CHECK="Project name must be between 5 to 40 characters";
    public static final String DATE_ERROR="Enter a valid date.";
    public static final String TIME_ERROR="Enter a valid time ";
    public static final String DESCRIPTION_PROJECT_ERROR="Project description must be between 50 to 2000 characters";
    public static final String IMAGE_ERROR="Upload a valid image. The file you uploaded was either not an image or a corrupted image.";
    public static final String PROJECT_COST_ERROR="Ensure this value is less than or equal to 4294967295.";
    


}
