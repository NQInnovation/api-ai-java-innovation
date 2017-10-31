package hello;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

	@RequestMapping(method = RequestMethod.POST)
    public @ResponseBody WebhookResponse webhook(@RequestBody String obj) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException, SQLException{

        ObjectMapper mapper = new ObjectMapper();
        APIPojo apiPojo = mapper.readValue(obj, APIPojo.class);
        System.out.println(apiPojo.getResult().getAction());
        System.out.println(apiPojo.getResult().getParameters().getEmpid());
        
        // For contribution rate change
         if (apiPojo.getResult().getAction().equals("contributionRateChange")) {
        	 Connection connection = getDatabaseConnection();
        	 int empid = apiPojo.getResult().getParameters().getEmpid();
        	 String plantype = execStmtForContributionRateChange(connection, empid);
        	 if (plantype.equals("457(b)")) {
        		String text = "Yes, you can change the contribution rate for a 457(b) plan using the Change Deferral link on the Plan Dashboard";
        		return new WebhookResponse(text, text);
        	 } else {
        		 String text = "No you cannot change your contribution rate for Excess plan";
        		 return new WebhookResponse(text, text);
        	 }
       
         }        
         return new WebhookResponse("Not Welcome Intent", " Not Welcome Intent");
    }
	
	public Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		
		connection = DriverManager.getConnection(
				"jdbc:postgresql://ec2-54-221-229-64.compute-1.amazonaws.com:5432/d5a4n902vanlmo?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", "kauctvpnsyodlt",
				"b6e5fc9bbe5f88c42f2a83cdfd779fa8ead8fd6b4cae3da7c49674182e8d1939");
		return connection;
	}
	
	public String execStmtForContributionRateChange (Connection connection, int empid) throws SQLException {
		Statement st =null;
		st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT plantype from test where empid = " +empid);
		String plantype = null;
		while (rs.next()) {
			plantype = rs.getString("plantype");
		}
		
		return plantype;
		
	}
}
