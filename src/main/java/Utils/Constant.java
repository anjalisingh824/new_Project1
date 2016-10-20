package Utils;

public class Constant {
	public static final String FILE_FULL_PATH="/home/smart/workspace/new_Project/src/main/resources/TestingApp.xlsx";
	public static final String FILE_PATH = "src/main/resources/";
	public static final String FILE_NAME = "TestingApp.xlsx";
	public static final String SHEET_NAME = "Sheet1";
	public static final String URL = "http://139.59.29.185/admin";
	public static final String mobileNo="919408580801";
	public static final String password="smart123";
	
	//page title check
    public static final String LogIn_Title="Log in | Django site admin";
    public static final String SelectFeed_Title="Site administration | Django site admin";
    public static final String NewsFeed_Title="Select news feed to change | Django site admin";
    public static final String AddNewsFeed_Title="Add news feed | Django site admin";

    //error check
    public static final String Mandatory_error="This field is required.";
    public static final String titleError_check="News title should be of 10 to 40 characters";
    public static final String description_error="News description should be of 100 to 2000 characters";
   
    
    //event 
    public static final String event_Title="Select event to change | Django site admin";
    public static final String AddEvent_Title="Add event | Django site admin";
    public static final String date_error="Event start date should be lesser than event end date";


    //project
    public static final String selectProject_Title="Select project to change | Django site admin";
    public static final String addProject_Title="Add project | Django site admin";
    public static final String projectTitle_error="Project name must be between 5 to 40 characters";
    public static final String Date_error="Enter a valid date.";
    public static final String Time_error="Enter a valid time ";
    public static final String descriptionProject_error="Project description must be between 50 to 2000 characters";
    public static final String image_error="Upload a valid image. The file you uploaded was either not an image or a corrupted image.";
    public static final String projectCost_error="Ensure this value is less than or equal to 4294967295.";
    


}
