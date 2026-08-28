<h1>Hotel Room Booking System</h1>

<p>A simple hotel room booking backend built using Spring Boot. The application manages rooms, customers, and bookings, with PostgreSQL used for persistent storage.</p>

<h2>1. Project Overview</h2>

<p>
This project started as a basic Java hotel room booking system and was later converted into a Spring Boot application.
The main purpose of the project is to practice building a backend application using REST APIs, JPA, PostgreSQL, validation, exception handling, and testing.
</p>

<p>The application currently supports:</p>

<ul>
    <li>Adding and viewing hotel rooms</li>
    <li>Adding and viewing customers</li>
    <li>Creating room bookings</li>
    <li>Viewing bookings</li>
    <li>Cancelling bookings</li>
    <li>Checking room availability</li>
    <li>Validating input data</li>
    <li>Handling invalid requests using custom exceptions</li>
</ul>

<h2>2. Features</h2>

<ul>
    <li>REST API using Spring Boot</li>
    <li>PostgreSQL database</li>
    <li>Spring Data JPA and Hibernate</li>
    <li>Room, Customer and Booking entities</li>
    <li>JPA relationships between bookings, rooms and customers</li>
    <li>Automatically generated booking IDs</li>
    <li>Room availability management</li>
    <li>Booking cancellation</li>
    <li>Input validation using Jakarta Validation</li>
    <li>Custom exceptions for missing resources and booking conflicts</li>
    <li>Repository query for checking active bookings</li>
    <li>Unit tests using JUnit and Mockito</li>
    <li>Controller tests using MockMvc</li>
</ul>

<h2>3. Technologies Used</h2>

<table>
    <tr>
        <th>Technology</th>
        <th>Usage</th>
    </tr>
    <tr>
        <td>Java 21</td>
        <td>Programming language</td>
    </tr>
    <tr>
        <td>Spring Boot 4.1.1</td>
        <td>Backend framework</td>
    </tr>
    <tr>
        <td>Spring Web MVC</td>
        <td>REST API</td>
    </tr>
    <tr>
        <td>Spring Data JPA</td>
        <td>Database access</td>
    </tr>
    <tr>
        <td>Hibernate</td>
        <td>ORM</td>
    </tr>
    <tr>
        <td>PostgreSQL</td>
        <td>Database</td>
    </tr>
    <tr>
        <td>Maven</td>
        <td>Build and dependency management</td>
    </tr>
    <tr>
        <td>JUnit 5</td>
        <td>Testing</td>
    </tr>
    <tr>
        <td>Mockito</td>
        <td>Mocking dependencies in unit tests</td>
    </tr>
    <tr>
        <td>MockMvc</td>
        <td>Testing REST controllers</td>
    </tr>
</table>

## 3. Project Structure

<p>The project is organized into the following main components:</p>

<ul>
  <li>
    <strong>Main Application</strong>
    <ul>
      <li><code>HotelroombookingsystemApplication.java</code> — starts the Spring Boot application</li>
    </ul>
  </li>

  <li>
    <strong>Controller</strong>
    <ul>
      <li><code>HotelController.java</code> — handles REST API requests</li>
    </ul>
  </li>

  <li>
    <strong>Service</strong>
    <ul>
      <li><code>HotelService.java</code> — contains the main booking and cancellation logic</li>
    </ul>
  </li>

  <li>
    <strong>Entities</strong>
    <ul>
      <li><code>Room.java</code></li>
      <li><code>Customer.java</code></li>
      <li><code>Booking.java</code></li>
      <li><code>BookingStatus.java</code></li>
    </ul>
  </li>

  <li>
    <strong>Repositories</strong>
    <ul>
      <li><code>RoomRepository.java</code></li>
      <li><code>CustomerRepository.java</code></li>
      <li><code>BookingRepository.java</code></li>
    </ul>
  </li>

  <li>
    <strong>Exception Handling</strong>
    <ul>
      <li><code>ResourceNotFoundException.java</code></li>
      <li><code>BookingConflictException.java</code></li>
      <li><code>GlobalExceptionHandler.java</code></li>
    </ul>
  </li>

  <li>
    <strong>Configuration</strong>
    <ul>
      <li><code>application.properties</code></li>
      <li><code>application-local.properties</code></li>
    </ul>
  </li>

  <li>
    <strong>Tests</strong>
    <ul>
      <li><code>HotelServiceTest.java</code></li>
      <li><code>HotelControllerTest.java</code></li>
      <li><code>HotelroombookingsystemApplicationTests.java</code></li>
    </ul>
  </li>
</ul>

<p>
  Other important project files include <code>pom.xml</code>, 
  <code>.gitignore</code>, and <code>README.md</code>.
