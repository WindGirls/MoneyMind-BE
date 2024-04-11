package WindGirls.MoneyMinder.dto;

import WindGirls.MoneyMinder.domain.FinancialTerm;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FinancialTermsDto {

    private final String term;
    private final String definition;

    @Builder
    public FinancialTermsDto(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    // 엔티티를 DTO로 변환하는 메서드
    public static FinancialTermsDto fromEntity(FinancialTerm term) {
        return FinancialTermsDto.builder()
                .term(term.getTerm())
                .definition(term.getDefinition())
                .build();
    }
}
