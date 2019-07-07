
# DB
```
cd /d H:\PostgreSQL11\pg11  
create database orderbooksystem;
CREATE USER orderbooksystem WITH PASSWORD 'orderbooksystem';
GRANT ALL PRIVILEGES ON database orderbooksystem TO orderbooksystem;
```

# API

## [financial instrument]

### financialinstrument-add
- path: "/rest/financialInstrument/add"
- param: type, code, name

### financialinstrument-findAll
- path: "/rest/financialInstrument/findAll"

## [order book]

### orderBook-add
- path: "/rest/orderBook/add"
- param: financialInstrumentId

### orderBook-findAll
- path: "/rest/orderBook/findAll"

### orderBook-open
- path: "/rest/orderBook/openOrderBook"
- Param: financialInstrumentId
- require: orderBook is close

### orderBook-close
- path: "/rest/orderBook/closeOrderBook"
- Param: financialInstrumentId
- require: orderBook is open

## [order]

### order-add  
- path: "/rest/orderInfo/add"
- param: financialInstrumentId, quantity, orderType, price
- require: orderBook is open

### order-findAll
- path: "/rest/orderInfo/findAll"

## [execution]

### execution-add 
- path: "/rest/execution/addExecution"
- param: orderBookId, price, (orderInfoId, Quantity)
- require: orderBook is close

### execution-findAll
- path: "/rest/execution/findAll"


# Test Case
## [financialInstrument]
### unit test
- missing fields: 
	type
	code
	name
- validate fields: no duplicate code, name 
- add, findAll

### API test
- financialinstrument-add
- financialinstrument-findAll

## [orderBook]
### unit test
- missing fields: financialInstrumentId
- validate fields: no duplicate orderBook, same status
- add, findAll, update

### API test
- orderBook-add
- orderBook-findAll
- orderBook-open
- orderBook-close

## [order]
### unit test
- missing fields: financialInstrumentId, quantity, orderType, price
- validate fields: valid financialInstrumentId, quantity, orderType, price, orderBook status   
- add, findAll

### API test
- order-add
- order-findAll


## [execution]
### unit test
- missing fields: orderBookId, price, (orderInfoId, Quantity)
- validate fields: valid orderBookId, price, (orderInfoId, Quantity), orderBook status 
- add, findAll

### API test
- execution-add
- execution-findAll