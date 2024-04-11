package WindGirls.MoneyMinder.service;

import WindGirls.MoneyMinder.domain.FinancialTerm;
import WindGirls.MoneyMinder.dto.FinancialTermsDto;
import WindGirls.MoneyMinder.repository.FinancialTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class FinancialTermService {

    private final FinancialTermRepository financialTermRepository;

    @Autowired
    public FinancialTermService(FinancialTermRepository financialTermRepository) {
        this.financialTermRepository = financialTermRepository;
    }
    public FinancialTerm getRandomTerm(){
        List<Long> allIds = financialTermRepository.findAllIds();

        Random random = new Random();
        Long randomId = allIds.get(random.nextInt(allIds.size()));

        return financialTermRepository.findById(String.valueOf(randomId)).orElseGet(() -> null);
    }

    public Optional<FinancialTerm> getTermByTerm(String term) {
        return financialTermRepository.findByTerm(term);
    }

    public FinancialTerm addTerm(FinancialTermsDto termDTO) {
        FinancialTerm newTerm = new FinancialTerm(termDTO.getTerm(), termDTO.getDefinition());
        return financialTermRepository.save(newTerm);
    }

    public void deleteTerm(String term) {
        Optional<FinancialTerm> termOptional = financialTermRepository.findByTerm(term);
        termOptional.ifPresent(financialTermRepository::delete);
    }

    public Optional<FinancialTerm> getTermById(Long id) {
        return financialTermRepository.findById(String.valueOf(id));
    }

    public List<FinancialTerm> getAllTerms() {
        return financialTermRepository.findAll();
    }

    // Add other methods if needed
}
