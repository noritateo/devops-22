# Use Case #4: Generate Population Report

---

## CHARACTERISTIC INFORMATION

### Goal in Context
As a Data Analyst, I want to generate population reports showing the total population across the world, continent, 
region, country, district, and city, including the number of people living in cities and not living in cities, so that 
I can compare population sizes and percentages across different demographic levels.

### Scope
Company

### Level
Primary Task

### Preconditions
Database contains up-to-date world population data.

### Success End Condition
A population report is successfully generated and available for the Data Analyst to provide to the organisation.

### Failed End Condition
No report is produced.

### Primary Actor
Data Analyst

### Trigger
A request for a Population Report is sent to the Data Analyst.

---

## MAIN SUCCESS SCENARIO
1. Organisation requests population information by demographic level (world, continent, region, country, district, or 
city).
2. Data Analyst captures the demographic level and retrieves the total population data.
3. System calculates the number of people living in cities, number not living in cities, and their respective 
percentages for each level.
4. Data Analyst reviews and provides the report to the organisation.

---

## EXTENSIONS
NA

---

## SUB-VARIATIONS
- **Demographic Level:** World | Continent | Region | Country | District | City
- **Data Shown:** Total Population | Living in Cities | Not Living in Cities | Percentage

---

## SCHEDULE
**Due Date:** Release 1.4.0
