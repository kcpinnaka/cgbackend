# backend-tech-assessment


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
 