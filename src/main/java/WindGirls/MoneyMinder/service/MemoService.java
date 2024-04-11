package WindGirls.MoneyMinder.service;

import WindGirls.MoneyMinder.domain.Memo;
import WindGirls.MoneyMinder.domain.User;
import WindGirls.MoneyMinder.dto.MemoRequest;
import WindGirls.MoneyMinder.repository.MemoRepository;
import WindGirls.MoneyMinder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    public List<Memo> getAllMemos() {
        return memoRepository.findAll();
    }

    public boolean saveMemo(MemoRequest memoRequest) throws Exception{

        try{
            Long userId = memoRequest.getUserId();
            String content = memoRequest.getContent();

            User user = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException("User not found with ID: "+ userId));

            Memo memo = new Memo();

            memo.setUser(user);

            memo.setContent(content);

            memoRepository.save(memo);

        }catch (Exception e ){
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }


    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }

}
