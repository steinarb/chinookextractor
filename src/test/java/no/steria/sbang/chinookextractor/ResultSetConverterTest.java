package no.steria.sbang.chinookextractor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.mockrunner.base.BaseTestCase;
import com.mockrunner.mock.jdbc.MockResultSet;

public class ResultSetConverterTest extends BaseTestCase {

	@Test
	public void testConvert() throws SQLException, JSONException {
		MockResultSet mockResultSet = new MockResultSet("resultsetId");
		mockResultSet.addColumn("Name");
		mockResultSet.addColumn("Address");
		mockResultSet.addColumn("City");
		mockResultSet.addColumn("Country");
		List<String> row1 = new ArrayList<String>();
		row1.add("John Doe");
		row1.add("Unknown street 101");
		row1.add("Los Angeles");
		row1.add("USA");
		mockResultSet.addRow(row1);
		List<String> row2 = new ArrayList<String>();
		row2.add("Arne Andsen");
		row2.add("Inkognitogata 10");
		row2.add("Andebu");
		row2.add("Norge");
		mockResultSet.addRow(row2);
		JSONArray json = ResultSetConverter.convert(mockResultSet);
		assertEquals(2, json.length());
		JSONObject firstElement = (JSONObject)(json.get(0)); 
		assertEquals(4, firstElement.length());
		JSONObject secondElement = (JSONObject)(json.get(1)); 
		assertEquals(4, secondElement.length());
	}

}
