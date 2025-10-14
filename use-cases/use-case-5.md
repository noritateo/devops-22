# Use Case #5: Generate Population Report on Languages

---

## CHARACTERISTIC INFORMATION

### Goal in Context
As a Data Analyst, I want to generate a report showing how many people speak Chinese, English, Hindi, Spanish, and 
Arabic so that I can display the total population for each language and their percentage of the world population.

### Scope
Company

### Level
Primary Task

### Preconditions
Database contains up-to-date world population data.

### Success End Condition
A language population report is successfully generated and available for the Data Analyst to provide to the organisation.

### Failed End Condition
No report is produced.

### Primary Actor
Data Analyst

### Trigger
A request for a Language Population Report is sent to the Data Analyst.

---

## MAIN SUCCESS SCENARIO
1. Organisation requests a language population report for major world languages (Chinese, English, Hindi, Spanish, 
and Arabic).
2. Data Analyst retrieves the total number of speakers for each language from the database.
3. System calculates the percentage of speakers relative to the world population.
4. Data Analyst reviews and provides the language population report to the organisation.

---

## EXTENSIONS
NA

---

## SUB-VARIATIONS
- **Languages Reported:** Chinese | English | Hindi | Spanish | Arabic
- **Data Shown:** Total Speakers | Percentage of World Population

---

## SCHEDULE
**Due Date:** Release 1.5.0