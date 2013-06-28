package plivo.helper;


import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.api.response.number.NumberSearchFactory;

public class ListRentedNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");

		NumberSearchFactory numbers = new NumberSearchFactory();
		
		try 
		{
			numbers = restAPI.getNumbers();
			System.out.println(numbers.numberList.get(0).number);
		}
		catch (PlivoException plivoException)
		{
			plivoException.printStackTrace();
		}
		
	}

}