</p>

<p>
  <code>application-local.properties</code> contains local PostgreSQL
  credentials and is excluded from Git.
</p>


<h2>5. Application Structure</h2>

<p>The project follows a simple layered structure:</p>

<pre>
Client / Postman -> HotelController -> HotelService -> JPA Repositories -> Hibernate / JPA -> PostgreSQL
</pre>

<p>
The controller handles HTTP requests, the service contains the main business logic,
and the repositories handle database operations through Spring Data JPA.
</p>

<h2>6. Database Design</h2>

<p>The application currently has three main entities.</p>

<h3>Room</h3>

<table>
    <tr>
        <th>Field</th>
        <th>Type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>roomNum</td>
        <td>int</td>
        <td>Primary key and room number</td>
    </tr>
    <tr>
        <td>roomType</td>
        <td>String</td>
        <td>Single, Double or Suite</td>
    </tr>
    <tr>
        <td>roomPrice</td>
        <td>double</td>
        <td>Price per night</td>
    </tr>
    <tr>
        <td>isAvailable</td>
        <td>boolean</td>
        <td>Current room availability</td>
    </tr>
</table>

<p>Room prices are currently set according to the room type:</p>

<table>
    <tr>
        <th>Room Type</th>
        <th>Price per night</th>
    </tr>
    <tr>
        <td>Single</td>
        <td>1200</td>
    </tr>
    <tr>
        <td>Double</td>
        <td>1800</td>
    </tr>
    <tr>
        <td>Suite</td>
        <td>2400</td>
    </tr>
</table>

<h3>Customer</h3>

<table>
    <tr>
        <th>Field</th>
        <th>Type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>customerId</td>
        <td>int</td>
        <td>Primary key</td>
    </tr>
    <tr>
        <td>customerName</td>
        <td>String</td>
        <td>Customer name</td>
    </tr>
</table>

<h3>Booking</h3>

<table>
    <tr>
        <th>Field</th>
        <th>Type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>bookingId</td>
        <td>int</td>
        <td>Automatically generated primary key</td>
    </tr>
    <tr>
        <td>status</td>
        <td>BookingStatus</td>
        <td>BOOKED or CANCELLED</td>
    </tr>
    <tr>
        <td>numOfNights</td>
        <td>int</td>
        <td>Number of nights</td>
    </tr>
    <tr>
        <td>room</td>
        <td>Room</td>
        <td>Room associated with the booking</td>
    </tr>
    <tr>
        <td>customer</td>
        <td>Customer</td>
        <td>Customer associated with the booking</td>
    </tr>
</table>

<h2>7. JPA Relationships</h2>

<p>
A booking belongs to one room and one customer. This is represented using
<code>@ManyToOne</code> relationships in the <code>Booking</code> entity.
</p>

<pre>
 Customer
         .    @ManyToOne
           .  
             . 
               Booking
             .     
           .  
         .    @ManyToOne
  Room     
</pre>

<p>The booking entity contains:</p>

<pre>
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "room_num")
private Room room;
</pre>

<p>and:</p>

<pre>
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "customer_id")
private Customer customer;
</pre>

<p>
The booking ID is generated by the database using
<code>GenerationType.IDENTITY</code>.
</p>

<pre>
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int bookingId;
</pre>

<h2>8. Repository Layer</h2>

<p>
The repositories extend <code>JpaRepository</code>, which provides common
database operations such as saving, finding, and deleting entities.
</p>

<pre>
public interface RoomRepository extends JpaRepository&lt;Room, Integer&gt;
</pre>

<pre>
public interface CustomerRepository extends JpaRepository&lt;Customer, Integer&gt;
</pre>

<pre>
public interface BookingRepository extends JpaRepository&lt;Booking, Integer&gt;
</pre>

<p>
The booking repository also contains a derived query used to check whether
a room currently has a booking:
</p>

<pre>
Optional&lt;Booking&gt; findByRoomRoomNumAndStatus(
    int roomNum,
    BookingStatus status
);
</pre>

<p>
This is used by the service before creating a new booking.
</p>

<h2>9. Service Layer</h2>

<p>
The main business logic is handled by <code>HotelService</code>.
</p>

<p>Some of the checks performed by the service include:</p>

<ul>
    <li>Checking whether a room exists</li>
    <li>Checking whether a customer exists</li>
    <li>Checking whether a room already has an active booking</li>
    <li>Checking whether the number of nights is valid</li>
    <li>Checking whether a room type is supported</li>
    <li>Updating room availability when booking or cancelling</li>
    <li>Updating booking status when a booking is cancelled</li>
</ul>

<p>
The service also calculates the total booking price using the room's price
and the number of nights.
</p>

