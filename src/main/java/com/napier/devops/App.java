package com.napier.devops;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App
{

    private Connection con = null;

    // connect to mysql
    public void connect()
    {
        try
        {
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
                Thread.sleep(5000);
                con = DriverManager.getConnection(
                        "jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root",
                        "example"
                );
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

    // disconnect from mysql
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    // get all countries details
    public List<Country> getAllCountries()
    {
        List<Country> countries = new ArrayList<>();

        try
        {
            Statement stmt = con.createStatement();
            String query =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, " +
                            "country.Population, city.Name AS CapitalCity " +
                            "FROM country " +
                            "LEFT JOIN city ON country.Capital = city.ID " +
                            "ORDER BY country.Population DESC";

            ResultSet rset = stmt.executeQuery(query);

            while (rset.next())
            {
                Country c = new Country();
                c.code = rset.getString("Code");
                c.name = rset.getString("Name");
                c.continent = rset.getString("Continent");
                c.region = rset.getString("Region");
                c.population = rset.getInt("Population");
                c.capitalCity = rset.getString("CapitalCity");
                countries.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed to get country details");
            System.out.println(e.getMessage());
        }

        return countries;
    }

    // display info from getAllCountries
    public void displayAllCountries(List<Country> countries)
    {
        if (countries == null || countries.isEmpty())
        {
            System.out.println("No countries found.");
            return;
        }

        System.out.println(String.format(
                "%-5s %-55s %-20s %-25s %-15s %-25s",
                "Code", "Name", "Continent", "Region", "Population", "Capital City"));

        for (Country c : countries)
        {
            String line = String.format(
                    "%-5s %-55s %-20s %-25s %-15d %-25s",
                    c.code, c.name, c.continent, c.region, c.population, c.capitalCity);
            System.out.println(line);
        }
    }

    public static void main(String[] args)
    {
        App a = new App();
        a.connect();

        List<Country> countries = a.getAllCountries();
        a.displayAllCountries(countries);

        a.disconnect();
    }
}
