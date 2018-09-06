/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import General.Connector;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Job {

    private String id;
    private String title;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;

    public Job(String id, String name, BigDecimal minSalary, BigDecimal maxSalary) {
        this.id = id;
        this.title = name;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }
    
    public Job() {
        this.id = "";
    }

    public boolean insertIntoDatabase() {
        String query = String.format("INSERT INTO jobs VALUES (\"%s\", \"%s\", %.2f, %.2f);",
                this.id,
                this.title,
                this.minSalary.doubleValue(),
                this.maxSalary.doubleValue());

        return Connector.updateDatabase(query);
    }
    
    public boolean deleteFromDatabase() {
        String query = String.format("DELETE FROM jobs WHERE JOB_ID = \"%s\"", this.id);
        return Connector.updateDatabase(query);
    }
    
    public void selfUpdate(String name, BigDecimal minSalary, BigDecimal maxSalary) {
        this.title = name;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }
    
    public boolean updateDatabase() {
        String query = String.format("UPDATE jobs "
                + "SET JOB_TITLE = \"%s\", "
                + "MIN_SALARY = %.2f, "
                + "MAX_SALARY = %.2f "
                + "WHERE JOB_ID = \"%s\"",
                this.title,
                this.minSalary.doubleValue(),
                this.maxSalary.doubleValue(),
                this.id);
        
        return Connector.updateDatabase(query);
    }
    
    public static ArrayList selectAllFromDatabase() {
        ArrayList<Job> result = new ArrayList<>();
        ResultSet resultSet;

        String query = String.format("SELECT * FROM jobs;");

        try {

            Connection connection = Connector.createConnection();
            Statement statement;
            statement = connection.createStatement();

            resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                String _id = resultSet.getString("JOB_ID");
                String _title = resultSet.getString("JOB_TITLE");
                String strMinSalary = resultSet.getString("MIN_SALARY");
                String strMaxSalary = resultSet.getString("MAX_SALARY");

                BigDecimal _minSalary = new BigDecimal(strMinSalary);
                BigDecimal _maxSalary = new BigDecimal(strMaxSalary);

                Job job = new Job(_id, _title, _minSalary, _maxSalary);

                result.add(job);
            }
            
            statement.close();
            connection.close();
            resultSet.close();
            
        } catch (SQLException e) {
            System.err.println("Job > SelectAllFromDatabase > SQLException");
            System.err.println(e);
        } catch (Exception e) {
            System.err.println("Job > SelectAllFromDatabase > Exception");
            System.err.println(e);
        }
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public BigDecimal getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }
    
    @Override
    public String toString() {
        return "Job{" + "id=" + id + ", title=" + title + ", minSalary=" + minSalary + ", maxSalary=" + maxSalary + '}';
    }

}
