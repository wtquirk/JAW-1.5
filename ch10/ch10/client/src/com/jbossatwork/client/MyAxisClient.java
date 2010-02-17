package com.jbossatwork.client;

public class MyAxisClient {
	public static void main(String [] args) {

		try {
			System.out.println("Finding InventoryService ...\n");
			InventoryService service = new InventoryServiceLocator();
			System.out.println("Getting InventoryEndpoint ...\n");
			InventoryEndpoint endpoint = service.getInventoryEndpointPort();

			System.out.println("Getting Cars ...");

                  // If you're using J2SE 1.4, the next 2 lines of code will work.
			//CarDTOArray carDTOArray = endpoint.findAvailableCars();
			//CarDTO[] cars = carDTOArray.getCars();

			// If you're using Java 5 (J2SE 1.5), delete the previous 2 lines of code and
			// and uncomment the next line of code. Axis 1.2.1 optimized away the CarDTOArray.
			CarDTO[] cars = endpoint.findAvailableCars();

			for (int i = 0; i < cars.length; ++i) {
				System.out.println("Year = [" + cars[i].getModelYear() + "], Make = [" + cars[i].getMake() +
			             "], Model = [" + cars[i].getModel() + "], status = [" + cars[i].getStatus() + "]");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}