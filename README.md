## Text statistic application.(Server part)

### 1. Implement console application
  -  Read txt file and split it by lines
  -  Calculate statistic for each line: longest word(symbols between 2 spaces), shortest word, line length, average word length. Unit test are mandatory
  -  Aggregate these values for all lines from file(unit test)
  -  Store line and file statistic into DB(with JDBC).  
  
  
### 2. Implement Web application
  - Create Hibernate mapping  for tables
  - Return from server side list of handled files and statistic per file. You can use RESTful services or Spring MVC controllers
  - Implement frontend part - display list of files, and statistic per line for selected file(JavaScript, jQuery, ReactJS etc)

### 3. Add to console app possibility to handle all files in directory and sub-directories (_optional_)

### 4. Add to Web app filter by file's statistic (_example_: *files with more than 10 lines*)(_optional_)

### 5. Add to Web app possibility to enter and send own text for handling (_optional_)

### 6. Implement concurrent handling of each file in directory (_optional_)

## How To Use:
   ##   Console module:
	  - Start Main.class in console and add like argument directory with *.txt files
   ##   Web module:
	  - Start WebTextStatisticApplication.run() in root src directory to start application.

## UI-part available on [web-statistic-ui](https://github.com/AndriiFedosov/web-text-statistic-ui)