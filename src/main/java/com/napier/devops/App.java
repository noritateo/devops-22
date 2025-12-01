package com.napier.devops;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App
{

    private Connection con = null;
    private Display display = new Display();

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
                Thread.sleep(30000);
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

    // World
    public ArrayList<City> top10populatedCities() {
        ArrayList<City> cities = new ArrayList<City>();

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT city.Name AS CityName, " +
                            "       country.Name AS CountryName, " +
                            "       city.District, " +
                            "       country.Region, " +
                            "       country.Continent, " +
                            "       city.Population " +
                            "FROM city " +
                            "JOIN country ON city.CountryCode = country.Code " +
                            "ORDER BY city.Population DESC " +
                            "LIMIT 10;";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                City city = new City(
                        resultSet.getString("CityName"),     // Set city name
                        resultSet.getString("CountryName"),  // Set country name
                        resultSet.getString("District"),     // Set district
                        resultSet.getString("Region"),       // Set region
                        resultSet.getString("Continent"),    // Set continent
                        resultSet.getInt("Population")       // Set population
                );
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    // Continent
    public ArrayList<City> top10populatedCitiesAsia() {
        ArrayList<City> cities = new ArrayList<City>();

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT city.Name AS CityName, " +
                            "       country.Name AS CountryName, " +
                            "       city.District, " +
                            "       country.Region, " +
                            "       country.Continent, " +
                            "       city.Population " +
                            "FROM city " +
                            "JOIN country ON city.CountryCode = country.Code " +
                            "WHERE country.Continent = 'Asia' " +
                            "ORDER BY city.Population DESC " +
                            "LIMIT 10;";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                City city = new City(
                        resultSet.getString("CityName"),
                        resultSet.getString("CountryName"),
                        resultSet.getString("District"),
                        resultSet.getString("Region"),
                        resultSet.getString("Continent"),
                        resultSet.getInt("Population")
                );
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    // Region
    public ArrayList<City> top10populatedCitiesCaribbean() {
        ArrayList<City> cities = new ArrayList<City>();

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT city.Name AS CityName, " +
                            "       country.Name AS CountryName, " +
                            "       city.District, " +
                            "       country.Region, " +
                            "       country.Continent, " +
                            "       city.Population " +
                            "FROM city " +
                            "JOIN country ON city.CountryCode = country.Code " +
                            "WHERE country.Region = 'Caribbean' " +
                            "ORDER BY city.Population DESC " +
                            "LIMIT 10;";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                City city = new City(
                        resultSet.getString("CityName"),
                        resultSet.getString("CountryName"),
                        resultSet.getString("District"),
                        resultSet.getString("Region"),
                        resultSet.getString("Continent"),
                        resultSet.getInt("Population")
                );
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    // Country
    public ArrayList<City> top10populatedCitiesMyanmar() {
        ArrayList<City> cities = new ArrayList<City>();

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT city.Name AS CityName, " +
                            "       country.Name AS CountryName, " +
                            "       city.District, " +
                            "       country.Region, " +
                            "       country.Continent, " +
                            "       city.Population " +
                            "FROM city " +
                            "JOIN country ON city.CountryCode = country.Code " +
                            "WHERE country.Name = 'Myanmar' " +
                            "ORDER BY city.Population DESC " +
                            "LIMIT 10;";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                City city = new City(
                        resultSet.getString("CityName"),
                        resultSet.getString("CountryName"),
                        resultSet.getString("District"),
                        resultSet.getString("Region"),
                        resultSet.getString("Continent"),
                        resultSet.getInt("Population")
                );
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    // District
    public ArrayList<City> top10populatedCitiesQueensland() {
        ArrayList<City> cities = new ArrayList<City>();

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT city.Name AS CityName, " +
                            "       country.Name AS CountryName, " +
                            "       city.District, " +
                            "       country.Region, " +
                            "       country.Continent, " +
                            "       city.Population " +
                            "FROM city " +
                            "JOIN country ON city.CountryCode = country.Code " +
                            "WHERE city.District = 'Queensland' " +
                            "ORDER BY city.Population DESC " +
                            "LIMIT 10;";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                City city = new City(
                        resultSet.getString("CityName"),
                        resultSet.getString("CountryName"),
                        resultSet.getString("District"),
                        resultSet.getString("Region"),
                        resultSet.getString("Continent"),
                        resultSet.getInt("Population")
                );
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    // Capital City World
    public ArrayList<City> capitalCitiesWorld() {
        ArrayList<City> captialCities = new ArrayList<City>();

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                            "co.Region, co.Continent, ci.Population " +
                            "FROM city ci " +
                            "JOIN country co ON ci.ID = co.Capital " +
                            "ORDER BY ci.Population DESC;";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                City capitalCity = new City(
                        resultSet.getString("CityName"),
                        resultSet.getString("CountryName"),
                        resultSet.getString("District"),
                        resultSet.getString("Region"),
                        resultSet.getString("Continent"),
                        resultSet.getInt("Population")
                );
                captialCities.add(capitalCity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return captialCities;
    }

    // Capital City Asia
    public ArrayList<City> capitalCitiesAsia() {
        ArrayList<City> captialCities = new ArrayList<City>();

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                            "       co.Region, co.Continent, ci.Population " +
                            "FROM city ci " +
                            "JOIN country co ON ci.ID = co.Capital " +
                            "WHERE co.Continent = 'Asia' " +
                            "ORDER BY ci.Population DESC;";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                City capitalCity = new City(
                        resultSet.getString("CityName"),
                        resultSet.getString("CountryName"),
                        resultSet.getString("District"),
                        resultSet.getString("Region"),
                        resultSet.getString("Continent"),
                        resultSet.getInt("Population")
                );
                captialCities.add(capitalCity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return captialCities;
    }

    // Capital City Region
    public ArrayList<City> capitalCitiesCaribbean() {
        ArrayList<City> captialCities = new ArrayList<City>();

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                            "       co.Region, co.Continent, ci.Population " +
                            "FROM city ci " +
                            "JOIN country co ON ci.ID = co.Capital " +
                            "WHERE co.Region = 'Caribbean' " +
                            "ORDER BY ci.Population DESC;";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                City capitalCity = new City(
                        resultSet.getString("CityName"),
                        resultSet.getString("CountryName"),
                        resultSet.getString("District"),
                        resultSet.getString("Region"),
                        resultSet.getString("Continent"),
                        resultSet.getInt("Population")
                );
                captialCities.add(capitalCity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return captialCities;
    }

    // Top 10 Capital Cities World
    public ArrayList<City> top10CapitalCitiesWorld() {
        ArrayList<City> captialCities = new ArrayList<City>();

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                            "       co.Region, co.Continent, ci.Population " +
                            "FROM city ci " +
                            "JOIN country co ON ci.ID = co.Capital " +
                            "ORDER BY ci.Population DESC " +
                            "LIMIT 10;";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                City capitalCity = new City(
                        resultSet.getString("CityName"),
                        resultSet.getString("CountryName"),
                        resultSet.getString("District"),
                        resultSet.getString("Region"),
                        resultSet.getString("Continent"),
                        resultSet.getInt("Population")
                );
                captialCities.add(capitalCity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return captialCities;
    }

    // Top 10 Capital City Continent
    public ArrayList<City> top10capitalCitiesAsia() {
        ArrayList<City> captialCities = new ArrayList<City>();

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                            "       co.Region, co.Continent, ci.Population " +
                            "FROM city ci " +
                            "JOIN country co ON ci.ID = co.Capital " +
                            "WHERE co.Region = 'Caribbean' " +
                            "ORDER BY ci.Population DESC " +
                            "LIMIT 10;";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                City capitalCity = new City(
                        resultSet.getString("CityName"),
                        resultSet.getString("CountryName"),
                        resultSet.getString("District"),
                        resultSet.getString("Region"),
                        resultSet.getString("Continent"),
                        resultSet.getInt("Population")
                );
                captialCities.add(capitalCity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return captialCities;
    }

    // Top 10 Capital City Region
    public ArrayList<City> top10CapitalCitiesCaribbean() {
        ArrayList<City> captialCities = new ArrayList<City>();

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                            "       co.Region, co.Continent, ci.Population " +
                            "FROM city ci " +
                            "JOIN country co ON ci.ID = co.Capital " +
                            "WHERE co.Region = 'Caribbean' " +
                            "ORDER BY ci.Population DESC;";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                City capitalCity = new City(
                        resultSet.getString("CityName"),
                        resultSet.getString("CountryName"),
                        resultSet.getString("District"),
                        resultSet.getString("Region"),
                        resultSet.getString("Continent"),
                        resultSet.getInt("Population")
                );
                captialCities.add(capitalCity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return captialCities;
    }

    // World Population
    public ArrayList<PeoplePopulation> worldPopulation() {
        ArrayList<PeoplePopulation> population = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT SUM(Population) AS WorldPopulation FROM country;";

            ResultSet resultSet = stmt.executeQuery(sql);

            if (resultSet.next()) {
                long total = resultSet.getLong("WorldPopulation");
                PeoplePopulation peoplePopulation = new PeoplePopulation("World", total);
                population.add(peoplePopulation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return population;
    }

    // Asian Continent Population
    public ArrayList<PeoplePopulation> asiaPopulation() {
        ArrayList<PeoplePopulation> population = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();

            String sql = "SELECT Continent, SUM(Population) AS TotalPopulation " +
                    "FROM country " +
                    "WHERE Continent = 'Asia' " +
                    "GROUP BY Continent;";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                String continentName = resultSet.getString("Continent");
                long totalPopulation = resultSet.getLong("TotalPopulation");

                PeoplePopulation peoplePopulation = new PeoplePopulation(continentName, totalPopulation);
                population.add(peoplePopulation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return population;
    }

    // Caribbean Region Population
    public ArrayList<PeoplePopulation> regionPopulation() {
        ArrayList<PeoplePopulation> population = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();

            String sql = "SELECT Region, SUM(Population) AS TotalPopulation " +
                    "FROM country " +
                    "WHERE Region = 'Caribbean' " +
                    "GROUP BY Region;";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                String regionName = resultSet.getString("Region");
                long totalPopulation = resultSet.getLong("TotalPopulation");

                PeoplePopulation peoplePopulation = new PeoplePopulation(regionName, totalPopulation);
                population.add(peoplePopulation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return population;
    }

    // Myanmar Country Population
    public ArrayList<PeoplePopulation> countryPopulation() {
        ArrayList<PeoplePopulation> population = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();

            String sql = "SELECT Name AS CountryName, Population AS TotalPopulation " +
                    "FROM country " +
                    "WHERE Name = 'Myanmar';";

            ResultSet resultSet = stmt.executeQuery(sql);

            // Process each record
            while (resultSet.next()) {
                String countryName = resultSet.getString("CountryName");
                long totalPopulation = resultSet.getLong("TotalPopulation");
                PeoplePopulation peoplePopulation = new PeoplePopulation(countryName, totalPopulation);
                population.add(peoplePopulation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return population;
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
        Display display = new Display();
        a.connect();

        // Cities
        display.displayAllCities(a.top10populatedCities());
        System.out.println();
        display.displayAllCities(a.top10populatedCitiesAsia());
        System.out.println();
        display.displayAllCities(a.top10populatedCitiesCaribbean());
        System.out.println();
        display.displayAllCities(a.top10populatedCitiesMyanmar());
        System.out.println();
        display.displayAllCities(a.top10populatedCitiesQueensland());
        System.out.println();

        // Capital Cities
        display.displayAllCities(a.capitalCitiesWorld());
        System.out.println();
        display.displayAllCities(a.capitalCitiesAsia());
        System.out.println();
        display.displayAllCities(a.capitalCitiesCaribbean());
        System.out.println();

        // Top Capital Cities
        display.displayAllCities(a.top10CapitalCitiesWorld());
        System.out.println();
        display.displayAllCities(a.top10capitalCitiesAsia());
        System.out.println();
        display.displayAllCities(a.top10CapitalCitiesCaribbean());
        System.out.println();

        display.displayAllPeoplePopulation(a.worldPopulation());
        System.out.println();
        display.displayAllPeoplePopulation(a.asiaPopulation());
        System.out.println();
        display.displayAllPeoplePopulation(a.regionPopulation());
        System.out.println();
        display.displayAllPeoplePopulation(a.countryPopulation());
        System.out.println();

        List<Country> countries = a.getAllCountries();
        a.displayAllCountries(countries);

        a.disconnect();
    }
}
