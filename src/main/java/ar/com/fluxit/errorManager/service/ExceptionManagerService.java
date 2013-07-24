package ar.com.fluxit.errorManager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.fluxit.errorManager.model.ExceptionDescriptor;
import ar.com.fluxit.errorManager.model.ExceptionDetail;

import com.google.common.base.Throwables;


@Service
public class ExceptionManagerService {

	@Autowired
	ExceptionDescriptorRepository exceptionDescriptorRepository;

	public List<ExceptionDetail> fillExceptionDetails(Throwable throwable) {
		List<ExceptionDetail> result = new ArrayList<ExceptionDetail>();
		List<Throwable> throwables = Throwables.getCausalChain(throwable);

		for (Throwable throwableCause : throwables) {
			ExceptionDetail exceptionDetail = new ExceptionDetail();

			exceptionDetail.setClassName(throwableCause.getClass().getName());

			ExceptionDescriptor exceptionDescriptor = exceptionDescriptorRepository
					.getExceptionDescriptor(throwableCause.getClass().getName());
			if (exceptionDescriptor == null) {
				exceptionDescriptor = new ExceptionDescriptor();
				exceptionDescriptor
						.setClassName(exceptionDetail.getClassName());
			}

			exceptionDetail.setExceptionDescriptor(exceptionDescriptor);
			exceptionDetail.setMessage(throwableCause.getMessage());

			exceptionDetail.setStackTraceElements(Arrays.asList(throwableCause
					.getStackTrace()));
			result.add(0, exceptionDetail);
		}
		return result;
	}

	@Transactional
	public void updateExceptionDescriptor(String className, String description) {

		ExceptionDescriptor exceptionDescriptor = exceptionDescriptorRepository
				.getExceptionDescriptor(className);
		if (exceptionDescriptor == null) {
			exceptionDescriptor = new ExceptionDescriptor();
			exceptionDescriptor
					.setClassName(className);
		}

		exceptionDescriptor.setDescription(description);

		exceptionDescriptorRepository.update(exceptionDescriptor);

	}

	public int simulateException() {
		return 1 / 0;

	}
	@Transactional
	public void simulateDbException() {
		
		ExceptionDescriptor exceptionDescriptor = new ExceptionDescriptor();
		exceptionDescriptor.setClassName(ArithmeticException.class.getName());
		exceptionDescriptorRepository.update(exceptionDescriptor);

	}

}
