# FAA Airmen database REST API
Converts FAA's public airmen certification csv files to a postgres database with a REST api wrapper.

### Setup
1. git clone ""
2. install postgresql database
3. Create postgres database named "app"
4. run gradlew build
5. execute

### Environmental Variables
 - POSTGRES_URL -  Connect string to postgres database.
 - POSTGRES_USERNAME - Postgres username
 - POSTGRES_PASSWORD - Postgres password
 - CSV_DESTINATION - Destination directory to download CSV files to.

### TODO
 - Automatically grab zip file every x days
 - Tests
 - Clean data
   - Split first/middle names
   - Region name from abbreviation
   - Cert rating name to description
   - Remove redundant data from relations
 - Swagger doc