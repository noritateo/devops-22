package com.napier.devops;

import java.sql.*;

public class App
{

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public Country getCountry(String code)
    {
        try
        {
            Statement stmt = con.createStatement();
            String query =
                    "SELECT Code, Name, Continent, Region, Population, Capital " +
                            "FROM country WHERE Code = '" + code + "'";
            ResultSet rset = stmt.executeQuery(query);

            if (rset.next())
            {
                Country c = new Country();
                c.code = rset.getString("Code");
                c.name = rset.getString("Name");
                c.continent = rset.getString("Continent");
                c.region = rset.getString("Region");
                c.population = rset.getInt("Population");
                c.capital = rset.getInt("Capital");
                return c;
            }
            else
            {
                System.out.println("No country found for code: " + code);
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed to get country details");
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Displays a country’s information.
     */
    public void displayCountry(Country c)
    {
        if (c != null)
        {
            System.out.println(
                    "Code: " + c.code + "\n" +
                            "Name: " + c.name + "\n" +
                            "Continent: " + c.continent + "\n" +
                            "Region: " + c.region + "\n" +
                            "Population: " + c.population + "\n" +
                            "Capital (ID): " + c.capital + "\n"
            );
        }
    }

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Get Country
        Country c = a.getCountry("SGP");

        // Display results
        a.displayCountry(c);

        // Disconnect from database
        a.disconnect();
    }
}