package com.blueharvest.accountservice;

import com.blueharvest.accountservice.Exceptions.CustomerNotFoundException;
import com.blueharvest.accountservice.Exceptions.ZeroInitialCreditException;
import com.blueharvest.accountservice.model.Account;
import com.blueharvest.accountservice.model.BaseResponse;
import com.blueharvest.accountservice.model.CreateAccount;
import com.blueharvest.accountservice.repository.AccountRepository;
import com.blueharvest.accountservice.repository.CustomerRepository;
import com.blueharvest.accountservice.service.AccountService;
import com.blueharvest.accountservice.util.ResponseCodes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountserviceApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private AccountService accountService;

	private long lastAccountId;

	@BeforeEach
	public void getLastId() {
		Account account = accountRepository.findTopByOrderByIdDesc().orElse(null);
		if(account != null)
			lastAccountId = account.getId();
	}

	@AfterEach
	public void cleanUpTestCreatedAccount() {
		Account account = accountRepository.findTopByOrderByIdDesc().orElse(null);
		if(lastAccountId != account.getId())
			accountRepository.deleteById(account.getId());
	}

	@Test
	void contextLoads() {
	}


	@Test
	@Order(1)
	void createTransaction_withValidParameters_returns201created() throws Exception {

		CreateAccount request = new CreateAccount(1, 300.00);
		mockMvc.perform(post("/api/v1/accounts/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk());

	}

	@Test
	@Order(2)
	void createAccount_withInvalidCustomerId_throwsCustomerNotFoundException() throws Exception {
		CreateAccount request = new CreateAccount(3, 300.00);

		Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
			accountService.createAccount(request);
		});

		String expectedMessage = ResponseCodes.CUSTOMER_NOT_FOUND.getMessage();
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	@Order(3)
	void createAccount_withZeroInitialCredit_throwsZeroInitialCreditException() throws Exception {
		CreateAccount request = new CreateAccount(1, 0.00);

		Exception exception = assertThrows(ZeroInitialCreditException.class, () -> {
			accountService.createAccount(request);
		});

		String expectedMessage = ResponseCodes.ZERO_INITIAL_CREDIT.getMessage();
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	@Order(4)
	void createAccount_withValidParameters_returnsSuccessResponse() throws Exception {
		CreateAccount request = new CreateAccount(1, 2000.00);
		BaseResponse res = accountService.createAccount(request);

		//For this to pass the transaction service needs to be up and running
		assertNotNull(res);
	}


}
