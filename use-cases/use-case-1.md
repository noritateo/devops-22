# Use Case #1: Generate population report on countries

---

## CHARACTERISTIC INFORMATION

### Goal in Context
As a Data Analyst, I want to generate a country report showing the code, name, continent, region, population, and 
capital so that I can view and compare population sizes in countries across the world, continent, and region.

### Scope
Company

### Level
Primary Task

### Preconditions
Database contains up-to-date world population data.

### Success End Condition
A country population report is successfully generated and available for the Data Analyst to provide to the organisation.

### Failed End Condition
No report is produced.

### Primary Actor
Data Analyst

### Trigger
A request for a World Population Report is sent to the Data Analyst.

---

## MAIN SUCCESS SCENARIO
1. Organisation requests population information for countries by demographic level (world, continent, or region).
2. Data Analyst captures the demographic level and whether to display all countries or the top N countries.
3. Data Analyst extracts the population data of the given demographic level and sorts it from largest to smallest.
4. Data Analyst reviews and provides the report to the organisation.

---

## EXTENSIONS
2. Organisation requests a “Top N” report but does N is larger than maximum data.
* Data Analyst displays the maximum available results.

---

## SUB-VARIATIONS
- **Demographic Level:** World | Continent | Region
- **Mode:** All Countries | Top N Countries

---

## SCHEDULE
**Due Date:** Release 1.1.0