<pre>
total price = room price × number of nights
</pre>

<h2>10. Controller Layer and REST API</h2>

<p>
The REST endpoints are exposed through <code>HotelController</code>.
</p>

<h3>Rooms</h3>

<table>
    <tr>
        <th>Method</th>
        <th>Endpoint</th>
        <th>Description</th>
        <th>Success Status</th>
    </tr>
    <tr>
        <td>GET</td>
        <td><code>/rooms</code></td>
        <td>Get all rooms</td>
        <td>200 OK</td>
    </tr>
    <tr>
        <td>GET</td>
        <td><code>/rooms/{roomNum}</code></td>
        <td>Get a specific room</td>
        <td>200 OK</td>
    </tr>
    <tr>
        <td>POST</td>
        <td><code>/rooms</code></td>
        <td>Add a room</td>
        <td>201 Created</td>
    </tr>
</table>

<h3>Customers</h3>

<table>
    <tr>
        <th>Method</th>
        <th>Endpoint</th>
        <th>Description</th>
        <th>Success Status</th>
    </tr>
    <tr>
        <td>GET</td>
        <td><code>/customers</code></td>
        <td>Get all customers</td>
        <td>200 OK</td>
    </tr>
    <tr>
        <td>POST</td>
        <td><code>/customers</code></td>
        <td>Add a customer</td>
        <td>201 Created</td>
    </tr>
</table>

<h3>Bookings</h3>

<table>
    <tr>
        <th>Method</th>
        <th>Endpoint</th>
        <th>Description</th>
        <th>Success Status</th>
    </tr>
    <tr>
        <td>POST</td>
        <td><code>/bookings</code></td>
        <td>Create a booking</td>
        <td>201 Created</td>
    </tr>
    <tr>
        <td>GET</td>
        <td><code>/bookings</code></td>
        <td>Get all bookings</td>
        <td>200 OK</td>
    </tr>
    <tr>
        <td>DELETE</td>
        <td><code>/bookings/{bookingId}</code></td>
        <td>Cancel a booking</td>
        <td>204 No Content</td>
    </tr>
</table>

<h2>11. Exception Handling</h2>

<p>The project uses two custom exceptions.</p>

<h3>ResourceNotFoundException</h3>

<p>
Used when a requested room, customer, or booking does not exist.
</p>

<pre>
public class ResourceNotFoundException extends RuntimeException
</pre>

<h3>BookingConflictException</h3>

<p>
Used when an operation conflicts with the current state of the system.
For example, trying to book a room that already has an active booking.
</p>

<pre>
public class BookingConflictException extends RuntimeException
</pre>

<p>The application uses the following HTTP responses for these situations:</p>

<table>
    <tr>
        <th>Status</th>
        <th>Example</th>
    </tr>
    <tr>
        <td>200 OK</td>
        <td>Successful GET request</td>
    </tr>
    <tr>
        <td>201 Created</td>
        <td>Room, customer or booking created</td>
    </tr>
    <tr>
        <td>204 No Content</td>
        <td>Booking successfully cancelled</td>
    </tr>
    <tr>
        <td>404 Not Found</td>
        <td>Room, customer or booking does not exist</td>
    </tr>
    <tr>
        <td>409 Conflict</td>
        <td>Room is already booked or booking is already cancelled</td>
    </tr>
</table>

<h2>12. Validation</h2>

<p>
Jakarta Bean Validation is used to validate incoming room and customer data.
</p>

<p>For example, a room number must be positive:</p>

<pre>
@Positive
private int roomNum;
</pre>

<p>Room type cannot be blank:</p>

<pre>
@NotBlank
private String roomType;
</pre>

<p>
The controller uses <code>@Valid</code> to trigger validation for request bodies.
</p>

<pre>
public ResponseEntity&lt;Room&gt; addRoom(
    @Valid @RequestBody Room room)
</pre>

<p>
The service also performs business validation, such as making sure the room
type is one of <code>Single</code>, <code>Double</code>, or <code>Suite</code>,
and that the number of nights is greater than zero.
</p>

<h2>13. Booking and Cancellation Flow</h2>

<h3>Booking</h3>

<ol>
    <li>Check whether the requested room exists.</li>
    <li>Check whether the customer exists.</li>
    <li>Check whether the room already has a <code>BOOKED</code> booking.</li>
    <li>Mark the room as unavailable.</li>
    <li>Create a new booking with status <code>BOOKED</code>.</li>
    <li>Save the booking using the repository.</li>
</ol>

<h3>Cancellation</h3>

<ol>
    <li>Find the booking.</li>
    <li>Check whether it has already been cancelled.</li>
    <li>Make the room available again.</li>
    <li>Change the booking status to <code>CANCELLED</code>.</li>
    <li>Save the updated booking.</li>
