package com.roombooking.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.roombooking.exception.CategoryNotFoundException;
import com.roombooking.exception.RoomNotAvailableException;
import com.roombooking.exception.TypeNotFoundException;
import com.roombooking.model.Room;
import com.roombooking.model.RoomCategory;
import com.roombooking.model.RoomType;
import com.roombooking.service.BookingServiceImpl;
import com.roombooking.service.IBookingService;
import com.roombooking.service.IRoomService;
import com.roombooking.service.IUserService;
import com.roombooking.service.RoomServiceImpl;
import com.roombooking.service.UserServiceImpl;
import com.roombooking.user.User;

public class Client {

	public static void main(String[] args) {
		IRoomService roomService = new RoomServiceImpl();
		IUserService userService = new UserServiceImpl();
		IBookingService bookingService = new BookingServiceImpl();
		Scanner sc = new Scanner(System.in);

		System.out.println("1).Admin \t2).User \t3).Change password ");
		int select = sc.nextInt();
		
		switch (select) {
		
		case 1: {
			sc.nextLine();		
			System.out.println("Enter the admin name :");
			String name = sc.nextLine();
			System.out.println("Enter the password :");
			String password = sc.nextLine();
			boolean admin= userService.login(name, password);
			if(admin) {
			System.out.println("1).Add Room\n2).Update Room\n3).Delete room");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:

				System.out.println("Enter the room category (0=AC,1=NONAC):");
				int selection = sc.nextInt();
				RoomCategory[] categoryArray = RoomCategory.values();
				String category = categoryArray[selection].roomCategory;

				System.out.println(
						"Enter room type(0=STANDARDQUEEN, 1=STANDARDKING,2=STANDARD,3=DELUXE,4=SUPERDELUXE,5=CLASSIC,6=SUITES)");
				int roomType = sc.nextInt();
				RoomType[] typeArray = RoomType.values();
				String type = typeArray[roomType].type;

				sc.nextLine();
				LocalDate startDate;
				System.out.println("Enter start date (yyyy-MM-dd) :");
				String date = sc.nextLine();
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				startDate = LocalDate.parse(date, format);

				LocalDate endDate;
				// sc.nextLine();
				System.out.println("Enter end date (yyyy-MM-dd) :");
				String departDate = sc.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				endDate = LocalDate.parse(departDate, formatter);

				System.out.println("Enter no of guests allowed :");
				int guest = sc.nextInt();

				System.out.println("Enter the price :");
				double price = sc.nextDouble();
				
				System.out.println("Enter the availability :");
				int availability = sc.nextInt();

				Room room = new Room(category, type, startDate, endDate, guest, price, availability);
				int result = roomService.addRoom(room);

				if (result == 1) {
					System.out.println("Added successfully");
				} else {
					System.out.println("Not added");
				}
				break;

			case 2:
				System.out.println("Enter the room number to update :");
				int roomNumber = sc.nextInt();
				System.out.println("Enter the amount to be update :");
				price = sc.nextDouble();
				int updateRoom = roomService.updateRoom(roomNumber, price);
				if (updateRoom == 1) {
					System.out.println("Updated successfully");
				} else {
					System.out.println("Not updated");
				}
				break;

			case 3:
				System.out.println("Enter the room number to be deleted :");
				roomNumber = sc.nextInt();
				roomService.deleteRoom(roomNumber);
				System.out.println("Deleted successfully");
				break;

			default:
				System.out.println("Invalid");
			}
			break;
			}
		}
	
		case 2: {
			select = 0;
			System.out.println("1).New user 2).Login");
			int option = sc.nextInt();
			if (option == 1) {
				System.out.println("Welcome to registartion");
				sc.nextLine();
				System.out.println("Enter the username :");
				String username = sc.nextLine();
				System.out.println("Enter the name :");
				String name = sc.nextLine();
				System.out.println("Create a password :");
				String password = sc.nextLine();
				System.out.println("Enter your location :");
				String location = sc.nextLine();
				System.out.println("Enter mobile number :");
				long mobileNumber = sc.nextLong();

				User user = new User(username, name, password, location, mobileNumber);
				userService.register(user);

			} else if (option == 2) {
				sc.nextLine();
				System.out.println("Enter the user name :");
				String username = sc.nextLine();
				System.out.println("Enter the password :");
				String password = sc.nextLine();
				boolean user = userService.login(username, password);
				System.out.println(user);
				if (!user) {
					System.out.println("Incorrect");
				} else {

					System.out.println(
							"1).Get all rooms\n2).Filter by price\n3).Search by category\n4).Search by room category and price\n5).Search by room type and price\n6).Search by date\n7).Search by date, room category and room type\n8).Booking");

					int choice = sc.nextInt();

					switch (choice) {
					case 1:
						List<Room> rooms = new ArrayList<>();
						rooms = roomService.getAllRooms();
						for (Room room : rooms) {
							System.out.println(room);
						}
						break;

					case 2:
						rooms = roomService.getByLessPrice();
						for (Room room : rooms) {
							System.out.println(room);
						}
						break;

					case 3:
						sc.nextLine();
						System.out.println("Enter AC/NON-AC room :");
						String roomCategory = sc.nextLine();
						try {
							rooms = roomService.getByCategory(roomCategory);
							for (Room room : rooms) {
								System.out.println(room);
							}
						} catch (CategoryNotFoundException e) {
							System.out.println(e.getMessage());
						}

						break;

					case 4:
						sc.nextLine();
						System.out.println("Enter AC/NON-AC room : ");
						String category = sc.nextLine();
						System.out.println("Enter price :");
						double roomPrice = sc.nextDouble();

						try {
							rooms = roomService.getByCategoryAndPrice(category, roomPrice);
							for (Room room : rooms) {
								System.out.println(room);
							}
						} catch (CategoryNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						break;

					case 5:
						List<Room> roomByPrice = new ArrayList<>();
						sc.hasNextLine();
						System.out.println(
								"Enter \n1.Queen size\n2.King Size\n3.Standard\n4.Double\n5.Family\n6.Single\n7.Suite");

						int selectType = sc.nextInt();
						if (selectType == 1) {
							String type = "Queen Size";
							System.out.println("Enter price :");
							double priceTwo = sc.nextDouble();
							try {
								roomByPrice = roomService.getByRoomType(type, priceTwo);
							} catch (TypeNotFoundException e) {
								System.out.println(e.getMessage());
							}
						} else if (select == 2) {
							String type = "King Size";
							System.out.println("Enter price :");
							double priceTwo = sc.nextDouble();
							try {
								roomByPrice = roomService.getByRoomType(type, priceTwo);
							} catch (TypeNotFoundException e) {
								System.out.println(e.getMessage());
							}
						} else if (select == 3) {
							String type = "Standard";
							System.out.println("Enter price :");
							double priceTwo = sc.nextDouble();
							try {
								roomByPrice = roomService.getByRoomType(type, priceTwo);
							} catch (TypeNotFoundException e) {
								System.out.println(e.getMessage());
							}
						} else if (select == 4) {
							String type = "Double";
							System.out.println("Enter price :");
							double priceTwo = sc.nextDouble();
							try {
								roomByPrice = roomService.getByRoomType(type, priceTwo);
							} catch (TypeNotFoundException e) {
								System.out.println(e.getMessage());
							}
						} else if (select == 5) {
							String type = "Family";
							System.out.println("Enter price :");
							double priceTwo = sc.nextDouble();
							try {
								roomByPrice = roomService.getByRoomType(type, priceTwo);
							} catch (TypeNotFoundException e) {
								System.out.println(e.getMessage());
							}
						} else if (select == 6) {
							String type = "Single";
							System.out.println("Enter price :");
							double priceTwo = sc.nextDouble();
							try {
								roomByPrice = roomService.getByRoomType(type, priceTwo);
							} catch (TypeNotFoundException e) {
								System.out.println(e.getMessage());
							}
						} else if (select == 7) {
							String type = "Suite";
							System.out.println("Enter price :");
							double priceTwo = sc.nextDouble();
							try {
								roomByPrice = roomService.getByRoomType(type, priceTwo);
							} catch (TypeNotFoundException e) {
								System.out.println(e.getMessage());
							}
						} else {
							System.out.println("Invalid");
						}

						for (Room room : roomByPrice) {
							System.out.println(room);
						}
						break;

					case 6:
						sc.nextLine();
						System.out.println("Enter date (yyyy-MM-dd)");
						LocalDate arrivalDate;
						String startDateOne = sc.nextLine();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						arrivalDate = LocalDate.parse(startDateOne, formatter);
						try {
							rooms = roomService.getByAvailability(arrivalDate);
							for (Room room : rooms) {
								System.out.println(room);
							}
						} catch (RoomNotAvailableException e) {
							System.out.println(e.getMessage());
						}

						break;

					case 7:
						List<Room> roomByAvailiability = new ArrayList<>();
						LocalDate startDate;
						sc.nextLine();
						System.out.println("Enter start date (yyyy-MM-dd) :");
						String date = sc.nextLine();
						DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						startDate = LocalDate.parse(date, format);

						System.out.println("Enter category :");
						String categoryTwo = sc.nextLine();
						System.out.println(
								"Enter \n1.Queen size\n2.King Size\n3.Standard\n4.Double\n5.Family\n6.Single\n7.Suite");

						int selectRoomType = sc.nextInt();
						if (selectRoomType == 1) {
							String type = "Queen Size";
							try {
								roomByAvailiability = roomService.getByAvailabilityAndType(startDate, categoryTwo,
										type);
							} catch (RoomNotAvailableException e) {
								System.out.println(e.getMessage());
							}
						} else if (selectRoomType == 2) {
							String type = "King Size";
							try {
								roomByAvailiability = roomService.getByAvailabilityAndType(startDate, categoryTwo,
										type);
							} catch (RoomNotAvailableException e) {
								System.out.println(e.getMessage());
							}
						} else if (selectRoomType == 3) {
							String type = "Standard";
							try {
								roomByAvailiability = roomService.getByAvailabilityAndType(startDate, categoryTwo,
										type);
							} catch (RoomNotAvailableException e) {
								System.out.println(e.getMessage());
							}
						} else if (selectRoomType == 4) {
							String type = "Double";
							try {
								roomByAvailiability = roomService.getByAvailabilityAndType(startDate, categoryTwo,
										type);
							} catch (RoomNotAvailableException e) {
								System.out.println(e.getMessage());
							}
						} else if (selectRoomType == 5) {
							String type = "Family";
							try {
								roomByAvailiability = roomService.getByAvailabilityAndType(startDate, categoryTwo,
										type);
							} catch (RoomNotAvailableException e) {
								System.out.println(e.getMessage());
							}
						} else if (selectRoomType == 6) {
							String type = "Single";
							try {
								roomByAvailiability = roomService.getByAvailabilityAndType(startDate, categoryTwo,
										type);
							} catch (RoomNotAvailableException e) {
								System.out.println(e.getMessage());
							}
						} else if (selectRoomType == 7) {
							String type = "Suite";
							try {
								roomByAvailiability = roomService.getByAvailabilityAndType(startDate, categoryTwo,
										type);
							} catch (RoomNotAvailableException e) {
								System.out.println(e.getMessage());
							}
						} else {
							System.out.println("Invalid");
						}
						System.out.println(roomByAvailiability);
						for (Room room : roomByAvailiability) {
							System.out.println(room);
						}

						break;
						
					case 8:
						sc.nextLine();
						System.out.println("Booking.....");
						System.out.println("Enter room category :");
						category = sc.nextLine();
						String type = null;
						System.out.println("Enter \n1.Queen size\n2.King Size\n3.Standard\n4.Double\n5.Family\n6.Single\n7.Suite");
						selectRoomType = sc.nextInt();
						if (selectRoomType == 1) {
							type = "Queen Size";							
						} else if (selectRoomType == 2) {
							type = "King Size";							
						} else if (selectRoomType == 3) {
							type = "Standard";							
						} else if (selectRoomType == 4) {
							type = "Double";							
						} else if (selectRoomType == 5) {
							type = "Family";							
						} else if (selectRoomType == 6) {
							type = "Single";							
						} else if (selectRoomType == 7) {
							type = "Suite";
						} else {
							System.out.println("Invalid");
						}
		
						try {
							User finalUser = new User();
							Room room = bookingService.booking(category,type);
							bookingService.updateAvailablity(room.getRoomNumber());
							System.out.println("Hi "+username+" your room is booked and your room number is "+room.getRoomNumber());
							System.out.println(room);
							
						} catch (RoomNotAvailableException e) {
							e.printStackTrace();
						}
						
					}
				}
			} else {
				System.out.println("Invalid");
			}

		}
			break;
		case 3:
			sc.nextLine();
			System.out.println("Enter user name :");
			String username = sc.nextLine();
			System.out.println("Enter the new password :");
			String newPassword = sc.nextLine();
			int user = userService.changePassword(newPassword, username);
			if(user == 1) {
				System.out.println("password updated successfully");
			}
			else {
				System.out.println("Failed");
			}
				
			break;
			
		default:
			System.out.println("Invalid input");
		}
		sc.close();
	}
		
	}
	

