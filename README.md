# backend-tech-assessment

```
Following assumptions were made:
	1) Autobid will kick-in only after the project deadline is reached.
	2) Project end date must be specified with timezone
	3) please refer to below request formats for more details .
content-type shall be application/json for POST req's .

http://localhost:8080/create-bidder
{   "name": "bidder1" } 

http://localhost:8080/create-employer
{   "name": "employer1" }

http://localhost:8080/create-project
{"name":"Proj1","employerId":1,"endDate":"11-06-2018 17:34:09 PDT"}
Note: endDate shall be in dd-mm-yyyy hh:mm:ss TimeZone

http://localhost:8080/create-bid
{   "projectId": 1,   "amount": 10,   "bidderId": 1,   "autoBid": 9  }

http://localhost:8080/get-project/1

	
```

```
	git clone https://github.com/kcpinnaka/cgbackend
	cd cgbackend
	./mvnw spring-boot:run
```

'''
You can then access API here: http://localhost:8080/
below API are available:
 "/projects"; 
 "/employers";
 "/bidders";
 "/bids";
 "/create-bid";
 "/create-project"   :
 	 	Note: endDate shall include timezone , ex: 04-06-2018 22:14:09 +0000 	 
 "/get-project/{projectId}";
 "/create-employer";
 "/create-bidder";
 '''



## Database configuration
used in-memory database (HSQLDB) which


The overhead of computing the minimum Bid and Autobid are done efficiently using muli-level index-ing .
Indexing also help not allowing duplicate values for columns Normal Bid,AutoBid.
 