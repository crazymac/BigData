package com.mirantis.aminakov.bigdatacourse.dao.cassandratests;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mirantis.aminakov.bigdatacourse.dao.Book;
import com.mirantis.aminakov.bigdatacourse.dao.DaoException;
import com.mirantis.aminakov.bigdatacourse.dao.cassandra.Constants;
import com.mirantis.aminakov.bigdatacourse.dao.cassandra.DaoApp;

public class PaginateByTokenTest {

	@SuppressWarnings("resource")
	@Test
	public void paginationTest() throws DaoException, FileNotFoundException{
		
		BasicConfigurator.configure();
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/DAOConfig.xml");
		
		Constants cts = (Constants) ctx.getBean("cassandra state");
		
		DaoApp dao = (DaoApp) ctx.getBean("DAOCassandra");
		
		Book beggining_state = new Book();
		
		for(int i = 0; i< 500; ++i){
			beggining_state.newBook("CassandraTest"+i%250, "Test" + i%2  , "Tester"+i%50, new FileInputStream("src/main/resources/testbook"));
			dao.addBook(beggining_state);
		}
		
		List<Book> lst1 = dao.getBooksByToken("Test0","book author");
		
		List<Book> lst2 = dao.getBooksByToken("Test1","book author");
		
		List<Book> lst3 = dao.getBooksByToken("CassandraTest0","book title");
		
		List<Book> lst4 = dao.getBooksByToken("Tester1","book genre");
		
		assertEquals(lst1.size() + lst2.size() + lst3.size() + lst4.size(), 250 + 250 + 2 + 10);
		
		cts.getCurrentClstr().dropKeyspace(cts.KEYSPACE_NAME);
	}

}
