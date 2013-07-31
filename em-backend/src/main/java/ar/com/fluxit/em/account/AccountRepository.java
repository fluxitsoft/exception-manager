package ar.com.fluxit.em.account;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class AccountRepository {

	@Autowired
	MongoOperations mongoOperations;

	@Inject
	private PasswordEncoder passwordEncoder;

	@Transactional
	public Account save(Account account) throws UserAlreadyExistsException {
		
		Account existingAccount = findByEmail(account.getEmail()); 
		if(existingAccount == null){
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			mongoOperations.insert(account, "accounts");
			
		}else{
			throw new UserAlreadyExistsException();
		}
		return account;
	}

	public Account findByEmail(String email) {
		try {

			Query query = new Query()
					.addCriteria(Criteria.where("email").is(email));
			return mongoOperations.findOne(query, Account.class, "accounts");
		} catch (Exception e) {
			return null;
		}
	}

}
