package com.cranestylelabs.danhoffman.listviewsample.unit_tests;

import com.cranestylelabs.danhoffman.listviewsample.adapters.DownloadDataAdapter;
import com.cranestylelabs.danhoffman.listviewsample.network.DownloadWebpageTask;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DownloadWebpageTaskTest extends TestCase {

    DownloadWebpageTask sut, sut2;
    DownloadDataAdapter adapter;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        sut = new DownloadWebpageTask();
        adapter = Mockito.mock(DownloadDataAdapter.class);
        sut2 = new DownloadWebpageTask(adapter);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testGetAdapter() throws Exception {
        assertTrue(sut.getAdapter().isEmpty());
        assertFalse(sut2.getAdapter().isEmpty());

        DownloadDataAdapter expected = adapter;
        DownloadDataAdapter actual = sut2.getAdapter();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetAdapter() throws Exception {

    }

    @Test
    public void testDoInBackground() throws Exception {

    }

    @Test
    public void testOnPostExecute() throws Exception {

    }

    @Test
    public void testReadIt() throws Exception {

    }
}