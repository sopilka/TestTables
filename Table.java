import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private WebElement tableElement;
    private WebDriver driver;

    public Table(WebElement tableElement, WebDriver driver) {
        this.tableElement = tableElement;
        this.driver = driver;
    }

    public List<WebElement> getRows() {
        List<WebElement> rows = tableElement.findElements(By.xpath(".//tr"));
        rows.remove(0);
        return rows;
    }

    public List<WebElement> getHeadings() {
        WebElement headingsRow = tableElement.findElement(By.xpath(".//tr[1]"));
        List<WebElement> headingsColumns = headingsRow.findElements(By.xpath(".//th"));
        return headingsColumns;
    }

    public List<List<WebElement>> getRowsWithColumns() {
        List<WebElement> rows = getRows();
        List<List<WebElement>> rowsWithColumns = new ArrayList<List<WebElement>>();
        for (WebElement row : rows) {
            List<WebElement> rowWithColumns = row.findElements(By.xpath(".//td"));
            rowsWithColumns.add(rowWithColumns);
        }
        return rowsWithColumns;
    }

    public String getValueFromCell(int rowNumber, int columnNumber) {
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        WebElement cell = rowsWithColumns.get(rowNumber - 1).get(columnNumber - 1);
        return cell.getText();
    }

    public List<Map<String, WebElement>> getRowsWithColumnsByHeading() {
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        List<Map<String, WebElement>> rowsWithColumnsByHeading = new ArrayList<Map<String, WebElement>>();
        Map<String, WebElement> rowByHeading;
        List<WebElement> headingColumns = getHeadings();
        for (List<WebElement> row : rowsWithColumns) {
            rowByHeading = new HashMap<String, WebElement>();
            for (int i = 0; i < headingColumns.size(); i++) {
                String heading = rowByHeading.get(i).getText();
                WebElement cell = row.get(i);
                rowByHeading.put(heading, cell);
            }
        }
        return rowsWithColumnsByHeading;
    }

    public String getValueFromCell (int rowNumber, String columnName) {
        List<Map<String, WebElement>> rowsWithColumnsByHeading = getRowsWithColumnsByHeading();
        WebElement cell = rowsWithColumnsByHeading.get(rowNumber - 1).get(columnName);
        return cell.getText();
    }
}