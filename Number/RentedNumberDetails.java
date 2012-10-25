package plivo.helper;
import java.util.LinkedHashMap;
import java.util.Scanner;

import com.plivo.sdk.client.RestAPI;
import com.plivo.sdk.exception.PlivoException;
import com.plivo.sdk.response.number.Number;


public class RentedNumberDetails {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");

		Number numberDetails = new Number();
		LinkedHashMap<String ,String> params = new LinkedHashMap<String, String>();
		
		String rentedNumber = "";
		Scanner scanner = new Scanner(System.in);
		System.out.print("enter the rented number : ");
		
		rentedNumber = scanner.nextLine();
		
		
		try 
		{
			numberDetails = restAPI.getRentedNumberDetails(rentedNumber);
			System.out.println(numberDetails.numberType);
		}
		catch (PlivoException plivoException) 
		{
			plivoException.printStackTrace();
		}
		
		
	}

}
