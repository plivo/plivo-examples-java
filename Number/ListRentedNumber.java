package plivo.helper;


import com.plivo.sdk.client.RestAPI;
import com.plivo.sdk.exception.PlivoException;
import com.plivo.sdk.response.number.NumberSearchFactory;

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
