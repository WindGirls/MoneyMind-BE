package WindGirls.MoneyMinder.controller;

import WindGirls.MoneyMinder.domain.FinancialTerm;
import WindGirls.MoneyMinder.dto.FinancialTermsDto;
import WindGirls.MoneyMinder.service.FinancialTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/terms")
public class FinancialTermController {

    private final FinancialTermService financialTermService;
    private Long previousRandomId;

    @Autowired
    public FinancialTermController(FinancialTermService financialTermService) {
        this.financialTermService = financialTermService;
    }

    @GetMapping
    public List<FinancialTerm> getAllTerms() {
        return financialTermService.getAllTerms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialTerm> getTermById(@PathVariable Long id) {
        Optional<FinancialTerm> termOptional = financialTermService.getTermById(id);
        return termOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<FinancialTerm> addTerm(@RequestBody FinancialTermsDto termDTO) {
        FinancialTerm newTerm = financialTermService.addTerm(termDTO);
        return new ResponseEntity<>(newTerm, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTerm(@PathVariable Long id) {
        financialTermService.deleteTerm(String.valueOf(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/random")
    public ResponseEntity<Long> getRandomTermId() {
        List<FinancialTerm> terms = financialTermService.getAllTerms();
        if (terms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Random random = new Random();
        Long randomId;

        do {
            randomId = terms.get(random.nextInt(terms.size())).getId();
        } while (randomId.equals(previousRandomId)); // 이전에 선택된 ID와 같은 경우 다시 선택

        previousRandomId = randomId; // 선택된 ID를 이전에 선택된 ID로 업데이트

        return ResponseEntity.ok(randomId);
    }

    // 수정 등 다른 API 엔드포인트가 필요하다면 여기에 추가
}
