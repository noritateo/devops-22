package com.napier.devops;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {

    private Connection con = null;
    private Display display = new Display();

    // -----------------------------------------------------------------------
    // CONNECT METHODS
    // -----------------------------------------------------------------------

    /**
     * Default connect method for Docker (calls the parameterized one)
     */
    public void connect() {
        connect("db:3306", 30000);
    }

    /**
     * Parameterized connect method for Testing (allows localhost)
     */
    public void connect(String location, int delay) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database using the location parameter
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root",
                        "example"
                );
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from mysql
     */
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    // -----------------------------------------------------------------------
    // REPORT METHODS
    // -----------------------------------------------------------------------

    /**
     * Get all countries details
     */
    public List<Country> getAllCountries() {
        List<Country> countries = new ArrayList<>();

        // Check connection is not null
        if (con == null) {
            System.out.println("No database connection");
            return countries;
        }

        try {
            Statement stmt = con.createStatement();
            String query =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, " +
                            "country.Population, city.Name AS CapitalCity " +
                            "FROM country " +
                            "LEFT JOIN city ON country.Capital = city.ID " +
                            "ORDER BY country.Population DESC";
            ResultSet rset = stmt.executeQuery(query);
            while (rset.next()) {
                Country c = new Country();
                c.code = rset.getString("Code");
                c.name = rset.getString("Name");
                c.continent = rset.getString("Continent");
                c.region = rset.getString("Region");
                c.population = rset.getInt("Population");
                c.capitalCity = rset.getString("CapitalCity");
                countries.add(c);
            }
        } catch (Exception e) {
            System.out.println("Failed to get country details");
            System.out.println(e.getMessage());
        }
        return countries;
    }

    /**
     * Top 10 populated cities in the World
     */
    public ArrayList<City> top10populatedCities() {
        ArrayList<City> cities = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return cities;
        }

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT city.Name AS CityName, " +
                            " country.Name AS CountryName, " +
                            " city.District, " +
                            " country.Region, " +
                            " country.Continent, " +
                            " city.Population " +
                            "FROM city " +
                            "JOIN country ON city.CountryCode = country.Code " +
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
            System.out.println("Failed to get cities");
            System.out.println(e.getMessage());
        }
        return cities;
    }

    /**
     * Top 10 populated cities in Asia (Continent)
     */
    public ArrayList<City> top10populatedCitiesAsia() {
        ArrayList<City> cities = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return cities;
        }

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT city.Name AS CityName, " +
                            " country.Name AS CountryName, " +
                            " city.District, " +
                            " country.Region, " +
                            " country.Continent, " +
                            " city.Population " +
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
            System.out.println("Failed to get cities");
            System.out.println(e.getMessage());
        }
        return cities;
    }

    /**
     * Top 10 populated cities in Caribbean (Region)
     */
    public ArrayList<City> top10populatedCitiesCaribbean() {
        ArrayList<City> cities = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return cities;
        }

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT city.Name AS CityName, " +
                            " country.Name AS CountryName, " +
                            " city.District, " +
                            " country.Region, " +
                            " country.Continent, " +
                            " city.Population " +
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
            System.out.println("Failed to get cities");
            System.out.println(e.getMessage());
        }
        return cities;
    }

    /**
     * Top 10 populated cities in Myanmar (Country)
     */
    public ArrayList<City> top10populatedCitiesMyanmar() {
        ArrayList<City> cities = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return cities;
        }

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT city.Name AS CityName, " +
                            " country.Name AS CountryName, " +
                            " city.District, " +
                            " country.Region, " +
                            " country.Continent, " +
                            " city.Population " +
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
            System.out.println("Failed to get cities");
            System.out.println(e.getMessage());
        }
        return cities;
    }

    /**
     * Top 10 populated cities in Queensland (District)
     */
    public ArrayList<City> top10populatedCitiesQueensland() {
        ArrayList<City> cities = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return cities;
        }

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT city.Name AS CityName, " +
                            " country.Name AS CountryName, " +
                            " city.District, " +
                            " country.Region, " +
                            " country.Continent, " +
                            " city.Population " +
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
            System.out.println("Failed to get cities");
            System.out.println(e.getMessage());
        }
        return cities;
    }

    /**
     * Capital Cities in the World
     */
    public ArrayList<City> capitalCitiesWorld() {
        ArrayList<City> capitalCities = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return capitalCities;
        }

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
                capitalCities.add(capitalCity);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get capital cities");
            System.out.println(e.getMessage());
        }
        return capitalCities;
    }

    /**
     * Capital Cities in Asia
     */
    public ArrayList<City> capitalCitiesAsia() {
        ArrayList<City> capitalCities = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return capitalCities;
        }

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                            " co.Region, co.Continent, ci.Population " +
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
                capitalCities.add(capitalCity);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get capital cities");
            System.out.println(e.getMessage());
        }
        return capitalCities;
    }

    /**
     * Capital Cities in Caribbean Region
     */
    public ArrayList<City> capitalCitiesCaribbean() {
        ArrayList<City> capitalCities = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return capitalCities;
        }

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                            " co.Region, co.Continent, ci.Population " +
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
                capitalCities.add(capitalCity);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get capital cities");
            System.out.println(e.getMessage());
        }
        return capitalCities;
    }

    /**
     * Top 10 Capital Cities in the World
     */
    public ArrayList<City> top10CapitalCitiesWorld() {
        ArrayList<City> capitalCities = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return capitalCities;
        }

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                            " co.Region, co.Continent, ci.Population " +
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
                capitalCities.add(capitalCity);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get capital cities");
            System.out.println(e.getMessage());
        }
        return capitalCities;
    }

    /**
     * Top 10 Capital Cities in Asia Continent
     */
    public ArrayList<City> top10capitalCitiesAsia() {
        ArrayList<City> capitalCities = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return capitalCities;
        }

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                            " co.Region, co.Continent, ci.Population " +
                            "FROM city ci " +
                            "JOIN country co ON ci.ID = co.Capital " +
                            "WHERE co.Continent = 'Asia' " +
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
                capitalCities.add(capitalCity);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get capital cities");
            System.out.println(e.getMessage());
        }
        return capitalCities;
    }

    /**
     * Top 10 Capital Cities in Caribbean Region
     */
    public ArrayList<City> top10CapitalCitiesCaribbean() {
        ArrayList<City> capitalCities = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return capitalCities;
        }

        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                            " co.Region, co.Continent, ci.Population " +
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
                capitalCities.add(capitalCity);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get capital cities");
            System.out.println(e.getMessage());
        }
        return capitalCities;
    }

    /**
     * World Population
     */
    public ArrayList<PeoplePopulation> worldPopulation() {
        ArrayList<PeoplePopulation> population = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return population;
        }

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
            System.out.println("Failed to get world population");
            System.out.println(e.getMessage());
        }
        return population;
    }

    /**
     * Asian Continent Population
     */
    public ArrayList<PeoplePopulation> asiaPopulation() {
        ArrayList<PeoplePopulation> population = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return population;
        }

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
            System.out.println("Failed to get Asia population");
            System.out.println(e.getMessage());
        }
        return population;
    }

    /**
     * Caribbean Region Population
     */
    public ArrayList<PeoplePopulation> regionPopulation() {
        ArrayList<PeoplePopulation> population = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return population;
        }

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
            System.out.println("Failed to get region population");
            System.out.println(e.getMessage());
        }
        return population;
    }

    /**
     * Myanmar Country Population
     */
    public ArrayList<PeoplePopulation> countryPopulation() {
        ArrayList<PeoplePopulation> population = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return population;
        }

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT Name AS CountryName, Population AS TotalPopulation " +
                    "FROM country " +
                    "WHERE Name = 'Myanmar';";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                String countryName = resultSet.getString("CountryName");
                long totalPopulation = resultSet.getLong("TotalPopulation");
                PeoplePopulation peoplePopulation = new PeoplePopulation(countryName, totalPopulation);
                population.add(peoplePopulation);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get country population");
            System.out.println(e.getMessage());
        }
        return population;
    }

    /**
     * Queensland District Population
     */
    public ArrayList<PeoplePopulation> distinctPopulation() {
        ArrayList<PeoplePopulation> population = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return population;
        }

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT ci.District AS District, " +
                    "SUM(ci.Population) AS totalPopulation " +
                    "FROM city ci " +
                    "WHERE ci.District = 'Queensland' " +
                    "GROUP BY ci.CountryCode, ci.District " +
                    "ORDER BY ci.CountryCode, ci.District;";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                String districtName = resultSet.getString("District");
                long totalPopulation = resultSet.getLong("totalPopulation");
                PeoplePopulation peoplePopulation = new PeoplePopulation(districtName, totalPopulation);
                population.add(peoplePopulation);
            }
        } catch (Exception e) {
            System.out.println("Failed to get district population");
            System.out.println(e.getMessage());
        }
        return population;
    }

    /**
     * Yangon City Population
     */
    public ArrayList<PeoplePopulation> cityPopulation() {
        ArrayList<PeoplePopulation> population = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return population;
        }

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT ci.Name AS CityName, " +
                    "SUM(ci.Population) AS totalPopulation " +
                    "FROM city ci " +
                    "WHERE ci.Name = 'Yangon' " +
                    "GROUP BY ci.Name;";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                String cityName = resultSet.getString("CityName");
                long totalPopulation = resultSet.getLong("totalPopulation");
                PeoplePopulation peoplePopulation = new PeoplePopulation(cityName, totalPopulation);
                population.add(peoplePopulation);
            }
        } catch (Exception e) {
            System.out.println("Failed to get city population");
            System.out.println(e.getMessage());
        }
        return population;
    }

    /**
     * Language Report - Get speakers of major world languages
     */
    public ArrayList<CountryLanguage> getWorldLanguageReport() {
        ArrayList<CountryLanguage> languages = new ArrayList<>();

        if (con == null) {
            System.out.println("No database connection");
            return languages;
        }

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT " +
                    "cl.Language AS language, " +
                    "ROUND(SUM(ROUND(c.Population * (cl.Percentage / 100))), 0) AS totalSpeakers, " +
                    "ROUND(SUM(ROUND(c.Population * (cl.Percentage / 100))) / " +
                    "(SELECT SUM(Population) FROM country) * 100, 2) AS worldPercentage " +
                    "FROM countrylanguage cl " +
                    "JOIN country c ON cl.CountryCode = c.Code " +
                    "WHERE cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                    "GROUP BY cl.Language " +
                    "ORDER BY totalSpeakers DESC";
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                CountryLanguage cl = new CountryLanguage(
                        rset.getString("language"),
                        rset.getDouble("totalSpeakers"),
                        rset.getDouble("worldPercentage")
                );
                languages.add(cl);
            }
        } catch (Exception e) {
            System.out.println("Failed to get language report");
            System.out.println(e.getMessage());
        }
        return languages;
    }

    /**
     * Display all countries
     */
    public void displayAllCountries(List<Country> countries) {
        // Check countries is not null
        if (countries == null) {
            System.out.println("No countries");
            return;
        }

        // Check countries is not empty
        if (countries.isEmpty()) {
            System.out.println("No countries found.");
            return;
        }

        // Print header
        System.out.println(String.format(
                "%-5s %-55s %-20s %-25s %-15s %-25s",
                "Code", "Name", "Continent", "Region", "Population", "Capital City"));

        // Loop over all countries in the list
        for (Country c : countries) {
            // Check for null country
            if (c == null) continue;

            String line = String.format(
                    "%-5s %-55s %-20s %-25s %-15d %-25s",
                    c.code, c.name, c.continent, c.region, c.population, c.capitalCity);
            System.out.println(line);
        }
    }

    // -----------------------------------------------------------------------
    // MAIN METHOD
    // -----------------------------------------------------------------------

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        // Use arguments if provided (e.g. from Docker)
        // Otherwise default to local connection (e.g. from IntelliJ)
        if (args.length < 1) {
            a.connect("localhost:33060", 30000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        // Cities
        a.display.displayAllCities(a.top10populatedCities());
        System.out.println();
        a.display.displayAllCities(a.top10populatedCitiesAsia());
        System.out.println();
        a.display.displayAllCities(a.top10populatedCitiesCaribbean());
        System.out.println();
        a.display.displayAllCities(a.top10populatedCitiesMyanmar());
        System.out.println();
        a.display.displayAllCities(a.top10populatedCitiesQueensland());
        System.out.println();

        // Capital Cities
        a.display.displayAllCities(a.capitalCitiesWorld());
        System.out.println();
        a.display.displayAllCities(a.capitalCitiesAsia());
        System.out.println();
        a.display.displayAllCities(a.capitalCitiesCaribbean());
        System.out.println();

        // Top Capital Cities
        a.display.displayAllCities(a.top10CapitalCitiesWorld());
        System.out.println();
        a.display.displayAllCities(a.top10capitalCitiesAsia());
        System.out.println();
        a.display.displayAllCities(a.top10CapitalCitiesCaribbean());
        System.out.println();

        // Population
        a.display.displayAllPeoplePopulation(a.worldPopulation());
        System.out.println();
        a.display.displayAllPeoplePopulation(a.asiaPopulation());
        System.out.println();
        a.display.displayAllPeoplePopulation(a.regionPopulation());
        System.out.println();
        a.display.displayAllPeoplePopulation(a.countryPopulation());
        System.out.println();
        a.display.displayAllPeoplePopulation(a.distinctPopulation());
        System.out.println();
        a.display.displayAllPeoplePopulation(a.cityPopulation());
        System.out.println();

        // Languages
        a.display.displaylanguages(a.getWorldLanguageReport());
        System.out.println();

        // Countries
        List<Country> countries = a.getAllCountries();
        a.displayAllCountries(countries);

        // Disconnect from database
        a.disconnect();
    }
}
