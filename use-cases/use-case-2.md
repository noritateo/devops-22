# Use Case #2: Generate Population Report on Cities

---

## CHARACTERISTIC INFORMATION

### Goal in Context
As a Data Analyst, I want to generate a city report showing the name, country, district, and population so that I can 
view and compare population sizes in cities across the world, continent, region, country, and district.

### Scope
Company

### Level
Primary Task

### Preconditions
Database contains up-to-date world population data.

### Success End Condition
A city population report is successfully generated and available for the Data Analyst to provide to the organisation.

### Failed End Condition
No report is produced.

### Primary Actor
Data Analyst

### Trigger
A request for a World City Population Report is sent to the Data Analyst.

---

## MAIN SUCCESS SCENARIO
1. Organisation requests population information for cities by demographic level (world, continent, region, country, or 
district).
2. Data Analyst captures the demographic level and whether to display all cities or the top N cities.
3. Data Analyst extracts the population data of the given demographic level and sorts it from largest to smallest.
4. Data Analyst reviews and provides the report to the organisation.

---

## EXTENSIONS
2. Organisation requests a “Top N” report but N is larger than maximum data.
* Data Analyst displays the maximum available results.

---

## SUB-VARIATIONS
- **Demographic Level:** World | Continent | Region | Country | District
- **Mode:** All Cities | Top N Cities

---

## SCHEDULE
**Due Date:** Release 1.2.0