</ol>

<p>The basic lifecycle is:</p>

<pre>
Room Available -->  Book  -->  Room Unavailable  -->  Cancel Booking  -->  Room Available
</pre>

<h2>14. Testing</h2>

<p>
The project includes tests for the service layer, controller layer, and
Spring application context.
</p>

<table>
    <tr>
        <th>Test Class</th>
        <th>Tests</th>
        <th>Purpose</th>
    </tr>
    <tr>
        <td><code>HotelServiceTest</code></td>
        <td>12</td>
        <td>Tests service and business logic using Mockito</td>
    </tr>
    <tr>
        <td><code>HotelControllerTest</code></td>
        <td>7</td>
        <td>Tests REST endpoints using MockMvc</td>
    </tr>
    <tr>
        <td><code>HotelroombookingsystemApplicationTests</code></td>
        <td>1</td>
        <td>Checks that the Spring application context loads</td>
    </tr>
    <tr>
        <td><strong>Total</strong></td>
        <td><strong>20</strong></td>
        <td><strong>All passing</strong></td>
    </tr>
</table>

<p>
The service tests cover both successful operations and failure cases such as
missing rooms, missing customers, invalid nights, duplicate bookings and
already-cancelled bookings.
</p>

<p>
The controller tests use <code>MockMvc</code> to send HTTP requests and verify
the returned status codes and JSON response values.
</p>

<p>Tests can be run using:</p>

<pre>
mvn test or ./mvnw clean test (Maven wrapper class)
</pre>

<h2>15. Running the Project</h2>

<h3>Requirements</h3>

<ul>
    <li>Java 21</li>
    <li>Maven</li>
    <li>PostgreSQL</li>
</ul>

<h3>1. Clone the repository</h3>

<pre>
git clone https://github.com/MonikaBiradar/Hotel-Room-Booking-System-SpringBoot.git
</pre>

<h3>2. Create the PostgreSQL database</h3>

<p>
Create a PostgreSQL database for the application. The current project uses
the database name <code>hotelbooking</code>.
</p>

<h3>3. Configure database credentials</h3>

<p>
Create an <code>application-local.properties</code> file with your own
PostgreSQL username and password.
</p>

<p>Example:</p>

<pre>
spring.datasource.url=jdbc:postgresql://localhost:5432/hotelbooking
spring.datasource.username=your_username
spring.datasource.password=your_password
</pre>

<p>
The local configuration file is excluded from Git using <code>.gitignore</code>,
so database credentials are not committed to the repository.
</p>

<h3>4. Build the project</h3>

<pre>
mvn clean install
</pre>

<h3>5. Run the application</h3>

<pre>
mvn spring-boot:run
</pre>

<p>
The API can then be tested using Postman or another REST client.
</p>

<h2>16. Example Requests</h2>

<h3>Add a Room</h3>

<pre>
POST /rooms
Content-Type: application/json

{
    "roomNum": 101,
    "roomType": "Single"
}
</pre>

<h3>Add a Customer</h3>

<pre>
POST /customers
Content-Type: application/json

{
    "customerId": 1,
    "customerName": "Alice"
}
</pre>

<h3>Book a Room</h3>

<pre>
POST /bookings?roomNum=101&amp;customerId=1&amp;totalNights=2
</pre>

<h3>Get Rooms</h3>

<pre>
GET /rooms
</pre>

<h3>Get Customers</h3>

<pre>
GET /customers
</pre>

<h3>Get Bookings</h3>

<pre>
GET /bookings
</pre>

<h3>Cancel a Booking</h3>

<pre>
DELETE /bookings/1
</pre>

<h2>Future Improvements</h2>

<p>Some things that could be added to the project later:</p>

<ul>
    <li>Add update and delete operations for rooms and customers</li>
    <li>Add check-in and check-out dates</li>
    <li>Support multiple bookings over different dates</li>
    <li>Add authentication and authorization</li>
    <li>Use DTOs instead of exposing entities directly</li>
    <li>Add Swagger/OpenAPI documentation</li>
    <li>Add integration tests with a separate test database</li>
    <li>Add a frontend</li>
    <li>Containerize the application with Docker</li>
</ul>

<h2>Project Status</h2>

<p>
The current version contains the core room, customer and booking functionality,
PostgreSQL persistence, REST APIs, validation, exception handling and automated
tests.
</p>

<p><strong>20 tests passing.</strong></p>

<p><strong>
This project was built as a hands-on Spring Boot backend project for my own practice
of Java backend development and to understand how the different parts of a typical
Spring Boot application work together.
</strong></p>

<hr>

<p><strong>Author:</strong> Monika Biradar</p>
