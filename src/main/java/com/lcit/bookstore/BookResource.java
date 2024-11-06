package com.lcit.bookstore;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lcit.dao.book.DaoBookImp;
import com.lcit.models.Book;

/**
 * @author Alexander Gutierrez
 */
@Path("books")
public class BookResource {
	
	private StringBuilder exceptionResponse = new StringBuilder("\"error\" : \"");
	private DaoBookImp dao = new DaoBookImp();
	
    @GET
    @Path("/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookByIsbn(@PathParam("isbn") String isbn) {
        try {
        	Book book = dao.selectOne(isbn);
        	if (book == null) {
        		return Response
            			.status(Response.Status.NO_CONTENT)
            			.entity(book)
            			.build();
        	}
        	return Response
        			.status(Response.Status.OK)
        			.entity(book)
        			.build();
        } catch (SQLException e) {
			exceptionResponse
			.append(e.getMessage())
			.append("\"");
        	return Response
        			.status(Response.Status.INTERNAL_SERVER_ERROR)
        			.entity(exceptionResponse.toString())
    				.build();
        }
    }
    
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
    	
    	List<Book> books;
		try {
			books = dao.selectAll();
	        return Response
	        		.status(Response.Status.OK)
	        		.entity(books)
	        		.build();
		} catch (SQLException e) {
			exceptionResponse
			.append(e.getMessage())
			.append("\"");
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(exceptionResponse.toString())
				.build();
		}

    }
    
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(@Valid Book book) {
    	
		try {
			dao.insert(book);
			return Response
    				.status(Response.Status.CREATED)
    				.entity(book)
    				.build();
		} catch (SQLException e) {
			exceptionResponse
				.append(e.getMessage())
				.append("\"");
			return Response
    				.status(Response.Status.INTERNAL_SERVER_ERROR)
    				.entity(exceptionResponse.toString())
    				.build();
		}
    }
    
    @PUT
    @Path("{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBookByIsbn(@PathParam("isbn") String isbn, Book book) {
    	book.setIsbn(isbn);
    	try {
			dao.updateOne(book);
			return Response
					.status(Response.Status.OK)
					.entity(book)
					.build();
		} catch (SQLException e) {
			exceptionResponse
			.append(e.getMessage())
			.append("\"");
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(exceptionResponse.toString())
				.build();
		}
    }
    
    @DELETE
    @Path("{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBookByIsbn(@PathParam("isbn") String isbn) {
    	try {
			dao.deleteOne(isbn);
			return Response
					.status(Response.Status.OK)
					.entity("Book Deleted from Database")
					.build();
		} catch (SQLException e) {
			exceptionResponse
			.append(e.getMessage())
			.append("\"");
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(exceptionResponse.toString())
				.build();
		}
    }
}
