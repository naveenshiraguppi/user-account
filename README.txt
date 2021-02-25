http://localhost:8030/api/customers/10001
http://localhost:8030/api/customers/10001/accounts
http://localhost:8030/api/customers/10001/accounts/1/transactions

	1	Clear demarcation between controller and service layer - DONE
	2	Implement logging – DONE using @Slf4j - could be done using logging advices.
	3	Implement exception handling – DONE using @ResponseStatus(code = HttpStatus.NOT_FOUND) - could be generalize using ControllerAdvice beans.
	4	Implement transaction when it is required – all service level methods are @Transactional
	5	Implement caching when it is appropriate – Not implemented for this simple application – could be implemented using Spring caching for AccountType entity.
	6	Write clear positive and negative test cases for controller and service layer – Both controller and service layer exceptions are exercised using Integration tests for this simple app.
	7	Implement swagger for browsing api. Make sure it is working -
	8	Implement mvn or gradle for build – Used Maven
	9	Use h2 or any in-memory database – used h2 inmemory database.
	10	Implement pagination if you are expecting to return more records – Nice to have – Not included.
