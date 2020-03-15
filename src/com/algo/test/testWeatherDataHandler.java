package com.algo.test;

import com.algo.WeatherDataHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Test class for WeatherDataHandler
 * @author Daniel
 * @since 2020-03-15
 */
public class testWeatherDataHandler {
    private static WeatherDataHandler handler = new WeatherDataHandler();
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    List<String> strings;

    /**
     * Code executed before all test methods
     * Load data
      */
    @BeforeClass
    public static void setUpClass() {
        String fileName = "smhi-opendata.csv";
        try {
            handler.loadData(fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * code executed before each test method
     * Resets Strings
     */
    @Before
    public void setUp() {
        strings = null;
    }

    //----Test Average Temp


    /**
     * Test average temp for 3 objects in the lsit
     */
    @Test
    public void testAverageTempIntheList() {
        String searchedValue1 = "0,42";
        String searchedValue2 = "2,26";
        String searchedValue3 = "2,78";
        try {

            LocalDate dateFrom = LocalDate.parse("2000-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("2000-01-03", dateFormat);
            strings = handler.avarageTemperatures(dateFrom, dateTo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertTrue(strings.get(0).contains(searchedValue1));
        Assert.assertTrue(strings.get(1).contains(searchedValue2));
        Assert.assertTrue(strings.get(2).contains(searchedValue3));
    }

    /**
     * Test average temp in first day in the list
     */
    @Test
    public void testAverageTempInBeginOfList() {
        String searchedValue1 = "1,57";

        try {
            LocalDate dateFrom = LocalDate.parse("1946-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("1946-01-01", dateFormat);
            strings = handler.avarageTemperatures(dateFrom, dateTo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(strings.get(0).contains(searchedValue1));
    }

    /**
     * Test average tempo on the last day of the list
     */
    @Test
    public void testAverageTempInEndOfList() {
        String searchedValue1 = "5,03";

        try {
            LocalDate dateFrom = LocalDate.parse("2019-12-31", dateFormat);
            LocalDate dateTo = LocalDate.parse("2019-12-31", dateFormat);
            strings = handler.avarageTemperatures(dateFrom, dateTo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(strings.get(0).contains(searchedValue1));
    }

    /**
     * Test AverageTemp with bad dates, from after to.
     */
    @Test
    public void testAverageTempBadDatesFromAfterTo() {
        try {
            LocalDate dateFrom = LocalDate.parse("2000-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("1946-01-01", dateFormat);
            strings = handler.avarageTemperatures(dateFrom, dateTo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(0, strings.size());
    }



    /**
     * Test AverageTemp with bad dates, from is outside range
     */
    @Test
    public void testAverageTempBadDatesToOutsideRange() {
        boolean foundException = false;

        try {
            LocalDate dateFrom = LocalDate.parse("1900-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("2000-01-01", dateFormat);
            strings = handler.avarageTemperatures(dateFrom, dateTo);

        } catch (Exception e) {
            foundException = true;
        }
        Assert.assertTrue(foundException);
    }

    /**
     * Test AverageTemp with bad dates, to is outside range
     */
    @Test
    public void testAverageTempBadDatesFromOutsideRange() {
        boolean foundException = false;

        try {
            LocalDate dateFrom = LocalDate.parse("2000-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("2100-01-01", dateFormat);
            strings = handler.avarageTemperatures(dateFrom, dateTo);

        } catch (Exception e) {
           foundException = true;
        }
        Assert.assertTrue(foundException);
    }



    //-----TEST MISSING VALUES

    /**
     * Testing MissingValues with 3 objects in the list.
     */
    @Test
    public void testMissingValuesIntheList() {
        String searchedValue1 = "missing: 0";
        String searchedValue2 = "missing: 1";
        String searchedValue3 = "missing: 1";

        try {
            LocalDate dateFrom = LocalDate.parse("2000-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("2000-01-03", dateFormat);
            strings = handler.missingValues(dateFrom, dateTo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertTrue(strings.get(0).contains(searchedValue1));
        Assert.assertTrue(strings.get(1).contains(searchedValue2));
        Assert.assertTrue(strings.get(2).contains(searchedValue3));
    }

    /**
     * Test MissingValues with first date
     */
    @Test
    public void testMissingValuesInBeginOfList() {
        String searchedValue1 = "missing: 21";

        try {
            LocalDate dateFrom = LocalDate.parse("1946-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("1946-01-01", dateFormat);
            strings = handler.missingValues(dateFrom, dateTo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(strings.get(0).contains(searchedValue1));
    }


    /**
     * Test MissingValues with last day
     */
    @Test
    public void testMissingValuesInEndOfList() {
        String searchedValue1 = "missing: 0";

        try {
            LocalDate dateFrom = LocalDate.parse("2019-12-31", dateFormat);
            LocalDate dateTo = LocalDate.parse("2019-12-31", dateFormat);
            strings = handler.missingValues(dateFrom, dateTo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(strings.get(0).contains(searchedValue1));
    }

    /**
     * Test MissingValues with bad dates, from after to
     */
    @Test
    public void testMissingValuesBadDatesFromAfterTo() {
        try {
            LocalDate dateFrom = LocalDate.parse("2000-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("1946-01-01", dateFormat);
            strings = handler.missingValues(dateFrom, dateTo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(0, strings.size());
    }

    /**
     * Test MissinvValues with to date is out of range.
     */
    @Test
    public void testMissingValuesBadDatesToOutsideRange() {
        boolean foundException = false;

        try {
            LocalDate dateFrom = LocalDate.parse("1900-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("2000-01-01", dateFormat);
            strings = handler.missingValues(dateFrom, dateTo);

        } catch (Exception e) {
            foundException = true;
        }
        Assert.assertTrue(foundException);
    }

    /**
     * Test MissingValues with from date is out of range
     */
    @Test
    public void testMissingValuesBadDatesFromOutsideRange() {
        boolean foundException = false;

        try {
            LocalDate dateFrom = LocalDate.parse("2000-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("2100-01-01", dateFormat);
            strings = handler.missingValues(dateFrom, dateTo);

        } catch (Exception e) {
            foundException = true;
        }
        Assert.assertTrue(foundException);
    }

    //----Test Approved values
    /**
     * Test ApprovedValues in the list
     */
    @Test
    public void testApprovedValuesIntheList() {
        String searchedValue1 = "32,86%";
        try {
            LocalDate dateFrom = LocalDate.parse("2000-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("2000-01-03", dateFormat);
            strings = handler.approvedValues(dateFrom, dateTo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(strings.get(0).contains(searchedValue1));
    }

    /**
     * Test ApprovedValues on the first date
     */
    @Test
    public void testApprovedValuesInBeginOfList() {
        String searchedValue1 = "100,00%";
        try {
            LocalDate dateFrom = LocalDate.parse("1946-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("1946-01-01", dateFormat);
            strings = handler.approvedValues(dateFrom, dateTo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(strings.get(0).contains(searchedValue1));
    }

    /**
     * Test ApprovedValues on the last date
     */
    @Test
    public void testApprovedValuesInEndOfList() {
        String searchedValue1 = "100,00%";
        try {
            LocalDate dateFrom = LocalDate.parse("2019-12-31", dateFormat);
            LocalDate dateTo = LocalDate.parse("2019-12-31", dateFormat);
            strings = handler.approvedValues(dateFrom, dateTo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(strings.get(0).contains(searchedValue1));
    }

    /**
     * Test ApprovedValues with bad dates, from after to
     */
    @Test
    public void testApprovedValuesBadDatesFromAfterTo() {
        String searchedValue1 = "NaN";
        try {
            LocalDate dateFrom = LocalDate.parse("2000-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("1946-01-01", dateFormat);
            strings = handler.approvedValues(dateFrom, dateTo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(strings.get(0).contains(searchedValue1));
    }

    /**
     * Test ApprovedValues with to date is out of range
     */
    @Test
    public void testApprovedValuesBadDatesToOutsideRange() {
        boolean foundException = false;
        try {
            LocalDate dateFrom = LocalDate.parse("1900-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("2000-01-01", dateFormat);
            strings = handler.approvedValues(dateFrom, dateTo);
        } catch (Exception e) {
            foundException = true;
        }

        Assert.assertTrue(foundException);
    }

    /**
     * Test ApprovedValues with from date is out of range
     */
    @Test
    public void testApprovedValuesBadDatesFromOutsideRange() {
        boolean foundException = false;
        try {
            LocalDate dateFrom = LocalDate.parse("2000-01-01", dateFormat);
            LocalDate dateTo = LocalDate.parse("2100-01-01", dateFormat);
            strings = handler.approvedValues(dateFrom, dateTo);

        } catch (Exception e) {
            foundException = true;
        }
        Assert.assertTrue(foundException);
    }
}
