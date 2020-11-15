package com.utility;

public class Constant {
    //General Path Test Data
    public static final String PathTestData = System.getProperty("user.dir") + "/TestData/";
    //public static final String SAPSheet = "GeneTech";

    //Timeout Values
    public static final int WaitingSeconds = 40;

    //Contracts Date
    public static final String EffectiveDate = WebdriverUtils.getDate(-10);
    public static final String TermDate = WebdriverUtils.getDate(+20);
    //Site Locations Date
    public static final String BeforeDate = WebdriverUtils.getDate(-3);
    public static final String AfterDate = WebdriverUtils.getDate(+3);

    //Browsers Firefox, Chrome, IE10, Safari
    public static final String vIEBrowserPath = System.getProperty("user.dir") + "/drivers/IEDriverServer.exe";
    public static final String vChromeBrowserPath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
    public static final String vFirefoxBrowserPath=System.getProperty("user.dir") + "/drivers/geckodriver.exe";

    public static class Columns {
        //Test Data Sheet Columns
        public static final String UserName = "Username";
        public static final String Password = "Password";
        public static final String Productcode = "Productcode";
		public static final String ItemAdded = "Itemaddedtext";
		public static final String OrderConfirmText = "";
		public static final String PromoApplyText ="PromoapplyText";
		public static final String QuoteOrderConfirmText = "QuoteOrderConfirmText";
		}
		
       }
