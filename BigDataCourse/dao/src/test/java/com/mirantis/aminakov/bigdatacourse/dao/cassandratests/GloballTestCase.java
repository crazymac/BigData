package com.mirantis.aminakov.bigdatacourse.dao.cassandratests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddBookTest.class, BookConvTest.class, DeleteBookTest.class,
	GetAllRowKeysTest.class, GetBookByTests.class, GetPageCountTest.class, 
	GetPageTest.class, GetRangedSliceTest.class, PaginateByTokenTest.class })
public class GloballTestCase {

}
