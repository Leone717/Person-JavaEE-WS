package hu.leone.rest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import hu.leone.datatypes.Person;

/** Representational State Transfer (RESTful)
 * - jobban integralodik a HTTP portokollba mint a SOAP alapu
 * - HTTP muveletekre es visszaadott statusz kodokra tamaszkodik (200, 201, 204, 400, 403, 404, 405, 409, 500)
 * - konnyen es gyorsan implementalhato
 * - nem nagy mereto SOAP/XML uzenetek cserelnek gazdat, hanem kis meretu, csak adatokra koncentralo uzenetek
 * 
 * RESTful WebService
 * - POJO alapon hozzuk letre
 * - Stateless mukodes, de stateful is lehet(ekkor allapotat kuldi az uzenetben)
 * - JAX-RS szabvany deifinalja
 * - 5 kulonbozo HTTP keresre adhat valaszt
 * - Cloud rendszerekben es Web-es szolgaltatasokban is jelentos szerephez jut 
 * 
 * HTTP methods: 
 * 
 * POST 	-> Create		-> datas in body 		/products, /products/new
 * GET 		-> Read			-> datas in URI			/products, /products/:id
 * PUT 		-> Update								/products/:id, /products/:id/edit
 * DELETE 	-> Delete								/products/:id
 * HEAD		-> =GET without body 
 * 
 * WADL - Web Application Description Language 
 * - WADL leiíras a WSDL megfeleloje REST Web Service-ek eseten. WADL nem standardizalt.
 * - WSDL is hasznalhato REST szolgatatas leirasara 
 * Apache CXF, JAX-RS(WADL -> Java)
 * http://host:port/.../application.wadl(Java -> WADL)
 * 
 * @GET, @POST, @DELETE, @PUT
 * @Produces, @Consumes - keres es valasz MIME tipusanak beallitasa
 * @PathParam - URL-ben atadott parameter kiolvasasa es adatadsa argumentumkent
 * 
 */
@Path("/rest")
public class DateTimeREST {

	@GET
	@Path("/datetime/{dateortime}")
	public String getDateOrTime(@PathParam("dateortime") String dateortime) {

		if (dateortime.equals("date")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			return sdf.format(new Date());
		}

		if (dateortime.equals("time")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			return dtf.format(LocalDateTime.now());
		}

		return "N/A";
	}

	// http://localhost:7001/PersonWebService/resources/rest/datetime/
	@GET
	@Path("/datetime")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8") /* javax.ws.rs.core.MediaType */
	public Map<String, String> getDateTime() {
		String date = getDateOrTime("date");
		String time = getDateOrTime("time");

		Map<String, String> result = new HashMap<>();
		result.put("date", date);
		result.put("time", time);

		return result;
	}

	// http://localhost:7001/PersonWebService/resources/rest/person
	@GET
	@Path("/person")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Person getPerson() {
		return new Person("Joe Doe", 3);
	}

	// http://localhost:7001/PersonWebService/resources/rest/person/xml
	@GET
	@Path("/person/xml")
	@Produces(MediaType.APPLICATION_XML + ";charset=UTF-8")
	public Response getPersonXML() {
		Person person = getPerson();
		return Response.status(Status.CREATED).entity(person).build();
	}

	// http://localhost:7001/PersonWebService/resources/rest/error
	@GET
	@Path("/error")
	@Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
	public Response getError() { /* javax.ws.rs.core.Response */
		return Response.status(Status.BAD_REQUEST).entity("Hibás bemeneti adat").build();
	}

}
