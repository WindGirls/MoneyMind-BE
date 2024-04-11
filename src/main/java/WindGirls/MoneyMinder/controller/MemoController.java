package WindGirls.MoneyMinder.controller;

import WindGirls.MoneyMinder.domain.Memo;
import WindGirls.MoneyMinder.dto.MemoRequest;
import WindGirls.MoneyMinder.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memos")
public class MemoController {
    private final MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/users/{userId}/test")
    public ResponseEntity<List<Memo>> getAllMemos() {
        List<Memo> memos = memoService.getAllMemos();
        return new ResponseEntity<>(memos, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveMemo(@RequestBody MemoRequest memoRequest) throws Exception {
        return new ResponseEntity<>(memoService.saveMemo(memoRequest), HttpStatus.OK);
    }


    @DeleteMapping("/users/{userId}/{memoId}/delete")
    public ResponseEntity<?> deleteMemo(@PathVariable Long userId, @PathVariable Long memoId) {
        memoService.deleteMemo(memoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
